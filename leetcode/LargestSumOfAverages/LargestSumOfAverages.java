/*
We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group.
What is the largest score we can achieve?

Note that our partition must use every number in A, and that scores are not necessarily integers.

Example:
Input: 
A = [9,1,2,3,9]
K = 3
Output: 20
Explanation: 
The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned A into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse. 

Note:
1 <= A.length <= 100.
1 <= A[i] <= 10000.
1 <= K <= A.length.
Answers within 10^-6 of the correct answer will be accepted as correct.

idea:
dp[i][j] [0, ..., i + 1] split into j groups, sum of averages
need to go back visit
*/

class LargestSumOfAverages {
	public double largestSumOfAverages(int[] A, int K) {
		int n = A.length;
		if (K == 0 || n == 0) {
			return 0;
		}

		double[][] dp = new double[n][K + 1];
		double[] sum = new double[n + 1];

		for (int i = 1; i <= n; i++) {
			sum[i] = sum[i - 1] + A[i - 1];
			dp[i - 1][1] = sum[i] / i;
		}

		for (int j = 2; j <= K; j++) {
			for (int i = 0; i < n; i++) {
				double maxSum = Double.MIN_VALUE;
				for (int g = 0; g < i; g++) {
					double sumAverages = dp[g][j - 1] + (sum[i + 1] - sum[g + 1]) / (i - g);
					maxSum = Math.max(maxSum, sumAverages);
				}
				dp[i][j] = maxSum;
			}
		}

		return dp[n - 1][K];
	}
}