/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
If possible, output any possible result. If not possible, return the empty string.

Example 1:
Input: S = "aab"
Output: "aba"

Example 2:
Input: S = "aaab"
Output: ""

Note:
S will consist of lowercase letters and have length in range [1, 500].

idea:
1. get each char's frequency
2. generate new string, each position, try with 26, put the most frequent letter but less than previous one in the result
3. remember to update chars[]-- 
*/

class ReorganizeString {
	public String reorganizeString(String S) {
		int size = S.length();
		int[] chars = new int[26];

		for (int i = 0; i < size; i++) {
			char c = S.charAt(i);
			chars[c - 'a']++;
		}

		// populate the new reorganized string
		char[] result = new char[size];

		for (int i = 0; i < size; i++) {
			int index = -1;
			int max = 0;
			// for each position try against each letter
			for (int j = 0; j < 26; j++) {
				if (chars[j] > max && (i == 0 || result[i - 1] - 'a' != j)) {
					max = chars[j];
					index = j;
				}
			}
			// 如果没有符合情况的 early return
			if (max == 0) {
				return "";
			}
			// find index, since it is 26, means found char letter
			result[i] = (char) (index + 'a');
			// used once, decrease the count
			chars[index]--;
		}

		return new String(result);
	}
}