/*
Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
Example:
Input:
   1
    \
     3
    /
   2
Output: 1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).

idea:
1. in order traversal, strictly use in order traversal will print out sorted array
2. BST, lower and upper bound
I not very understand these two solutions, bst tree left, root, right should have the smallest absolute difference
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class MinimumAbsoluteDifferenceInBST {
	// method 1
    Integer prev = null;
    public int getMinimumDifference(TreeNode root) {
    	int[] min = { Integer.MAX_VALUE };
    	
    	inorder(root, min);
    	return min[0];
    }

    private void inorder(TreeNode node, int[] min) {
    	if (node == null) {
    		return;
    	}
    	inorder(node.left, min);
    	if (prev != null) {
    		min[0] = Math.min(min[0], node.val - prev);
    	}
    	prev = node.val;
    	inorder(node.right, min);
    }
    // method 2
    int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
    	helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    	return min;
    }
    public void helper(TreeNode node, int lower, int upper) {
    	if (node == null) {
    		return;
    	}
    	if (lower != Integer.MIN_VALUE) {
    		min = Math.min(min, node.val - lower);
    	}
    	if (upper != Integer.MAX_VALUE) {
    		min = Math.min(min, upper - node.val);
    	}
    	helper(node.left, lower, node.val);
    	helper(node.right, node.val, upper);
    }
}