/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
Find and return the shortest palindrome you can find by performing this transformation.

For example:
Given "aacecaaa", return "aaacecaaa".
Given "abcd", return "dcbabcd".

idea:
from a certain character go to both sides, if it can go to the start of the string,
by 'can' it means it is equal char centered on this certain char
and if there are some characters left at the back, reverse it and prepend it to the start of the string

notes:
Method 1
scan from the back of the array
check if substring(0, j) is a palindrome
this way can guarantee that the shortest palindrome can be found out
because the earliest a partial palindrome found,
the biggest partial this palindrome in the string,
the fewest characters added to generate the shortest the palindrome

abccba|fe => fe => ef => ef|abccba|fe

Method 2
recursion, keep applying shortestPalindrom()

Method 3
1. a certain character is the middle character, in this way, it can find the shortest palindrome
2. a pivot (middle) character(s) could be one or two
*/

public class ShortestPalindrome {
	// Time Limit Exceeded, 119 / 120 test cases passed.
    public String shortestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        for (int i = s.length(); i >= 1; i--) {
            if (isPalindrome(s.substring(0, i))) {
                return new StringBuilder(s.substring(i)).reverse().toString() + s;
            }
        }

        return "";
    }

    public boolean isPalindrome(String s) {
        int len = s.length();
        int i = 0;
        while ( i < len / 2 ) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
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
            // abbae when i = 4 substring(0, 4) === abba
            // eabba when i = 4 substring(1) === abba
            if (s.substring(0, i).equals(reversedS.substring(n - i))) {
                break;
            }
        }

        return reversedS.substring(0, n - i) + s;
    }
 	// passed test, method 2
    public String shortestPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (j >= 0) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
            j--;
        }

        if (i == s.length()) {
            return s;
        }
        
        String suffix = s.substring(i);
        String prefix = new StringBuilder(suffix).reverse().toString();
        String mid = shortestPalindrome(s.substring(0, i));
        
        return prefix + mid + suffix;
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