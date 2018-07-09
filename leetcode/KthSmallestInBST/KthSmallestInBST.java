/*
Kth Smallest Element in a BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?

Hint:
Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).

idea:
http://algorithms.tutorialhorizon.com/inorder-traversal-non-recursive-approach/
in-order == left - root - right

1. queue to in-order traverse a BST, count k times, recursively
use List interface, better, can use get()
2. must remember how to use stack to in-order traverse BST
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class KthSmallestInBST {
    // self 07/08/2018
    public int kthSmallest(TreeNode root, int k) {
        List<TreeNode> queue = new LinkedList<TreeNode>();
        inorder(root, queue); 
        return queue.get(k - 1).val;
    }
    private void inorder(TreeNode root, List<TreeNode> q) {
        if (root != null) {
            inorder(root.left, q);
            q.add(root);
            inorder(root.right, q);
        }
    }
    // method 1
    public int kthSmallest(TreeNode root, int k) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        inorder(root, q);

        TreeNode result = null;
        while (k != 0) {
            result = q.poll();
            k--;
        }

        return result.val;
    }
    private void inorder(TreeNode root, Queue<TreeNode> q) {
        if (root != null) {
            inorder(root.left, q);
            q.offer(root);
            inorder(root.right, q);
        }
    }
    // method 2
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        int result = 0;
     
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            }
            else {
                TreeNode t = stack.pop();
                p = t.right;
                k--;
                if (k == 0) {
                    result = t.val;
                }
            }
        }
     
        return result;
    }
}
