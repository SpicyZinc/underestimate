/*
Invert a binary tree.

Example:
Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9

Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1


idea:
本质还是 level traversal
1. iterative (queue)
2. recursive
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class InvertBinaryTree {
	// 1, iterative 
	public TreeNode invertTree(TreeNode root) {
		if (root == null || root.left == null && root.right == null) {
			return root;
		}

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();

			if (current.left != null) {
				queue.offer(current.left);
			}
			if (current.right != null) {
				queue.offer(current.right);
			}
			// 开始invert
			TreeNode temp = current.left;
			current.left = current.right;
			current.right = temp;
		}

		return root;
	}

	// 2, recursive
	public TreeNode invertTree(TreeNode root) {
		if (root == null || root.left == null && root.right == null) {
			return root;
		}

		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		
		root.left = right;
		root.right = left;
		
		return root;
	}

	// 2', recursive
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}

		helper(root);
		
		return root;
	}

	private void helper(TreeNode root) {
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		if (root.left != null) {
			helper(root.left);
		}

		if (root.right != null) {
			helper(root.right);
		}
	}
}