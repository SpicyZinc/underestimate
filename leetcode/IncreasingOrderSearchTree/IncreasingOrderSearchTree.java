

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class IncreasingOrderSearchTree {
	public TreeNode increasingBST(TreeNode root) {
		return increasingBST(root, null);
	}

	public TreeNode increasingBST(TreeNode node, TreeNode tail) {
		if (node == null) {
			return tail;
		}

		TreeNode result = increasingBST(node.left, node);
		node.left = null;
		node.right = increasingBST(node.right, tail);

		return result;
	}
}
