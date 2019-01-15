/*
A binary tree is univalued if every node in the tree has the same value.
Return true if and only if the given tree is univalued.

Example 1:
https://assets.leetcode.com/uploads/2018/12/28/unival_bst_1.png
Input: [1,1,1,1,1,null,1]
Output: true

Example 2:
https://assets.leetcode.com/uploads/2018/12/28/unival_bst_2.png
Input: [2,2,2,5,2]
Output: false

Note:
The number of nodes in the given tree will be in the range [1, 100].
Each node's value will be an integer in the range [0, 99].

idea:
recursion
*/

// Definition for a binary tree node.
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class UnivaluedBinaryTree {
	public boolean isUnivalTree(TreeNode root) {
		return isUnivalTree(root, root.val);
	}

	public boolean isUnivalTree(TreeNode node, int val) {
		if (node == null) {
			return true;
		}

		return node.val == val && isUnivalTree(node.left, val) && isUnivalTree(node.right, val);
	}

	// method 2
	public boolean isUnivalTree(TreeNode root) {
		boolean isLeftUnivalTree = root.left == null || (root.val == root.left.val && isUnivalTree(root.left));
		boolean isRightUnivalTree = root.right == null || (root.val == root.right.val && isUnivalTree(root.right));

		return isLeftUnivalTree && isRightUnivalTree;
	}
}