/*
Given n nodes in a graph labeled from 1 to n.
There is no edges in the graph at beginning.

You need to support the following method:
connect(a, b), an edge to connect node a and node b
query(), Returns the number of connected component in the graph

Example
5 // n = 5
query() return 5
connect(1, 2)
query() return 4
connect(2, 4)
query() return 3
connect(1, 4)
query() return 3

idea:
typical union find, should remember 
*/

public class ConnectingGraph {
	int componentCnt;
	int[] f;

	public ConnectingGraph(int n) {
		this.componentCnt = n;
		this.f = new int[n + 1];
		// initialization
		for (int i = 0; i <= n; i++) {
			f[i] = i;
		}
	}
	// union
	public void connect(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		
		if (fa != fb) {
			f[fa] = fb;
			componentCnt--;
		}
	}

	// compress path after find()
	public int find(int i) {
		int j = i;

		while (j != f[j]) {
			j = f[j];
		}
		// path compress
		while (i != j) {
			int fi = f[i];
			// 把路上的每一个点都指向 j which is the final father
			f[i] = j;
			// 继续沿着 path 走
			i = fi;
		}
		
		return i;
	}

	public int query() {
		return componentCnt;
	}
}