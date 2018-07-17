/*
Given a non-empty string s, you may delete at most one character.
Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True

Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

idea:
start from both head and tail of the string
if char at i and j not equal, break while
try to skip the character by i + 1 or j - 1
as long as return true, it is true
*/

class ValidPalindromeII {
	public boolean validPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;

		while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
		}
        if (i >= j || isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1)) {
            return true;
        }
		return false;
	}

	public boolean isPalindrome(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	// TLE
	public boolean validPalindrome(String s) {
		int len = s.length();
		if (len <= 2) {
			return true;
		}
		for (int i = 0; i < len; i++) {
			String substring = s.substring(0, i) + s.substring(i + 1);
			if (isPalindrome(substring)) {
				return true;
			}
		}
		return false;
	}

	public boolean isPalindrome(String s) {
        if (s.length() == 0 || s == null) return true;

        int n = s.length();
        int i = 0;
		while (i < n / 2) {
			if (s.charAt(i) != s.charAt(n-1-i)) {
			    return false;
            }
			i++;
		}
		return true;
    }
}