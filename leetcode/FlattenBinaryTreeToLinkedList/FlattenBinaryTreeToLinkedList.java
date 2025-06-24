/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

        1
       / \
      2   5
     / \   \
    3   4   6
    
The flattened tree should look like:
    1 
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6           

Hint:
If you notice carefully in the flattened tree, each node’s right child points to the next node of a pre-order traversal.

idea:
http://n00tc0d3r.blogspot.com/2013/03/flatten-binary-tree-to-linked-list-in.html

in the flattened tree, 
each node's right child points to the next node of a pre-order traversal result (which is a linked list).
->  ->  ->  ->  ->  ->

note, after flatten, result is still a tree with only "right" pointer existing looks like a linked list, but not a list

1. recursion
1) if current node is null or leaf, return it.
2) get the left child and right child of current node, 
    2.1) if the left is not null, set the left as the current node's right, flat the left tree.
    return the tail of left child tree as current node.
    2.2) if the right is not null, set the right as the current node's right, flat the right tree.
    return the tail of left child tree as current node.
         
return root
*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) { 
        val = x; 
    }

    public void preOrder() {
        preOrder(this);
    }

    public void preOrder(TreeNode root) {
        System.out.print(root.val + " ");
        if (root.left != null) {
            preOrder(root.left);
        }
        if (root.right != null) {
            preOrder(root.right);
        }
    }
}

public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList eg = new FlattenBinaryTreeToLinkedList();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        
        root.preOrder();
        eg.flatten(root);
        root.preOrder();
    }
    // flatten left and right subtree and paste each sublist to the right child of the root. (don't forget to set left child to null)
    // Mon Jul 15 23:05:45 2019
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        // 彻底消灭掉 left
        root.left = null;


        // 经过测试 这两个放哪里位置无所谓
        flatten(left);
        flatten(right);

        root.right = left;

        TreeNode current = root;
        // 走 right 走到底
        while (current.right != null) {
            current = current.right;
        }
        // 然后再连起来
        current.right = right;
    }

    // method 1
    public void flatten(TreeNode root) {
        root = dfs(root);
    }
    
    // Mon Jul 15 22:47:54 2019
    public TreeNode dfs(TreeNode root) {  
        if (root == null) {
            return root;
        }

        TreeNode right = root.right;

        if (root.left != null) {
            root.right = root.left;
            root.left = null;

            root = dfs(root.right);  
        }

        if (right != null) {
            root.right = right;

            root = dfs(root.right);  
        }

        return root;
    }
    
    // iterative without stack
    public void flatten(TreeNode root) {     
        TreeNode current = root;
        TreeNode right = null;
        TreeNode left = null;
        
        while (current != null) {    
            if (current.left != null) {
                left = current.left;
                TreeNode tmp = left;
                
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                
                if (current.right != null) {
                    right = current.right;
                }            
                tmp.right = right;
                
                current.right = left;
                current.left = null;
                
                right = null;
            }
            
            current = current.right;
        }
    }
    
    // iterative with stack
    // go down through the left, when right is not null, push right to stack.
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
            
        Stack stack = new Stack();
        stack.push(root);
        TreeNode lastNode = null;
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (lastNode != null) {
                lastNode.left = null;
                lastNode.right = node;
            }

            lastNode = node;
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
        }
    }
}