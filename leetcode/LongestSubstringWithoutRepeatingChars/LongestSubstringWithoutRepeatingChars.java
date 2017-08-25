/*
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
For "bbbbb" the longest substring is "b", with the length of 1.

similar:
Substring with Concatenation of All Words，Minimum Window Substring

idea:

no matter map or char[] is used, key - value pair value is boolean

-----second time to think over
sliding window
if found out a character which appears before
AB------A
start will be moved to A's position
meanwhile all B to second A not inclusive will be set false, since the new substring is starting from 2nd A 
use hashmap<Character, Integer> or boolean[] to record

-----1st time to think over
Greedy algorithm, from the starting point to ending point, it takes O(n)
use a boolean array "isExist[]" to record if a character appears or not

loop through the char array
遇到没有出现过的字符, 将isExist[]对应位置标记, 并且子串长度+1
if this char not appears before, now it appears, mark boolean array to be "true"
else meeting a char which appears before, 
	loop from j through i exclusive, find which position is the one appearing before
	set isExist[] back to be false, and j starts from k+1
	re-calculate the max length of the longest substring without repeating characters
	Math.max(i - j, maxLen)

O(n) algorithm:
http://blog.csdn.net/linhuanmars/article/details/19949159
*/
public class LongestSubstringWithoutRepeatingChars {
    // self written using hashset, hashset only contains unique character or no repeating chars
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int startsHere = 0;
        int max = 0;
        HashSet<Character> hs = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hs.contains(c)) {
                for (int j = startsHere; j < i; j++) {
                    if (s.charAt(j) == c) {
                        startsHere = j + 1;
                        break;
                    }
                    hs.remove(s.charAt(j));
                }
            }
            else {
                hs.add(c);
            }
            max = Math.max(max, i - startsHere + 1);
        }

        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int maxLength = 0;
        int windowStart = 0;
        HashMap<Character, Boolean> hm = new HashMap<Character, Boolean>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hm.get(c) != null && hm.get(c)) {
                for (int j = windowStart; j < i; j++) {
                    if (s.charAt(j) == c) {
                        windowStart = j + 1;
                        break;
                    }
                    hm.put(s.charAt(j), false);
                }
            }
            else {
                hm.put(c, true);
            }
            maxLength = Math.max( i - windowStart + 1, maxLength );
        }
        
        return maxLength;
    }

    // self written version, no need to c - '0'
    // not as index - boolean, it is index - integer (frequency)
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] letters = new int[256];
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (letters[c] >= 1) {
                // find new start
                for (int j = start; j < i; j++) {
                    if (s.charAt(j) == c) {
                        // update the start position
                        start = j + 1;
                        break;
                    }
                    // those logged letter corresponding minus 1
                    letters[s.charAt(j)]--;
                }
            }
            else {
                letters[c]++;
            }
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }
    // use hashmap to contain <Character, Index>, time out, O(n^3)
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
 		int maxLength = 0;

 		for (int i = 0; i < chars.length; i++) {
 			char c = chars[i];
 			if ( hm.containsKey(c) ) {
 				i = hm.get( c );
 				maxLength = Math.max(maxLength, hm.size());
 				hm.clear();
 			}
 			else {
 				hm.put( c, i );
 			}
 		}
        
        return maxLength;
    }
}
