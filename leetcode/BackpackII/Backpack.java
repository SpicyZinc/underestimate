/*
Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.

Note
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

idea:
dp[i] when backpack size is i, the maximum possible value
*/
public class Backpack {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
	public int backPackII(int m, int[] A, int[] V) {
		int[] dp = new int[m + 1];
		for (int i = 0; i < A.length; i++) {
			for (int j = m; j >= 1; j--) {
				if (j >= A[i]) {
					dp[j] = Math.max(dp[j], dp[j - A[i]] + V[i]);    
				}
			}
		}

		return dp[m];
	}
}