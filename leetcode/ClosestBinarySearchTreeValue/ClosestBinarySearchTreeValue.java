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
	// iteration
    public int closestValue(TreeNode root, double target) {
		int closestVal = root.val;
		while (root != null) {
			closestVal = Math.abs(closestVal - target) > Math.abs(root.val - target) ? root.val : closestVal;
			root = target > closestVal ? root.right : root.left;
		}

		return closestVal;
	}
	// recursion
	double min = Double.MAX_VALUE;
	int closestVal = 0;

	public int closestValue(TreeNode root, double target) {
		dfs(root, target);
		return closestVal;
	}

	public void dfs(TreeNode node, double target) {
		if (node == null) {
			return;
		}

		double diff = Math.abs(node.val - target); 
		if (diff < min) {
			min = diff;
			closestVal = node.val;
		}

		if (target < node.val) {
			dfs(node.left, target);
		} else {
			dfs(node.right, target);
		}
	}
}
