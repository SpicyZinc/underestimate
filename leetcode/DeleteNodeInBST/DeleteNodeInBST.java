/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST.
Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:
Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:
root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].
    5
   / \
  2   6
   \   \
    4   7

idea:
1. recursive
note: node has both left and right
find the minimum value in the right subtree, set that value to the currently found node,
then the problem turns into recursively deleting the minimum value in the right subtree
2. iterative
https://discuss.leetcode.com/topic/67962/iterative-solution-in-java-o-h-time-and-o-1-space/2 
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class DeleteNodeInBST {
	// Mon Jun  3 00:46:24 2019
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);    
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // why
            // after deletion of root
            // the most left in right part will be the one smallest bigger than root value
            // find it and replace root value with it
            TreeNode minOfRightTree = findMinNode(root.right);
            root.val = minOfRightTree.val;
            root.right = deleteNode(root.right, root.val);
        }

        return root;
    }

    // find the most left (the minimum) node in BST, keep left will find the minimum node
    private TreeNode findMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}