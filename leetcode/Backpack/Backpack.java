/*
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?

Example
If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10.
If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.
You function should return the max size we can fill in the given backpack.

Note
You can not divide any item into small pieces.

idea:
dp[i] when backpack size is i, the possible max total size of items
*/
public class Backpack {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
	public int backPack(int m, int[] A) {
		int[] dp = new int[m + 1];
		for (int i = 0; i < A.length; i++) {
			for (int j = m; j >= 1; j--) {
				if (j >= A[i]) {
					dp[j] = Math.max(dp[j], dp[j - A[i]] + A[i]);
				}
			}
		}

		return dp[m];
	}
	// dp[i][j] is it possible to reach the size i from first j items
	// i in [1, m], j in [1, A.length] item
	public int backPack(int m, int[] A) {
		boolean[][] dp = new boolean[m + 1][A.length + 1];
		dp[0][0] = true;

	    for (int i = 0; i <= m; i++) {
	        for (int j = 1; j <= A.length; j++) {	
				// 1. (j - 1) items can reach i
				// 2. (j - 1) items reach i - current size A[j - 1], so j items can reach i
				// in 2 backpack size should be > current size
				// note in 2, current item's index is (j - 1)
				dp[i][j] = dp[i][j - 1] || i >= A[j - 1] && dp[i - A[j - 1]][j - 1];
			}
		}
		// reversely loop to find
		for (int i = m; i >= 1; i--) {
			if (dp[i][A.length]) {
				return i;
			}
		}
		return 0;
	}
}