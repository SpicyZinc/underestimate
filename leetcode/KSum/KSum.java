/*
Given n distinct positive integers, integer k (k <= n) and a number target.
Find k numbers where sum is target. Calculate how many solutions there are?

Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].
Return 2.

idea:
dp[i][j][t]
[0-i) array
picked up j values
to sum up to t
*/

public class KSum {
	public int kSum(int[] A, int k, int target) {
		int n = A.length;
		int[][][] dp = new int[n + 1][k + 1][target + 1];
		// initialization
		// only 1 way to reach target 0 using 0 values
		// 什么也不选
		for (int i = 0; i <= n; i++) {
			dp[i][0][0] = 1;
		}

		for (int i = 0; i <= n; i++) {
			// k has to be <= n, otherwise no sense, pick j from i
			for (int j = 1; j <= k && j <= i; j++) {
				for (int t = 1; t <= target; t++) {
					// 如果A[i - 1]本身就大于t 没有必要进行下去了
					if (t >= A[i - 1]) {
						// 之前有多少种方法 就是简单继承
						dp[i][j][t] = dp[i - 1][j - 1][t - A[i - 1]];
					}
					// 不选现在的数 而可以达到 j 个 to sum of t
					dp[i][j][t] += dp[i - 1][j][t];
				}
			}
		}

		return dp[n][k][target];
	}
}