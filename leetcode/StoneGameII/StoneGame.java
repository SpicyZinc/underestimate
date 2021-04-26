/*
Alex and Lee continue their games with piles of stones.
There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
The objective of the game is to end with the most stones. 

Alex and Lee take turns, with Alex starting first.
Initially, M = 1.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.
Then, we set M = max(M, X).

The game continues until all the stones have been taken.

Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
 

Example 1:
Input: piles = [2,7,9,4,4]
Output: 10
Explanation:  If Alex takes one pile at the beginning, Lee takes two piles, then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total. If Alex takes two piles at the beginning, then Lee can take all three piles left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since it's larger. 

Constraints:
1 <= piles.length <= 100
1 <= piles[i] <= 10 ^ 4

idea:
Thu Aug 20 00:11:51 2020
need to revisit


*/
public class StoneGame {
    public int stoneGame2(int[] A) {
        int n = A.length;
        if (n <= 1) {
            return 0;
        }

        int[][] dp = new int[2 * n][2 * n];
        int[] sum = new int[2 * n + 1];

        for (int i = 1; i <= 2 * n; i++) {
            sum[i] = sum[i - 1] + A[(i - 1) % n];
        }

        for (int i = 0; i < 2 * n; i++) {
            dp[i][i] = 0;
        }

        for (int len = 2; len <= 2 * n; len++) {
            for (int i = 0; i < 2 * n && i + len - 1 < 2 * n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    if (dp[i][k] + dp[k+1][j] + sum[j + 1] - sum[i] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k+1][j] + sum[j + 1] - sum[i];
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[i][i + n - 1] < answer) {
                answer = dp[i][i + n - 1];
            }
        }

        return answer;
    }
}