/*
Given n nodes in a graph labeled from 1 to n.
There is no edges in the graph at beginning.

You need to support the following method:
connect(a, b), an edge to connect node a and node b
query(a), Returns the number of connected component nodes which include node a.

Example
5 // n = 5
query(1) return 1
connect(1, 2)
query(1) return 2
connect(2, 4)
query(1) return 3
connect(1, 4)
query(1) return 3

idea:
typical union find, should remember 
*/

public class ConnectingGraph {
	int[] f;
	int[] size;

	public ConnectingGraph(int n) {
		this.f = new int[n + 1];
		this.size = new int[n + 1];
		// initialization
		for (int i = 0; i <= n; i++) {
			f[i] = i;
			// 开始每个group里只有一个
			size[i] = 1;
		}
		
	}
	// union
	public void connect(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		
		if (fa != fb) {
			f[fa] = fb;
			
			size[fb] += size[fa];
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

	public int query(int a ) {
		int fa = find(a);
		return size[fa];
	}
}