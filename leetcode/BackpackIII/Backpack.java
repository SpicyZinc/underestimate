/*
Given n kind of items with size Ai and value Vi (each item has an infinite number available) and a backpack with size m.
What's the maximum value can you put into the backpack?

You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 15.

idea:
size j
dp[j] 为前 i 个数填满大小为 j 的背包 的最大值

https://pobenliu.gitbooks.io/leetcode/Backpack%20III.html
*/
public class Backpack {
	public int backPackIII(int[] A, int[] V, int m) {
		if (A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) {
			return 0;
		}

		int[] dp = new int[m + 1];
		for (int i = 0; i < A.length; i++) {
			// each size, try up to m times
			int eachSize = A[i];
			for (int size = 1; size <= m; size++) {
				if (size >= eachSize) {
					// A[i] size is taken, value is V[i]
					dp[size] = Math.max(dp[size], dp[size - eachSize] + V[i]);
				}
			}
		}

		return dp[m];
	}

	//02/09/2019
	public int backPackIII(int[] A, int[] V, int m) {
		if (A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) {
			return 0;
		}

		int[] dp = new int[m + 1];
		dp[0] = 0;
		for (int i = 1; i <= A.length; i++) {
			dp[i] = -1;
		}
		for (int i = 1; i <= A.length; i++) {
			for (int size = 0; size <= m; size++) {
					if (dp[size - A[i- 1] != - 1]) {
					dp[size] = Math.max(dp[size], dp[size - A[i - 1]] + V[i - 1]);
				}
			}
		}

		int result =  0;
	}
}