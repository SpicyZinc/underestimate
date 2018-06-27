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
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class LongestUnivaluePath {
	int max = 0;	

	public int longestUnivaluePath(TreeNode root) {
		edgeLength(root);
		return max;
	}

	public int edgeLength(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int leftUniValuePathLen = edgeLength(node.left);
		int rightUniValuePathLen = edgeLength(node.right);
        
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
		max = Math.max(max, leftEdges + rightEdges);

		return Math.max(leftEdges, rightEdges);
	}
}