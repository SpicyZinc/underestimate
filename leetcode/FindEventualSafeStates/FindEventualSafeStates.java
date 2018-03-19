/*
In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.
If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.
Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.
More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.
Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.
The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]

Here is a diagram of the above graph.
https://s3-lc-upload.s3.amazonaws.com/uploads/2018/03/17/picture1.png

Note:
graph will have length at most 10000.
The number of edges in the graph will not exceed 32000.
Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].

idea:
DFS, find if a node in a cycle or walk into a cycle
*/

class FindEventualSafeStates {
	public enum States {
		UNKNOWN,
		VISITING,
		UNSAFE,
		SAFE
	};

	public List<Integer> eventualSafeNodes(int[][] graph) {
		int m = graph.length;
		States[] states = new States[m];
		for (int i = 0; i < m; i++) {
			states[i] = States.UNKNOWN;
		}

		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			if (dfs(graph, i, states) == States.SAFE) {
				result.add(i);
			}
		}
		Collections.sort(result);

		return result;
	}

	public States dfs(int[][] graph, int curr, States[] states) {
		if (states[curr] == States.VISITING) {
			return States.UNSAFE;
		}
		if (states[curr] != States.UNKNOWN) {
			return states[curr];
		}
		// otherwise set to be visiting
		states[curr] = States.VISITING;
		for (int next : graph[curr]) {
			// if entering a cycle
			if (dfs(graph, next, states) == States.UNSAFE) {
				return states[curr] = States.UNSAFE;
			}
		}
		return states[curr] = States.SAFE;
	}
}