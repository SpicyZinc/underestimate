/*
Given a string, find the longest substring that contains only two unique characters.
For example, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is "bcbbbbcccb".

idea:
how to know it is K characters, hash map size is a good way to get k distinct characters
*/

import java.util.*;

public class LongestSubstringwithAtMostKDistinctCharacters {
	public static void main(String[] args) {
		LongestSubstringwithAtMostKDistinctCharacters eg = new LongestSubstringwithAtMostKDistinctCharacters();
		String s = "abcbbbbcccbdddadacb";
		int k = 2;
		int maxLen = eg.lengthOfLongestSubstringKDistinct(s, k);
		System.out.println(maxLen);
	}
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (k == 0 || s.length() == 0 || s == null) return 0;
		if (s.length() <= k) return s.length();

		// map to keep character - frequency pair
		Map<Character, Integer> hm = new HashMap<Character, Integer>();
		int start = 0;
		// the worst case all characters are different, so the length is k
		int maxLen = k;
		String longestSubstring = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			hm.put(c, hm.getOrDefault(c, 0) + 1);
			// have to calculate maxLen here, (i - 1) - start + 1
			if (i - start > maxLen) {
				maxLen = i - start;
				longestSubstring = s.substring(start, (i - 1) + 1);
			}
			// if the number of unique characters bigger than k, against the rule at most k distinct characters
			while (hm.size() > k) {
				char charAtStart = s.charAt(start);
				int cnt = hm.get(charAtStart);
				if (cnt >= 2) {
					hm.put(charAtStart, cnt - 1);
				} else {
					hm.remove(charAtStart);
				}
				start++;
			}
		}
		// last check out of for loop
		if (s.length() - start > maxLen) {
			longestSubstring = s.substring(start);
		}
		System.out.println(longestSubstring);

		return Math.max(maxLen, s.length() - start);
	}


	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		int[] letters = new int[256];
		int maxLen = k;
		String longestSubstring = "";
		int start = 0;
		int uniqueCnt = 0;
		for (int i = 0; i < s.length(); i++) {
			// a new unique character appears, so uniqueCnt++
			if (letters[s.charAt(i)] == 0) {
				uniqueCnt++;
			}
			letters[s.charAt(i)]++;

			char c = s.charAt(start);
			while (uniqueCnt > k && start < s.length()) {
				if (--letters[c] == 0) uniqueCnt--;
				c = s.charAt(++start);
			}

			if (i - start + 1 > maxLen) {
				maxLen = i - start + 1;
				// note, it is i + 1
				longestSubstring = s.substring(start, i + 1);
			}
		}

		return maxLen;
	}
}
