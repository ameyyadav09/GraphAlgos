import java.util.*;

public class BMF {

	private int [] [] mat;
	private int [] weight;
	private int [] source_node;
	private int node_num;

	public BMF(int node_num) { 
		this.node_num = node_num;
		mat = new int [node_num+1][node_num+1];
		weight = new int[node_num+1];
		source_node = new int [node_num+1];
	}

	private boolean algo (int source, int [] [] mat) {
		this.mat = mat;

		for(int i = 1; i <= node_num; i++) {
			weight[i] = 99999;
		}

		weight[source] = 0;

		for (int i = 0; i < node_num-1; i++) {
			for (int v = 1; v <= node_num; v++) {
				for (int x = 1; x <= node_num; x++) {
					if (weight[x] > weight[v] + mat[v][x]) {
						weight[x] = weight[v] + mat[v][x];
						source_node[x] = v;
					}
				}
			}
		}
		for (int v = 1; v <= node_num; v++) {
			for (int x = 1; x <= node_num; x++) {
				if (weight[x] > weight[v] + mat[v][x]) {
					System.out.println("Graph contains a negative weight cycles.");
					return false;
				}
			}
		}
		return true;
	}

	private void print(String [] node_array,int source_num) {
		for (int i = 1; i <= node_array.length-1; i++) {
            String str = node_array[i];
            if(node_array[i] == node_array[source_num]) {
                System.out.println(node_array[i]+"->"+str+":"+weight[source_num]);
                continue;
            }
            for(int j = i; j <= node_num; ) {
                str = node_array[source_node[j]] +"->"+ str;
                j = source_node[j];
                if(node_array[j] == node_array[source_num]||j == 0) break;
            }
            System.out.println(str+":"+weight[i]);
	    }
	}
	
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
        int number_of_nodes = Integer.parseInt(scan.nextLine());
        String source_node = scan.nextLine().trim();
        int source = 0;
        String [] node_array = new String[number_of_nodes+1];
        int [] [] adjacencyMatrix = new int[number_of_nodes+1][number_of_nodes+1];
        String [] st = scan.nextLine().split(",");
        for(int i = 1; i <= st.length ; i++) {
            node_array[i] = st[i-1];
            if (node_array[i].trim().equals(source_node)) {
                source = i;
            }
        }
        for ( int i = 1; i <= number_of_nodes; i++) {
            st = scan.nextLine().split(",");
            for ( int j = 1; j <= st.length; j++) {
                int val = Integer.parseInt(st[j-1]);
                if (val != 0)adjacencyMatrix[i][j] = val;
                else adjacencyMatrix [i][j] = 99999;
            }
        }
        BMF obj = new BMF(number_of_nodes);
        boolean flag = obj.algo(source,adjacencyMatrix);
        if (flag)obj.print(node_array,source);
	}
}