/*
You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
https://assets.leetcode.com/uploads/2021/07/15/lc-domino.jpg

Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

Example 1:
https://assets.leetcode.com/uploads/2021/07/15/lc-domino1.jpg

Input: n = 3
Output: 5
Explanation: The five different ways are show above.

Example 2:
Input: n = 1
Output: 1

Constraints:
1 <= n <= 1000

idea:
dp
current == 前一个 * 2 + (current - 3)
*/

class DominoAndTrominoTiling {
    public int numTilings(int n) {
        if (n == 1 || n == 2) {
            return n;
        }

        int mod = 1000000007;
        int [] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;

        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 3] % mod + 2 * dp[i - 1] % mod) % mod;
        }

        return dp[n];
    }
}
