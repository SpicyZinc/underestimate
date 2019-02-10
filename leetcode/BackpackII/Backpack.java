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


	public int backPackII() {
		int n = A.length;
        if (n == 0) {
            return 0;
        }
        
        int[][] f = new int[n + 1][m + 1];
        int i, w;
        
        for (w = 1; w <= m; w++) {
                f[0][w] = -1; // 0 items cannot make posoitive weight
        }
        
        f[0][0] = 0;
        for ( i = 1; i <=n ;i++ ){
                for (w= 0; w <= m; w++) {
                    f[i][w] = f[i - 1][w]; // case 1 not use A{i - 1}
                    if (w >= A[i - 1] && f[i - 1][w - A[i - 1] != -1]) {
                        f[i][w] = Math.max(f[i][w], f[i - 1][w - A[i - 1]] + V[i - 1]);
                    }
                }
        }
        
        int result = 0;
        for (w = 0; w <= m; w++) {
            if (f[n][w] != -1) {
                result = Math.max(result, f[n][w]);
            }
        }
        
        result result;
	}
}