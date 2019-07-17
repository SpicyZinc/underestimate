/*
You are given coins of different denominations and a total amount of money.
Write a function to compute the number of combinations that make up that amount.
You may assume that you have infinite number of each kind of coin.

Note: You can assume that
0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
 
Example 1:
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
 
Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
 
Example 3:
Input: amount = 10, coins = [10] 
Output: 1

idea:
knapsack problem
dp[i][j] the number of combinations for first i of coins to generate amount j

dfs()
T(sum, i) = T(sum - c[i], i) + T(sum, i + 1)
*/

class CoinChange {
	// dfs 要会
	// dfs + memo === dp
	public int change(int amount, int[] coins) {
		int size = coins.length;

		int[][] memo = new int[size][amount + 1];
		for (int[] a : memo) {
			Arrays.fill(a, -1);
		}

		return dfs(amount, coins, 0, memo);
	}

	public int dfs(int remaining, int[] coins, int pos, int[][] memo) {
		int size = coins.length;

		if (remaining == 0) {
			return 1;
		}
		
		if (remaining < 0 || pos >= size) {
			return 0;
		}

		if (memo[pos][remaining] != -1) {
			return memo[pos][remaining];
		}

		int count = dfs(remaining, coins, pos + 1, memo) + dfs(remaining - coins[pos], coins, pos, memo);

		return memo[pos][remaining] = count;
	}

	public int change(int amount, int[] coins) {
		int size = coins.length;
		int[][] dp = new int[size + 1][amount + 1];
		dp[0][0] = 1;

		for (int i = 1; i <= size; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= amount; j++) {
				// use current coins[i - 1] or not
				dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
			}
		}

		return dp[size][amount];
	}
}