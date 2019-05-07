/*
Given an array of integers, find a maximum sum of non-adjacent elements.
For example, inputs [1, 0, 3, 9, 2] should return 10 (1 + 9).

idea:
similar to house robber
*/

class MaxSumOfNondjacentElements {
	public static void main(String[] args) {
		MaxSumOfNondjacentElements eg = new MaxSumOfNondjacentElements();
		// int[] A = {1, 0, 3, 9, 2};
		int[] A = {4,1,1, 4, 2, 1};
		int max = eg.getNonadjacentMax(A);

		System.out.println(max);
	}

	public int maxSum(int arr[]) {
		int excl = 0;
		int incl = arr[0];

		for (int i = 1; i < arr.length; i++) {
			int temp = incl;
			incl = Math.max(excl + arr[i], incl);
			excl = temp;
		}

		return incl;
	}


	public int getNonadjacentMax(int[] A) {
		if (A.length == 0 || A == null) {
			return 0;
		}

		int n = A.length;
		int[] dp = new int[n + 1];

		dp[0] = 0;
		dp[1] = A[0];

		for (int i = 2; i <= n; i++) {
			dp[i] = Math.max(A[i - 1] + dp[i - 2], dp[i - 1]);
		}

		return dp[n];
	}
}