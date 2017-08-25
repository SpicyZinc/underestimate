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
solve the big problem from small subproblem.
It is clear that the current result relies on the previous steps.

first build a new array with two extra 1's at both ends of the original array to facilitate the calculation
the base case there is one balloon in the array, which is the span of k = 2
gradually, k = 3

0 1 2 3 4 5 6 7 8 9
-------
  -------
    -------
      -------
        -------
          -------
            -------
l starts at index 0
at most goes to index 6

dp[l][r] l - r = k; l, r exclusive
the maximum coins to burst balloon from l to r, l, r not included
*/

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int[] copy = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            copy[i+1] = nums[i];
        }
        copy[0] = copy[nums.length + 1] = 1;
        
        int n = copy.length;
        int[][] dp = new int[n][n];
        for (int k = 2; k < n; k++) {
            for (int l = 0; l < n - k; l++) {
                int r = l + k;
                for (int m = l + 1; m < r; m++) {
                    dp[l][r] = Math.max(dp[l][r], copy[l] * copy[m] * copy[r] + dp[l][m] + dp[m][r]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}