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
need to go back
*/

class RedundantConnection {
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int n = edges.length;
		int[] root = new int[n + 1];
		int[] first = new int[2];
		int[] second = new int[2];

		for (int[] edge : edges) {
			int start = edge[0];
			int end = edge[1];
			// indegree
			if (root[end] == 0) {
				root[end] = start;
			} else {
				first[0] = root[end];
				first[1] = end;
				second = edge;
				// ?
				edge[1] = 0;
			}
		}

		for (int i = 0; i <= n; i++) {
			root[i] = i;
		}

		for (int[] edge : edges) {
			int start = edge[0];
			int end = edge[1];
			if (end == 0) {
				continue;
			}

			int x = getRoot(root, start);
			int y = getRoot(root, end);
			if (x == y) {
				return first.length == 0 ? edge : first;
			}
			root[x] = y;
		}

		return second;
	}

	public int getRoot(int[] root, int i) {
		while (i != root[i]) {
			root[i] = root[root[i]];
			i = root[i];
		}
		return i;
	}
}