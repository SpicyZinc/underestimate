/*
Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.


Example 1:
https://assets.leetcode.com/uploads/2020/11/09/tmp-tree.jpg
Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.

Example 2:
https://assets.leetcode.com/uploads/2020/11/09/tmp-tree-1.jpg
Input: root = [1,null,2,null,0,3]
Output: 3

Constraints:
The number of nodes in the tree is in the range [2, 5000].
0 <= Node.val <= 105

idea:
dfs
保证了 是 ancestor 和 child
dfs(当前最大最小值 和 left right's value)
*/

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class MaximumDifferenceBetweenNodeAndAncestor {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(11);

        MaximumDifferenceBetweenNodeAndAncestor eg = new MaximumDifferenceBetweenNodeAndAncestor();
        int diff = eg.maxAncestorDiff(root);

        System.out.println("diff == " + diff);
    }

    public int maxAncestorDiff(TreeNode root) {
        int[] maxDiff = new int[1];
        maxDiff[0] = Integer.MIN_VALUE;
        dfs(root, root.val, root.val, maxDiff);

        return maxDiff[0];
    }
    
    public void dfs(TreeNode node, int min, int max, int[] maxDiff) {
        if (node == null) {
            return;
        }

        int maxSoFar = Math.max(max, node.val);
        int minSoFar = Math.min(min, node.val);

        System.out.println("maxSoFar == " + maxSoFar);
        System.out.println("minSoFar == " + minSoFar);

        dfs(node.left, minSoFar, maxSoFar, maxDiff); // 3 8 8
        dfs(node.right, minSoFar, maxSoFar, maxDiff); // 11 8 8
        
        maxDiff[0] = Math.max(maxDiff[0], maxSoFar - minSoFar);
        System.out.println("max diff = " + maxDiff[0]);
        System.out.println("======");
    }
}
