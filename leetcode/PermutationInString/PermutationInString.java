/*
Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
In other words, one of the first string's permutations is the substring of the second string.

Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").

Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False

Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

idea:
https://discuss.leetcode.com/topic/87884/8-lines-slide-window-solution-in-java/2
note: this is to get substring NOT subsequence, must be consecutive
1. digging hole, and fill it out
    挖的坑填上
    没挖的填上超出要减去
2. basic idea, 2 steps
	How do we know string p is a permutation of string s?
	Easy, each character in p is in s too. So we can abstract all permutation strings of s to a map (Character -> Count). i.e. abba -> {a:2, b:2}.
	Since there are only 26 lower case letters in this problem, we can just use an array to represent the map.
	
	How do we know string s2 contains a permutation of s1? We just need to create a sliding window with length of s1,
    move from beginning to the end of s2.
	When a character moves in from right of the window, we subtract 1 to that character count from the map.
	When a character moves out from left of the window, we add 1 to that character count.
	So once we see all zeros in the map, meaning equal numbers of every characters between s1 and the substring in the sliding window, we know the answer is true.
*/

public class PermutationInString {
	// method 1
    public boolean checkInclusion(String s1, String s2) {
        int[] map = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i)]--;
        }
        int left = 0;
        for (int right = 0; right < s2.length(); right++) {
            if (++map[s2.charAt(right)] >= 1) {
                while (--map[s2.charAt(left++)] < 0) {}
            } else if (right - left + 1 == s1.length()) {
                return true;
            }
        }
        return s1.length() == 0;
    }
    // method 2
    public boolean checkInclusion(String s1, String s2) {
    	if (s1.length() == 0 || s1 == null) {
    		return true;
    	}
    	if (s1.length() > s2.length()) {
    		return false;
    	}

        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            count[c1 - 'a']++;
            count[c2 - 'a']--;
        }
        if (allZero(count)) {
        	return true;
        }
        int window = s1.length(); // why start with i = window, because length of window chars have been coped with
        for (int i = window; i < s2.length(); i++) {
        	char end = s2.charAt(i);
        	char start = s2.charAt(i - window);
        	count[end - 'a']--;
        	count[start - 'a']++;
        	if (allZero(count)) {
        		return true;
        	}
        }
        return false;
    }

    private boolean allZero(int[] count) {
    	for (int i = 0; i < count.length; i++) {
    		if (count[i] != 0) {
    			return false;
    		}
    	}

    	return true;
    }
}