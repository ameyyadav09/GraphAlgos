/*
6
a 12
b 2
c 7
d 13
e 14
f 85
*/

import java.util.*;

public class Huffman {
	public static void main(String [] args) {

		Scanner scan = new Scanner(System.in);

		int no_of_symbols = Integer.parseInt(scan.nextLine());

		NodeType [] nodearray = new NodeType[no_of_symbols+1];
		// Huffman object = new Huffman(no_of_symbols);

		MinHeap object = new MinHeap(no_of_symbols);

		for(int i = 1; i <= no_of_symbols; i++) {
			String [] str = scan.nextLine().split(" ");
			NodeType temp = new NodeType(str[0].charAt(0),Integer.parseInt(str[1]));
			object.insert(temp);
			nodearray[i] = temp;			
		}

		for (int i = 1; i < no_of_symbols; i++) {
			NodeType p = object.extractmin();
			p.huffman = "0";
			NodeType q = object.extractmin();
			q.huffman = "1";
			NodeType r = new NodeType('X',p.frequency + q.frequency,p,q);

			p.parent = r;
			q.parent = r;
			object.insert(r);
		}
		NodeType p = object.extractmin();

		for (int i = 1; i <= no_of_symbols; i++) {
			object.print(p,i);
		}
		object.printArr();
	}
}