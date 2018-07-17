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
