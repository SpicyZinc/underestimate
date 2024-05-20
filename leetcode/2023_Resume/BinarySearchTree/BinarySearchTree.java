/*
Given a binary search tree and a new tree node, insert the node into the tree.
You should keep the tree still be a valid binary search tree.

You can assume there is no duplicate values in this tree + node.

Example
Given binary search tree as follow, after Insert node 6, the tree should be:

  2             2
 / \           / \
1   4   -->   1   4
   /             / \ 
  3             3   6

idea:
*/

import java.util.*;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class BinarySearchTree {
    // instance variable
    public TreeNode root;

    // constructor for initialize the root to null BYDEFAULT
    public BinarySearchTree() {
        this.root = null;
    }

    // insert method to insert the new Date
    public void insert(int value) {
        this.root = insert(root, value);
    }

    public TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        } else if (root.val >= value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }

        return root;
    }

    public TreeNode deleteNode(TreeNode node) {
        // check for node initially
        if (root == null) {
            return null;
        } else if (node.data < root.data) {
            // process the left sub tree
            root.left = deleteNode(root.left, node);
        } else if (node.data > root.data) {
            // process the right sub tree
            root.right = deleteNode(root.right, node);
        } else if (root.data == node.data) {
            // case 3: 2 child
            if (root.left != null && root.right != null) {
                int lmax = findMaxData(root.left);
                root.data = lmax;
                root.left = deleteNode(root.left, new TreeNode(lmax));
                return root;
            }
            // case 2: one child
            // case i-> has only left child
            else if (root.left != null) {
                return root.left;
            }
            // case ii-> has only right child
            else if (root.right != null) {
                return root.right;
            }
            // case 1:- no child
            else {
                return null;
            }
        }
        return root;
    }

    public void levelPrint() {
        TreeNode current = this.root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(current);


        List<String> levels = new ArrayList<>();

        while (!queue.isEmpty()) {
            String level = "";
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                int value = node.val;
                level += i == size - 1 ? value : value + " -> ";

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            levels.add(level);

        }

        for (String s : levels) {
            System.out.println(s);
        }
    }

    private boolean search(TreeNode root, int data) {
        if (root == null) {
            return false;
        } else if (root.data == data) {
            return true;
        } else if (root.data > data) {
            return search(root.left, data);
        }
        return search(root.right, data);
    }


    public List<List<Integer>> levelOrder() {
        TreeNode current = this.root;
        List<List<Integer>> levels = new ArrayList<List<Integer>>();

        if (current == null) {
            return levels;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(current);

        while (!q.isEmpty()) {
            List<Integer> layer = new ArrayList<>();
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                layer.add(node.val);

                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null){
                    q.add(node.right);
                }

            }

            levels.add(layer);
        }

        return levels;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        BinarySearchTree bt = new BinarySearchTree();
        bt.root = root;

        bt.levelPrint();

        BinarySearchTree bst = new BinarySearchTree();

        bst.insert(8);
        bst.insert(10);
        bst.insert(3);
        bst.insert(7);
        bst.insert(9);

        bst.levelPrint();
    }
}