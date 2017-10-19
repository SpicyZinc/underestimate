/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note: 
(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

Example:
Given [3, 1, 5, 8]
Return 167

    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

idea:
when to use DP:
solve the big problem from small subproblems.
It is clear that the current result relies on the previous steps.

first build a new array with two extra 1's at both ends of the original array to facilitate the calculation
the base case there is one balloon in the array, which is the span of k = 2

e.g., 0, 1, 2,
k = 2, left = 0, right = 2
left + k = right
start from left + 1, < right

dp[l][r], the maximum coins to burst balloon from l to r; l, r exclusive, l - r = k
*/

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int m = nums.length;
        // copy version of nums with new front and end element as 1
        int[] copy = new int[m + 2];
        for (int i = 0; i < m; i++) {
            copy[i + 1] = nums[i];
        }
        copy[0] = copy[m + 1] = 1;

        int n = copy.length;
        int[][] dp = new int[n][n];
        for (int span = 2; span < n; span++) {
            for (int left = 0; left < n - span; left++) {
                int right = left + span;
                for (int mid = left + 1; mid < right; mid++) {
                    dp[left][right] = Math.max(dp[left][right], dp[left][mid] + copy[left] * copy[mid] * copy[right] + dp[mid][right]);
                }
            }
        }

        return dp[0][n - 1];
    }
}