/*
There are n coins in a line.
Two players take turns to take one or two coins from right side until there are no more coins left.
The player who take the last coin wins.

Could you please decide the first player will win or lose?

If the first player wins, return true, otherwise return false.

Example 1:
Input: 1
Output: true

Example 2:
Input: 4
Output: true
Explanation:
The first player takes 1 coin at first. Then there are 3 coins left.
Whether the second player takes 1 coin or two, then the first player can take all coin(s) left.

Challenge
O(n) time and O(1) memory

idea:
will win 就是有一种可能 对方 lose 就可以
Game Theory: 如果我要赢, 后手得到的局面一定要'有输的可能'

1. 归纳出 只要 n 可以被3整除 first player cannot win
就是 先手无论怎么拿 总会 有余下的 coin
2. dp[i]: 面对i个coins的局面时是否能赢, 取决于我拿掉1个,或者2个时, 对手是不是会可能输
只要 一种情况成立就行
dp[i] = !dp[i - 1] || !dp[i - 2]
https://github.com/awangdev/LintCode/blob/master/Java/Coins%20in%20a%20Line.java
*/

public class CoinsInALine {
	public boolean firstWillWin(int n) {
		return n % 3 != 0;
	}

	public boolean firstWillWin(int n) {
		if (n <= 0) {
			return false;
		}

		boolean[] dp = new boolean[n + 1];
		dp[0] = false;
		dp[1] = true;

		for (int i = 2; i <= n; i++) {
			dp[i] = !dp[i - 1] || !dp[i - 2];
		}

		return dp[n];
	}
}