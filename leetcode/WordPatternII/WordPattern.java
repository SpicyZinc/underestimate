/*
Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:

pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
 
Notes:
You may assume both pattern and str contains only lowercase letters.

idea:
http://www.programcreek.com/2014/07/leetcode-word-pattern-ii-java/
dfs()
*/

import java.util.*;

public class WordPattern {
	public static void main(String[] args) {
		// String pattern = "abab", str = "redblueredblue";
		// String pattern = "aaaa", str = "asdasdasdasd";
		String pattern = "aabb", str = "xyzabcxzyabc";
		WordPattern eg = new WordPattern();
		boolean result = eg.wordPatternMatch(pattern, str);
		System.out.println(pattern + " " + str + " == " + result);
	}

	public boolean wordPatternMatch(String pattern, String str) {
		if (pattern.length() == 0 && str.length() == 0) return true;

		Map<Character, String> hm = new HashMap<Character, String>();
		return dfs(pattern, str, 0, 0, hm);
	}

	public boolean dfs(String pattern, String str, int i, int j, Map<Character, String> hm) {
		if (i == pattern.length() && j == str.length()) {
			return true;
		}
		if (i >= pattern.length() || j >= str.length()) {
			return false;
		}
		char c = pattern.charAt(i);
		// note k <= str.length()
		for (int k = j + 1; k <= str.length(); k++) {
			String word = str.substring(j, k);
			if (!hm.containsKey(c) && !hm.containsValue(word)) {
				hm.put(c, word);
				if (dfs(pattern, str, i + 1, k, hm)) return true;
				hm.remove(c);
			}
			else if (hm.containsKey(c) && hm.get(c).equals(word)) {
				if (dfs(pattern, str, i + 1, k, hm)) return true;
			}
		}

		return false;
	}
}
