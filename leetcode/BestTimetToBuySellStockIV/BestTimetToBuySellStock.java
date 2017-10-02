/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most k transactions.
note: You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

idea:
http://blog.csdn.net/linhuanmars/article/details/23236995

int[][] mustsell = new int[days][k + 1], mustSell[i][j] 表示前i天 ith day not inclusive, 至多进行j次交易, 第i天必须sell的最大获益
int[][] globalbest = new int[days][k + 1], globalbest[i][j] 表示前i天 ith day not inclusive, 至多进行j次交易, 第i天可以不sell的最大获益
*/
public class BestTimetToBuySellStock {
    public int maxProfit(int k, int[] prices) {
        int days = prices.length;
        // can have multiple transactions
        if (k >= days) {
            return maxProfitFromMulti(prices);
        }

        int[][] mustsell = new int[days][k + 1];
        int[][] global = new int[days][k + 1];
        for (int i = 1; i < days; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                mustsell[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), mustsell[i - 1][j] + diff);
                global[i][j] = Math.max(global[i - 1][j], mustsell[i][j]);
            }
        }
        
        return Math.max(global[days - 1][k], mustsell[days - 1][k]);
    }
    
    public int maxProfitFromMulti(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += (prices[i] - prices[i - 1]);
            }
        }
        return maxProfit;
    }
}