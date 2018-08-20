/*
Given a binary tree, find the length of the longest path where each node in the path has the same value.
This path may or may not pass through the root.
Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:
Input: 
              5
             / \
            4   5
           / \   \
          1   1   5
Output:
2

Example 2:
Input: 
              1
             / \
            4   5
           / \   \
          4   4   5
Output:
2

Note: The given binary tree has not more than 10000 nodes.
The height of the tree is not more than 1000.

idea:
quintessential problem
dfs()
return subtree longest solo value
how ?
recursion + consider current node, then + 1

this is to find consecutive 1's 
如果最后左右连到 root的两个 edge 没有 同样的数字 没有连接起来
不能称其为 UnivaluePath 所以到这个root 点 最大edge数就是0
就是一旦不连续 就是0
返回的值不是 需要的值 是过渡值
similar to consecutive path in tree
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class LongestUnivaluePath {	
	public int longestUnivaluePath(TreeNode root) {
		int[] max = new int[1];
		edgeLength(root, max);

		return max[0];
	}

	public int edgeLength(TreeNode node, int[] max) {
		if (node == null) {
			return 0;
		}

		int leftUniValuePathLen = edgeLength(node.left, max);
		int rightUniValuePathLen = edgeLength(node.right, max);
		
		// 一旦不是univalue, 就是0
		int leftEdges = 0;
		int rightEdges = 0;

		if (node.left != null) {
			if (node.val == node.left.val) {
				leftEdges = leftUniValuePathLen + 1;
			}
		}

		if (node.right != null) {
			if (node.val == node.right.val) {
				rightEdges = rightUniValuePathLen + 1;
			}
		}
		// note, this is addition, not Math.max()
		max[0] = Math.max(max[0], leftEdges + rightEdges);

		return Math.max(leftEdges, rightEdges);
	}
}