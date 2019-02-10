/*
There is a stone game.At the beginning of the game the player picks n piles of stones in a line.
The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
The score is the number of stones in the new pile.

You are to determine the minimum of the total score.

Example
For [4, 1, 1, 4], in the best solution, the total score is 18:
1. Merge second and third piles => [4, 2, 4], score +2
2. Merge the first two piles => [6, 4]，score +6
3. Merge the last two piles => [10], score +10

Other two examples:
[1, 1, 1, 1] return 8
[4, 4, 5, 9] return 43

idea:
https://zhengyang2015.gitbooks.io/lintcode/stone_game_476.html

dp[i][j] represents the min cost of merging A[i...j];
Function:
dp[i][j] = min{dp[i][k] + dp[k + 1][j] + prefixSum[j + 1] - prefixSum[i]} for all k: [i, j - 1]
Initialization:
dp[i][i] = 0;
dp[i][j] = Integer.MAX_VALUE for all j > i;
prefixSum[i]: the sum of the first ith elements of A;
Answer:
dp[0][A.length - 1];

need to come back
*/

public class StoneGame {
	public int stoneGame(int[] A) {
		if (A == null || A.length <= 1) {
			return 0;
		}

		int n = A.length;
		int[] prefixSum = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			prefixSum[i] = prefixSum[i - 1] + A[i - 1];
		}

		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = 0;
		}

		for (int span = 2; span <= n; span++) {
			for (int i = 0; i < n - span + 1; i++) {
				int j = i + span - 1;
				dp[i][j] = Integer.MAX_VALUE;
		        for (int k = i; k < j; k++) {
		            dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + prefixSum[j + 1] - prefixSum[i]);
		        }
			}
		}

		return dp[0][n - 1];
	}
}