/*
Best Time to Buy and Sell Stock
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction
(ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

idea:

At first glance, you might think that finding the minimum and maximum value would do, 
but it does have a hidden restriction, that is: You must buy before you can sell.

The question is equivalent to the following:
Find i and j that maximizes Aj - Ai, where i < j.

go through the array, two jobs need to be done
1. find the so far min value of the array by comparing min with current number, once finding smaller one, assign it to "min"
2. meanwhile, current number - current min, find the max difference which is result
*/

import java.util.*;

public class BestTimeToBuyAndSellStock {
	public static void main(String[] args) {
		new BestTimeToBuyAndSellStock();
	}
	public BestTimeToBuyAndSellStock() {
		Random rand = new Random(12345);		
		int[] prices = new int[10];
		
		for (int i = 0; i < prices.length; i++) {
			prices[i] = rand.nextInt(100);
			System.out.print(prices[i] + " ");
		}
		System.out.print("\n");
		System.out.println("The maximum profit is: " + maxProfit(prices));		
	}
	public int maxProfit(int[] prices) {
        int min = 0;
        int maxVal = 0;
		int buy = 0, sell = 0;
		
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < prices[min]) {
				min = i;
			}
			int val = prices[i] - prices[min];
			if (val > maxVal) {
				buy = min;
				sell = i;
				maxVal = val;
			}
		}
		
        return maxVal;
    }
    // self written recent
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices.length == 0 || prices == null) {
            return maxProfit;
        }
        int min_so_far = prices[0];
        for (int price : prices) {
            min_so_far = Math.min(min_so_far, price);
            maxProfit = Math.max(maxProfit, price - min_so_far);
        }

        return maxProfit;
    }
}