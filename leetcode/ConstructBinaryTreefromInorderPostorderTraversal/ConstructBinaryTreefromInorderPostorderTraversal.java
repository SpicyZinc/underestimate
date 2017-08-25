/*
Given inorder and postorder traversal of a tree, construct the binary tree.

idea:
in-order:   4 2 5  (1)  6 7 3 8
post-order: 4 5 2  6 7 8 3  (1)

note:
find postorder array new start index and end index
left sub tree end index + 1 = right sub tree start index

postStart+k-(inStart+1) + 1 = postStart+k-inStart

inStart needs to be considered when calculating the length of left sub tree in postorder, 
otherwise it is wrong 

*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class ConstructBinaryTreefromInorderPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inStart = 0;
        int inEnd = inorder.length-1;
        int postStart =0;
        int postEnd = postorder.length-1;
 
        return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
    }
 
    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
        	return null;
        }
 
        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);
 
        int k = 0;
        for (int i=0; i<inorder.length; i++) {
            if (inorder[i] == rootValue) {
                k = i;
                break;
            }
        }
 
        root.left = buildTree( inorder, inStart, k-1, postorder, postStart, postStart+k-(inStart+1) );
        root.right = buildTree( inorder, k+1, inEnd, postorder, postStart+k-inStart, postEnd-1 );
 
        return root;
    }
}