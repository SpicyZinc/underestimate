/*
Binary Tree Inorder Traversal

idea:
recursion
iteration
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeInorderTraversal {
	// recursion
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        inorderTraversal(root, ret);

        return ret;
    }

    public void inorderTraversal(TreeNode root, List<Integer> ret) {
    	if (root != null) {
    		if (root.left != null) {
    			inorderTraversal(root.left, ret);
    		}
    		ret.add(root.val);
    		if (root.right != null) {
    			inorderTraversal(root.right, ret);
    		}
    	}
    }

    // iteration
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
        	return ret;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
		
		while (current != null || !stack.isEmpty()) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				current = stack.pop();
				ret.add(current.val);
				current = current.right;
			}
		}

		return ret;
    }
}