/*
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.

]Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4
Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”

Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2
Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

idea:
typical DP problem
dp[i][j] = the max number of strings composed of i 0 and j 1
loop through each string in the array
count zeros and ones in this string, minus them, dp[i-zeros][j-ones] + 1, +1 corresponds to each string
Math.max(dp[i][j], dp[i-zeros][j-ones] + 1);

*/
public class OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
    	// each string, take it or not take it, can give rise to different results
    	// e.g. {"10", "0", "1"} if 10 taken, m=1, n=1 can only give 1 string
    	// otherwise can give 2 strings
        for (String str : strs) {
        	int zeros = 0, ones = 0;
        	for (int k = 0; k < str.length(); k++) {
        		char c = str.charAt(k);
        		if (c == '0') {
        		    zeros++;
        		} else {
        		    ones++;
        		}
        	}
        	for (int i = m; i >= zeros; i--) {
        		for (int j = n; j >= ones; j--) {
					dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
        		}
        	}
        }

        return dp[m][n];
    }
}