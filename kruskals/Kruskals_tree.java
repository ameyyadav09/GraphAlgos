/*
* source link: foundation of algorithms Richard E.Neopolitan Naimipour
https://books.google.co.in/books?id=DAorddWEgl0C&pg=PA155&lpg=PA155&dq=kruskal%27s+algorithm+java+-%22union%22+-%22find%22+-
%22make%22&source=bl&ots=o3NKa1_pNM&sig=pbOEhOApIZoA-PgOUzbBONwpk6M&hl=en&sa=X&ved=0ahUKEwjXycLU3fbLAhVHj44KHQgfA9UQ6AEIHDAA#v=
onepage&q&f=false


7
(A,B,C,D,E,F,G)
0 2 0 3 3 0 0
2 0 3 0 4 0 0
0 3 0 0 1 8 0
3 0 0 0 0 7 0
3 4 1 0 0 6 0
0 0 8 7 6 6 9
0 0 0 0 0 9 0

(C,E)
(A,B)
(A,D)
(A,E)
(E,F)
(F,G)
24
*/


import java.util.*;

class Edge {
	public String node1;
	public String node2;
	public int weight;

	public Edge(String node1, String node2, int weight) {
		this.node1 = node1;
		this.node2 = node2;
		this.weight = weight;
	}
	public String toString() {
		return "("+node1+","+node2+")";
	}
}

public class Kruskals_tree {

	private int [] [] adjacencyMatrix;
	private String [] nodeArray;
	private int number_of_vertices;
	private Edge [] visited;
	private Edge [] edgeArray;
	private String [] parentArray;

	public Kruskals_tree (int number_of_vertices) {
		this.number_of_vertices = number_of_vertices; 
		adjacencyMatrix = new int[number_of_vertices+1] [number_of_vertices+1];
		nodeArray = new String[number_of_vertices+1];
		visited = new Edge[number_of_vertices];
		edgeArray = new Edge[30];
		parentArray = new String[number_of_vertices+1];
	}

	private void insertIntoMatrix (int value, int i, int j) {
		adjacencyMatrix [i][j] = value;
	}

	private void insertIntoNodeArray(String value, int i) {
		nodeArray[i] = value;
	}

	private void initialise() {
		for ( int i = 1; i <= number_of_vertices; i++) {
			parentArray[i] = nodeArray[i];
		}
	}

	private void createTrees() {
		int count = 1;
		for (int sour = 1; sour <= number_of_vertices; sour++) {
			for (int destination = 1; destination <= number_of_vertices; destination++) {
				if (adjacencyMatrix[sour][destination] != 0) {
					int value = adjacencyMatrix[sour][destination];
					adjacencyMatrix[destination][sour] = 0;
					Edge obj = new Edge(nodeArray[sour], nodeArray[destination], value);
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
				else if (edgeArray[min].weight == edgeArray[j].weight) {
					if (edgeArray[min].node1.compareTo(edgeArray[j].node1) > 0)
						min = j;
					else if (edgeArray[min].node1.compareTo(edgeArray[j].node1) == 0) {
						if (edgeArray[min].node2.compareTo(edgeArray[j].node2) > 0) {
							min = j;
						}
					}
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
		for (int i = 1; i <= number_of_vertices; i++) {
			if ( str.equals(nodeArray[i])) {
				return i;
			}
		}
		return -1;
	}

	private void algorithm () {
		createTrees();
		sorting();
		initialise();
		int count = 0;
		for(int i = 1; edgeArray[i] != null; i++) {
			Edge edge = edgeArray[i];
			if (count < number_of_vertices-1) {
				int index1 = getIndexOf(edge.node1);
				int index2 = getIndexOf(edge.node2);
				String p1 = parentArray[index1];
				String p2 = parentArray[index2];
				if (!p1.equals(p2)) {
					visited[count] = edge;
					count++;
					if (edge.node1.compareTo(edge.node2) > 0) {
						String str = p1;
						p1 = p2; p2 = str;
					}
					for ( int f = 1; f < parentArray.length; f++) {
						if (parentArray[f].equals(p2)) {
							parentArray[f] = p1;
						}
					}
				}
			}
		}		
	}

	private void print() {
		int sum = 0;
		for ( int i = 0; i < visited.length-1; i++) {
			System.out.println(visited[i].toString());
			sum += visited[i].weight;
		}	
		System.out.println(sum);
	}

	public static void main (String [] args) {
		Scanner scan = new Scanner(System.in);

		int number_of_nodes = Integer.parseInt(scan.nextLine());

		Kruskals_tree obj = new Kruskals_tree(number_of_nodes);

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