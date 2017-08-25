/*
Given a string S, find the length of the longest substring T that contains at most two distinct characters.
For example,
Given S = "eceba",
T is "ece" which its length is 3.

idea:
whenever hashmap size > 2, it is time to move the sliding window start position
calculate the length of any window containing 2 distinct characters,
then find the max
the default is that 2 distinct characters will be longer than 1 distinct characters

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
    	if (s == null || s.length() == 0) {
    		return 0;
    	}

    	int max = 0;
    	int start = 0;
    	HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		hm.put(c, hm.getOrDefault(c, 0) + 1);
    		if (hm.size() > 2) {
    			max = Math.max(max, i - start);
    			while (hm.size() > 2) {
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
    	}
    	return Math.max(max, s.length() - start);
    }
}