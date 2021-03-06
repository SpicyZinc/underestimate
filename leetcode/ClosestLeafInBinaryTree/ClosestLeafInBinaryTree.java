/*
Given a binary tree where every node has a unique value, and a target key k,
find the value of the nearest leaf node to target k in the tree.
Here, nearest to a leaf means the least number of edges traveled on the binary tree to reach any leaf of the tree.
Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row.
The actual root tree given will be a TreeNode object.

Example 1:
Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
		  1
		 / \
		3   2
Output: 2 (or 3)
Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.

Example 2:
Input:
root = [1], k = 1
Output: 1
Explanation: The nearest leaf node is the root node itself.

Example 3:
Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
			 1
			/ \
		   2   3
		  /
		 4
		/
	   5
	  /
	 6
Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.

Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.

idea:
dfs, plus 双向的 hashmap
node => node.left
node => node.right
node => node.parent
know how to dfs (node, parent, hm)
每个点都会有两个 list, to parent and to children
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class ClosestLeafInBinaryTree {
	public int findClosestLeaf(TreeNode root, int k) {
		Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
		// populate the graph
		dfs(graph, root, null);

		Queue<TreeNode> queue = new LinkedList<>();
		Set<TreeNode> visited = new HashSet<>();
		
		// 只有变成graph也就是个HashMap后才可以loop找到target of k
		for (TreeNode node : graph.keySet()) {
			if (node != null && node.val == k) {
				queue.add(node);
				visited.add(node);
				break;
			}
		}

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			// 所指的list中只有node 显然是Leaf
			if (node != null && graph.get(node).size() == 1) {
				return node.val;
			}
			// 通过 neighbor 找, 一定是距离最小的 最closest
			for (TreeNode neighbor : graph.get(node)) {
				if (!visited.contains(neighbor)) {
					queue.add(neighbor);
					visited.add(neighbor);
				}
			}
		}

		return -1;
	}

	public void dfs(Map<TreeNode, List<TreeNode>> graph, TreeNode node, TreeNode parent) {
		if (node != null) {
			if (!graph.containsKey(node)) {
				graph.put(node, new LinkedList<TreeNode>());
			}
			if (!graph.containsKey(parent)) {
				graph.put(parent, new LinkedList<TreeNode>());
			}

			graph.get(node).add(parent);
			graph.get(parent).add(node);

			dfs(graph, node.left, node);
			dfs(graph, node.right, node);
		}
	}
}