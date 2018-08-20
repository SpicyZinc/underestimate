/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:
Input: [10,5,15,1,8,null,7]
   10 
   / \ 
  5  15 
 / \   \ 
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.

Follow up:
Can you figure out ways to solve it with O(n) time complexity?


idea:

*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}

class LargestBSTSubtree {
	public int largestBSTSubtree(TreeNode root) {

	}

	public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean dfs(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        boolean isLeftValid = dfs(node.left, min, node.val);
        boolean isRightValid = dfs(node.right, node.val, max);
        
        return min < node.val && node.val < max && isLeftValid && isRightValid;
    }
}