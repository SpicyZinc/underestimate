/*
A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.

Example 1:
Input: root = [1,null,2,null,3,null,4,null,null]
Output: [2,1,3,null,null,null,4]
Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.

Example 2:
Input: root = [2,1,3]
Output: [2,1,3]

Constraints:
The number of nodes in the tree is in the range [1, 10^4].
1 <= Node.val <= 10^5

idea:
1. change to sorted array,
2. sorted array to bst
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

class BalanceABinarySearchTree {
    List<TreeNode> list = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        inorder(root);
        return balanceBST(0, list.size() - 1);
    }

    public TreeNode balanceBST(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;

        TreeNode root = list.get(mid);
        root.left = balanceBST(start, mid - 1);
        root.right = balanceBST(mid + 1, end);

        return root;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        list.add(node);
        inorder(node.right);
    }
}
