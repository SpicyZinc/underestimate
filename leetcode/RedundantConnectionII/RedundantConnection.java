/*
In this problem, a rooted tree is a directed graph such that,
there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent,
except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added.
The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v,
where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes.
If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3

Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3

Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

idea:
3 cases
有环
入度为2
both

https://www.cnblogs.com/grandyang/p/8445733.html
https://blog.csdn.net/zjucor/article/details/78153680
*/

class RedundantConnection {
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int n = edges.length;

		int[] roots = new int[n + 1];
		// 入度为2的 点 的 两个edge
		int[] first = new int[2];
		int[] second = new int[2];

		// find indegree will become 2
		for (int[] edge : edges) {
			// start -> end
			int start = edge[0];
			int end = edge[1];
			// end's parent is start
			if (roots[end] == 0) {
				roots[end] = start;
			} else { // end could have two parents
				first[0] = roots[end];
				first[1] = end;

				second[0] = start;
				second[1] = end;

				edge[1] = 0; // ?
			}
		}

		for (int i = 0; i <= n; i++) {
			roots[i] = i;
		}

		for (int[] edge : edges) {
			// start -> end
			int start = edge[0];
			int end = edge[1];
			// corresponds to edge[1] = 0
			if (end == 0) {
				continue;
			}

			int x = getRoot(roots, start); // child
			int y = getRoot(roots, end); // parent

			// there is a cycle
			if (x == y) {
				// if first empty 只有环 没有入度为2 返回edge
				// 既有环又有入度为2 返回first
				return first[0] == 0 && first[1] == 0 ? edge : first;
			}
			roots[x] = y;
		}

		return second;
	}

	public int getRoot(int[] roots, int i) {
		while (roots[i] != i) {
			i = roots[i];
		}

		return i;
	}
}