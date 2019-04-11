/*
  1  2  3
/  \/  	\
4  5 	6
		 \
		  7

1 2 4
\/ /\
 3 5 8 
 \/\ \
 6 7 9

{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 10}
hasCommonAncester

follow up 是如果array里有line跟Point的话怎么过滤掉

*/
import java.util.*;

class Graph {
	public static void main(String[] args) {
		int[][] input = {
			{1, 3},
			{2, 3},
			{4, 5},
			{4, 8},
			{3, 6},
			{5, 6},
			{5, 7},
			{8, 9},
		};

		Graph graph = new Graph();
		List[] result = graph.getLonelyNodes(input);

		for (List item : result) {
			System.out.println(item);
		}

		graph.commonParent(3, 8, input);
	}

	// nodes of 0 parent and 1 parent 
	public List[] getLonelyNodes(int[][] input) {
		Map<Integer, List<Integer>> hm = new HashMap<>();
		Map<Integer, Integer> dependencyCnt = new HashMap<>();

		Set<Integer> nodes = new HashSet<>();

		// build the graph
		for (int[] edge : input) {
			int parent = edge[0];
			int child = edge[1];

			nodes.add(parent);
			nodes.add(child);

			if (hm.containsKey(parent)) {
				hm.get(parent).add(child);
			} else {
				List<Integer> children = new ArrayList<>();
				children.add(child);
				hm.put(parent, children);
			}

			dependencyCnt.put(child, dependencyCnt.getOrDefault(child, 0) + 1);
		}

		List<Integer> zeroParents = new ArrayList<>();
		List<Integer> oneParents = new ArrayList<>();

		for (int node : nodes) {
			if (!dependencyCnt.containsKey(node)) {
				zeroParents.add(node);
			}

			if (dependencyCnt.containsKey(node) && dependencyCnt.get(node) == 1) {
				oneParents.add(node);
			}
		}

		return new List[] {zeroParents, oneParents};
	}

	public int commonParent(int a, int b, int[][] input) {
		Map<Integer, List<Integer>> hm = new HashMap<>();
		// build the graph
		for (int[] edge : input) {
			int parent = edge[0];
			int child = edge[1];

			if (hm.containsKey(parent)) {
				hm.get(parent).add(child);
			} else {
				List<Integer> children = new ArrayList<>();
				children.add(child);
				hm.put(parent, children);
			}
		}

		List<Integer> zeroParents = getLonelyNodes(input)[0];
		List<Integer> pathToA = new ArrayList<>();
		dfs(zeroParents.get(0), a, pathToA, hm);

		System.out.println(pathToA);

		return 1;
	}

	public void dfs(int root, int target, List<Integer> path, Map<Integer, List<Integer>> hm) {
		if (root == target) {
			// find a path
			return;
		}
		if (hm.containsKey(root)) {
			List<Integer> children = hm.get(root);
			path.add(root);
			if (children == null || children.size() == 0) {
				return;
			}
			for (int child : children) {
				dfs(child, target, path, hm);
			}
		}
	}
}