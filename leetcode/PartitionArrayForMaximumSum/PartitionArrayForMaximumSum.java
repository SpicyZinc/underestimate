/*
Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.
After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.

Example 1:
Input: A = [1,15,7,9,2,5,10], K = 3
Output: 84
Explanation: A becomes [15,15,15,9,10,10,10]

Note:
1 <= K <= A.length <= 500
0 <= A[i] <= 10^6

dp[i] record the maximum sum considering A[0] ~ A[i]
To get dp[i], try to change last k numbers separately to the maximum of them, where k = 1 to k = K.
依次分别 把 后 K 个 num 的每个 num 换成 currMax

dp[i] 表示i位置的最大和
dp[i] = dp[i-j] + max(A[i], ..., A[i-j])*j j-->[0, K-1]
求每个位置的最大值即可, 典型的动态规划

idea:
DP
dp[i] max sum 前 i 个 [0, i - 1]
*/

class PartitionArrayForMaximumSum {
	// Sat Jul  6 21:21:14 2019
	public int maxSumAfterPartitioning(int[] A, int K) {
		int size = A.length;
		int[] dp = new int[size + 1];
		
		for (int i = 0; i < size; i++) {
			int currMax = A[i];
			int cnt = i + 1; // 前 i + 1, [0, i]
			
			for (int j = 0; j < K && cnt - (j + 1) >= 0; j++) {
				int k = j + 1;
				currMax = Math.max(currMax, A[cnt - k]);

				dp[cnt] = Math.max(dp[cnt], dp[cnt - k] + currMax * k);
			}
		}

		return dp[size];
	}

	public int maxSumAfterPartitioning(int[] A, int K) {
		int size = A.length;
		int[] dp = new int[size];

		for (int i = 0; i < size; i++) {
			int currMax = 0;

			for (int k = 1; k <= K && i - k + 1 >= 0; k++) {
				currMax = Math.max(currMax, A[i - k + 1]);

				dp[i] = Math.max(dp[i], (i >= k ? dp[i - k] : 0) + currMax * k);
			}
		}

		return dp[size - 1];
	}
}