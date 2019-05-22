/*
Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
In one move, we may choose two adjacent nodes and move one coin from one node to another.
(The move may be from parent to child, or from child to parent.)
Return the number of moves required to make every node have exactly one coin.

Example 1:
https://assets.leetcode.com/uploads/2019/01/18/tree1.png
Input: [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.

Example 2:
https://assets.leetcode.com/uploads/2019/01/18/tree2.png
Input: [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.

Example 3:
https://assets.leetcode.com/uploads/2019/01/18/tree3.png
Input: [1,0,2]
Output: 2

Example 4:
https://assets.leetcode.com/uploads/2019/01/18/tree4.png
Input: [1,0,0,null,3]
Output: 4

Note:
1<= N <= 100
0 <= node.val <= N

idea:
a dfs helper, return the number of coins given to the parent.
see comments

https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221939/C%2B%2B-with-picture-post-order-traversal
*/

// Definition for a binary tree node.
public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class DistributeCoinsInBinaryTree {
	// the excess number of coins will be the number of moves
	int moveCnt = 0;

	public int distributeCoins(TreeNode root) {
		dfs(root);

		return moveCnt;
	}

	public int dfs(TreeNode node) {
		// not existing node, no own to parents
		if (node == null) {
			return 0;
		}

		int leftOwned = dfs(node.left);
		int rightOwned = dfs(node.right);

		moveCnt += Math.abs(leftOwned) + Math.abs(rightOwned);
		// node should give these number of coins except leave 1 for itself
		return node.val + leftOwned + rightOwned - 1;
	}
}