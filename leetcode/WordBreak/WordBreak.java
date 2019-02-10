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
Define an array dp[] such that dp[i] == true => 0-(i-1) substring(0, i) in dict
dp[i] i of chars in s can be breakable and found in dict or not
Initial state dp[0] == true
*/

public class WordBreak {
    // 02/07/2019 TLE, 29/36
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (wordDict.contains(sub) && wordBreak(s.substring(i), wordDict)) {
                return true;
            }
        }
        
        return false;
    }
    // 07/08/2018 TLE
    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, 0, wordDict);
    }
    
    public boolean dfs(String s, int pos, List<String> wordDict) {
        if (pos == s.length()) {
            return true;
        }
        
        for (int i = pos + 1; i <= s.length(); i++) {
            String word = s.substring(pos, i);
            if (wordDict.contains(word)) {
                if (dfs(s, i, wordDict)) {
                    return true;
                }
            }
        }
        
        return false;
    }
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
        int size = s.length();
        
        boolean[] dp = new boolean[size + 1];
        // initialization
        dp[0] = true;
        
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < i; j++) {
                String word = s.substring(j, i); // actually [j + 1, i] if not array index
                if (dp[j] && wordDict.contains(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[size];
    }
}