/*
Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times.
Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 10^4.

Example 1:
Input:
s = "ABAB", k = 2
Output:
4
Explanation:
Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input:
s = "AABABBA", k = 1
Output:
4
Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

idea:
sliding window
the minimal changes to convert a substring to a string with all the same characters
(length of substring - number of times of the maximum occurring character in the substring) <= k
*/

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int maxLen = 0;
        int maxOccurringCount = 0;
        int[] letters = new int[26];
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            letters[c - 'A']++;
            maxOccurringCount = Math.max(maxOccurringCount, letters[c - 'A']);
            while (end - start + 1 - maxOccurringCount > k) {
                letters[s.charAt(start) - 'A']--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }
}
