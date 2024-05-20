/*
Given the root of a binary tree, return the sum of values of nodes with an even-valued grandparent. If there are no nodes with an even-valued grandparent, return 0.
A grandparent of a node is the parent of its parent if it exists.

Example 1:
https://assets.leetcode.com/uploads/2021/08/10/even1-tree.jpg
Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
Output: 18
Explanation: The red nodes are the nodes with even-value grandparent while the blue nodes are the even-value grandparents.

Example 2:
Input: root = [1]
Output: 0

Constraints:
The number of nodes in the tree is in the range [1, 104].
1 <= Node.val <= 100

idea:
dfs
关键是grandparent 祖父 两层
Time complexity: O(n)
Space complexity: O(log n)
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


class SumOfNodesWithEvenValuedGrandparent {
    int sum = 0;

    public int sumEvenGrandparent(TreeNode root) {
        dfs(root);
        return sum;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.val % 2 == 0) {
            if (node.left != null) {
                if (node.left.left != null) {
                    sum += node.left.left.val;
                }
                if (node.left.right != null) {
                    sum += node.left.right.val;
                }
            }

            if (node.right != null) {
                if (node.right.left != null) {
                    sum += node.right.left.val;
                }
                if (node.right.right != null) {
                    sum += node.right.right.val;
                }
            }
        }

        dfs(node.left);
        dfs(node.right);
    }
}
