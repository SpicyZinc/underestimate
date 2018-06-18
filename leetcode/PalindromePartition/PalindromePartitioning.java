/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return
  [
    ["aa","b"],
    ["a","a","b"]
  ]

idea:
typical dfs
note: remove(Object o), removes the first occurrence of the specified element from this list, if it is present.			
Make a copy of each oneList which is one example of ArrayList<String>() to avoid messed reference
*/

import java.util.*;

public class PalindromePartition {
	public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        dfs(s, 0, new ArrayList<String>(), result);
        
        return result;
    }
    
    public void dfs(String s, int pos, List<String> path, List<List<String>> result) {
        if (pos == s.length()) {
            List<String> copy = new ArrayList<String>(path);
            result.add(copy);
        }
        
        for (int i = pos + 1; i <= s.length(); i++) {
            String subStr = s.substring(pos, i);
            if (isPalindrome(subStr)) {
                path.add(subStr);
                dfs(s, i, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String s) {
        int n = s.length();
        int i = 0;
        while (i < n / 2) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                return false;
            }
            i++;
        }
        
        return true;
    }

    // a little different
    public List<List<String>> partition(String s) {
        if (s.length() == 0 || s == null) {
            return null;
        }
        List<List<String>> ret = new ArrayList<>();
        partitionHelper(s, new ArrayList<String>(), ret);

        return ret;
    }

    public void partitionHelper(String rest, List<String> path, List<List<String>> ret) {
        // base case
        if (rest.length() == 0) {
            ret.add(new ArrayList<String>(path));
        }

        int length = rest.length();
        for (int i = 1; i <= length; i++) {
            String current = rest.substring(0, i);
            if ( isPalindrome(current) ) {
                path.add(current);
                partitionHelper(rest.substring(i, length), path, ret);
                path.remove(path.size() - 1);
            }
        }
    }
}


