/*
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.
Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

Example 1:
Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
https://assets.leetcode.com/uploads/2019/03/06/1266.png

Note:
1 <= preorder.length <= 100
The values of preorder are distinct.

idea:
dfs()
第一点是 root
找到 小于 root.val 应用 recursion
找到 大于 root.val 应用 recursion

注意
if (start > end) {
	return null;
}
*/

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class ConstructBinarySearchTreeFromPreorderTraversal {
	public TreeNode bstFromPreorder(int[] preorder) {
		return dfs(preorder, 0, preorder.length - 1);
	}

	public TreeNode dfs(int[] preorder, int start, int end) {
		if (start > end) {
			return null;
		}

		int val = preorder[start];

		int i = start + 1;
		for (; i <= end; i++) {
			// 右半个树找到了 break
			if (preorder[i] > val) {
				break;
			}
		}

		TreeNode root = new TreeNode(val);
		root.left = dfs(preorder, start + 1, i - 1);
		root.right = dfs(preorder, i, end);

		return root;
	}
}
