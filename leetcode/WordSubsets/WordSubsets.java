/*
We are given two arrays A and B of words. Each word is a string of lowercase letters.
Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.
For example, "wrr" is a subset of "warrior", but is not a subset of "world".

Now say a word a from A is universal if for every b in B, b is a subset of a. 
Return a list of all universal words in A.  You can return the words in any order.

Example 1:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
Output: ["facebook","google","leetcode"]

Example 2:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
Output: ["apple","google","leetcode"]

Example 3:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
Output: ["facebook","google"]

Example 4:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
Output: ["google","leetcode"]

Example 5:
Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
Output: ["facebook","leetcode"]

Note:
1 <= A.length, B.length <= 10000
1 <= A[i].length, B[i].length <= 10
A[i] and B[i] consist only of lowercase letters.
All words in A[i] are unique: there isn't i != j with A[i] == A[j].

idea:
reduce strings in B to a bigger one
*/

class WordSubsets {
	public List<String> wordSubsets(String[] A, String[] B) {
		int[] bMax = new int[26];

		for (String s : B) {
			int[] bCharsCount = count(s);
			for (int i = 0; i < bCharsCount.length; i++) {
				bMax[i] = Math.max(bMax[i], bCharsCount[i]);
			}
		}

		List<String> universalWords = new ArrayList<>();
		for (String s : A) {
			int[] aCharsCount = count(s);
			boolean isUniversal = true;
			for (int i = 0; i < aCharsCount.length; i++) {
				if (aCharsCount[i] < bMax[i]) {
					isUniversal = false;
					break;
				}
			}
			if (isUniversal) {
				universalWords.add(s);
			}
		}

		return universalWords;
	}

	public int[] count(String s) {
		int[] letters = new int[26];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			letters[c - 'a']++;
		}

		return letters;
	}

	// TLE
	public List<String> wordSubsets(String[] A, String[] B) {
		List<String> universalWords = new ArrayList<>();
		for (String str : A) {
			boolean isUniversal = true;
			for (String s : B) {
				if (!isUniversal(str, s)) {
					isUniversal = false;
					break;
				}
			}
			if (isUniversal) {
				universalWords.add(str);
			}
		}

		return universalWords;
	}

	public boolean isUniversal(String word, String s) {
		int[] letters = new int[26];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			letters[c - 'a']++;
		}
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (letters[c - 'a'] > 0) {
				letters[c - 'a']--;
			}
		}
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] != 0) {
				return false;
			}
		}

		return true;
	}
}