/*
Given a string s and a string t, check if s is subsequence of t.
You may assume that there is only lower case English letters in both s and t.
t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters
without disturbing the relative positions of the remaining characters.
(ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"
Return true.

Example 2:
s = "axc", t = "ahbgdc"
Return false.

idea:
on the t's sequence remove char in s
return if s is empty,
since s is immutable, so have to use a queue
note: Queue<Character> queue = new LinkedList<Character>();
queue.add()
queue.remove()
follow up, https://zhuhan0.blogspot.com/2017/07/leetcode-392-is-subsequence.html
*/

public class IsSubsequence {
	// 02/07/2019
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0 || s == null) {
            return true;
        }

        int j = 0;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            
            if (j == s.length()) {
                return true;
            }

            if (c == s.charAt(j)) {
                j++;
            }
        }

        return j == s.length();
    }
    // 12 ms
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0 || s == null) {
            return true;
        }
        
        int n = s.length();
        int m = t.length();
        
        int i = 0;
        int j = 0;
        
        while (i < m) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
                if (j == n) {
                    return true;
                }
            }
            i++;            
        }
        
        return false;
    }
    // 34 ms
    public boolean isSubsequence(String s, String t) {
    	if (s == null || s.length() == 0 ) {
            return true;
        }
        if (t.length() == 0 || t == null) {
            return false;
        }
        Queue<Character> queue = new LinkedList<Character>();
        for (int i = 0; i < s.length(); i++) {
        	queue.add(s.charAt(i));
        }

        for (int i = 0; i < t.length(); i++) {
        	char c = t.charAt(i);
        	if (!queue.isEmpty() && queue.peek() == c) {
        		queue.remove();
        	}
        }

        return queue.isEmpty();
    }
}