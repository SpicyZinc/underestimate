/*
You are given two strings word1 and word2.
Merge the strings by adding letters in alternating order, starting with word1.
If a string is longer than the other, append the additional letters onto the end of the merged string.

Return the merged string.

Example 1:
Input: word1 = "abc", word2 = "pqr"
Output: "apbqcr"
Explanation: The merged string will be merged as so:
word1:  a   b   c
word2:    p   q   r
merged: a p b q c r

Example 2:
Input: word1 = "ab", word2 = "pqrs"
Output: "apbqrs"
Explanation: Notice that as word2 is longer, "rs" is appended to the end.
word1:  a   b 
word2:    p   q   r   s
merged: a p b q   r   s

Example 3:
Input: word1 = "abcd", word2 = "pq"
Output: "apbqcd"
Explanation: Notice that as word1 is longer, "cd" is appended to the end.
word1:  a   b   c   d
word2:    p   q 
merged: a p b q c   d
 
Constraints:
1 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.

idea:
依靠 StringBuilder length() 来决定 alternating order
*/

class MergeStringsAlternately {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        if (m == 0) {
            return word2;
        }
        
        if (n == 0) {
            return word1;
        }
        
        int i = 0;
        int j = 0;

        StringBuilder sb = new StringBuilder();
        while (i < m || j < n) {
            int size = sb.length();
            if (size % 2 == 0) {
                sb.append(word1.charAt(i++));
            } else {
                sb.append(word2.charAt(j++));
            }
            if (i == m) {
                sb.append(word2.substring(j));
                break;
            }
            if (j == n) {
                sb.append(word1.substring(i));
                break;
            }
        }

        return sb.toString();
    }
}