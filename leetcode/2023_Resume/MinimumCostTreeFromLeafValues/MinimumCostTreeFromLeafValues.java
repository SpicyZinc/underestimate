/*
Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree, respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node. It is guaranteed this sum fits into a 32-bit integer.

A node is a leaf if and only if it has zero children.


Example 1:
https://assets.leetcode.com/uploads/2021/08/10/tree1.jpg
Input: arr = [6,2,4]
Output: 32
Explanation: There are two possible trees shown.
The first has a non-leaf node sum 36, and the second has non-leaf node sum 32.

Example 2:
https://assets.leetcode.com/uploads/2021/08/10/tree2.jpg
Input: arr = [4,11]
Output: 44

Constraints:
2 <= arr.length <= 40
1 <= arr[i] <= 15

idea:
dp, but lee said not good
somehow need to comeback
*/


class MinimumCostTreeFromLeafValues {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] max = calculateMaxRange(arr);
        int[][] dp = new int[n][n];

        for (int g = 1; g < n; g++) {
            for (int i = 0, j = g; i < n && j < n; i++, j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int leftMax = max[i][k];
                    int rightMax = max[k + 1][j];
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + leftMax * rightMax);
                }
            }
        }

        return dp[0][n - 1];
    }

    public int[][] calculateMaxRange(int[] a) {
        int size = a.length;
        int[][] ans = new int[size][size];
        for (int i = 0; i < size; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < size; j++) {
                max = Math.max(max, a[j]);
                ans[i][j] = max;
            }
        }

        return ans;
    }

    public int mctFromLeafValues(int[] A) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int a : A) {
            while (stack.peek() <= a) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), a);
            }
            stack.push(a);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}
