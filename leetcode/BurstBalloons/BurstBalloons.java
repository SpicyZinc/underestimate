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

dp[l][r], the maximum coins obtained to burst balloon from l + 1 to r - 1; l, r exclusive, l - r = k
*/

public class BurstBalloons {
	// 02/04/2019
	public int maxCoins(int[] nums) {
        int n = nums.length;
        
        int[] copy = new int[n + 2];
        
        copy[0] = 1;
        for (int i = 0; i < n; i++) {
            copy[i + 1] = nums[i];
        }
        copy[n + 1] = 1;
        
        int m = n + 2;
        int[][] dp = new int[m][m];
        
        for (int span = 2; span < m; span++) {
            for (int left = 0; left + span < m; left++) {
                int right = left + span;
                
                for (int mid = left + 1; mid <= right - 1; mid++) {
                    int value = dp[left][mid] + copy[left] * copy[mid] * copy[right] + dp[mid][right];
                    dp[left][right] = Math.max(dp[left][right], value);
                }
            }
        }
        
        return dp[0][m - 1];
    }

    public int maxCoins(int[] nums) {
        int[] copy = new int[nums.length + 2];        
        for (int i = 0; i < copy.length; i++) {
            if (i == 0 || i == copy.length - 1) {
                copy[i] = 1;
            } else {
                copy[i] = nums[i - 1];
            }
        }
        
        int n = copy.length;
        int[][] dp = new int[n][n];

        for (int span = 2; span < n; span++) {
            for (int left = 0; left + span < n; left++) {
                int right = left + span;
                // left, right are exclusive
                for (int mid = left + 1; mid <= right - 1; mid++) {
                    // left, left + 1, ..., mid - 1, mid, mid + 1, ..., right - 1, right
                    // burst [left + 1, mid - 1] and [mid + 1, right - 1]
                    // the rest is left, mid, right, then burst mid
                    int value = dp[left][mid] + copy[left] * copy[mid] * copy[right] + dp[mid][right];

                    dp[left][right] = Math.max(dp[left][right], value);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}