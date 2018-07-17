/*
In this problem, a tree is an undirected graph that is connected and has no cycles.
The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added.
The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges.
Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes.
If there are multiple answers, return the answer that occurs last in the given 2D-array.
The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3

Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3

Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

idea:
hashmap to store undirected graph
note prev

must use containsKey() for hm
otherwise, cost me the whole afternoon to debug

Exception in thread "main" java.lang.NullPointerException
	at Solution.hasCycle(Solution.java:29)
	at Solution.findRedundantConnection(Solution.java:8)
	at __DriverSolution__.__helper__(__Driver__.java:8)
	at __Driver__.main(__Driver__.java:52)
*/

class RedundantConnection {
	public int[] findRedundantConnection(int[][] edges) {
		Map<Integer, Set<Integer>> hm = new HashMap<>();
		for (int[] edge : edges) {
			int first = edge[0];
			int second = edge[1];

			if (hasCycle(first, second, -1, hm)) {
				return edge;
			}

			if (!hm.containsKey(first)) {
				hm.put(first, new HashSet<Integer>());
			}
			hm.get(first).add(second);

			if (!hm.containsKey(second)) {
				hm.put(second, new HashSet<Integer>());
			}
			hm.get(second).add(first);
		}

		return new int[] {};
	}
			
	public boolean hasCycle(int curr, int target, int prev, Map<Integer, Set<Integer>> hm) {
		if (!hm.containsKey(curr)) {
			return false;
		}
		if (hm.get(curr).contains(target)) {
			return true;
		}
		for (int next : hm.get(curr)) {
			if (next == prev) {
				continue;
			}
			if (hasCycle(next, target, curr, hm)) {
				return true;
			}
		}

		return false;
	}

	// union-find
	public int[] findRedundantConnection(int[][] edges) {
		int[] roots = new int[2000];
		for (int i = 0; i < roots.length; i++) {
			roots[i] = i;
		}

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];

			int rootU = getRoot(roots, u - 1);			
			int rootV = getRoot(roots, v - 1);

			if (rootU == rootV) {
				return edge;
			}

			roots[rootU] = rootV;
		}

		return new int[] {};
	}

	private int getRoot(int[] roots, int i) {
		while (roots[i] != i) {
			i = roots[i];
		}

		return i;
	}
}