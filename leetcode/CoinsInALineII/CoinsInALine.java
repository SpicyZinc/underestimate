/*
There are n coins with different value in a line. Two players take turns to take one or two coins from left side until there are no more coins left.
The player who take the coins with the most value wins.

Could you please decide the first player will win or lose?

If the first player wins, return true, otherwise return false.

Example 1:
Input: [1, 2, 2]
Output: true
Explanation: The first player takes 2 coins.

Example 2:
Input: [1, 2, 4]
Output: false
Explanation: Whether the first player takes 1 coin or 2, the second player will gain more value.

idea:
https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line%20II.java

simpler as below, think as difference
dp[i]为一方在面对a[i..n-1]这些数字时, 能得到的最大的与对手的数字差

*/

public class CoinsInALine {
	public boolean firstWillWin(int[] values) {
		int n = values.length;

		int[] dp = new int[n + 1];
		dp[0] = 0;

		for (int i = n - 1; i >= 0; i--) {
			dp[i] = values[i] - dp[i + 1];
			if (i < n - 1) {
				dp[i] = Math.max(dp[i], values[i] + values[i + 1] - dp[i + 2]);    
			}
		}

		return dp[0] > 0;
	}
}