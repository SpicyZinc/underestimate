/*
Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.


Example 1:

Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
Output: 20
Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.

Example 2:


Input: root = [4,3,null,1,2]
Output: 2
Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.

Example 3:
Input: root = [-4,-2,-5]
Output: 0
Explanation: All values are negatives. Return an empty BST.

Constraints:
The number of nodes in the tree is in the range [1, 4 * 10^4].
-4 * 10^4 <= Node.val <= 4 * 10^4

idea:
考察 isValidBST and maxSumBST
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class MaximumSumBSTInBinaryTree {
    // Mon Apr 22 22:24:50 2024
    int maxSum = 0;
    public int maxSumBST(TreeNode root) {
        postorder(root);
        return maxSum;
    }

    private int[] postorder(TreeNode root) {
        if (root == null) {
            return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        // post order -> Left Right Root
        int[] leftTree = postorder(root.left);
        int[] rightTree = postorder(root.right);

        if (leftTree == null ||
            rightTree == null ||
            root.val <= leftTree[1] ||
            root.val >= rightTree[0]
        ) {
            return null;
        }

        int sum = root.val + leftTree[2] + rightTree[2];
        maxSum = Math.max(maxSum, sum);

        int min = Math.min(root.val, leftTree[0]);
        int max = Math.max(root.val, rightTree[1]);

        return new int[] {min, max, sum};
    }

    // direct idea but TLE
    int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        if (root == null) return 0;

        if (isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            maxSum = Math.max(maxSum, sumTree(root));
        }

        maxSumBST(root.left);
        maxSumBST(root.right);

        return maxSum;
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) return true;

        if (root.val <= min || root.val >= max) return false;

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    public int sumTree(TreeNode root) {
        if (root == null) return 0;

        return root.val + sumTree(root.left) + sumTree(root.right);
    }
}