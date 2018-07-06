/*
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "".
If there are multiple such minimum-length windows, return the one with the left-most starting index.

Notice
All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
Example
Given S = "abcdebdde", T = "bde" Return "bcde"

Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.

idea:
https://www.cnblogs.com/grandyang/p/8684817.html
dp[i][j] 表示范围S中前i个字符包含范围T中前j个字符的子串的起始位置

substring 子串
subsequence 子序列

when S[i] == T[j]
实际上起始位置和 dp[i - 1][j - 1] 是一样的
e.g. dbd包含bd的起始位置和db包含b的起始位置一样
when S[i] != T[j]
其实是和 dp[i - 1][j] 是一样的
e.g. dbd包含b的起始位置和db包含b的起始位置是一样

S = "dbd", T = "bd"

S (i)	T (j)
db     	b
dbd     bd


S (i)	T (j)
db    	b
dbd     b (d != b)
*/

import java.util.*;

public class MinimumWindowSubsequence {
	public static void main(String[] args) {
		MinimumWindowSubsequence eg = new MinimumWindowSubsequence();
		String S = "abcdebdde";
		String T = "bde";
		String minWindow = eg.minWindow(S, T);

		System.out.println(minWindow);
	}

	public String minWindow(String S, String T) {
        int m = S.length();
        int n = T.length();
        
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
		for (int i = 0; i <= m; i++) {
			// the position of first i characters in S contain empty string in T
			dp[i][0] = i;
		}
        
        int minLen = Integer.MAX_VALUE;
        int start = -1;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= Math.min(i, n); j++) {
            	// dp[3][1] S.charAt(2) == T.charAt(0)
                dp[i][j] = S.charAt(i - 1) == T.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j];
            }
            if (dp[i][n] != -1) {
                int len = i - dp[i][n];
                if (minLen > len) {
                    minLen = len;
                    start = dp[i][n];
                }
            }
        }
        
        return start == -1 ? "" : S.substring(start, start + minLen);
    }
}