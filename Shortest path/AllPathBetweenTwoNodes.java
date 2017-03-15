import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStreamReader;
 
 
class AllPathBetweenTwoNodes{
     static int dim;
    static boolean[] color;     
    static int[][] graph;
    static ArrayList<Integer> list;
    static int size;
    static String [] nodeArray;

    public AllPathBetweenTwoNodes() {
        color = new boolean[dim + 1];
        graph = new int[10][10];
        list = new ArrayList<Integer>();
        nodeArray = new String [dim+1];
    }
     
    public static void main (String [] args) throws IOException{
     
        BufferedReader br = new BufferedReader (new InputStreamReader ((System.in)));
        dim = Integer.parseInt(br.readLine());
        AllPathBetweenTwoNodes obj = new AllPathBetweenTwoNodes();

        String nodes = br.readLine();
        String [] st = nodes.substring(1,nodes.length()-1).split(",");

        for ( int i = 1; i <= dim; i++) {
            nodeArray[i] = st[i-1].trim();
        }
        st = br.readLine().split(",");

        String source_node = st[0].trim();
        String destination_node = st[1].trim();

        int source = 0, destination = 0;

        for ( int i = 1; i <= dim; i++) {
            System.out.println(nodeArray[i]+"---");
            if (nodeArray[i].equals(source_node)) source = i;
            if (nodeArray[i].equals(destination_node)) destination = i;
        }

        for (int I = 1; I <= dim; I++) {             
            String[] input = br.readLine().split(",");
            int len = input.length;             
            for (int J = 1; J <= len; J++) {
                graph[I][J] = Integer.parseInt(input[J - 1]);
            }
        }
         
        Arrays.fill(color, false);
        System.out.println(source+" "+destination);
        algo(source, destination);
    }
     
     static void algo(int src, int dst) {
        list.add(src);
        size++;
        color[src] = true;
        if (src == dst) {
            for (Integer node : list) {
                System.out.print(node + "  ");
            }
            System.out.println();
            return;
        }         
        for (int I = 1; I <= dim; I++) {
            if (graph[src][I] != 9999) {
                if (color[I] == false) {
                    algo(I, dst);
                    color[I] = false;
                    size--;
                    list.remove(size);
                }
            }
        }
    }
}