/*
Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
Note that you need to maximize the answer before taking the mod and not after taking it.


Example 1:
https://assets.leetcode.com/uploads/2020/01/21/sample_1_1699.png
Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

Example 2:
https://assets.leetcode.com/uploads/2020/01/21/sample_2_1699.png
Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)

Constraints:
The number of nodes in the tree is in the range [2, 5 * 10^4].
1 <= Node.val <= 10^4

idea:
dfs

*/

class MaximumProductOfSplittedBinaryTree {
    // Fri Mar 10 18:15:12 2023
    List<Long> sum = new ArrayList<>();

    public int maxProduct(TreeNode root) {
        int modval = 1000000007;

        long totalSum = dfs(root);
        long max = 0;
        for (long subSum : sum) {
            long product = subSum * (totalSum - subSum);
            max = Math.max(max, product);
        }

        return (int) (max % modval);
    }

    // get path sum
    long dfs(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            return (long) node.val;
        }

        long sumLeft = dfs(node.left);
        long sumRight = dfs(node.right);

        sum.add(sumLeft);
        sum.add(sumRight);

        return sumLeft + node.val + sumRight;
    }



    // Partially passed because not using long
    List<Integer> sum = new ArrayList<>();

    public int maxProduct(TreeNode root) {
        int modval = 1000000007;

        int totalSum = dfs(root);
        int max = 0;
        for (int subSum : sum) {
            int product = subSum * (totalSum - subSum);
            max = Math.max(max, product);
        }

        return max % modval;
    }

    // get path sum
    int dfs(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            return node.val;
        }

        int sumLeft = dfs(node.left);
        int sumRight = dfs(node.right);

        sum.add(sumLeft);
        sum.add(sumRight);

        return sumLeft + node.val + sumRight;
    }
}