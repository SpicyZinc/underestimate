/*
The power of the string is the maximum length of a non-empty substring that contains only one unique character.

Given a string s, return the power of s.

Example 1:
Input: s = "leetcode"
Output: 2
Explanation: The substring "ee" is of length 2 with the character 'e' only.

Example 2:
Input: s = "abbcccddddeeeeedcba"
Output: 5
Explanation: The substring "eeeee" is of length 5 with the character 'e' only.

Constraints:
1 <= s.length <= 500
s consists of only lowercase English letters.

idea: one loop while loop. pointer
*/

class ConsecutiveCharacters {
    public int maxPower(String s) {
        int i = 1;
        char current = s.charAt(0);
        int count = 1;
        int maxCount = count;

        while (i < s.length()) {
            if (current == s.charAt(i)) {
                count++;
            } else {
                if (count > maxCount) {
                    maxCount = count;
                }
                count = 1;
            }
            current = s.charAt(i);
            i++;
        }

        if (i == s.length()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }

        return maxCount;
    }
}