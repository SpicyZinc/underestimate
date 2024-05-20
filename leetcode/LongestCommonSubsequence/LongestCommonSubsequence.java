/*
Given two strings, find the longest common subsequence (LCS).
Your code should return the length of LCS.

Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.
For "ABCD" and "EACB", the LCS is "AC", return 2.

idea:
typical 双序列DP
dp[i][j] first i chars in A and first j chars in B common sub sequence's length
*/

import java.util.*;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        LongestCommonSubsequence eg = new LongestCommonSubsequence();

        // String A = "ABCD";
        // String B = "EDCA";
        String A = "ABCD";
        String B = "EACB";


        String lcs = eg.getLCS(A, B);
        // String lcs = eg.findLongestCommonSubsequence(A, B);

        System.out.println("lcs == " + lcs);
    }

    public int longestCommonSubsequence(String A, String B) {
        int m = A.length();
        int n = B.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char a = A.charAt(i - 1);
                char b = B.charAt(j - 1);
                
                dp[i][j] = a == b ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }

    // 不仅求最大长度 而且求出最大的那个subsequence
    public String getLCS(String A, String B) {
        int m = A.length();
        int n = B.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char a = A.charAt(i - 1);
                char b = B.charAt(j - 1);
                
                dp[i][j] = a == b ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        StringBuilder longestCommonSub = new StringBuilder();

        while (dp[m][n] > 0) {
            if (dp[m][n] == dp[m - 1][n]) {
                m--;
            } else if (dp[m][n] == dp[m][n - 1]) {
                n--;
            } else {
                longestCommonSub.append(A.charAt(m - 1));
                m--;
                n--;
            }
        }

        return longestCommonSub.reverse().toString();
    }

    public String findLongestCommonSubsequence(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] lcsTable = new int[m + 1][n + 1];
 
        int maxLength = 0;
        int maxRow = 0;
        int maxColumn = 0;
 
        // init first row with 0
        for (int i = 0; i < m; i++) {
            lcsTable[i][0] = 0;
        }
 
        // init first col with 0
        for (int j = 0; j < n; j++) {
            lcsTable[0][j] = 0;
        }
 
        // starting from 1 as row 0 and col 0 filled with 0. <= since it has go up to string length.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    lcsTable[i][j] = 1 + lcsTable[i - 1][j - 1];
                    if (maxLength < lcsTable[i][j]) {
                        maxLength = lcsTable[i][j];
                        maxRow = i;
                        maxColumn = j;
                    }
                } else {
                    lcsTable[i][j] = Math.max(lcsTable[i - 1][j], lcsTable[i][j - 1]);
                }
            }
        }
 
 
        StringBuilder longestSubsequence = new StringBuilder();
        // Remember that row 0 and column 0 indicate absence of one of the strings.
        while (maxRow >= 1 && maxColumn >= 1) {
            if (x.charAt(maxRow - 1) == y.charAt(maxColumn - 1)) {
                longestSubsequence.append(x.charAt(maxRow - 1));
                maxRow--;
                maxColumn--;
            } else {
                if (lcsTable[maxRow - 1][maxColumn] >= lcsTable[maxRow][maxColumn - 1]) {
                    maxRow--;
                } else {
                    maxColumn--;
                }
            }
        }
 
        return longestSubsequence.reverse().toString();
    }
}
