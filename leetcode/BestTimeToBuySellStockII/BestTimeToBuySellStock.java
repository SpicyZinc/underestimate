/*
Design an algorithm to find the maximum profit. 
You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time 
(ie, you must sell the stock before you buy again).

idea:
there might be some ascending subarrays in the final prices[] array
buy at first position of such subarray, and sell at last position of such subarray
buy at lower price, and sell at higher price
The difference is profit, accumulate the profit to get max profit.

how to look at these problems:
in an ascending subarray, profit += (next - preceding)
*/

public class BestTimetToBuySellStockII {
	public int maxProfit(int[] prices) {
		int profit = 0;

		for (int i = 0; i < prices.length - 1; i++) {
			if (prices[i + 1] > prices[i]) {
				profit += (prices[i + 1] - prices[i]);
			}
		}
		
		return profit;
	}
}