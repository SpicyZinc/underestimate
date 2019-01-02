/*
Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example:
Input: 
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".

Note:
All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].

idea:
1. isSubsequence() method to help, TLE
2. https://www.cnblogs.com/grandyang/p/9201323.html, bucket
*/

class NumberOfMatchingSubsequences {
	// TLE, 46 / 49 test cases passed
	public int numMatchingSubseq(String S, String[] words) {
		int cnt = 0;
		for (String word : words) {
			if (isSub(S, word)) {
				cnt++;
			}
		}

		return cnt;
	}

	// check if t is subsequence of s
	public boolean isSub(String s, String t) {
		int sLen = s.length();
		int tLen = t.length();

		int i = 0;
		int j = 0;

		while (i < sLen && j < tLen) {
			if (s.charAt(i) == t.charAt(j)) {
				j++;
			}
			i++;
		}

		return j == tLen;
	}

	// method 2
	public int numMatchingSubseq(String S, String[] words) {
		Map<Character, List<Pair>> hm = new HashMap<>();

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			Pair pair = new Pair(i, 1);
			char firstChar = word.charAt(0);
			if (hm.containsKey(firstChar)) {
				List<Pair> list = hm.get(firstChar);
				list.add(pair);
			} else {
				List<Pair> list = new ArrayList<Pair>();
				list.add(pair);
				hm.put(firstChar, list);
			}
		}

		int cnt = 0;

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			List<Pair> pairs = hm.get(c);
			
			if (pairs == null) {
				continue;
			}
			
			hm.remove(c);

			for (Pair pair : pairs) {
				if (pair != null) {
					int idx = pair.index;
					int next = pair.nextPos;

					if (next == words[idx].length()) {
						cnt++;
					} else {
						char nextChar = words[idx].charAt(next);
						pair.nextPos += 1;
						
						if (hm.containsKey(nextChar)) {
							hm.get(nextChar).add(pair);
						} else {
							List<Pair> newList = new ArrayList<>();
							newList.add(pair);
							hm.put(nextChar, newList);
						}
					}
				}
			}
		}

		return cnt;
	}
}

class Pair {
	int index;
	int nextPos;

	public Pair(int index, int nextPos) {
		this.index = index;
		this.nextPos = nextPos;
	}
}