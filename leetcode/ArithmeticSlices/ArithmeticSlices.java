/*
A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9

The following sequence is not arithmetic.
1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.

Example:
A = [1, 2, 3, 4]
return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

idea:
quintessential dp, dp[i] represents the length of arithmetic sequence ends with A[i]
or observe the rule
https://discuss.leetcode.com/topic/62162/3ms-question-maker-solution-in-cpp-o-n-time-and-in-space/2

一个数组中有多少连续等差数列
*/
public class ArithmeticSlices {
	public int numberOfArithmeticSlices(int[] A) {
		if (A.length <= 2 || A == null) {
			return 0;
		}

		int n = A.length;
		int[] dp = new int[n];
		if (A[2] - A[1] == A[1] - A[0]) {
			dp[2] = 1;
		}

		for (int i = 3; i < n; i++) {
			if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
				dp[i] = dp[i - 1] + 1;
			}
		}
		// 把所有的加起来最后
		int result = 0;
		for (int i = 0; i < n; i++) {
			result += dp[i];
		}

		return result;
	}
}