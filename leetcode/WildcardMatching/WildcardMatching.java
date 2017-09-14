/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

idea:
https://longwayjade.wordpress.com/2015/04/26/leetcode-recursion-dp-greedy-wildcard-matching/

1. greedy algorithm
p = starIdx + 1;

2. DP
(1) 1D DP
http://codeganker.blogspot.com/2014/03/wildcard-matching-leetcode.html
(2) 2D DP
note: index issues in DP problem

3. least best method:
http://gongxuns.blogspot.com/2013/01/leetcode-wildcard-matching.html
*/

public class WildcardMatching {
    public boolean isMatch(String str, String pattern) {
        int s = 0;
        int p = 0;
        int starIdx = -1; // once we found a '*', we want to record the place of the '*'.
        int match = 0; // once we found a '*', we want to start to match the rest of pattern with str,
        // starting from match; this is for remembering the place where we need to start.
         
        // we check and match every char for str.
        while (s < str.length()) {
            // 1. case 1: p matches s one character 
            if (p < pattern.length() && (str.charAt(s) == pattern.charAt(p) || pattern.charAt(p) == '?')) {
                p++;
                s++;
            } // 2. case 2: we are currently at a '*'
            else if (p < pattern.length() && pattern.charAt(p) == '*') {
                starIdx = p;
                p++;
                match = s;
            } // 3. case 3: NOT case 1, NOT case 2, but the last matched is a *, not case 1 and 2, so greedy algorithm, so advance pointer in string
            else if (starIdx != -1) {
                match++;
                s = match;
                p = starIdx + 1;
            } // 4. case 4: they do not match, do not currently at a *, and last matched is not a *, then the answer is false;
            else {
                return false;
            }
        }
        // when we finish matching all characters in str, is pattern also finished? 
        // we could only allow '*' at the rest of pattern
        while (p < pattern.length() && pattern.charAt(p) == '*') {
            p++;
        }
   
        return p == pattern.length();
    }
    // self written greedy algorithm
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        if (pLen == 0) {
            return sLen == 0;
        }
        int i = 0;
        int j = 0;
        int matchStart = 0;
        int starPos = -1;
        
        while (i < sLen) {
            if (j < pLen && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?')) {
                i++;
                j++;
            }
            else if (j < pLen && p.charAt(j) == '*') {
                starPos = j++;
                matchStart = i;
            }
            else if (starPos != -1) {
                i = ++matchStart;
                j = starPos + 1;
            }
            else {
                return false;
            }
        }
        while (j < pLen && p.charAt(j) == '*') {
            j++;
        }
        return j == pLen;
    }
    // 2D DP
    // dp[i][j] represents boolean status if to j in pattern matches to i in str
    // although not passed OJ, get the DP is more important
    public boolean isMatch(String str, String pattern) {
        int width = str.length();
        int height = pattern.length();
        boolean[][] dp = new boolean[width+1][height+1];
        dp[0][0] = true;

        for (int i = 1; i <= width; i++) {
            for (int j = 1; j <= height; j++) {
                if (str.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                }
                else if (pattern.charAt(j-1) == '*') {
                    int current = i;
                    while (current > 0) {
                        if (dp[i-1][j-1]) {
                            dp[i][j] = true;
                        }
                        current--;
                    }
                }
            }
        }
        return dp[width][height];
    }

    // passed 1705/1805
    public boolean isMatch(String s, String p) {
        return helper(s, p, 0, 0);
    }

    boolean helper(String s, String p, int l, int r) {
        if (p.length() == r) {
            return s.length() == l;
        }
        
        if (p.charAt(r) == '*') {
            // find the non-star position char in pattern
            while (r < p.length() && p.charAt(r) == '*') r++;
            while (l < s.length()) {
                if (helper(s, p, l, r)) {
                    return true;
                }
                l++;
            }
            return helper(s, p, l, r);
        }
        else if (l < s.length() && (p.charAt(r) == '?' || p.charAt(r) == s.charAt(l))) {
            return helper(s, p, l + 1, r + 1);
        }
        else {
            return false;
        }
    }
    // self written passed 1703/1805
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.charAt(0) != '*') {
            if (s.length() == 0 || p.charAt(0) != '?' && p.charAt(0) != s.charAt(0)) {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        }
        else {
            // find the non-* position in pattern
            int nonStar = 0;
            while (nonStar < p.length() && p.charAt(nonStar) == '*') {
                nonStar++;
            }
            int i = -1;
            while (i < s.length()) {
                if (isMatch(s.substring(i + 1), p.substring(nonStar))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }
}