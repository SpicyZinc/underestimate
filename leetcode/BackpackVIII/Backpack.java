/*
Give some coins of different value and their quantity.
Find how many values which are in range 1 ~ n can these coins be combined

Example
Given:
n = 10
value = [1,2,4]
amount = [2,1,1]

Return: 8
They can combine all the values in 1 ~ 8

idea:
*/

public class Backpack {
    /**
     * @param n: the value from 1 - n
     * @param value: the value of coins
     * @param amount: the number of coins
     * @return: how many different value
     */
	public int backPackVIII(int n, int[] value, int[] amount) {
		int m = value.length;
		boolean[][] dp = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = true;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (dp[i][j]) {
                    continue;
                }
                for (int k = 1; k <= amount[i - 1]; k++) {
                    if (j >= value[i - 1] * k && dp[i - 1][j - value[i - 1] * k]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }
        
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += dp[m][i] ? 1 : 0;
        }

        return count;
	}
}