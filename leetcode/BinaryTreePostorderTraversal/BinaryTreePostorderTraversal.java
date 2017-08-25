/*
idea:
http://blog.csdn.net/ljphhj/article/details/21369053
*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreePostorderTraversal {
	// recursion
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        postOrder(root, ret);
        
        return ret;
    }
    
    private void postOrder(TreeNode root, List<Integer> ret) {
        if (root != null) {
            postOrder(root.left, ret);
            postOrder(root.right, ret);
            ret.add(root.val);
        }
    }

    // iteration
    public List<Integer> postorderTraversal(TreeNode root) {
    	List<Integer> ret = new ArrayList<Integer>();
    	if (root == null) {
    		return ret;
    	}

    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	stack.push(root);

    	while (!stack.isEmpty()) {
    		TreeNode top = stack.peek();

    		if (top.left == null && top.right == null) {
    			ret.add(top.val);
    			stack.pop();
    		}
    		if (top.left != null) {
    			stack.push(top.left);
    			top.left = null;
    			continue;
    		}
    		if (top.right != null) {
    			stack.push(top.right);
    			top.right = null;
    			continue;
    		}
    	}

    	return ret;
    }
}