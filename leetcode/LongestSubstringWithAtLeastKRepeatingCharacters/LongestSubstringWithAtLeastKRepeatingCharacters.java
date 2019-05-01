/*
Find the length of the longest substring T of a given string (consists of lowercase letters only)
such that every character in T appears no less than k times.

Example 1:
Input: s = "aaabb", k = 3
Output: 3
The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input: s = "ababbc", k = 2
Output: 5
The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

idea:
recursion

In each step, find the infrequent elements (appear less than k times) as splits
since any of these infrequent elements couldn't be any part of the substring we want.

my idea ignores a fact that using character of less than k to split the string,
the characters of >= k will spread into the arrays, cannot guarantee T has at least k times
*/

public class LongestSubstringWithAtLeastKRepeatingCharacters {
	public static void main(String[] args) {
		LongestSubstringWithAtLeastKRepeatingCharacters eg = new LongestSubstringWithAtLeastKRepeatingCharacters();
		int max = eg.longestSubstring("ababacb", 3);
		System.out.println("max == " + max);
	}

	// Sun Apr 28 00:45:02 2019
    public int longestSubstring(String s, int k) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        if (k == 0) {
        	return s.length();
        }

        // record each character appearing times, can also use letters[26] as hash
        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        // there is no one letter appearing < k times
        boolean noCharLessThanKTime = true;
        for (int frequency : hm.values()) {
            if (frequency < k) {
                noCharLessThanKTime = false;
            }
        }
        if (noCharLessThanKTime) {
        	return s.length();
        }
        
        int maxLen = 0;
        int left = 0;
        // because loop from i = 0, first char less than k times,
        // substring before this char guarantees that no one char in this substring less than k times
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int frequency = hm.get(c);
            // find a letter appearing less than k times
            if (frequency < k) {
                String precedingSubstr = s.substring(left, i);
                maxLen = Math.max(maxLen, longestSubstring(precedingSubstr, k));
                left = i + 1;
            }
        }
        // not forget last one
        return Math.max(maxLen, longestSubstring(s.substring(left), k));
    }

    // my idea
    public int longestSubstring(String s, int k) {
    	if (s.length() == 0 || s == null) {
    		return 0;
    	}
    	if (k == 0) {
    		return s.length();
    	}

    	HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			hm.put(c, hm.getOrDefault(c, 0) + 1);
		}
		boolean flag = true;
		for (int i = 0; i < chars.length; i++) {
			char c = s.charAt(i);
			if (hm.get(c) < k) {
				flag = false;
			}
		}
		if (flag == true) {
			return s.length();
		}

		int start = 0;
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (hm.containsKey(c)) {
				result.add(s.substring(start, i));
				start = i + 1;
			}
		}
		int max = 0;
		for (String ss : result) {
			max = Math.max(max, ss.length());
		}

		return max;
    }
}