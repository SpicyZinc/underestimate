/*
Given a binary tree, return the postorder traversal of its nodes' values.

Example:
Input: [1,null,2,3]
   1
	\
	 2
	/
   3
Output: [3,2,1]

Follow up: Recursive solution is trivial, could you do it iteratively?

idea:
http://blog.csdn.net/ljphhj/article/details/21369053
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class BinaryTreePostorderTraversal {
	// recursion
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		postOrder(root, result);
		
		return result;
	}

	private void postOrder(TreeNode root, List<Integer> result) {
		if (root != null) {
			postOrder(root.left, result);
			postOrder(root.right, result);
			result.add(root.val);
		}
	}

	// how to use stack to post order traverse
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();

		if (root == null) {
			return result;
		}

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode top = stack.peek();

			if (top.left == null && top.right == null) {
				result.add(top.val);
				stack.pop();
			}

			if (top.left != null) {
				stack.push(top.left);
				top.left = null;

				continue;
			}
			if (top.right != null) {
				stack.push(top.right);
				top.right = null;

				continue;
			}
		}

		return result;
	}
}