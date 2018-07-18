/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
For example, 
given n = 12, return 3 because 12 = 4 + 4 + 4; 
given n = 13, return 2 because 13 = 4 + 9.

idea:
所有自然数至多只要用四个数的平方和就可以表示
http://bookshadow.com/weblog/2015/09/09/leetcode-perfect-squares/
http://www.cnblogs.com/grandyang/p/4800552.html

1 recursion is the natural way to tackle this problem
i starting from 2, if n can be divided exactly, count the number
if not, get the remaining number, recursively passing in the function again

2. dynamic programming
create an array of length n + 1, dp[i] the smallest number of square numbers added up to i
dp[0] = 0, the rest is Integer.MAX_VALUE
i[0, n], j[1, n] but i+j*j <= n,
update dp[i+j*j], return dp[n]
*/

import java.util.*;

public class PerfectSquares {
	public static void main(String[] args) {
		PerfectSquares eg = new PerfectSquares();
		System.out.println(eg.numSquares(3));
		System.out.println(eg.numSquares(12));
	}
	// method 1
	public int numSquares(int n) {
        int minNum = n;
        int i = 2;
        while (i * i <= n) {
            int square = i * i;
            int num = n / square;
            int remaining = n % square;
            minNum = Math.min(minNum, num + numSquares(remaining));
            i++;
        }
        
        return minNum;
    }

    // method 2
    public int numSquares(int n) {
    	int[] dp = new int[n + 1];
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	dp[0] = 0;
    	for (int i = 0; i <= n; i++) {
    	    for (int j = 1; i + j * j <= n; j++) {
    	    	dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
    	    }
    	}

       	return dp[n];
    }
}