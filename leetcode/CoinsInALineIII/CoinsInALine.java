/*
There are n coins in a line, and value of i-th coin is values[i].

Two players take turns to take a coin from one of the ends of the line until there are no more coins left.
The player with the larger amount of money wins.

Could you please decide the first player will win or lose?

Example 1:
Input: [3, 2, 2]
Output: true
Explanation: The first player takes 3 at first. Then they both take 2.

Example 2:
Input: [1, 20, 4]
Output: false
Explanation: The second player will take 20 whether the first player take 1 or 4.

Challenge
O(1) memory and O(n) time when n is even.

idea:
need to come back
从 coin line 两头中任意取一个
*/

public class CoinsInALine {
	public boolean firstWillWin(int[] values) {
		int n = values.length;
		if (n == 0) {
			return true;
		}

		int[][] f = new int[n][n];

		// when len 1
		for (int i = 0; i < n; i++) {
			f[i][i] = values[i];
		}
		
		for (int len = 2; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				int j = i + len - 1;
				f[i][j] = Math.max(values[i] - f[i + 1][j], values[j] - f[i][j - 1]);
			}
		}

		return f[0][n - 1] >= 0;
	}
}
