/*
Given a string s and an integer k, return true if s is a k-palindrome.
A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.

Example 1:
Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.

Example 2:
Input: s = "abbababa", k = 1
Output: true

Constraints:
1 <= s.length <= 1000
s consists of only lowercase English letters.
1 <= k <= s.length

idea:
at most == "<="
1. dfs
2. dfs + memo
3. good idea, find out the longest subarray palindrome. and then check if the rest of the digits is less than k. closely related to LongestPalindromicSubsequence
*/
class ValidPalindrome {
    // Fri May 10 02:39:23 2024
    public boolean isValidPalindrome(String s, int k) {
        return s.length() - longestPalindrome(s) <= k;
    }

    public int longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] + 2 : Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }

    public boolean isValidPalindrome(String s, int k) {
        return s.length() - longestPalindromeSubseq(s) <= k;
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;

            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) ? (dp[i + 1][j - 1] + 2) : Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }

        return dp[0][n - 1];
    }

    public boolean isValidPalindrome(String str, int k) {
        int n = str.length();
        int dp[][] = new int[n][n];

        for (int i = 0; i < n; i++) dp[i][i] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i - 1][j + 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j+1]);
                }
            }
        }

        return n - dp[n - 1][0] <= k;
    }

    /*
    longest palindromic subsequence:
    LCS of the given string & its reverse will be the longest palindromic sequence.
     */
    private int lcs(String X, String Y, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }


    // TLE, but good to understand
    public boolean isValidPalindrome(String s, int k) {
        return dfs(s, k, 0, s.length() - 1);
    }

    public boolean dfs(String s, int k, int start, int end) {
        if (k < 0) {
            return false;
        }
        if (start >= end) {
            return true;
        }

        if (s.charAt(start) == s.charAt(end)) {
            return dfs(s, k, start + 1, end - 1);
        }
        // remove char at left or char at right
        return dfs(s, k - 1, start + 1, end) || dfs(s, k - 1, start, end - 1);
    }

    public boolean isValidPalindrome(String s, int k) {
        return helper(s, k, 0, s.length() - 1);
    }

    private boolean helper(String s, int k, int left, int right) {
        if (k < 0) {
            return false;
        }
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                if (k == 0) {
                    return false;
                } 
                return helper(s, k - 1, left + 1, right) || helper(s, k - 1, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }
}