import java.util.*;
 
public class ShortestPath
{
    private int distances[];
    private ArrayList<Integer> visited;
    private Set<Integer> unvisited;
    private int number_of_nodes;
    private static int adjacencyMatrix[][];
    private static int [] [] array;
    int size;
    private static ArrayList<Integer> list;
    private boolean[] color;
    private static char [] node_array;
 
    public ShortestPath(int number_of_nodes) {
        size = 0;
        list = new ArrayList<Integer>();
        color = new boolean[number_of_nodes + 1];
        this.number_of_nodes = number_of_nodes;
        node_array = new char[number_of_nodes+1];
        distances = new int[number_of_nodes + 1];
        visited = new ArrayList<Integer>();
        unvisited = new HashSet<Integer>();
        adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
        array = new int [number_of_nodes+1][number_of_nodes+1];
    }
 
    public void dijkstra_algorithm(int source) {
        int minNode;
 
        for (int i = 1; i <= number_of_nodes; i++) {
            distances[i] = 99999;
        }
 
        unvisited.add(source);
        distances[source] = 0;
        while (!unvisited.isEmpty()) {
            minNode = extractMin();
            unvisited.remove(minNode);
            visited.add(minNode);
            pathSearch(minNode);
        }
    }
 
    private int extractMin() {
        int min;
        int node = 0;
 
        node = unvisited.iterator().next();
        min = distances[node];
        for (int i = 1; i <= distances.length; i++) {
            if (unvisited.contains(i)) {
                if (distances[i] <= min) {
                    min = distances[i];
                    node = i;
                }
            }
        }
        return node;
    }
 
    private void pathSearch(int minNode) {
        int edgeDistance = -1;
        int newDistance = -1;
        for (int destinationNode = 1; destinationNode <= number_of_nodes; destinationNode++) {
            if (!visited.contains(destinationNode)) {
                if (adjacencyMatrix[minNode][destinationNode] != 99999) {
                    edgeDistance = adjacencyMatrix[minNode][destinationNode];
                    newDistance = distances[minNode] + edgeDistance;
                    if (newDistance < distances[destinationNode]) {
                        eradicate(destinationNode);
                        array[minNode][destinationNode] = newDistance;
                        distances[destinationNode] = newDistance;
                    }
                    unvisited.add(destinationNode);
                }
            }
        }
    }

    private void eradicate(int j) {
        for (int k = 0; k <= number_of_nodes; k++) {
            array[k][j] = 0;
        }
    }

    private void print(int src, int dst) {
        if (!list.contains(src)) {
            list.add(src);
            size++;
        }         

        //node marked as visited
        color[src] = true;

        //when the destination node is found
        if (src == dst) {
            // Print the path
            for (Integer node : list) {
                System.out.print(node_array[node]+"->");
            }
            return;
        }
         
         
        for (int i = 1; i <= number_of_nodes; i++) {
            // if there's an edge between  src and node
            if (array[src][i] != 0) {
                 
                if (color[i] == false) { //and that node is not visited yet
                     
                    //start dfs from that node
                    print(i, dst);
                     
                    //it marks the node unvisited which we have just visited
                    color[i] = false;
                    size--;
                     
                    //remove that node at index "size" from list
                    list.remove(size);
                }
            }
        }
    }


    public static void main(String [] arg) {
        int adjacency_matrix[][];
        int number_of_vertices;
        int source = 0;
        Scanner scan = new Scanner(System.in);

        //number of vertices input
        number_of_vertices = Integer.parseInt(scan.nextLine());
        ShortestPath dijkstrasAlgorithm = new ShortestPath(number_of_vertices);

        char source_node = scan.nextLine().charAt(0);//source node input

        StringTokenizer st = new StringTokenizer(scan.nextLine(),",()"); //node input
        for ( int i = 1; st.hasMoreTokens();i++) {
            node_array[i] = st.nextToken().charAt(0);
            if (node_array[i] == source_node) {
                source = i;
            }
        }

        //adjacency matrix input
        for ( int i = 1; i <= number_of_vertices; i++) {
            st = new StringTokenizer(scan.nextLine(),",");
            for ( int j = 1; st.hasMoreTokens(); j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val != 999) {
                    adjacencyMatrix[i][j] = val;
                }
                else {
                    adjacencyMatrix[i][j] = 99999;   
                }
            }
        }

        //calculating shortest path
        dijkstrasAlgorithm.dijkstra_algorithm(source);

        for (int i = 1; i <= dijkstrasAlgorithm.distances.length - 1; i++) {
            // System.out.println(source_node+"-> "+node_array[i]+ ": "+dijkstrasAlgorithm.distances[i]);
            dijkstrasAlgorithm.print(source,i);
            if(dijkstrasAlgorithm.distances[i] == 99999)System.out.print(source_node+"->"+node_array[i]+":");
            System.out.println(dijkstrasAlgorithm.distances[i]);
        }

        scan.close();
    }
}