/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. 
You may complete at most two transactions.
Note:
You may not engage in multiple transactions at the same time 
(ie, you must sell the stock before you buy again).

idea:
note: two transactions at most

1. 
http://blog.unieagle.net/2012/12/05/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Abest-time-to-buy-and-sell-stock-iii%EF%BC%8C%E4%B8%80%E7%BB%B4%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/
http://www.darrensunny.me/leetcode-best-time-to-buy-and-sell-stock-iii/
Time complexity: O(n)

2.
Given an i, split the whole array into two parts:
[0, i] and [i+1, n], it generates two max value based on i, Max(0,i) and Max(i+1,n)
So, we can define the transformation function as:
maxProfit = max(Max(0, i) + Max(i+1, n)) (0 <= i < n)
maxProfit = max((Max(0, 0) + Max(1, n)), (Max(0, 1) + Max(2, n)), (Max(0, 2) + Max(3, n)), ... , (Max(0, n-1) + Max(n, n))) 
(0 <= i < n)
Preprocessing Max(0, i) might be easy.
time complexity: O(n^2)
Judge Large time limit exceeded
*/

public class BestTimetToBuySellStock {
	// split the array into two parts, and each part is a buy and sell I problem
	// in I, only one final maxProfit; but here, two transactions,
	// so save maxProfit into array

	public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices == null) {
            return 0;
        }
        int n = prices.length;
        int[] preMaxProfit = new int[n];
        int[] postMaxProfit = new int[n];
        
        int minBuyInPrice = prices[0];
        for (int i = 1; i < n; i++) {
            minBuyInPrice = Math.min(minBuyInPrice, prices[i]);
            // save all possible pre maxProfit for array ending at i
            preMaxProfit[i] = Math.max(preMaxProfit[i - 1], prices[i] - minBuyInPrice);
        }
        
        int maxSellOutPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxSellOutPrice = Math.max(maxSellOutPrice, prices[i]);
            postMaxProfit[i] = Math.max(postMaxProfit[i + 1], maxSellOutPrice - prices[i]);
        }

        int maxProfit = 0;
        // get the maximum of forward[i]+backward[i]
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, preMaxProfit[i] + postMaxProfit[i]);
        }

        return maxProfit;
    }

    public int maxProfit(int[] prices) {
		if (prices.length <= 1) {
            return 0;
		}
            
        // stores the max profit in [0, ... , i] subarray in prices
    	int[] maxEndWith = new int[prices.length];
		int lowest = prices[0];
		int maxprofit = 0;
		maxEndWith[0] = 0;
		for (int i = 1; i < prices.length; i++) {
			int profit = prices[i] - lowest;
			if (profit > maxprofit) {
				maxprofit = profit;
			}
			maxEndWith[i] = maxprofit;
			
			if (prices[i] < lowest) {
				lowest = prices[i];
			}					
		}
        
        int ret = maxEndWith[prices.length - 1];
		// reverse to see what is the max profit of [i, ... , n-1] subarray in prices 
		// and meanwhile calculate the final result
		int highest = prices[prices.length - 1];
		maxprofit = 0;
		for (int i = prices.length - 2; i >= 0; i--) {
			int profit = highest - prices[i];
			if (profit > maxprofit) {
				maxprofit = profit;
			}
			int finalprofit = maxprofit + maxEndWith[i];
			if (finalprofit > ret) {
				ret = finalprofit;
			}				
			if (prices[i] > highest) {
				highest = prices[i];
			}				
		}

        return ret;
    }
	
	// method 2 Judge Large time limit exceeded
	public int maxProfit(int[] prices) {
		int max1 = 0;
		int max2 = 0;
		
		int max = 0;
		
		for (int i = 0; i < prices.length; i++) {
			max1 = searchForMaxProfit(prices, 0, i);
			max2 = searchForMaxProfit(prices, i + 1, prices.length - 1);			
			if (max1 + max2 > max) {
				max = max1 + max2;
			}
		}
		
		return max;
	}
	
	private int searchForMaxProfit(int[] a, int start, int end) {
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		for (int i = start; i <= end; i++) {
			if (min > a[i]) {
				min = a[i];
			}
			
			int diff = a[i] - min;
			if (diff > max) {
				max = diff;
			}
		}
		
		return max;
	}
}