/*
Given a string S, find the length of the longest substring T that contains at most two distinct characters.
For example,
Given S = "eceba",
T is "ece" which its length is 3.

idea:
whenever hashmap size > 2, it is time to move the sliding window's start position
calculate the length of any window containing 2 distinct characters,
then find the max
the default is that 2 distinct characters will be longer than 1 distinct character

http://blog.csdn.net/whuwangyi/article/details/42451289#
*/

import java.util.*;

public class LongestSubstringwithAtMostTwoDistinctCharacters {
	public static void main(String[] args) {
		LongestSubstringwithAtMostTwoDistinctCharacters eg = new LongestSubstringwithAtMostTwoDistinctCharacters();
		int len = eg.lengthOfLongestSubstringTwoDistinct("eceba");
		System.out.println(len);
	}

	public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() <= 1) {
            return s.length();
        }
        
        int n = s.length();
        Map<Character, Integer> hm = new HashMap<>();
        
        int left = 0;
        int right = 0;
        // the worst case all characters are different, so the length is k
        int k = 2;
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