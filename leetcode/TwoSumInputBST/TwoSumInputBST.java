/*
Given a Binary Search Tree and a target number,
return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9
Output: True

Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7
Target = 28
Output: False

idea:
use hashset to see if existing (target - val) before
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

public class TwoSumInputBST {
	public boolean findTarget(TreeNode root, int k) {
		return dfs(root, k, new HashSet<Integer>());
	}

	public boolean dfs(TreeNode node, int k, Set<Integer> hs) {
		if (node == null) {
			return false;
		}
		if (hs.contains(k - node.val)) {
			return true;
		}
		
		hs.add(node.val);

		return dfs(node.left, k, hs) || dfs(node.right, k, hs);
	}
}