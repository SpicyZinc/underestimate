/*Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to find the number of connected components in an undirected graph.

Example 1:
Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
    0          3
    |          |
    1 --- 2    4 
Output: 2

Example 2:
Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
    0           4
    |           |
    1 --- 2 --- 3
Output:  1

Note:
You can assume that no duplicate edges will appear in edges.
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

idea:
build graph with hashmap
dfs()
note, 一个点一个点地来 要初始化hashmap
*/

import java.util.*;

class NumberOfConnectedComponentsInAnUndirectedGraph {
	public static void main(String[] args) {
		NumberOfConnectedComponentsInAnUndirectedGraph eg = new NumberOfConnectedComponentsInAnUndirectedGraph();
		int n = 4;
		int[][] edges = {
			{2,3},
			{1,2},
			{1,3}	
		};
	}

	public int countComponents(int n, int[][] edges) {
		Map<Integer, List<Integer>> hm = new HashMap<>();
		for (int i = 0; i < n; i++) {
			hm.put(i, new ArrayList<>());
		}

		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];

			List<Integer> connectedToU = hm.get(u);
			List<Integer> connectedToV = hm.get(v);
 			connectedToU.add(v);
			connectedToV.add(u);           
		}

		int cnt = 0;
		boolean[] visited = new boolean[n];
		// one vertex by one vertex, 不是一个edge一个edge
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				cnt++;
				dfs(i, visited, hm);
			}
		}
		return cnt;
	}

	public void dfs(int vertex, boolean[] visited, Map<Integer, List<Integer>> hm) {
		if (visited[vertex]) {
			return;
		}

		visited[vertex] = true;

		List<Integer> connected = hm.get(vertex);

		for (int node : connected) {
			dfs(node, visited, hm);
		}
	}
}