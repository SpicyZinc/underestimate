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
isSubsequence() method to help, almost TLE
need to go back for quicker O(n)
*/

class NumberOfMatchingSubsequences {
	// self 1513ms
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

		if (j == tLen) {
			return true;
		}
		return false;
	}
}