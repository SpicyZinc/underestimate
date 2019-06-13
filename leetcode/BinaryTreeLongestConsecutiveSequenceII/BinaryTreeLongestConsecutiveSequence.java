/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
Especially, this path can be either increasing or decreasing.
For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
		1
	   / \
	  2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].

Example 2:
Input:
		2
	   / \
	  1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].

idea:
https://www.cnblogs.com/grandyang/p/5252599.html

recursion, dfs
note, define clearly
from root to child if increase, it is increment
from root to child if decrease, it is decrement

increment = left increment path length + 1 + right decrement path length
decrement = right increment path length + 1 + left decrement path length

recursion return 的不是结果 是帮助用的
[0] 是增长路径长度
[1] 是降低路径长度
[increment, decrement]
真正 return max
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class BinaryTreeLongestConsecutiveSequence {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);

		BinaryTreeLongestConsecutiveSequence eg = new BinaryTreeLongestConsecutiveSequence();
		int max = eg.longestConsecutive(root);

		System.out.println(max);
	}

	public int longestConsecutive(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int[] max = new int[1];
		dfs(root, root, max);

		return max[0];
	}

	// 返回最长递增和递减路径
	public int[] dfs(TreeNode node, TreeNode parent, int[] max) {
		int[] result = new int[2];
		
		if (node == null) {
			return result;
		}

		int[] left = dfs(node.left, node, max);
		int[] right = dfs(node.right, node, max);

		// 最长递增路径长度
		max[0] = Math.max(max[0], left[0] + 1 + right[1]);
		// 最长递减路径长度
		max[0] = Math.max(max[0], right[0] + 1 + left[1]);

		int increment = 0;
		int decrement = 0;

		if (node.val == parent.val + 1) {
			increment = Math.max(left[0], right[0]) + 1;
		} else if (node.val == parent.val - 1) {
			decrement = Math.max(left[1], right[1]) + 1;
		}

		result[0] = increment;
		result[1] = decrement;
		
		return result;
	}
}