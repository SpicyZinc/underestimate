/*
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected 
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

idea:
a very typical 1d DP
dp[i] indicates the max value when ith room gets rubbed
dp[0] = 0 indicates when 0th room gets rubbed
dp[1] = nums[0] indicates when 1st room gets rubbed

for any dp[i], 2 choices, max these two choices
1. ith value included, dp[i-2] + nums[i-1] (i-1 means reaching ith room selected)
dp[1] = nums[0], 1-1 = 0 (i == 1)
2. ith value not included, dp[i-1]

Time Complexity O(n)
Space Complexity O(n)

Method 2
check even and odd 
get max for current even or odd ith position,
e.g. current even position could be previous even + current even
or previous odd in between previous even and current even
*/
public class HouseRobber {
    // 02/03/2019
    // lintcode version
     public long houseRobber(int[] A) {
        if (A.length == 0 || A == null) {
            return 0;
        }

        int n = A.length;
        long[] dp = new long[n + 1];
        
        dp[0] = 0;
        dp[1] = A[0];
        
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(A[i - 1] + dp[i - 2], dp[i - 1]);
        }
        
        return dp[n];
    }

    public int rob(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }

        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];

        for (int i = 2; i <= nums.length; i++) {
            // ith number not counted, or counted; this ith NOT array indexed
            // rob or not rob current i
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }

        return dp[nums.length];
    }

    // method 2
    public int rob(int[] nums) {
        if ( nums.length == 0 || nums == null ) {
            return 0;
        }

        int a = 0, b = 0;
        for (int i = 0; i < num.length; i++) {
            if (i % 2 == 0) {
                a += num[i];
                a = Math.max(a, b);
            } else {
                b += num[i];
                b = Math.max(a, b);
            }
        }

        return Math.max(a, b);
    }
}