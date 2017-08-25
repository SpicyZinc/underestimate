/*
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same,
where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.

idea:
actually this is to longest common substring (Subsequence)
DP is for sure needed
this problem is to dp find longest common substring
*/

public class DeleteOperationForTwoStrings {
    // self written using letters[]
    // 928 / 1307 test cases passed.
    public int minDistance(String word1, String word2) {
        int[] letters = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            char c = word1.charAt(i);
            letters[c - 'a']++;
        }
        for (int i = 0; i < word2.length(); i++) {
            char c = word2.charAt(i);
            letters[c - 'a']--;
        }
        int distance = 0;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0) {
                distance += Math.abs(letters[i]);
            }
        }

        return distance;
    }
    // method DP
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) {
        	return 0;
        }

        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
        	for (int j = 0; j <= len2; j++) {
        		if (i == 0 || j == 0) {
        			dp[i][j] = 0;
        		}
        		else {
        			char c1 = word1.charAt(i - 1);
        			char c2 = word2.charAt(j - 1);
        			dp[i][j] = c1 == c2 ? (dp[i-1][j-1] + 1) : Math.max(dp[i-1][j], dp[i][j-1]);
        		}
        	}
        }

        return len1 + len2 - 2 * dp[len1][len2];
    }
}