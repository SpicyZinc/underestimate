/*
Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string.
If there are less than k characters left, reverse all of them.
If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.

Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"

Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]

idea:
go though each char, for every 2k chars, first k, and second k char
note, not forget to check if exceed str length or not
*/

public class Reverse String {
	public String reverseStr(String s, int k) {
		if (s.length() == 0 || s == null) {
			return s;
		}

		int len = s.length();
		if (k > 0 && k % len == 0) {
			return new StringBuilder(s).reverse().toString();
		}
		StringBuilder sb = new StringBuilder();
		int i = 0;
		int j = 0;
		while (i < len) {
			// first k chars
			j = (i + k) <= len ? i + k : len;
			sb.append( new StringBuilder(s.substring(i, j)).reverse().toString() );
			// second k chars
			i = j;
			j = (i + k) <= len ? i + k : len;
			sb.append(s.substring(i, j));
			// update i
			i = j;
		}

		return sb.toString();
    }
    // c++, not working because of syntax, logic is correct
	public String reverseStr(String s, int k) {
		for (int i = 0; i < s.length(); i += 2 * k) {
			int end = Math.min(s.begin() + i + k, s.length());
			reverse(s.begin() + i, end);
		}

		return s;
    }
}