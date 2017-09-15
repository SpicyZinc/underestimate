/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

idea:
recursion
https://codeganker.blogspot.com/2014/02/regular-expression-matching-leetcode.html

For the 1st case, if the first char of pattern is not ".", the first char of string and pattern should be the same
Then continue to match the rest
For the 2nd case, if the first char of pattern is "." or first char of pattern == the ith char of string,
continue to match the rest

dp version
https://gist.github.com/chenwu054/6978342
*/
public class RegularExpressionMatching {
    // method 1 with better understanding
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        // two cases
        // 1. 2nd char of p is NOT *
        // 2. 2nd char of p is *
        if (p.length() == 1 || p.charAt(1) != '*') {
            // 1st of pattern is '.' or equals 1st of s
            if (s.length() > 0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))) {
                return isMatch(s.substring(1), p.substring(1));
            } else {
                return false;
            }
            // if ( s.length() == 0 || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0)) ) {
            //     return false;
            // }
            // return isMatch( s.substring(1), p.substring(1) );   
        } else {
            // i == -1 is to cover the whole s
            // because 2nd char of p is '*', e.g. "aab", "c*a*b" 
            int i = -1;
            
            // while first character of p equals first character of s, p.charAt(0) == s.charAt(i) or p.charAt(0) == '.'
            // because second char of p is *, can cover to match all repeating i chars in s
            // so if rest chars in s s[i], s[i+1], ... s[i+k] match rest chars in p, then the whole s and p are matching
            // give a try for all possibilities, recursion in while loop, i starts from -1
            
            while (i < s.length() && (i == -1 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                // each i match once, because the part till 2nd char of p (*) have been matched
                // if rest match, then p and s match
                if (isMatch(s.substring(i + 1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }

    // method 2
    public boolean isMatch(String s, String p) {
        if (s.length() == 0) return p.length()>1 && p.length()%2==0 ? p.charAt(1)== '*' && isMatch(s, p.substring(2)) : p.length()==0;
        if (p.isEmpty()) return false;
        char c1 = s.charAt(0);
        char c2 = p.charAt(0);
        char c2next = p.length() > 1 ? p.charAt(1) : 'x';
        if (c2next == '*') {
            if (isSame(c1,c2)) {
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            }
            else { 
                return isMatch(s, p.substring(2));
            }
        }
        else {
            if (isSame(c1, c2)) {
                return isMatch(s.substring(1), p.substring(1));
            }
            else {
                return false;
            }
        }        
    }
    public boolean isSame (char c1, char c2) {
        return c2 == '.' || c1 == c2;
    }
}