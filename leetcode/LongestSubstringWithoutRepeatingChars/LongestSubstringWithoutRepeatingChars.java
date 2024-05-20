/*
Given a string, find the length of the longest substring without repeating characters.

Examples:
Given "abcabcbb", the answer is "abc", which the length is 3.
Given "bbbbb", the answer is "b", with the length of 1.
Given "pwwkew", the answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

similar:
Substring with Concatenation of All Words, Minimum Window Substring

idea:
1. 187 ms
longest unique substring should be based on a fact that
e.g. ABCDEC... first candidate ABCDE, then next candidate should starts from D

hashmap to record char <-> index pair
whenever there is duplicate char, get index, for loop will get index + 1
then start from index + 1

note: not forget the whole string could be a unique string, so Math.max(maxLen, s.length());

2. 45 ms
char <-> boolean pair, and value is boolean
sliding window
ABCDE------A
start will be moved to A's position
meanwhile all B and other chars to second A not inclusive will be set false, j starts from left + 1
*/

public class LongestSubstringWithoutRepeatingChars {
    // Wed Apr 19 00:43:32 2023
    public int lengthOfLongestSubstring(String s) {
        int size = s.length();
        int left = 0;
        int right = 0;

        Set<Character> hs = new HashSet<>();

        int max = 0;

        for (; right < size; right++) {
            char c = s.charAt(right);
            if (hs.contains(c)) {
                int len = right - left;
                char leftChar = s.charAt(left);
                // Find the point which has duplicate char
                while (leftChar != c) {
                    hs.remove(leftChar);
                    left++;
                    leftChar = s.charAt(left);
                }
                left++;

                max = Math.max(max, len);
            } else {
                hs.add(c);
            }
        }

        max = Math.max(max, size - left);

        return max;
    }
    // Mon Mar  6 23:15:50 2023
    public int lengthOfLongestSubstring(String s) {
        Set<Character> hs = new HashSet<>();
        int size = s.length();
        int left = 0;
        int max = 0;
        for (int i = 0; i < size; i++) {
            char c = s.charAt(i);

            if (hs.contains(c)) {
                int len = i - left;
                max = Math.max(max, len);

                char leftChar = s.charAt(left);
                while (leftChar != c) {   
                    hs.remove(leftChar);
                    left++;
                    leftChar = s.charAt(left);
                }
                left++;
            } else {
                hs.add(c);
            }
        }

        max = Math.max(max, size - left);

        return max;
    }
    // Sun Jun 23 17:32:05 2019
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }
        
        Set<Character> hs = new HashSet<>();
        
        int left = 0;
        int max = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (hs.contains(c)) {
                max = Math.max(max, right - left);

                while (s.charAt(left) != c) {
                    hs.remove(s.charAt(left++));
                }
                left += 1;
            } else {
                hs.add(c);
            }
        }
        
        max = Math.max(max, s.length() - left);
        
        return max;
    }

    // Sat Jun 15 01:36:18 2019
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        Set<Character> hs = new HashSet<>();

        int left = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (hs.contains(c)) {
                max = Math.max(max, i - left);

                int j = left;

                for (; j < i; j++) {
                    if (s.charAt(j) == c) {
                        break;
                    }
                    // where to remove, matters,
                    // 第一个重复的移出去了 第二个就没有必要了
                    hs.remove(s.charAt(j));
                }

                left = j + 1;

            } else {
                hs.add(c);
            }
        }
        max = Math.max(max, s.length() - left);

        return max;
    }

    // Thu May  9 23:28:27 2019
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        Set<Character> hs = new HashSet<>();
        int maxLen = Integer.MIN_VALUE;

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (hs.contains(c)) {
                
                for (int j = left; j < right; j++) {
                    if (s.charAt(j) == c) {
                        left = j + 1;
                        break;
                    }
                    // don't forget remove
                    hs.remove(s.charAt(j));
                }
            } else {
                hs.add(c);
            }
            // where to compare is the key
            int len = right - left + 1;
            if (len > maxLen) {
                maxLen = len;
            }
        }

        return maxLen == Integer.MIN_VALUE ? s.length() : maxLen;
    }

    // 01/26/2019
    // right moving, left conditionally increases
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        int left = 0;
        int max = Integer.MIN_VALUE;
        Set<Character> hs = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (hs.contains(c)) {
                for (int i = left; i < right; i++) {
                    if (s.charAt(i) == c) {
                        left = i + 1;
                        break;
                    }
                    hs.remove(s.charAt(i));
                }
            } else {
                hs.add(c);
            }
            max = Math.max(max, right - left + 1);
        }
        
        return max == Integer.MIN_VALUE ? 1 : max;
    }
    // 01/13/2019
    public int lengthOfLongestSubstring(String s) {
        int[] letters = new int[256];
        
        int maxLen = 0;
        int right = 0;

        for (int left = 0; left < s.length(); left++) {
            // 还没有出现过
            while (right < s.length() && letters[s.charAt(right)] == 0) {
                letters[s.charAt(right)]++;
                right++;    
            }
            maxLen = Math.max(maxLen, right - left);
            // 开始移动 left window
            letters[s.charAt(left)]--;
        }
        
        return maxLen;
    }
    // method 1
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }
        
        int maxLen = 0;
        Map<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hm.containsKey(c)) {
                // reset i, because for loop i++, so it will start from i + 1
                i = hm.get(c);
                maxLen = Math.max(maxLen, hm.size());
                hm.clear();
            } else {
                hm.put(c, i);
            }
        }
        
        return Math.max(maxLen, hm.size());
    }
    // method 2
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s == null) {
            return 0;
        }

        boolean[] letters = new boolean[256];
        int left = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (letters[c]) {
                // find the new window left position
                for (int j = left; j < i; j++) {
                    if (c == s.charAt(j)) {
                        // update the window start position
                        left = j + 1;
                        // it has to break early, in the new substring, letters[c] should be true;
                        // if not break, it will be set to be false
                        break;
                    }
                    // window left moves, so remove all chars to left of the window from map
                    letters[s.charAt(j)] = false;
                }
            } else {
                letters[c] = true;
            }

            maxLen = Math.max(maxLen, i - left + 1);
        }
        
        return maxLen;
    }

    // hashset
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int startsHere = 0;
        int maxLen = 0;
        Set<Character> hs = new HashSet<Character>();
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
            } else {
                hs.add(c);
            }
            maxLen = Math.max(maxLen, i - startsHere + 1);
        }

        return maxLen;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] letters = new int[256];
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (letters[c] >= 1) {
                for (int j = start; j < i; j++) {
                    if (s.charAt(j) == c) {
                        start = j + 1;
                        break;
                    }
                    letters[s.charAt(j)]--;
                }
            } else {
                letters[c]++;
            }
            maxLen = Math.max(maxLen, i - start + 1);
        }

        return maxLen;
    }
}