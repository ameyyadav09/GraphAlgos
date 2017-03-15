import java.util.*;

public class BellMan_Ford {

    int number_of_vertices;
    private static int [] [] adjacencyMatrix;
    private static int source;
    private int [] distance;
    private static int [] parent;

    BellMan_Ford (int number_of_vertices) {
        this.number_of_vertices = number_of_vertices;
        adjacencyMatrix = new int [number_of_vertices+1] [number_of_vertices+1];
        distance = new int [number_of_vertices+1];
        parent = new int [number_of_vertices+1];

    }

    private boolean BellMan_Ford_Algorithm() {
        for (int i = 1; i <= number_of_vertices; i++) {
            distance[i] = 99999;
        }

        distance[source] = 0;

        for (int i = 0; i < number_of_vertices-1; i++) {
            for (int sour = 1; sour <= number_of_vertices; sour++) {
                for (int destination = 1; destination <= number_of_vertices; destination++) {
                    if (distance[destination] > (distance[sour] + adjacencyMatrix[sour][destination])) {
                        distance[destination] = distance[sour] + adjacencyMatrix[sour][destination];
                        parent[destination] = sour;
                    }
                }
            }
        }

        for (int sour = 1; sour <= number_of_vertices; sour++) {
            for ( int destination = 1; destination <= number_of_vertices; destination++) {
                if (distance[destination] > (distance[sour] + adjacencyMatrix[sour][destination])) {
                    System.out.println("Graph contains a negative weight cycles.");
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String [] args) {

        Scanner scan = new Scanner(System.in);

        int number_of_nodes = Integer.parseInt(scan.nextLine());
        BellMan_Ford obj = new BellMan_Ford(number_of_nodes);
        char source_node = scan.nextLine().charAt(0);

        char [] node_array = new char[number_of_nodes+1];

        StringTokenizer st = new StringTokenizer(scan.nextLine(),",");
        for(int i = 1; st.hasMoreTokens(); i++) {
            node_array[i] = st.nextToken().charAt(0);
            if (node_array[i] == source_node) {
                source = i;
            }
        }

        for ( int i = 1; i <= number_of_nodes; i++) {
            st = new StringTokenizer(scan.nextLine(),",");
            for ( int j = 1; j <= number_of_nodes; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val != 0)adjacencyMatrix[i][j] = val;
                else adjacencyMatrix [i][j] = 99999;
            }
        }
        boolean flag = obj.BellMan_Ford_Algorithm();
        if (flag) {
            for (int i = 1; i <= obj.distance.length - 1; i++) {
                String str = ""+node_array[i];
                for(int j = i; j <= number_of_nodes; ) {
                    str = node_array[parent[j]] +"->"+ str;
                    j = parent[j];
                    if(j == source||j == 0) break;
                }
                if(i == source) {
                    System.out.println(source_node+str.trim()+":"+obj.distance[source]);
                    continue;
                }
                 else
                    System.out.println(str+":"+obj.distance[i]);
            }
        }

    }
}