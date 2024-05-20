/*
Given a string s consisting only of characters a, b and c.
Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

Example 2:
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

Example 3:
Input: s = "abc"
Output: 1

Constraints:
3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.

idea:
two while, and map
sliding window, head and tail, note to decrease head
*/

class NumberOfSubstringsContainingAllThreeCharacters {
    public int numberOfSubstrings(String s) {
        int[] chars = new int[26];

        int count = 0;
        int head = 0;
        int tail = 0;
        int n = s.length();

        while (tail < n) {
            char c = s.charAt(tail);
            chars[c - 'a']++;
            while (chars[0] > 0 && chars[1] > 0 && chars[2] > 0) {
                // rest chars can build (n - tail) different substring
                count += (n - tail);
                chars[s.charAt(head++) - 'a']--;
            }
            tail++;
        }

        return count;
    }
}