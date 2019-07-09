/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

idea:
http://blog.csdn.net/u011095253/article/details/9248073

1. recursion
2. DP
one thing to note:
String's index is based on 0;
dp[m+1][n+1] is based on 1.
			s2
		-------------------
		|
	 s1 |
		|
		|
		|
*/

public class InterleavingString {  
	public boolean isInterleave(String s1, String s2, String s3) {

	}

	// DFS with memo
	// hashset 里存着所有匹配不成功的 直接 return false
	public boolean isInterleave(String s1, String s2, String s3) {
		if (s1.length() + s2.length() != s3.length()) {
			return false;
		}

		return isInterleaveHelper(s1, 0, s2, 0, s3, 0, new HashSet<String>());  
	}  
	public boolean isInterleaveHelper(String s1, int p1, String s2, int p2, String s3, int p3, Set<String> hs) {  
		String key = p1 + "-" + p2;

		if (hs.contains(key)) {
			return false;
		}
		
		if (p3 == s3.length()) {
			return true;
		}
		if (p1 == s1.length()) {
			return s2.substring(p2).equals(s3.substring(p3));  
		}
		if (p2 == s2.length()) {
			return s1.substring(p1).equals(s3.substring(p3));  
		}

		if (s1.charAt(p1) == s3.charAt(p3) && isInterleaveHelper(s1, p1 + 1, s2, p2, s3, p3 + 1, hs) ||
			s2.charAt(p2) == s3.charAt(p3) && isInterleaveHelper(s1, p1, s2, p2 + 1, s3, p3 + 1, hs)
		) {
			return true;
		}
		
		hs.add(key);

		return false;
	}
	// DP
	public boolean isInterleave(String s1, String s2, String s3) {
		int l1 = s1.length();
		int l2 = s2.length();
		int l3 = s3.length();
		
		if (l1 + l2 != l3) {
			return false;
		}

		// first i letters in s1 and first j letters in s2 can interleave to s3 or not
		boolean[][] dp = new boolean[l1 + 1][l2 + 1];
		// initialize
		dp[0][0] = true;
		for (int i = 1; i <= l1; i++) {
			if (s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0]) {
				dp[i][0] = true;
			}
		}
		for (int j = 1; j <= l2; j++) {
			if (s2.charAt(j - 1) == s3.charAt(j - 1) && dp[0][j - 1]) {
				dp[0][j] = true;
			}
		}
		// populate the rest
		for (int i = 1; i <= l1; i++) {
			for (int j = 1; j <= l2; j++) {
				if ( s1.charAt(i - 1) == s3.charAt(i - 1 + j) && dp[i - 1][j] ) {
					dp[i][j] = true;
				}
				if ( s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1] ) {
					dp[i][j] = true;
				}
			}
		}

		return dp[l1][l2];
	}
}