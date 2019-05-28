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
1. brute force
2. recursion with memoization
https://www.cnblogs.com/grandyang/p/5138186.html
3. dp
*/

public class CoinChange {
	// brute force, TLE, 114 / 182 test cases passed
	int minCount = Integer.MAX_VALUE;
	public int coinChange(int[] coins, int amount) {
		Arrays.sort(coins);

		coinChange(coins.length - 1, 0, coins, amount);
		return minCount == Integer.MAX_VALUE ? -1 : minCount;
	}

	private void coinChange(int pos, int currCnt, int[] coins, int remaining) {
		if (remaining < 0) {
			return;
		}

		if (remaining == 0) {
			minCount = Math.min(minCount, currCnt);

			return;
		}

		for (int i = pos; i >= 0; i--) {
			coinChange(i, currCnt + 1, coins, remaining - coins[i]);
		}
	}


	public int coinChange(int[] coins, int amount) {
		if (amount < 1) {
			return 0;
		}

		return dfs(coins, amount, new int[amount]);
	}

	// remaining: remaining amount after the last step
	// count[] length is amount
	// count[amount - 1] last element in array, which is minimum number of coins to sum up to amount
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
			if (cnt >= 0) {
				// 用了一个coin 所以 +1
				minCnt = Math.min(minCnt, cnt + 1);
			}
		}
		count[remaining - 1] = (minCnt == Integer.MAX_VALUE) ? -1 : minCnt;

		return count[remaining - 1];
	}

	// 01/23/2019
	// dp[i] to get amount of i, the min number of coins
	public int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		// note, not forget to initialize with max possible number of coins
		Arrays.fill(dp, amount + 1);
		dp[0] = 0;

		for (int i = 1; i <= amount; i++) {
			for (int coin : coins) {
				if (coin <= i) {
					// +1 is used one coin
					dp[i] = Math.min(dp[i], dp[i - coin] + 1);
				}
			}
		}

		return dp[amount] > amount ? -1 : dp[amount];
	}
}