/*
Given many words, words[i] has weight i.
Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix).
It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

Examples:
Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1

Note:
words has length in range [1, 15000].
For each test case, up to words.length queries WordFilter.f may be made.
words[i] has length in range [1, 10].
prefix, suffix have lengths in range [0, 10].
words[i] and prefix, suffix queries consist of lowercase letters only.

idea:
since prefix, suffix are in [0, 10], for loop is 0-10
*/

class WordFilter {
	Map<String, Integer> hm = new HashMap<String, Integer>();
	public WordFilter(String[] words) {
		for (int w = 0; w < words.length; w++) {
			String word = words[w];
			int len = word.length();
			for (int i = 0; i <= 10 && i <= len; i++) {
				for (int j = 0; j <= 10 && j <= len; j++) {
					String key = word.substring(0, i) + "_" + word.substring(len - j);
					hm.put(key, w);
				}
			}
		}
	}

	public int f(String prefix, String suffix) {
		String key = prefix + "_" + suffix;
		if (hm.containsKey(key)) {
			return hm.get(key);
		} else {
			return -1;
		}
	}
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */