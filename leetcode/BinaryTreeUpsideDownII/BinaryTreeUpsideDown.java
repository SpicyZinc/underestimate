/*
Given a binary tree where all the right nodes are
either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
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

idea:
http://blog.csdn.net/whuwangyi/article/details/43186045
http://yuanhsh.iteye.com/blog/2170647

1. recursion, easy to understand
2. iteration, 
node.left = parentRight; (reassign parentRight with new value so keep going)
node.right = parent (reassign parent with new value so keep going)

*/
import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public void print() {
        if (this != null) {
            if ( this.left != null ) {
                this.left.print();
            }
            System.out.print(this.val + " ");
            if ( this.right != null ) {
                this.right.print();
            }
        }
    }
}


public class BinaryTreeUpsideDown {
    public void print(TreeNode node) {
        if (node != null) {
            if (node.left != null) {
                print(node.left);
            }
            System.out.print(node.val + " ");
            if (node.right != null) {
                print(node.right);
            }
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.print();
        System.out.println();
        
        BinaryTreeUpsideDown eg = new BinaryTreeUpsideDown();
        TreeNode newRoot = eg.upsideDownBinaryTree(root);
        newRoot.print();
        System.out.println();
    }

    // recursive
    // when print, stack over flow
    // public TreeNode upsideDownBinaryTree(TreeNode root) {
    //     if ( root == null ) {
    //         return root;
    //     }
    //     TreeNode parent = root;
    //     TreeNode left = root.left;
    //     TreeNode right = root.right;

    //     if ( left != null ) {
    //         TreeNode rest = upsideDownBinaryTree(left);
    //         left.left = right;
    //         left.right = parent;
    //         return rest;
    //     }
    //     return root;
    // }
    // iterative
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if ( root == null ) {
            return null;
        }

        TreeNode node = root;
        TreeNode parent = null;
        TreeNode parentRight = null;

        while (node != null) {
            TreeNode left = node.left;

            node.left = parentRight;
            parentRight = node.right;
 
            node.right = parent;
            parent = node;

            node = left;
        }

        return parent;
    }

    public TreeNode reverseTree(TreeNode root) {
        TreeNode p = null, c = root;
        while (c != null) {
            TreeNode t1 = c.left, t2 = c.right;
            c.left = t2; 
            c.right = p; 
            p = c; 
            c = t1;
        }
        TreeNode result = p;
        while (p.right != null) {
            p.left = p.right.left;
            p.right.left = null;
            p = p.right;
        }

        return result;
    }
}

