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
1. recursion
count[remaining] is array to memorize the minimum number of coins to sum up to remaining
this array will be passed into the helper function(coins[], remaining, count[])
*/

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
	    if (amount < 1) {
	    	return 0;
	    }

	    int[] count = new int[amount];
	    return dfs(coins, amount, count);
	}

	// remaining: remaining amount after the last step
	// count[remaining]: minimum number of coins to sum up to remaining
	private int dfs(int[] coins, int remaining, int[] count) { 
	    if (remaining < 0) {
	    	return -1;
	    }
	    if (remaining == 0) {
	    	return 0;
	    }
	    if (count[remaining - 1] != 0) {
	    	return count[remaining - 1];
	    }

	    int minCnt = Integer.MAX_VALUE;
	    // 可以重复用同一个coin
	    for (int coin : coins) {
	        int cnt = dfs(coins, remaining - coin, count);
	        if (cnt >= 0 && cnt < minCnt) {
	            minCnt = cnt + 1;
	        }
	    }
	    count[remaining - 1] = (minCnt == Integer.MAX_VALUE) ? -1 : minCnt;

	    return count[remaining - 1];
	}
}