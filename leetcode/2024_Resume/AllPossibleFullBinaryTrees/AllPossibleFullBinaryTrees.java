/*
Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.
Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Example 1:
Input: n = 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

Example 2:
Input: n = 3
Output: [[0,0,0]]

Constraints:
1 <= n <= 20

idea:
dfs + memo

a full binary tree can only have odd number of nodes
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
class AllPossibleFullBinaryTrees {
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode>[] memo = new ArrayList[n + 1];
        return dfs(n, memo);
    }

    public List<TreeNode> dfs(int n, List<TreeNode>[] memo) {
        if (memo[n] != null) {
            return memo[n];
        }

        List<TreeNode> list = new ArrayList<>();
        if (n == 1) {
            list.add(new TreeNode(0));
            return list;
        }

        if (n % 2 == 0) {
            // return the empty list. We cannot form a full binary tree 
            // without n = 1, 3, 5, 7 and so on..
            return list;
        }

        for (int i = 2; i <= n; i += 2) {
            // find list of left and right nodes using odd n.
            // for n = 7 and i = 2 => i-1 = 1 and n-i = 5
            // so, we are finding the list for every odd pair like (1, 5), (3, 3), (5, 1)
            List<TreeNode> left = dfs(i - 1, memo);
            List<TreeNode> right = dfs(n - i, memo);
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    // 这是剩余的那个 node 1 + (i - 1) + (n - i)
                    list.add(new TreeNode(0, leftNode, rightNode));
                }
            }
        }

        return memo[n] = list;
    }
}
