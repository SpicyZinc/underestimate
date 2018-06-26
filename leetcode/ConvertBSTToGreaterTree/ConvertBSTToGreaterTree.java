/*
Given a Binary Search Tree (BST), convert it to a Greater Tree
such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
idea:
use stack to in order traversal
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class ConvertBSTToGreaterTree {
	public TreeNode convertBST(TreeNode root) {
		int sum = 0;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode curr = root;

		while (curr != null || !stack.isEmpty()) {
			while (curr != null) {
				stack.push(curr);
				curr = curr.right;
			}
			curr = stack.pop();
			curr.val += sum;
			sum = curr.val;
			curr = curr.left;
		}

		return root;
	}
}