/*
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number.
Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: 9973
Output: 9973
Explanation: No swap.

idea:
from left, find the right most and greatest digit, swap them.
why need to be right most because leave the ones equal to the greatest where they are, this is greedy algorithm
e.g. 1993
*/

class MaximumSwap {
	public int maximumSwap(int num) {
		String number = "" + num;
		int[] rightmostPositions = new int[10];
		// i from 0 is a must, record the right most position
		for (int i = 0; i < number.length(); i++) {
			rightmostPositions[number.charAt(i) - '0'] = i;
		}

		for (int i = 0; i < number.length(); i++) {
			// for each digit, get right most and greatest digit bigger than it
			// rightmost, 由 rightmost rightmostPositions 保证
			// greatest, so starting from 9 由 从 9 开始 保证
			// bigger then current index, 由 rightmost index > i 保证
			char current = number.charAt(i);
			// 这样保证了 在 i 后 最大的一个 比 num.charAt(i)
			for (int j = 9; j > current - '0'; j--) {
				if (rightmostPositions[j] > i) {
					return Integer.parseInt(swap(number, i, rightmostPositions[j]));
				}
			}
		}

		return num;
	}

	public String swap(String s, int i, int j) {
		char[] arr = s.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

		return new String(arr);
	}
}