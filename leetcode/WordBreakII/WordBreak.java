/*
Given a string s and a dictionary of words dict, 
add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.
For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].

idea:
typical DFS with memoization
use a map of string <=> list of sentences to avoid duplicate calculations
dfs return List<String> instead of void
*/
import java.util.*;

public class WordBreak {
	public static void main(String[] args) {
		WordBreak eg = new WordBreak();
		String s = "catsanddog";
		List<String> dict = new ArrayList<String>();
		dict.add("cat");
		dict.add("cats");
		dict.add("and");
		dict.add("sand");
		dict.add("dog");
		
		List<String> result = eg.wordBreak(s, dict);
		for (String sentence : result) {
			System.out.println(sentence);
		}
	}
	// Sun Jun 16 01:38:40 2019
	public List<String> wordBreak(String s, List<String> wordDict) {
		Map<String, List<String>> hm = new HashMap<>();

		return dfs(s, wordDict, hm);
	}

	public List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> hm) {
		if (hm.containsKey(s)) {
			return hm.get(s);
		}

		List<String> result = new ArrayList<>();

		for (int i = 0; i <= s.length(); i++) {
			String left = s.substring(0, i);

			if (wordDict.contains(left)) {
				// note, not forget i == s.length()
				if (i == s.length()) {
					result.add(left);
				} else {
					String right = s.substring(i, s.length());
					List<String> rightWordBreak = dfs(right, wordDict, hm);

					for (String path : rightWordBreak) {
						result.add(left + " " + path);
					}
				}
			}
		}

		hm.put(s, result);

		return result;
	}

	// 03/19/2019
	public List<String> wordBreak(String s, List<String> wordDict) {
		Map<String, List<String>> hm = new HashMap<>();
		
		return dfs(s, wordDict, hm);
	}
	
	public List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> hm) {
		if (hm.containsKey(s)) {
			return hm.get(s);
		}
		
		List<String> result = new ArrayList<>();
		if (s.length() == 0) {
			return result;
		}
		
		for (int i = 0; i <= s.length(); i++) {
			String left = s.substring(0, i);
			
			if (wordDict.contains(left)) {
				if (i == s.length()) {
					result.add(left);
				} else {
					String right = s.substring(i);
					List<String> rightWordBreak = dfs(right, wordDict, hm);
					for (String path : rightWordBreak) {
						String newPath = left + " " + path;
						result.add(newPath);
					}
				}
			}
		}
		// memoization to save time
		hm.put(s, result);
		
		return result;
	}

	// self written version, TLE, 31 / 39 test cases passed
	public List<String> wordBreak(String s, List<String> wordDict) {
		List<String> result = new ArrayList<String>();
		if (s == null || s.length() == 0) {
			return result;
		}

		dfs(s, wordDict, new ArrayList<String>(), result);

		return result;
	}
	
	public void dfs(String s, List<String> dict, List<String> sentence, List<String> result) {
		if (s.length() == 0) {
			result.add(convertToString(sentence));
			return;
		}
		
		for (int i = 0; i <= s.length(); i++) {
			String left = s.substring(0, i);

			if (dict.contains(left)) {
				sentence.add(left);
				dfs(s.substring(i), dict, sentence, result);
				sentence.remove(sentence.size() - 1);
			}
		}
	}
	
	public String convertToString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i) + (i == list.size() - 1 ? "" : " ");
			sb.append(str);
		}

		return sb.toString();
	}
}