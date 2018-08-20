/*
Given a binary tree, count the number of uni-value subtrees.
A Uni-value subtree means all nodes of the subtree have the same value.

Example:
Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5
Output: 4

idea:
https://www.cnblogs.com/grandyang/p/5206862.html
dfs()
note, tree self is subtree of itself
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class CountUnivalueSubtrees {
	public int countUnivalSubtrees(TreeNode root) {
		int[] count = new int[1];
		countUnivalSubtrees(root, count);

		return count[0];
	}

	public void countUnivalSubtrees(TreeNode root, int[] count) {
		if (root == null) {
			return;
		}
		if (isUniValue(root, root.val)) {
			count[0]++;
		}
		// left and right separately call countUnivalSubtrees
		countUnivalSubtrees(root.left, count);
		countUnivalSubtrees(root.right, count);
	}

	private boolean isUniValue(TreeNode node, int val) {
		if (node == null) {
			return true;
		}

		return node.val == val && isUniValue(node.left, val) && isUniValue(node.right, val);
	}
}