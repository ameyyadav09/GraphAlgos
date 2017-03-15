class NodeType {
	public char symbol;
	public int frequency;
	public NodeType left;
	public NodeType right;
	public String huffman = "";
	public NodeType parent;

	public NodeType () {
		symbol = '\0';
		this.frequency = 0;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	public NodeType(char symbol, int frequency) {
		this.symbol = symbol;
		this.frequency = frequency;
		parent = null;
		left = null;
		right = null;
	}

	public NodeType(char symbol, int frequency, NodeType left, NodeType right) {
		this.symbol = symbol;
		this.frequency = frequency;
		this.parent = null;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		return symbol+"->"+huffman;
	}
}