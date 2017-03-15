import java.util.*;

class MinHeap {
	private ArrayList<NodeType> list;
	public NodeType [] array;
	private int count;
	private int sum = 0;
	MinHeap(int n) {
		list = new ArrayList<NodeType>();
		array =  new NodeType[n+1];
		count = 1;
	}
	public void insert(NodeType value) {
		if(count == 1) {
			array[count] = value;
		}
		else {
			array[count] = value;
			bubbleUp();
		}
		count++;
	}
	public NodeType extractmin() {
			NodeType temp = array[1];
			bubbleDown();
			count--;
		return temp;
	}
	public void bubbleUp() {
		int index = count;
		int temp = index/2;
			while(index > 0 && temp > 0) {
				if(array[index].frequency < array[temp].frequency) {
					NodeType hold = array[temp];
					array[temp] = array[index];
					array[index] = hold;
					index = temp;temp = temp/2;
				}
				else {
					break;
				}
			}
	}
	public void bubbleDown() {
		int index = 1;
		array[index] = array[count - 1];
		int ptr1 = 2 * index;
		int ptr2 = (2 * index) + 1;
		while(index < count && index > 0) {
			if(ptr1 < count && ptr2 < count ) {
			if(array[index].frequency > array[ptr1].frequency) {
				NodeType hold = array[ptr1];
				array[ptr1] = array[index];
				array[index] = hold;
				if(array[index].frequency > array[ptr2].frequency) {
					hold = array[ptr2];
					array[ptr2] = array[index];
					array[index] = hold;
				}
			}
		}
			index++;
			ptr1 = 2 * index; ptr2 = (2 * index) + 1;
		}	
	}

	public void printArr() {
		for (int i = 1; i < list.size() ; i++) {
			if(list.get(i).parent.equals(list.get(i-1).parent)) {
				if(list.get(i).symbol < list.get(i-1).symbol) {
					Collections.swap(list,i,i-1);
				}
			}
		}
		
		for (int i = 0; i < list.size() ; i++) {
			System.out.println(list.get(i));
		}
		System.out.println(sum);
	}

	public void print(NodeType node, int level) {
		//base condition
		if(node == null) {
			return;
		}
		if(level == 1) {
			if(node.symbol == 'X') {
				sum += node.frequency;
			}
			NodeType temp = node.parent;
			if (temp != null) {
				node.huffman = temp.huffman + node.huffman;
				if (node.symbol != 'X') {
					list.add(node);
				}
			}
		}
		else if(level > 1) {
			print(node.left, level -1);
			print(node.right, level -1);
		}
	}
}