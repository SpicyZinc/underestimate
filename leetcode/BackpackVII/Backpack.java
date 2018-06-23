/*
Assume that you have n yuan. There are many kinds of rice in the supermarket. Each kind of rice is bagged and must be purchased in the whole bag.
Given the weight, price and quantity of each type of rice, find the maximum weight of rice that you can purchase.

Example
Given:
n = 8
prices = [2,4]
weight = [100,100]
amounts = [4,2]

Return:400

idea:

*/

public class Backpack {
    /**
     * @param n: the money of you
     * @param prices: the price of rice[i]
     * @param weight: the weight of rice[i]
     * @param amounts: the amount of rice[i]
     * @return: the maximum weight
     */
    public int backPackVII(int n, int[] prices, int[] weight, int[] amounts) {
        int m = prices.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 1; k <= amounts[i - 1]; k++) {
                    if (j >= prices[i - 1] * k) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - prices[i - 1] * k] + weight[i - 1] * k);
                    }
                }
            }
        }

        return dp[m][n];
    }
}