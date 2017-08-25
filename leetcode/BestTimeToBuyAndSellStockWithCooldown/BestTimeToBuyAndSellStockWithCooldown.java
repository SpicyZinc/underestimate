/*
Say you have an array for which the ith element is the price of a given stock on day i. 
Design an algorithm to find the maximum profit.
You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:
prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]

idea:
http://www.programcreek.com/2014/09/leetcode-utf-8-validation-java/

*/
public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        int prev_sell_profit = 0;
        int sell_profit = 0;
        int buy_cost = Integer.MIN_VALUE;
        int prev_buy_cost = 0;
        for (int price : prices) {
            prev_buy_cost = buy_cost;
            buy_cost = Math.max(prev_buy_cost, prev_sell_profit - price);
            prev_sell_profit = sell_profit;
            sell_profit = Math.max(prev_sell_profit, price + prev_buy_cost);
        }
        return sell_profit;
    }
}