/*
Given a binary tree, return the tilt of the whole tree.
The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values.
Null node has tilt 0.
The tilt of the whole tree is defined as the sum of all nodes' tilt.

Example:
Input: 
         1
       /   \
      2     3
Output: 1
Explanation: 
Tilt of node 2 : 0
Tilt of node 3 : 0
Tilt of node 1 : |2-3| = 1
Tilt of binary tree : 0 + 0 + 1 = 1

Note:
The sum of node values in any subtree won't exceed the range of 32-bit integer.
All the tilt values won't exceed the range of 32-bit integer.

idea:
nothing more to articulate here, just recursion
return value is not what we want, it is just for recursion use,
during the recursion, populate the real value wanted by this question
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeTilt {
    public int findTilt(TreeNode root) {
    	int[] tilt = {0};
    	dfs(root, tilt);
    	return tilt[0];
    }

    public int dfs(TreeNode node, int[] tilt) {
    	if (node == null) {
    		return 0;
    	}
    	int left = 0, right = 0;
    	if (node.left != null) {
    		left = dfs(node.left, tilt);
    	}
    	if (node.right != null) {
    		right = dfs(node.right, tilt);
    	}
    	tilt[0] += Math.abs(left - right);
    	return left + right + node.val;
    }
}