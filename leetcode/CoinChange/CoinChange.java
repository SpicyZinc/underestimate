/*
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

idea:
https://leetcode.com/discuss/76217/java-both-iterative-recursive-solutions-with-explanations
dynamic programming
1. recursion
count[remainder] is array to memorize the minimum number of coins to sum up to remainder
this array will be passed into the helper function(coins[], remainder, count[])

2. iteration
*/

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
	    if (amount < 1) {
	    	return 0;
	    }
	    int[] count = new int[amount];
	    return helper(coins, amount, count);
	}

	// remainder: remaining coins after the last step
	// count[remainder]: minimum number of coins to sum up to remainder
	private int helper(int[] coins, int remainder, int[] count) { 
	    if (remainder < 0) {
	    	return -1;
	    }
	    if (remainder == 0) {
	    	return 0;
	    }
	    if (count[remainder - 1] != 0) {
	    	return count[remainder - 1];
	    }
	    int min = Integer.MAX_VALUE;
	    for (int coin : coins) {
	        int res = helper(coins, remainder - coin, count);
	        if (res >= 0 && res < min) {
	            min = res + 1;
	        }
	    }
	    count[remainder - 1] = (min == Integer.MAX_VALUE) ? -1 : min;

	    return count[remainder - 1];
	}
	// iterative
	public int coinChange(int[] coins, int amount) {
	    if (amount < 1) {
	    	return 0;
	    }
	    int[] dp = new int[amount + 1];
	    int sum = 1;
	    while (sum <= amount) {
	    	// sentinel as -1
	        int min = -1;
	        for (int coin : coins) {
	            if (sum >= coin && dp[sum - coin] != -1) {
	                int temp = dp[sum - coin] + 1;
	                min = min < 0 ? temp : Math.min(temp, min);
	            }
	        }
	        dp[sum] = min;
	        sum++;
	    }

	    return dp[amount];
	}
}