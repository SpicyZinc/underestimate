/*
Given preorder and inorder traversal of a tree, construct the binary tree.

idea:
in-order:   4 2 5  (1)  6 7 3 8
pre-order: (1) 4 5 2  6 7 8 3  

note: recursion
calculate start and end for inorder and preorder
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ConstructBinaryTreefromPreorderInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int inStart = 0;
        int inEnd = inorder.length-1;
        int preStart = 0;
        int preEnd = preorder.length-1;
 
        return buildTree(inorder, inStart, inEnd, preorder, preStart, preEnd);
    }
 
    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] preorder, int preStart, int preEnd) {
        if (inStart > inEnd || preStart > preEnd) {
        	return null;
        }
 
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);
 
        int k = 0;
        for (int i=0; i<inorder.length; i++) {
            if (inorder[i] == rootValue) {
                k = i;
                break;
            }
        }
 
        root.left = buildTree( inorder, inStart, k-1, preorder, preStart+1, preStart + k - inStart );
        root.right = buildTree( inorder, k+1, inEnd, preorder, preStart + k + 1 - inStart, preEnd );
 
        return root;
    }
}