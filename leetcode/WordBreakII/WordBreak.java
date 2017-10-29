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

	public List<String> wordBreak(String s, List<String> wordDict) {
		Map<String, List<String>> cache = new HashMap<String, List<String>>();
		return dfs(s, wordDict, cache);
	}

	public List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> cache) {
		if (cache.containsKey(s)) {
			return cache.get(s);
		}

		List<String> result = new ArrayList<String>();
		if (s.length() == 0) {
			return result;
		}

		for (int i = 1; i <= s.length(); i++) {
			String left = s.substring(0, i);
			if (wordDict.contains(left)) {
				if (i == s.length()) {
					result.add(left);
				} else {
					String right = s.substring(i);
					List<String> rightBreakdown = dfs(right, wordDict, cache);
					for (String word : rightBreakdown) {
						String sentence = left + " " + word;
						result.add(sentence);
					}
				}
			}
		}
		// memoization to save time
		cache.put(s, result);

		return result;
	}

	// self written version, TLE, 29 / 37 passed
	public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ret = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return ret;
        }        
        dfs(s, wordDict, ret, new ArrayList<String>());
        return ret;
    }
    
    public void dfs(String s, List<String> dict, List<String> ret, List<String> sentence) {
        if (s.length() == 0) {
            ret.add(convertToString(sentence));
            return;
        }
        
        for (int i = 0; i <= s.length(); i++) {
            String left = s.substring(0, i);
            if (dict.contains(left)) {
                sentence.add(left);
                dfs(s.substring(i), dict, ret, sentence);
                sentence.remove(sentence.size() - 1);
            }
        }
    }
    
    public String convertToString(List<String> list) {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                s += list.get(i) + " ";
            } else {
                s += list.get(i);
            }
        }
        
        return s;
    }
}