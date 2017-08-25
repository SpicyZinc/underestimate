/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
Note: Given target value is a floating point. You are guaranteed to have only one unique value in the BST that is closest to the target.

idea:
1. recursion
2. iteration

	1000
50       1050
target = 990

easy to understand
http://www.programcreek.com/2014/05/leetcode-closest-binary-search-tree-value-java/
*/
public class ClosestBinarySearchTreeValue {

	// method 1
    public int closestValue(TreeNode root, double target) {
    	TreeNode current = target < root.val ? root.left : root.right;
    	if (current == null) {
    		return root.val;
    	}
    	int closest = closestValue(current, target);
    	return Math.abs(root.val - target) < Math.abs(closest) ? root.val : closest;
    }
	// method 2
    public int closestValue(TreeNode root, double target) {
		int closestVal = root.val;
		while (root != null) {
			closestVal = Math.abs(closestVal - target) > Math.abs(root.val - target) ? root.val : closestVal;
			root = target > closestVal ? root.right : root.left;
		}

		return closestVal;
	}
	// method 3
	double min = Double.MAX_VALUE;
	int closestVal = 0;
	public int closestValue(TreeNode root, double target) {
		helper(root, target);
		return closestVal;
	}

	public void helper(TreeNode node, double target) {
		if (node == null) {
			return;
		}
		if ( Math.abs(node.val - target) < min ) {
			min = Math.abs(node.val - target);
			closestVal = node.val;
		}

		if (target < node.val) {
			helper(node.left, target);
		}
		else (target > node.val) {
			helper(node.right, target);
		}
	}
}
