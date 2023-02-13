/*
Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.
Return the minimum number of substrings in such a partition.
Note that each character should belong to exactly one substring in a partition.


Example 1:
Input: s = "abacaba"
Output: 4
Explanation:
Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
It can be shown that 4 is the minimum number of substrings needed.

Example 2:
Input: s = "ssssss"
Output: 6
Explanation:
The only valid partition is ("s","s","s","s","s","s").

Constraints:
1 <= s.length <= 105
s consists of only English lowercase letters.

idea:
traversing through the string, we keep record of characters in current substring,
if a character gets repeated it means we need to partition the string at that position and we now have to consider new substring starting from that position.
use a set to keep record of characters present in our current substring, if we encounter a repeated character, we increment the ans and start fresh with an empty set.
*/

class OptimalPartitionOfString {
    public int partitionString(String s) {
        int count = 1;
        Set<Character> hs = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hs.contains(c)) {
                count++;
                hs.clear();
            }
            hs.add(c);
        }

        return count;
    }
}
