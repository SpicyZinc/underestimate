/*
Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Example 1:
Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.

Example 2:
Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.

Example 3:
Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.

Constraints:
1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= s.length

idea:
sliding window
*/

class MaximumNumberOfVowelsInASubstringOfGivenLength {
    public int maxVowels(String s, int k) {
        int left = 0;
        int right = 0;
        int size = s.length();

        int max = 0;
        int count = 0;

        while (right < size) {
            if (isVowel(s.charAt(right))) {
                count++;
            }

            if (right - left + 1 == k) {
                max = Math.max(max, count);
                // update so far vowels count
                if (isVowel(s.charAt(left++))) {
                    count--;
                }
            }
            right++;
        }

        return max;
    }

    public boolean isVowel(char c) {
        String vowels = "aeiou";
        return vowels.contains("" + c);
    }
}
