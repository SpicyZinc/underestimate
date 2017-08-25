/*
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.

Example 2:
Input: "aba"
Output: False

Example 3:
Input: "abcabcabcabc"
Output: True

Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

idea:
direct thought and implement it
*/

public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String str) {
        if (str.length() == 0 || str == null) {
        	return true;
        }
        for (int i = 1; i < str.length(); i++) {
        	char c = str.charAt(i);
        	if (c == str.charAt(0)) {
        		int repeatedPatternLength = i;
        		if (str.length() % repeatedPatternLength != 0) {
        			continue;
        		}
        		if (helper(str, repeatedPatternLength)) {
        			return true;
        		}
        	}
        }
        return false;
    }

    private boolean helper(String s, int l) {
    	int parts = s.length() / l;
    	for (int i = 1; i < parts; i++) {
    		if (!s.substring(l * i, l + l * i).equals(s.substring(l * (i-1), l * i))) {
    			return false;
    		}
    	}

    	return true;
    }
}