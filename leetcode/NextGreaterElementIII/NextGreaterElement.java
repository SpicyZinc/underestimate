/*
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer
which has exactly the same digits existing in the integer n and is greater in value than n.
If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21

Example 2:
Input: 21
Output: -1

idea:
Here is one simple example.
index: 012345
given: 124651 => 125 641 => 125 146
ans : 125146
procedure:
Starting from the rightmost digit, going to left. Find the first digit which is smaller than the previous digit.
In this example, 4 is smaller than 6. Remember 4 and its index 2.
Going from rightmost again. This time, find the first digit which is bigger than 4. It is 5 here.
Swap 4 and 5. The number becomes 125641.
Reverse all the digits which are right to 4's original index (That is 2), 641 should be reversed to 146 here.

similar to next permutation,
note: the smallest number, so have to reverse all the digits which are right to 4's original index
*/
public class NextGreaterElementIII {
	public int nextGreaterElement(int n) {
		if (n == Integer.MAX_VALUE) {
			return -1;
		}
		if (n <= 10) {
			return -1;
		}

		String num = "" + n;
		int size = num.length();

		for (int i = size - 2; i >= 0; i--) {
			if (num.charAt(i) >= num.charAt(i + 1)) {
				continue;
			}
			// found the first digit which is smaller than the previous digit
			// now start find the first digit which is just a little bit bigger then the found digit above
			for (int j = size - 1; j > i; j--) {
				if (num.charAt(j) > num.charAt(i)) {
					try {
						return helper(num, i, j);
					} catch (NumberFormatException e) {
						return -1;
					}
				}
			}
		}
		return -1;
	}
	// helper to generate correct re-constructed number
	private int helper(String s, int i, int j) {
		// regular way
		// 1. swap (i, j), no need, just extract and place in right direction
		// 2. sort [i + 1, end] in ascending order
		String ascendingTail = new StringBuilder( s.substring(i + 1, j) + s.charAt(i) + s.substring(j + 1) ).reverse().toString();
		String next = s.substring(0, i) + s.charAt(j) + ascendingTail;
		return Integer.parseInt(next);
	}
}