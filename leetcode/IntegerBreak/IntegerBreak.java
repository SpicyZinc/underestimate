/*
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers.
Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: you may assume that n is not less than 2.

Hint:
There is a simple O(n) solution to this problem.
You may check the breaking results of n ranging from 7 to 10 to discover the regularities.

idea:
7 = 2 + 2 + 3
8 = 3 + 3 + 2
9 = 3 + 3 + 3
10 = 3 + 3 + 3 + 1
11 = 3 + 3 + 3 + 2

1. no more than three 2s (< 3 2s) is the regularity
The optimal product should contain less than three 2, should be as many 3 as possible
2. dynamic programming
*/

public class IntegerBreak {
	// Wed Mar 27 23:55:43 2019
	public int integerBreak(int n) {
        if (n < 4) {
            return n - 1;
        }

        if (n == 4) {
            return n;
        }
        
        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        
        product *= n;
        
        return product;
    }

	public int integerBreak(int n) {
		if (n == 2) {
			return 1;
		}
		if (n == 3) {
			return 2;
		}
		if (n == 4) {
			return 4;
		}

		int product = 1;
		while (n > 4) {
			product *= 3;
			n -= 3;
		}
		product *= n;

		return product;
	}

	// dp[i]
	// i in [1 - n]
	// dp[i] refers to among all combinations of numbers adding up to i, the maximum of all the possible products
	// dp[11] = Math.max(3 + 3 + 3 + 2, 7 + 2 + 2, ...)
	// dp[11] = Math.max(3 * 3 * 3 * 2, 7 * 2 * 2, ...)
	// dp[11] = Math.max(54, 28);
	
	// Try to write i as: i = j + S where S=i-j corresponds to either one number or a sum of two or more numbers
	// Assuming that j+S corresponds to the optimal solution for dp[i], we have two cases:
	// (1) i is the sum of two numbers, i.e. S=i-j is one number, and so dp[i]=j*(i-j)
	// (2) i is the sum of at least three numbers, i.e. S=i-j is a sum of at least 2 numbers,
	// and so the product of the numbers in this sum for S is dp[i-j]
	// (=maximum product after breaking up i-j into a sum of at least two integers):
	// dp[i] = j * dp[i-j]

	// dp[i] = Math.max(dp[i], j * Math.max((i - j), dp[i - j]));
	// 要么等于 与最大乘积 dp[i - j] 要么就是 i - j
	// 03/18/2019
	public int integerBreak(int n) {
		int[] dp = new int[n + 1];
		dp[1] = 0;

		for (int i = 2; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				dp[i] = Math.max(dp[i], j * Math.max((i - j), dp[i - j]));
			}
		}

		return dp[n];
	}

	// this is some fun, from a little different perspective
	public int integerBreak(int n) {
        // dp[i] means output when input = i, e.g. dp[4] = 4 (2*2),dp[8] = 18 (2*2*3)...
        int[] dp = new int[n + 1];
        dp[1] = 1;

		// fill the entire dp array
        for (int i = 2; i <= n; i++) {
            // let's say i = 8, we are trying to fill dp[8]: if 8 can only be broken into 2 parts, 
            // the answer could be among 1 * 7, 2 * 6, 3 * 5, 4 * 4... but these numbers can be further broken.
            // so we have to compare 1 with dp[1], 7 with dp[7], 2 with dp[2], 6 with dp[6]...etc
            for (int j = 1; j <= i / 2; j++) {
				// use Math.max(dp[i],....)  so dp[i] maintain the greatest value
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
        }

        return dp[n];
    }
}