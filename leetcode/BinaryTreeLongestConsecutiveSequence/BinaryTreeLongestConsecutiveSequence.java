/*
Given a binary tree, find the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:
Input:
   1
	\
	 3
	/ \
   2   4
		\
		 5

Output: 3
Explanation: Longest consecutive sequence path is 3-4-5, so return 3.

Example 2:
Input:
   2
	\
	 3
	/ 
   2    
  / 
 1

Output: 2 
Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

idea:
1. parent-child, reverse not okay
2. no across parent path
dfs()
*/
class BinaryTreeLongestConsecutiveSequence {
	public int longestConsecutive(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int[] max = new int[1];
		dfs(root, root.val, 0, max);

		return max[0];
	}
	
	public void dfs(TreeNode node, int val, int len, int[] max) {
		if (node == null) {
			return;
		}
		
		if (node.val == val + 1) {
			len += 1;
		} else {
			len = 1;
		}
		
		max[0] = Math.max(max[0], len);

		dfs(node.left, node.val, len, max);
		dfs(node.right, node.val, len, max);
	}
}