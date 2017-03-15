/*
*
6
(A,B,C,D,E,F)
A
0 2 3 3 1 3
2 0 3 0 0 0
3 3 0 3 3 1
3 0 3 0 3 0
1 0 3 3 0 0
3 0 1 0 0 0
output=(A,A,A,A,A,C)
(A,E,B,C,F,D)
10
*/
import java.util.*;

public class Prims_Algorithm {

	private String [] nodeArray;
	private int number_of_vertices;
	private int [] [] adjacencyMatrix;
	private boolean [] visited;
	private String [] parent;
	private static int source;
	private int sum;

	public Prims_Algorithm(int number_of_vertices) {
		this.number_of_vertices = number_of_vertices;
		nodeArray = new String[number_of_vertices+1];
		adjacencyMatrix = new int[number_of_vertices+1] [number_of_vertices+1];
		visited = new boolean[number_of_vertices+1];
		parent = new String[number_of_vertices+1];
		sum = 0;
	}

	private void insertNodes (String str, int i) {
		nodeArray[i] = str;
	}

	private void insertIntoMatrix(int value, int i, int j) {
		if (value == 0)
			adjacencyMatrix[i][j] = 999;
		else
			adjacencyMatrix[i][j] = value;
	}

	private String algorithm () {
		// int v = -1;
		for ( int i = 0; i <= number_of_vertices; i++) {
			visited[i] = false;
			parent[i] = nodeArray[i];
		}
		visited [source] = true;
		parent[source] = nodeArray[source];
		String order = "("+nodeArray[source];
		for (int i = 0; i < number_of_vertices - 1; i++) {
			int v = 0;
			int min = 999;
			for ( int sour = 1; sour <= number_of_vertices; sour++) {
				if (visited[sour]) {
					for ( int destination = 1; destination <= number_of_vertices; destination++) {
						if (!visited[destination]) {
							if ( min > adjacencyMatrix[sour][destination]) {

								min = adjacencyMatrix[sour][destination];
								parent[destination] = nodeArray[sour];
								v = destination;
							}
						}
					}
				}
			}
			visited[v] = true;
			sum += min;
			order = order+ "," + nodeArray[v];
		}
		return order;
	}

	private void print(String str) {
		System.out.print("(");
		for ( int i = 1; i <= number_of_vertices; i++) {
			if (i == number_of_vertices)
				System.out.print(parent[i]);
			else
				System.out.print(parent[i]+",");
		}
		System.out.println(")");
		System.out.println(str+")");
		System.out.println(sum);
	}
	
	public static void main(String [] args) {

		Scanner scan = new Scanner(System.in);

		int number_of_nodes = Integer.parseInt(scan.nextLine());

		Prims_Algorithm obj = new Prims_Algorithm(number_of_nodes);

		String str = scan.nextLine();

		String [] nodeArray = str.substring(1,str.length()-1).split(",");
		
		String source_node = scan.nextLine();

		for( int i = 0; i < nodeArray.length; i++) {
			if (nodeArray[i].equals(source_node)) {
				source = i + 1;
			}
			obj.insertNodes(nodeArray[i], i+1);
		}

		for ( int i = 1; i <= number_of_nodes; i++) {
			for ( int j = 1; j <= number_of_nodes; j++) {
				int value = Integer.parseInt(scan.next());
				obj.insertIntoMatrix(value, i ,j);
			}
		}
		str = obj.algorithm();
		obj.print(str);
	}
}