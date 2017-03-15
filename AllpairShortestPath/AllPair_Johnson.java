import java.util.*;

public class AllPair_Johnson {

	private static int [] [] adjacencyMatrix;
	private static int [] [] potentialMatrix;
	private ArrayList<Integer> visited;
    private Set<Integer> unvisited;
	private static int [] distance;
	private static int [] cross;
	private int number_of_vertices;

	public AllPair_Johnson(int number_of_vertices) {
		this.number_of_vertices = number_of_vertices;
		adjacencyMatrix = new int[number_of_vertices+1][number_of_vertices+1];
		potentialMatrix = new int[number_of_vertices+1][number_of_vertices+1];
		visited = new ArrayList<Integer>();
        unvisited = new HashSet<Integer>();
		distance = new int[number_of_vertices+1];
		cross = new int[number_of_vertices+1];
	}

	private void insertIntoMatrix (int value,int i, int j) {
    	adjacencyMatrix[i][j] = value;
    	potentialMatrix[i][j] = value;
    }

    private boolean johnson_Bell() {
    	for (int i = 1; i <= number_of_vertices; i++) {
            distance[i] = 999;
            potentialMatrix[i][0] = 999;
        }
        distance[0] = 0;

        for (int i = 0; i < number_of_vertices-1; i++) {
            for (int sour = 0; sour <= number_of_vertices; sour++) {
                for (int destination = 0; destination <= number_of_vertices; destination++) {
                    if (distance[destination] > (distance[sour] + potentialMatrix[sour][destination])) {
                        distance[destination] = distance[sour] + potentialMatrix[sour][destination];
                    }
                }
            }
        }

        for (int sour = 0; sour <= number_of_vertices; sour++) {
            for ( int destination = 0; destination <= number_of_vertices; destination++) {
                if (distance[destination] > (distance[sour] + potentialMatrix[sour][destination])) {
                    System.out.println("Graph contains a negative weight cycles.");
                    return false;
                }
            }
        }
        return true;
    }

    private int extractMin() {
        int min;
        int node = 0;
 
        node = unvisited.iterator().next();
        min = cross[node];
        for (int i = 1; i <= cross.length; i++) {
            if (unvisited.contains(i)) {
                if (cross[i] <= min) {
                    min = cross[i];
                    node = i;
                }
            }
        }
        return node;
    }

    private void clear() {
    	unvisited.clear();
    	visited.clear();
    }
    public void johnson_Dijkstra(int source) {
    	int minNode;
        for (int i = 0; i <= number_of_vertices; i++) {
            cross[i] = 999;
        }
 
        unvisited.add(source);
        cross[source] = 0;
        while (!unvisited.isEmpty()) {
            minNode = extractMin();
            unvisited.remove(minNode);
            visited.add(minNode);
            pathSearch(minNode);
        }
    }

    private void pathSearch(int minNode) {
        int edgeDistance = -1;
        int newDistance = -1;
        for (int destinationNode = 1; destinationNode <= number_of_vertices; destinationNode++) {
            if (!visited.contains(destinationNode)) {
                if (adjacencyMatrix[minNode][destinationNode] != 999) {
                    edgeDistance = adjacencyMatrix[minNode][destinationNode];
                    newDistance = cross[minNode] + edgeDistance;
                    if (newDistance < cross[destinationNode]) {
                        cross[destinationNode] = newDistance;
                    }
                    unvisited.add(destinationNode);
                }
            }
        }
    }

    private void updatePotential(int i) {
    	for (int sour = 1; sour <= number_of_vertices; sour++) {
    		potentialMatrix[i][sour] = cross[sour];
    	}
    }
    private void print () {
    	for (int sour = 1; sour <= number_of_vertices; sour++) {
			for( int destination = 1; destination <= number_of_vertices; destination++) {
				System.out.print((potentialMatrix[sour][destination]+distance[destination]-distance[sour])+" ");
			}
			System.out.println();
    	}
    }

    public static void main(String [] args) {

        Scanner scan = new Scanner(System.in);

        int number_of_nodes = Integer.parseInt(scan.nextLine());

        char [] node_array = new char[number_of_nodes+1];

        StringTokenizer st = new StringTokenizer(scan.nextLine(),",");
        for(int i = 1; st.hasMoreTokens(); i++) {
            node_array[i] = st.nextToken().charAt(0);
        }

        AllPair_Johnson obj = new AllPair_Johnson(number_of_nodes);

        for ( int i = 1; i <= number_of_nodes; i++) {
            st = new StringTokenizer(scan.nextLine(),",");
            for ( int j = 1; j <= number_of_nodes; j++) {
                obj.insertIntoMatrix(Integer.parseInt(st.nextToken()),i,j);
            }
        }
        if (obj.johnson_Bell()) {
        	for (int sour = 1; sour <= number_of_nodes; sour++) {
	            for ( int destination = 1; destination <= number_of_nodes; destination++) {
	            	if (adjacencyMatrix[sour][destination] != 999)
    	            adjacencyMatrix[sour][destination] = adjacencyMatrix[sour][destination] + distance[sour] - distance[destination];
	            }
	        }
 			potentialMatrix = new int[number_of_nodes+1][number_of_nodes+1];
	        for (int i = 1; i <= number_of_nodes; i++) {
	        	obj.johnson_Dijkstra(i);
	        	obj.updatePotential(i);
	        	obj.clear();	        	
	        }
	        obj.print();
        }
    }
}