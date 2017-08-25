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
*/

public class Reverse String {
	public String reverseStr(String s, int k) {
        if (s.length() == 0 || s == null) {
    		return s;
    	}
    	if (k % s.length() == 0) {
    	    return new StringBuilder(s).reverse().toString();
    	}

		StringBuilder sb = new StringBuilder();
		int i = 0, j = 0;
		while (i < s.length()) {
			// first k
			j = (i + k) <= s.length() ? i + k : s.length();
			sb.append( new StringBuilder(s.substring(i, j)).reverse().toString() );
			// second k
			i = j;
			j = i + k <= s.length() ? i + k : s.length();
			sb.append(s.substring(i, j));

			i = j;
		}
		return sb.toString();
    }
}