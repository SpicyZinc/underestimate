/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to check whether these edges make up a valid tree.

For example:
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
Show More Hint Note:
you can assume that no duplicate edges will appear in edges.
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

idea:
https://www.cnblogs.com/grandyang/p/5257919.html
判断 是否是连通图和是否含有环

用DFS来搜索节点0
当DFS到某个节点
看当前节点是否被访问过
如果已经被访问过
说明环存在
直接返回false
如果未被访问过
将其状态标记为已访问过
然后到邻接链表里去找跟其相邻的节点继续递归遍历
注意我们还需要一个变量pre来记录上一个节点
以免回到上一个节点
这样遍历结束后
我们就把和节点0相邻的节点都标记为true
然后在看v里面是否还有没被访问过的节点
如果有
则说明图不是完全连通的
返回false
反之返回true
*/
import java.util.*;

public class GraphValidTree {
	public static void main(String[] args) {
		GraphValidTree eg = new GraphValidTree();

		int n = 5;
		int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};

		boolean result = eg.validTree(n, edges);
		System.out.println(result);
	}
	// DFS
	public boolean validTree(int n, int[][] edges) {
		Map<Integer, List<Integer>> hm = new HashMap<>();
		for (int i = 0; i < n; i++) {
			hm.put(i, new ArrayList<Integer>());
		}
		// populate the hashmap
		for (int[] edge : edges) {
			int first = edge[0];
			int second = edge[1];

			hm.get(first).add(second);
			hm.get(second).add(first);
		}

		boolean[] visited = new boolean[n];
		// start from 0
		if (!dfs(0, -1, hm, visited)) {
			return false;
		}

		// check if all n nodes have been visited
		for (boolean i : visited) {
			if (!i) {
				return false;
			}
		}

		return true;
	}
	// dfs check if valid tree
	public boolean dfs(int curr, int prev, Map<Integer, List<Integer>> hm, boolean[] visited) {
		// visited before, a cycle detected
		if (visited[curr]) {
			return false;
		}

		visited[curr] = true;

		for (int next : hm.get(curr)) {
			// next != prev 确定不是从prev 来的道路 因为都是互相的 不是 directed
			if (next != prev && !dfs(next, curr, hm, visited)) {
				return false;
			}
		}

		return true;
	}

	// BFS
	public boolean validTree(int n, int[][] edges) {
		Map<Integer, List<Integer>> hm = new HashMap<>();
		for (int i = 0; i < n; i++) {
			hm.put(i, new ArrayList<Integer>());
		}
		for (int[] edge : edges) {
			int first = edge[0];
			int second = edge[1];

			hm.get(first).add(second);
			hm.get(second).add(first);
		}

		boolean[] visited = new boolean[n];

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);

		while (!queue.isEmpty()) {
			int top = queue.remove();
			if (visited[top]) {
				return false;
			}

			visited[top] = true;

			for (int next : hm.get(top)) {
				if (!visited[next]) {
					queue.offer(next);
				}
			}
		}
		// check if all n nodes have been visited
		for (boolean i : visited) {
			if (!i) {
				return false;
			}
		}
		return true;
	}
}