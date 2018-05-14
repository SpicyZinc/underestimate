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
        int preStart = 0;
        int preEnd = preorder.length - 1;
        
        int inStart = 0;
        int inEnd = inorder.length - 1;
        
        return buildTree(preorder, preStart, preEnd, inorder, inStart, inEnd);
    }
    
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd ) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);
        
        // find the root in inorder
        int k = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                k = i;
                break;
            }
        }
        // use in order, get length of left tree, this length then can be used to where to stop at pre_order
        root.left = buildTree(preorder, preStart + 1, preStart + (k - inStart), inorder, inStart, k - 1);
        root.right = buildTree(preorder, preStart + (k - inStart) + 1, preEnd, inorder, k + 1, inEnd);
        
        return root;
    }
}