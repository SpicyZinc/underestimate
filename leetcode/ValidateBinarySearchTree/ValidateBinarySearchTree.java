/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.

Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.

idea:
1. recursion
note long type, note no node.left == null && node.right == null return true
2. Another solution (if space is not a constraint)
Do an inorder traversal of the tree and store the node values in an array. 
If the array is in sorted order, its a valid BST otherwise not.
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ValidateBinarySearchTree {
	// self
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

	public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    public boolean dfs(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        
        if (min < node.val && node.val < max) {
            return dfs(node.left, min, node.val) && dfs(node.right, node.val, max);
        }
        
        return false;
    }
	
	public static void main(String[] args) {
		ValidateBinarySearchTree eg = new ValidateBinarySearchTree();
		/*
			1
		 2	   3
		4 5	  6 7
		*/
		TreeNode tree1Root = new TreeNode(1);
		tree1Root.left = new TreeNode(2);
		tree1Root.right = new TreeNode(3);
		tree1Root.left.left = new TreeNode(4);
		tree1Root.left.right = new TreeNode(5);
		tree1Root.right.left = new TreeNode(6);
		tree1Root.right.right = new TreeNode(7);
		
		System.out.println("Is tree1Root a valid BST via isValidBST: " + eg.isValidBST(tree1Root));
		/*
				5
			 3	   6
			1 4	  2 7 
		*/
		TreeNode tree2Root = new TreeNode(5);
		tree2Root.left = new TreeNode(3);
		tree2Root.right = new TreeNode(6);
		tree2Root.left.left = new TreeNode(1);
		tree2Root.left.right = new TreeNode(4);
		tree2Root.right.left = new TreeNode(2);
		// tree2Root.left.left.right = new TreeNode(2);
		tree2Root.right.right = new TreeNode(7);
		System.out.println("Is tree2Root a valid BST via isValidBST: " + eg.isValidBST(tree2Root));
		
		/*
				4
			 2	   6
			1 3	  5 7	
		*/
		TreeNode tree3Root = new TreeNode(4);
		tree3Root.left = new TreeNode(2);
		tree3Root.right = new TreeNode(6);
		tree3Root.left.left = new TreeNode(1);
		tree3Root.left.right = new TreeNode(3);
		tree3Root.right.left = new TreeNode(5);
		tree3Root.right.right = new TreeNode(7);
		System.out.println("Is tree3Root a valid BST via isValidBST: " + eg.isValidBST(tree3Root));
	}
}