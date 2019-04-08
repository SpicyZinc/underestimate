/*
Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Note:
The input string length won't exceed 1000.

idea:
substrings 中回文的最多个数
n char + (n - 1) spaces positions

*/

class PalindromicSubstrings {
	public int countSubstrings(String s) {
		int n = s.length();
		int cnt = 0;

		for (int i = 0; i < n * 2 - 1; i++) {
			// note, a good way to handle parity
			int left = i / 2;
			int right = (i + 1) / 2;

			while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
				left--;
				right++;
				cnt++;
			}
		}

		return cnt;
	}
}