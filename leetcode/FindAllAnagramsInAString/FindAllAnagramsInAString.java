/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:
Input: s: "cbaebabacd" p: "abc"
Output: [0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input: s: "abab" p: "ab"
Output: [0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

idea:
1. helper function to check if two strings are anagrams,
then loop through s to see any substring is anagram to p, then saves the index
2. https://discuss.leetcode.com/topic/64434/shortest-concise-java-o-n-sliding-window-solution/2
should see sliding window template
https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/
*/

public class FindAllAnagramsInAString {
	// Sun Apr 16 23:15:34 2023
	public List<Integer> findAnagrams(String s, String p) {
        int[] hash = new int[256];

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            hash[c]++;
        }

        int left = 0;
        int right = 0;
        int count = p.length();
        int size = s.length();

        List<Integer> result = new ArrayList<>();

        while (right < size) {
            if (hash[s.charAt(right)] >= 1) {
                count--;
            }
            hash[s.charAt(right)]--;
            right++;

            if (count == 0) {
                result.add(left);
            }

            if (right - left == p.length()) {
                if (hash[s.charAt(left)] >= 0) {
                    count++;
                }
                // since refilled
                hash[s.charAt(left)]++;
                // this leftChar not belonging to this window, so need to go to right by 1
                left++;
            }
        }

        return result;
    }

	// Sun Jun  2 20:13:16 2019
	public List<Integer> findAnagrams(String s, String p) {
        int len = p.length();
        String normalized = normalize(p);
        
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i <= s.length() - len; i++) {
            String str = s.substring(i, i + len);
            String normalizedStr = normalize(str);

            if (normalizedStr.equals(normalized)) {
                list.add(i);
            }
        }
        
        return list;
    }
    
    public String normalize(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        
        return new String(chars);
    }

	// Wed May 22 00:56:42 2019
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> list = new ArrayList<>(); 
		int pLen = p.length();
		if (pLen > s.length()) {
			return list;
		}

		for (int i = 0; i <= s.length() - pLen; i++) {
			String subStr = s.substring(i, i + pLen);
			if (isAnagram(subStr, p)) {
				list.add(i);
			}
		}

		return list;
	}

	public boolean isAnagram(String s, String t) {
		int[] letters = new int[26];

		for (int i = 0; i < s.length(); i++) {
			char sc = s.charAt(i);
			char tc = t.charAt(i);
			letters[sc - 'a']++;
			letters[tc - 'a']--;
		}
		for (int letter : letters) {
			if (letter != 0) {
				return false;
			}
		}

		return true;
	}

	// method 2
	public List<Integer> findAnagrams(String s, String p) {
	    List<Integer> list = new ArrayList<>();
	    if (s == null || s.length() == 0 || p == null || p.length() == 0) {
	    	return list;
	    }
	    int[] hash = new int[256];
	    for (int i = 0; i < p.length(); i++) {
	        char c = p.charAt(i);
	        hash[c]++;
	    }

	    int left = 0;
	    int right = 0;
	    int count = p.length();

	    while (right < s.length()) {
	        if (hash[s.charAt(right++)]-- >= 1) {
	        	count--; 
	        }
	        // Find an anagram
	        if (count == 0) {
	        	list.add(left);
	        }
	        if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) {
	        	count++;
	        }
	    }

	    return list;
	}

	// this case, always maintain a window of size p length
	public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        if (s.length() == 0 || s == null || p.length() == 0 || p == null) {
            return result;
        }
        int[] hash = new int[256];
        for (int i = 0; i < p.length(); i++) {
            hash[p.charAt(i)]++;
        }

        int left = 0;
        int right = 0;
        int count = p.length();
        while (right < s.length()) {
            if (hash[s.charAt(right)] >= 1) count--;
        	// decrease the hash
			hash[s.charAt(right)]--;
            right++;

            if (count == 0) result.add(left);
	        // maintain the fixed sized window
	        if (right - left == p.length()) {
                if (hash[s.charAt(left)] >= 0) count++;
            	// refill the hash
                hash[s.charAt(left)]++;
                left++;
	        }
        }

        return result;
    }
}
