/*
Given a string s and a dictionary of words dict, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

idea:
1. recursion
http://www.programcreek.com/2012/12/leetcode-solution-word-break/

2. dp
Define an array t[] such that t[i] == true => 0-(i-1) can be segmented using dictionary
Initial state t[0] == true

*/

public class WordBreak {
    // recursive, TLE
    public boolean wordBreak(String s, List<String> wordDict) {
        for (int i = 1; i <= s.length(); i++) {
            String word = s.substring(0, i);
            if (wordDict.contains(word)) {
                if ( word.length() == s.length() || wordBreak(s.substring(i), wordDict) ) {
                    return true;
                }
            }
        }
        return false;
    }
    // method 2, dp
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i] i of chars in s can be breakable and found in dict or not
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String substr = s.substring(j, i);
                // f(n) = f(0,i) + f(i,j) + f(j,n)
                if (dp[j] && wordDict.contains(substr)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}