/*
Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".
Here, we have groups, of adjacent letters that are all the same character, and adjacent characters to the group are different.
A group is extended if that group is length 3 or more,
so "e" and "o" would be extended in the first example, and "i" would be extended in the second example.
As another example, the groups of "abbcccaaaa" would be "a", "bb", "ccc", and "aaaa";
and "ccc" and "aaaa" are the extended groups of that string.

For some given string S, a query word is stretchy if it can be made to be equal to S by extending some groups.
Formally, we are allowed to repeatedly choose a group (as defined above) of characters c,
and add some number of the same character c to it so that the length of the group is 3 or more.

Note that we cannot extend a group of size one like "h" to a group of size two like "hh" -
all extensions must leave the group extended - ie., at least 3 characters long.

Given a list of query words, return the number of words that are stretchy.
Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.

Notes:
0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters

idea:
helper method isStretchy to loop both S and word to see if word is stretchy
*/

class ExpressiveWords {
	public int expressiveWords(String S, String[] words) {
		int count = 0;
		for (String word : words) {
			if (isStretchy(S, word)) {
				count++;
			}
		}

		return count;
	}
	// can word be stretched to S?
	public boolean isStretchy(String S, String word) {
		int sLen = S.length();
		int wordLen = word.length();

		if (wordLen == sLen && !S.equals(word) || wordLen > sLen) {
			return false;
		}

		int i = 0;
		int j = 0;

		while (i < sLen && j < wordLen) {
			if (S.charAt(i++) != word.charAt(j++)) {
				return false;
			}
			// cntS means number of consecutive chars in Stretch starting from i - 1
			// cntO means number of consecutive chars in word starting from j - 1
			int cntS = 1; // stretchy word
			int cntO = 1; // origin word
			
			while (i < sLen && S.charAt(i - 1) == S.charAt(i)) {
				i++;
				cntS++;
			}

			while (j < wordLen && word.charAt(j - 1) == word.charAt(j)) {
				j++;
				cntO++;
			}

			if (cntO == cntS || cntS >= 3) {
				continue;
			}

			return false;
		}

		return i == sLen && j == wordLen;
	}
}