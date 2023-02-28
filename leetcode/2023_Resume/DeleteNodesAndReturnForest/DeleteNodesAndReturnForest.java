/*
Given the root of a binary tree, each node in the tree has a distinct value.
After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
Return the roots of the trees in the remaining forest. You may return the result in any order.

Example 1:
https://assets.leetcode.com/uploads/2019/07/01/screen-shot-2019-07-01-at-53836-pm.png

Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]

Example 2:
Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]


Constraints:
The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.

idea:
dfs
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
class DeleteNodesAndReturnForest {
    // Thu Feb 23 00:00:30 2023
    // Better understand
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> forrest = new ArrayList<>();
        Set<Integer> toDelete = new HashSet<>();

        for (int i : to_delete) toDelete.add(i);
        if (!dfs(root, toDelete, forrest)) {
            forrest.add(root);
        }

        return forrest;
    }
    // fn to tell is deleted
    boolean dfs(TreeNode node, Set<Integer> toDelete, List<TreeNode> forrest) {
        if (node == null) return true;
        if (dfs(node.left, toDelete, forrest)) node.left = null;
        if (dfs(node.right, toDelete, forrest)) node.right = null;
        if (toDelete.contains(node.val)) {
            if (node.left != null) forrest.add(node.left);
            if (node.right != null) forrest.add(node.right);

            return true;
        }

        return false;
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = new HashSet<>();
        List<TreeNode> result = new ArrayList<>();
        for (int i : to_delete) {
            toDelete.add(i);
        }

        dfs(root, true, toDelete, result);
        return result;
    }

    private TreeNode dfs(TreeNode node, boolean is_root, Set<Integer> toDelete, List<TreeNode> result) {
        if (node == null) return null;
        boolean shouldDelete = toDelete.contains(node.val);
        if (is_root && !shouldDelete) result.add(node);
        node.left = dfs(node.left, shouldDelete, toDelete, result);
        node.right =  dfs(node.right, shouldDelete, toDelete, result);
        return shouldDelete ? null : node;
    }
}

