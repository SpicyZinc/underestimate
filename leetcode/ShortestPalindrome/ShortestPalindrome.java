/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
Find and return the shortest palindrome you can find by performing this transformation.

For example:
Given "aacecaaa", return "aaacecaaa".
Given "abcd", return "dcbabcd".

idea:

from a certain character go to both sides, if it can go to the start of the string,
and if there are some characters left at the back, reverse it and prepend it to the start of the string

notes:
Method 1
scan from the back of the array
check if substring(0, j) is a palindrome
in this way, we can guarantee that the shortest palindrome can be found out
because the earliest a partial palindrome found, the shortest the palindrome could be generated

Method 2
recursion, keep applying shortestPalindrom()

Method 3
1. a certain character is the middle character, in this way, it can find the shortest palindrome
2. a pivot (middle) character(s) could be one or two
*/

public class ShortestPalindrome {
	// Time Limit Exceeded, 119 / 120 test cases passed.
    public String shortestPalindrome(String s) {
        if ( s.length() <= 1 ) {
            return s;
        }
        String res = "";
        for ( int i = s.length(); i >= 1; i-- ) {
            if ( isPalindrome(s.substring(0, i)) ) {
                return new StringBuilder(s.substring(i)).reverse().toString() + s;
            }
        }
        return "";
    }
    public boolean isPalindrome(String s) {
        int len = s.length();
        int i = 0;
        while ( i < len / 2 ) {
            if ( s.charAt(i) != s.charAt(len - i - 1) ) {
                return false;
            }
            i++;
        }
        return true;
    }
    // Time Limit Exceeded, 119 / 120 test cases passed
    public String shortestPalindrome(String s) {
        String reversedS = new StringBuilder(s).reverse().toString();

        int n = s.length();
        int i = n;
        for (i = n; i >= 0; i--) {
            // this is a way to check if palindrome
            // after reverse, still equal, meaning before reverse, it is palindrome
            if ( s.substring(0, i).equals(reversedS.substring(n - i)) ) {
                break;
            }
        }

        return reversedS.substring(0, n - i) + s;
    }
 	// passed test, method 2
    public String shortestPalindrome(String s) {
		int j = 0;
	    for (int i = s.length() - 1; i >= 0; i--) {
	        if (s.charAt(i) == s.charAt(j)) { 
	        	j += 1; 
	        }
	    }
	    if (j == s.length()) { 
	    	return s; 
	    }
	    String suffix = s.substring(j);
	    return new StringBuilder(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }
    // passed test, method 3
    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        int index = 0;
        for (int i = 0; i < s.length() * 2 - 1; i++) {
            int left = i / 2;
            int right = (i + 1) / 2;
            if (helper(s, left, right)) {
                index = i % 2 == 1 ? right * 2 : right * 2 + 1;
            }
        }

        return new StringBuilder(s.substring(index)).reverse() + s;
    }
    private boolean helper(String s, int left, int right) {
        while ( left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right) ) {
            left--;
            right++;
        }
        // cross boundary
        return left == -1;
    }
}