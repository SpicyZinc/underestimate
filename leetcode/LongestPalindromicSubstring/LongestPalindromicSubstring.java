/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:
Input: "babad"
Output: "bab"

Note: "aba" is also a valid answer.

Example:
Input: "cbbd"
Output: "bb"

idea:
http://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
1. brute force
2. from each char or space in-between chars to scan towards both directions
a string of length n, the number of central points should be 2 * n - 1
字符作为中心有n个, 间隙有n - 1个 e.g., abc, 中心可以是a, b, c, ab的间隙, bc的间隙
往两边同时进行扫描, 直到不是回文串为止
这个思路是对的 但是不是为了 中心 而是为了到达最后一个char 在string
3. DP
palindromic[i][j] refers to 字符串区间[i, j]是否为回文串
https://www.cnblogs.com/grandyang/p/4464476.html
*/

class LongestPalindromicSubstring {
    public static void main(String[] args) {
        LongestPalindromicSubstring eg = new LongestPalindromicSubstring();
        String s = "abracadabra";
        System.out.println(s + " isPalindromicString == " + eg.isPalindromic(s));
        System.out.println(s + " getLongestPalindromicSubstring == " + eg.longestPalindrome(s));
    }
    // 2025
    public String longestPalindrome(String s) {
        int n = s.length();
        String result = "";

        for (int i = 0; i < n + n - 1; i++) {
            int left = i / 2;
            int right = (i + 1) / 2;

            String ss = getPalindrom(s, left, right);
            if (ss.length() > result.length()) {
                result = ss;
            }
        }

        return result;
    }

    public String getPalindrom(String s, int i, int j) {
        while (i >= 0 && j <= s.length()-1) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }

        return s.substring(i + 1, j);
    }

    // dp
    // Thu May 16 23:42:37 2019
    public String longestPalindrome(String s) {
        String result = "";

        if (s == null || s.length() == 0) {
            return result;
        }

        int maxLength = 0;
        int size = s.length();
        boolean[][] palindromic = new boolean[size][size];

        for (int j = 0; j < size; j++) {
            // note, equal
            for (int i = 0; i <= j; i++) {
                // 前一层里的两边的 char 相等
                if ( s.charAt(i) == s.charAt(j) && (j - i <= 2 || palindromic[i + 1][j - 1]) ) {
                    palindromic[i][j] = true;
                    if (maxLength < j - i + 1) {
                        maxLength = j - i + 1;
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }

        return result;
    }

    public String longestPalindrome(String s) {
        int size = s.length();
    
        int maxLen = 0;
        String longest = "";
        
        for (int i = 0; i < size + size - 1; i++) {
            // note, a good way to handle parity
            int left = i / 2;
            int right = (i + 1) / 2;
            
            String longestPalindrome = getLongestPalindrome(s, left, right);
            
            if (longestPalindrome.length() > maxLen) {
                maxLen = longestPalindrome.length();
                longest = longestPalindrome;
            }
        }
        
        return longest;
    }
    
    public String getLongestPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }

    // direct method
    public String longestPalindrome(String s) {
        String palindrome = "";
        int maxSubPalindromeLength = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String temp = s.substring(i, j);
                int len = j - i + 1;
                if (isPalindromic(temp)) {
                    if (len > maxSubPalindromeLength) {
                        palindrome = temp;
                        maxSubPalindromeLength = len;
                    }
                }
            }
        }

        return palindrome;
    }
    
    private boolean isPalindromic(String s) {
        int size = s.length();
        for (int i = 0; i < size / 2; i++) {
            if (s.charAt(i) != s.charAt(size - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}