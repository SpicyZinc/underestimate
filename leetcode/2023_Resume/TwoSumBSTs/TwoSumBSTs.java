/*
Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node in the first tree and a node in the second tree whose values sum up to a given integer target.


Example 1:
https://assets.leetcode.com/uploads/2021/02/10/ex1.png
Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.

Example 2:
https://assets.leetcode.com/uploads/2021/02/10/ex2.png
Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
Output: false

Constraints:
The number of nodes in each tree is in the range [1, 5000].
-10^9 <= Node.val, target <= 10^9

idea:
dfs to fully utilize the binary search tree
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
class TwoSumBSTs {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        return dfs(root1, root2, target);
    }

    public boolean dfs(TreeNode node1, TreeNode node2, int target) {
        if (node1 == null || node2 == null) {
            return false;
        }

        int value = node1.val + node2.val;
        if (value == target) {
            return true;
        }

        if (value > target) {
            return dfs(node1.left, node2, target) || dfs(node1, node2.left, target);
        }

        if (value < target) {
            return dfs(node1.right, node2, target) || dfs(node1, node2.right, target);
        }

        return true;
    }
}
