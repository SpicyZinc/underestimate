/*
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9

to
     4
   /   \
  7     2
 / \   / \
9   6 3   1


idea:
1. iterative (queue)
2. recursive
*/


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class InvertBinaryTree {
	// 1. iterative 
    public TreeNode invertTree(TreeNode root) {
        if ( root == null ) {
        	return root;
        }
        if ( root.left == null && root.right == null ) {
        	return root;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        while ( !q.isEmpty() ) {
        	TreeNode current = q.poll();
        	if ( current.left != null ) {
        		q.offer(current.left);
        	}
        	if ( current.right != null ) {
        		q.offer(current.right);
        	}

        	TreeNode temp = current.left;
        	current.left = current.right;
        	current.right = temp;
        }
        
        return root;
    }

    // 2. recursive
    public TreeNode invertTree(TreeNode root) {
        if ( root == null ) {
        	return root;
        }
        helper(root);
        
        return root;
    }

    private void helper(TreeNode root) {
    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;

    	if ( root.left != null ) {
    		helper(root.left);
    	}

    	if ( root.right != null ) {
    		helper(root.right);
    	}
    }

    // self
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        if (root.left == null && root.right == null) {
            return root;
        }
        
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        root.left = right;
        root.right = left;
        
        return root;
    }
}