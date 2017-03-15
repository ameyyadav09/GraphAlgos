import java.util.*;

public class Union {

	private int [] parent;
	private int [] rank;

	public Union(int N) {
		parent = new int[N+1];
		rank = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	private int check(int i) {
		if (parent[i] ==  i) return i;

		int temp = check(parent[i]);
		parent[i] = temp;
		return temp;
	}
	
	private void link(int i, int j) {
		if (parent[i] != parent[j]) {
			if (rank[i] < rank[j]) {
				parent[i] = j;
			}
			else {
				parent[j] = i;
				if (rank[i] == rank[j]) {
					rank[i]++;
				}
			}
		}
	}

	private boolean union(int i, int j) {
		int p1 = check(i);
		int p2 = check(j);
		if (p1 != p2) {
			link(p1,p2);
		}
		else {
			System.out.println("There is a Cycle");
			return false;
		}
		return true;
	}

	private void print () {
		for (int i = 1; i < parent.length; i++) {
			String str = "";
			for (int j = 1; j < parent.length; j++) {
				if (parent[j] == i)
					if (str.length() == 0)
						str = str + j;
					else
						str = str+","+j;
			}
			if (str.length() > 0) {
				System.out.print("("+str+")");
			}
		}
		System.out.println();
	}
	
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);

		int N = Integer.parseInt(scan.nextLine());
		Union obj = new Union(N);
		while(scan.hasNext()) {
			String str = scan.nextLine();
			if(str.equals("end"))break;
			StringTokenizer st = new StringTokenizer(str,"(),");
			if (st.hasMoreTokens()) {
				switch(st.nextToken()) {
					case "Union":
								boolean flag = obj.union(Integer.parseInt(st.nextToken()),
														Integer.parseInt(st.nextToken()));
								if (flag)obj.print(); break;
					case "Check": 
						if (obj.check(Integer.parseInt(st.nextToken())) == obj.check(Integer.parseInt(st.nextToken()))) {
							System.out.println("Yes");
						}
						else {
							System.out.println("No");
						}
					 	break;
					default : break;
				}
			}
		}
	}	
}