/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. 
Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
*/
   
class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode(int value) {
        this.value = value; 
    }
}

public class BinaryTreeUpsideDown {
    TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode temp = null;
        TreeNode newRoot = null;
        temp = buildUpsideDownBT(root, newRoot);
        return newRoot;
    }
    
    TreeNode buildUpsideDownBT(TreeNode root, TreeNode newRoot) {
        if (!root) {
            return root; 
        }
        if (!root.left && !root.right) {
            newRoot = root;
            return root;
        }
        TreeNode parent = buildUpsideDownBT(root.left, newRoot);
        parent.left = root.right;
        parent.right = root;
        root.left = null;
        root.right = null;

        return parent.right;
    }
}