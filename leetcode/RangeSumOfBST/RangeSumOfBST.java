/*
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
The binary search tree is guaranteed to have unique values.

Example 1: 
Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32

Example 2: 
Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23 

Note:
The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.

idea: not quite understand dfs()
关键就是 当 root.val not in [L, R]
怎么去选继续recursion的参数 是 left 还是 right
*/

// Definition for a binary tree node.
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class RangeSumOfBST {
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) {
			return 0;
		}

		if (root.val > R) {
			return rangeSumBST(root.left, L, R);
		}
		if (root.val < L) {
			return rangeSumBST(root.right, L, R);
		}

		return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
	}

	// method 2
	int cnt = 0;
	public int rangeSumBST(TreeNode root, int L, int R) {
		dfs(root, L, R);
		return cnt;
	}

	public void dfs(TreeNode node, int L, int R) {
		if (node != null) {
			if (L <= node.val && node.val <= R) {
				cnt += node.val;
			}
			if (node.val > L) {
				dfs(node.left, L, R);
			}
			if (node.val < R) {
				dfs(node.right, L, R);
			}
		}
	}
}