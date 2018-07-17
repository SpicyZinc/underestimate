/*
After robbing those houses on that street, 
the thief has found himself a new place for his thievery so that he will not get too much attention. 
This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. 
Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

idea:
http://blog.csdn.net/xudli/article/details/45886721

this is an extension of HouseRobber, since this is circle,
first element and last element cannot be robbed at the same time, otherwise alarm will be triggered

create the helper method which basically does what House Robber I does
call this method without 1st element once, without last element once

dp normally creates an array of length of n + 1,
of course n works, but you get to deal with dp[1] carefully
*/

public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }
    // rob in a range from i to j inclusive
    public int rob(int[] nums, int i, int j) {
        if (i == j) {
            return nums[i];
        }

        // both working
//         int n = j - i + 1;
//         int[] dp = new int[n];
//         dp[0] = nums[i];
//         dp[1] = Math.max(nums[i], nums[i + 1]);
          
//         for (int k = 2; k < n; k++) {
//             dp[k] = Math.max(dp[k - 2] + nums[i + k], dp[k - 1]);
//         }

//         return dp[n - 1];

        int n = j - i + 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[i];
        for (int k = 2; k <= n; k++) {
            // rob current or not rob current
            dp[k] = Math.max(dp[k - 2] + nums[i + k - 1], dp[k - 1]);
        }

        return dp[n];
    }
}