/*
We are playing the Guess Game. The game is as follows:
I pick a number from 1 to n. You have to guess which number I picked.
Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:
n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

idea:
https://www.hrwhisper.me/leetcode-guess-number-higher-lower-ii/
https://www.cnblogs.com/grandyang/p/5677550.html

dp[i][j]: minimum money to guarantee win for subproblem [i, j]
Corner case: dp[i][i] = 0 (because the only element must be correct)
return dp[1][n]

Equation: we can choose k (i<=k<=j) as our guess, and pay price k.
After our guess, the problem turns into two subproblems.
Notice we do not need to pay the money for both subproblems.
We only need to pay the worst case (because the system will tell us which side we should go) to guarantee win.
So dp[i][j] = min (i<=k<=j) { k + max(dp[i][k-1], dp[k+1][j]) }
*/
public class GuessNumberHigherOrLower {
	// recursion
    // dp[L][R] minimum cost to win for subproblem [L, R]
    public int getMoneyAmount(int n) {
        return solve(1, n, new int[n + 1][n + 1]);
    }
    
    public int solve(int L, int R, int[][] dp) {
        if (L >= R) return 0;
        if (dp[L][R] > 0) return dp[L][R];
        dp[L][R] = Integer.MAX_VALUE;
        for (int guess = L; guess <= R; guess++) {
            // worse case of two subproblems + guess
            int costToWin = Math.max(solve(L, guess - 1, dp), solve(guess + 1, R, dp)) + guess;
            dp[L][R] = Math.min(dp[L][R], costToWin);
        }
        return dp[L][R];
    }

	public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
		for (int len = 1; len < n; len++) {
	        for (int i = 1; i < n; i++) {
	            int j = i + len;
	            int minCost = Integer.MAX_VALUE;
                if (j <= n) {
                    for (int k = i; k < j; k++) {
                        int cost = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                        minCost = Math.min(minCost, cost);
                    }
                    dp[i][j] = minCost;
                }
	        }
	    }

	    return dp[1][n];
    }
    // another method
    public int getMoneyAmount(int n) {
	    int[][] dp = new int[n+1][n+1];
	    for (int L = n - 1; L > 0; L--) {
			for (int R = L + 1; R <= n; R++) {
				dp[L][R] = Integer.MAX_VALUE;
				for (int i = L; i < R; i++) {
					dp[L][R] = Math.min(dp[L][R], i + Math.max(dp[L][i - 1], dp[i + 1][R]));
				}
			}
		}

	    return dp[1][n];
    }
}