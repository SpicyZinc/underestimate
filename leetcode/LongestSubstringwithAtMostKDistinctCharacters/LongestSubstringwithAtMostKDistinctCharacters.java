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
	// Wed Jun 19 02:29:45 2019
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s.length() == 0 || s == null) {
			return 0;
		}
		
		int left = 0;
		
		int[] letters = new int[256];
		
		int uniqueCnt = 0;
		
		int max = 0;
		
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			letters[c]++;
		
			if (letters[c] == 1) {
				uniqueCnt++;
			}
			
			while (uniqueCnt > k && left < s.length()) {
				if (--letters[s.charAt(left)] == 0) {
					uniqueCnt--;
				}
				left++;
			}
			
			max = Math.max(max, right - left + 1);
		}
		
		return s.length() < k ? s.length() : max;
	}
	// 01/26/2019
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s.length() == 0 || s == null) {
			return 0;
		}
		
		if (k == 0) {
			return 0;
		}
		
		int[] letters = new int[256];
		int left = 0;
		int max = k;
		int uniqueCnt = 0;
		
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			
			letters[c]++;
			if (letters[c] == 1) {
				uniqueCnt++;
			}
			
			while (uniqueCnt > k && left < s.length()) {
				// move window's left
				if (--letters[s.charAt(left)] == 0) {
					uniqueCnt--;
				}
				left++;
			}

			max = Math.max(max, right - left + 1);
		}
		
		return s.length() < k ? s.length() : max;
	}

	// typical sliding window
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		int n = s.length();
		
		Map<Character, Integer> hm = new HashMap<>();
		
		int left = 0;
		int right = 0;
		// the worst case all characters are different, so the length is k
		int maxLen = k;
		for (; right < n; right++) {
			char c = s.charAt(right);
			hm.put(c, hm.getOrDefault(c, 0) + 1);

			while (hm.size() > k) {
				char leftChar = s.charAt(left);
				int cnt = hm.get(leftChar);
				// note >= 2
				if (cnt >= 2) {
					hm.put(leftChar, cnt - 1);
				} else {
					hm.remove(leftChar);
				}
				
				left++;
			}

			maxLen = Math.max(maxLen, right - left + 1);
		}
		
		return maxLen;
	}
}
