/*
Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"

Example 2:
Input: -7
Output: "-10"
Note: The input will be in range of [-1e7, 1e7].

idea:
typical method (/ base) to continue while then (% base) to get each digit
*/
public class Base7 {
	public String convertToBase7(int num) {
		if (num == 0) {
			return "0";
		}

		boolean isNeg = num < 0;
		String result = "";

		while (num != 0) {
			result = Math.abs(num % 7) + result;
			num /= 7;
		}

		return isNeg ? "-" + result : result;
	}
}