/*
Given a string, find the longest substring that contains only two unique characters.
For example, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is "bcbbbbcccb".

idea:
how to compare against K, hash map size is a good way to get k distinct characters
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
		int maxLen = k;
		String longestSubstring = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			hm.put(c, hm.getOrDefault(c, 0) + 1);
			// have to put calculation of maxLen here, just use i, otherwise (i-1) - start + 1
			if (i - start > maxLen) {
				maxLen = i - start;
				longestSubstring = s.substring(start, i);
			}
			// if time of unique characters bigger than k, against the rule at most k distinct characters
			while (hm.size() > k) {
				char charAtStart = s.charAt(start);
				int cnt = hm.get(charAtStart);
				if (cnt >= 2) {
					hm.put(charAtStart, cnt - 1);
				}
				else {
					hm.remove(charAtStart);
				}
				start++;
			}
		}
		if (s.length() - start > maxLen) {
			longestSubstring = s.substring(start);
		}
		System.out.println(longestSubstring);
		return Math.max(maxLen, s.length() - start);
	}


	public int lengthOfLongestSubstringKDistinct(String s, int k) {
	    int[] count = new int[256];
	    int i = 0;  // i will be behind j
	    int num = 0;
	    int res = 0;
	    for (int j = 0; j < s.length(); j++) {
	    	// if count[s.charAt(j)] == 0, it is a distinct character
	        if (count[s.charAt(j)] == 0) num++;
	        count[s.charAt(j)]++;
	        while (num > k && i < s.length()) {
	            count[s.charAt(i)]--;
	            if (count[s.charAt(i)] == 0){ 
	                num--;
	            }
	            i++;
	        }
	        res = Math.max(res, j - i + 1);
	    }

	    return res;    
    }
}
