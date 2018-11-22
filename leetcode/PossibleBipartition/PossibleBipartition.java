/*
Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
Return true if and only if it is possible to split everyone into two groups in this way.

Example 1:
Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]

Example 2:
Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false

Example 3:
Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false


Note:
1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].

idea:
graph, array of list to represent graph
*/

class PossibleBipartition {
	public boolean possibleBipartition(int N, int[][] dislikes) {
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
		Map<Integer, Integer> color = new HashMap();

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList();
		}

		for (int[] dislike : dislikes) {
			int A = dislike[0];
			int B = dislike[1];
			graph[A].add(B);
			graph[B].add(A);
		}

		for (int i = 1; i <= N; i++) {
			// 一开始所有的node 都应该在color group 0里
			if (!color.containsKey(i) && !dfs(i, 0, color, graph)) {
				return false;
			}
		}

		return true;
	}

	public boolean dfs(int node, int group, Map<Integer, Integer> color, ArrayList<Integer>[] graph) {
		if (color.containsKey(node)) {
			return color.get(node) == group;
		}

		color.put(node, group);
		for (int neighbor : graph[node]) {
			// use xor
			if (!dfs(neighbor, group ^ 1, color, graph)) {
				return false;
			}
		}

		return true;
	}
}