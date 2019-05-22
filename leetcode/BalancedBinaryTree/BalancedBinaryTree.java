/*
a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1

idea:
height-balanced binary tree  => 最多相差一

Depth of a node: depth from root, count how many edges
Height of a node: longest path to leaf

The depth is equal to the integer part of log2(n) where n is the number of nodes on the balanced tree.
balanced tree with 5 nodes is log2(5) = 2.32
	*
   / \
  *   *
 / \
*   *
<= 1 is OK
We can say a subtree depth, meaning from leaf to root  

Attention:
Math.abs()
Math.max()
*/

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;
	TreeNode(int value) {
		this.value = value; 
	}
}

public class BalancedBinaryTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.left.left.left = new TreeNode(8);
		
		BalancedBinaryTree aTest = new BalancedBinaryTree();
 
	    if (aTest.isBalanced(root)) {
	    	System.out.print("Tree is balanced");
	    } else {
	    	System.out.printf("Tree is not balanced"); 
	    }
	}

	public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDepth = getMaxDepth(root.left);
        int rightDepth = getMaxDepth(root.right);
        
        int diff = Math.abs(leftDepth - rightDepth);
        
        if (diff > 1) {
            return false;
        }
        // not only from the general, going finer, see if left or right subtree also balanced
        return diff <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        return 1 + Math.max(getMaxDepth(node.left), getMaxDepth(node.right));
    }
}
