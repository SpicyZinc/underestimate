/*
Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:
Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3

Example 2:
Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4

Example 3:
Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5

Note: 1 <= N <= 10 ^ 9.

idea:
N写成公差为1的正等差数列的和的方式有多少种
	1, 2, 3, 4, 5  (1 + 5) * 5 / 2
	+
1	1, 1, 1, 1, 1
i + 1 = 5 + 1 = 6
*/

class ConsecutiveNumbersSum {
	public int consecutiveNumbersSum(int N) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			int sum = (1 + i) * i / 2;
			if (sum > N) {
				break;
			}
			int remaining = N - sum;
			// 不能整除继续
			if (remaining % (i + 1) != 0) {
				continue;
			}
			// 开始的数字a大于0
			if (remaining / (i + 1) > 0) {
				count++;
			}
		}

		return count;
	}
}