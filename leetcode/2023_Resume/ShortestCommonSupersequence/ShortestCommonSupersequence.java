/*
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.
A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

Example 1:
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.

Example 2:
Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"

Constraints:
1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.

idea:
find LCS
then for each str1 str2 seperate add char
*/

class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        String lcs = longestCommonSubsequence(str1, str2);

        int m = str1.length();
        int n = str2.length();
        StringBuilder sb = new StringBuilder();
        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i < lcs.length(); i++) {
            while (s1 < m && str1.charAt(s1) != lcs.charAt(i)) {
                sb.append(str1.charAt(s1++));
            }
            while (s2 < n && str2.charAt(s2) != lcs.charAt(i)) {
                sb.append(str2.charAt(s2++));
            }
            sb.append(lcs.charAt(i));
            s1++;
            s2++;
        }
        sb.append(str1.substring(s1)).append(str2.substring(s2));
        return sb.toString();
    }

    public String longestCommonSubsequence(String A, String B) {
        int m = A.length();
        int n = B.length();

        String[][] dp = new String[m + 1][n + 1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], "");
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + A.charAt(i - 1);
                } else {
                    dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ?  dp[i - 1][j] : dp[i][j - 1]; 
                }
            }
        }

        return dp[m][n];
    }
}
