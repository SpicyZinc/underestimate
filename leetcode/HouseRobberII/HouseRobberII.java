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

public class HouseRobberII {
    public int rob(int[] nums) {
        if ( nums.length == 0 || nums == null ) {
    		return 0;
    	}
    	if (nums.length == 1) {
    		return nums[0];
    	}
        if (nums.length == 2) {
        	return Math.max(nums[0], nums[1]);
        }

        return Math.max( robInLine(nums, 0, nums.length - 2), robInLine(nums, 1, nums.length - 1) );
    }

    public int robInLine(int[] nums, int start, int end) {
    	// int n = e - s + 1;
     //    int[] d = new int[n];
     //    d[0] = nums[s];
     //    d[1] = Math.max(nums[s], nums[s+1]);
          
     //    for (int i=2; i<n; i++) {
     //        d[i] = Math.max(d[i-2]+nums[s+i], d[i-1]);
     //    }
     //    return d[n-1];

    	int n = end - start + 1;
    	int[] dp = new int[n + 1];
    	dp[0] = 0;
    	dp[1] = nums[start];

    	for (int i = 2; i <= n; i++) {
    		dp[i] = Math.max( dp[i - 2] + nums[start + i - 1], dp[i-1] );
    	}

    	return dp[n];
    }
}