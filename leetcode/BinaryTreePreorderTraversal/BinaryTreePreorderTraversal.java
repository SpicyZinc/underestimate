/*
idea:
recursion
iteration

*/

// Definition for binary tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreePreorderTraversal {
	// recursion
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if (root == null) {
        	return ret;
        }
        preorderTraversal(root, ret);

        return ret;
    }

    public void preorderTraversal(TreeNode root, List<Integer> result) {
    	result.add(root.val);
    	if (root.left != null) {
    		preorderTraversal(root.left, result);
    	}

    	if (root.right != null) {
    		preorderTraversal(root.right, result);
    	}
    }
    // iteration
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> ret = new ArrayList<Integer>();
    	Stack<TreeNode> stack = new Stack<TreeNode>();

    	if (root == null) {
    		return ret;
    	}

    	stack.push(root);

    	while (!stack.isEmpty()) {
    		TreeNode current = stack.pop();
    		ret.add(current.val);
    		// attention, push right first
    		if (current.right != null) {
    			stack.push(current.right);
    		}
    		// then push left
    		if (current.left != null) {
    			stack.push(current.left);
    		}
    	}

    	return ret;
    }
}