import java.util.*;
 
public class Shortest_Path
{
    private int distances[];
    private ArrayList<Integer> visited;
    private Set<Integer> unvisited;
    private int number_of_nodes;
    private static int adjacencyMatrix[][];
    private static char [] node_array;
    private static int [] path;
 
    public Shortest_Path(int number_of_nodes) {
        this.number_of_nodes = number_of_nodes;
        node_array = new char[number_of_nodes+1];
        distances = new int[number_of_nodes + 1];
        visited = new ArrayList<Integer>();
        unvisited = new HashSet<Integer>();
        adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
        path = new int [number_of_nodes+1];
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
                        distances[destinationNode] = newDistance;
                        path[destinationNode] = minNode;
                    }
                    unvisited.add(destinationNode);
                }
            }
        }
    }

    public static void main(String [] arg) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int adjacency_matrix[][];
        int number_of_vertices;
        int source = 0;
        Scanner scan = new Scanner(System.in);

        //number of vertices input
        number_of_vertices = Integer.parseInt(scan.nextLine());
        Shortest_Path dijkstrasAlgorithm = new Shortest_Path(number_of_vertices);

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
                if (val != 0) {
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
            String str = ""+node_array[i];
            for(int j = i; j <= number_of_vertices; ) {
                str = node_array[path[j]] +"->"+ str;
                j = path[j];
                if(j == source||j == 0) break;
            }
            if(dijkstrasAlgorithm.distances[i] == 0 || dijkstrasAlgorithm.distances[i] == 99999) {
                System.out.println(source_node+str.trim()+":"+dijkstrasAlgorithm.distances[i]);
            }
            else System.out.println(str+":"+dijkstrasAlgorithm.distances[i]);
        }

        scan.close();
    }
}