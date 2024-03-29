/*
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node.
If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7
Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.

Example 2:
Input: 
    2
   / \
  2   2
Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

idea: DFS
note: If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
if two sub-nodes equal, continue the dfs to getSecondMin
if not equal, return Math.max(two sub-nodes) vs dfs() smaller one
use Integer.MAX_VALUE as an flag to see if there is second min or not
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class SecondMinimumNodeInABinaryTree {
    public int findSecondMinimumValue(TreeNode root) {
    	if (root == null || root.left == null && root.right == null) {
    		return -1;
    	}
        int secondMin = getSecondMin(root);
    	return secondMin == Integer.MAX_VALUE ? -1 : secondMin;
    }

    public int getSecondMin(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
    		return Integer.MAX_VALUE;
    	}

        int left = getSecondMin(root.left);
        int right = getSecondMin(root.right);
        int min = Math.min(left, right);        
    	if (root.left.val != root.right.val) {
    		return Math.min(Math.max(root.right.val, root.left.val), min);
    	} else {
    		return min;
    	}
    }
}