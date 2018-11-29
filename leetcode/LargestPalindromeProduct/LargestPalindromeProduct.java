/*
Find the largest palindrome made from the product of two n-digit numbers.
Since the result could be very large, you should return the largest palindrome mod 1337.

Example:
Input: 2
Output: 987
Explanation: 99 x 91 = 9009, 9009 % 1337 = 987

Note:
The range of n is [1,8].

idea:
n = 2, 数字可选区间就是(9, 99].
最大product 99 * 99 = 9801.
最大的位数是2n which is even number. 

https://blog.csdn.net/magicbean2/article/details/78683871

*/

class LargestPalindromeProduct {
	public int largestPalindrome(int n) {
		if (n == 1) {
			return 9;
		}

		int max = (int) Math.pow(10, n) - 1;

		// (max / 10, max]
		for (int v = max - 1; v > max / 10; v--) {
			long palindrome = Long.valueOf(v + new StringBuilder().append(v).reverse().toString());
			for (long x = max; x * x >= palindrome; x--) {
				if (palindrome % x == 0) {
					return (int) (palindrome % 1337);
				}
			}
		}

        return 0;
	}
}
