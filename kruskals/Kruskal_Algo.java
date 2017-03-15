import java.util.*;

public class Kruskal_Algo {

	private int [] [] adjacencyMatrix;
	private String [] nodeArray;
	private int number_of_vertices;
	private Edge [] visited;
	private Edge [] edgeArray;
	private boolean [] visitedVertex;

	public Kruskal_Algo (int number_of_vertices) {
		this.number_of_vertices = number_of_vertices; 
		adjacencyMatrix = new int[number_of_vertices+1] [number_of_vertices+1];
		nodeArray = new String[number_of_vertices+1];
		visited = new Edge[30];
		edgeArray = new Edge[30];
		visitedVertex = new boolean[number_of_vertices+1];
	}

	private void insertIntoMatrix (int value, int i, int j) {
		adjacencyMatrix [i][j] = value;
	}

	private void insertIntoNodeArray(String value, int i) {
		nodeArray[i] = value;
	}

	private void createTrees() {
		int count = 1;
		for (int sour = 1; sour <= number_of_vertices; sour++) {
			for (int destination = 1; destination <= number_of_vertices; destination++) {
				if (adjacencyMatrix[sour][destination] != 0) {
					int value = adjacencyMatrix[sour][destination];
					adjacencyMatrix[destination][sour] = 0;
					Edge obj = new Edge(nodeArray[sour], nodeArray[destination], value, false);
					edgeArray[count] = obj;
					count++;
				}
			}
		}
	}

	private void sorting() {
		for ( int i = 1; edgeArray[i] != null; i++) {
			int min = i;
			for ( int j = i+1; edgeArray[j] != null; j++) {
				if (edgeArray[min].weight > edgeArray[j].weight) {
					min = j;
				}
			}
			if ( min != i) {
				Edge obj = edgeArray[min];
				edgeArray[min] = edgeArray[i];
				edgeArray[i] = obj;
			}
		}
	}

	private int getIndexOf(String str) {
		int x = -1;
		for (int i = 1; i <= number_of_vertices; i++) {
			if ( str.equals(nodeArray[i])) {
				x = i;
			}
		}
		return x;
	}

	private void algorithm () {
		createTrees();
		sorting();
		int count = 1;
		for (int i = 1; edgeArray[i] != null; i++) {
			Edge obj = edgeArray[i];
			int index1 = getIndexOf(edgeArray[i].node1);
			int index2 = getIndexOf(edgeArray[i].node2);
			if (index1 != -1 && index2 != -1) {
				if (!visitedVertex[index1] || !visitedVertex[index2]) {
					if (!obj.visited) {
						visited[count] = obj;
						obj.visited = true;
						count++;
					}
					visitedVertex[index2] = true;
					visitedVertex[index1] = true;
				}
			}
		}
	}

	private void print() {
		int sum = 0;
		for ( int i = 1; visited[i] != null; i++) {
			System.out.println(visited[i].toString());
			sum += visited[i].weight;
		}
		System.out.println(sum);
	}

	public static void main (String [] args) {
		Scanner scan = new Scanner(System.in);

		int number_of_nodes = Integer.parseInt(scan.nextLine());

		Kruskal_Algo obj = new Kruskal_Algo(number_of_nodes);

		String str = scan.nextLine();

		String [] nodeArray = str.substring(1,str.length()-1).split(",");

		for( int i = 0; i < nodeArray.length; i++) {
			obj.insertIntoNodeArray(nodeArray[i],i+1);
		}

		for ( int i = 1; i <= number_of_nodes; i++) {
			for(int j = 1; j <= number_of_nodes; j++) {
				int value = Integer.parseInt(scan.next());
				obj.insertIntoMatrix(value, i, j);
			}
		}
		obj.algorithm();
		obj.print();
	}
}