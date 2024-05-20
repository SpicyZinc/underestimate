/*
A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.

Example 1:
Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".

Example 2:
Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.

Example 3:
Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".

Constraints:
1 <= source.length, target.length <= 1000
source and target consist of lowercase English letters.


idea:
贪心 尽最可能多得match source
不行了 从下个位置 继续全部 从头match source
*/
class ShortestWayToFormString {
    public int shortestWay(String source, String target) {
        int count = 0;
        int size = target.length();
        int j = 0; // target index

        while (j < size) {
            int prevJ = j;
            // greedily matching as many as chars possible from source to target
            j = isSubsequence(source, target, j);
            // or no helper, just embedded into here 
            // for (int s = 0; s < source.length(); s++) {
            //     if (j < size && source.charAt(s) == target.charAt(j)) {
            //         j++;
            //     }
            // }
            // if we are not able to find for match for the char from target
            // in source we won't be able to form target
            if (j == prevJ) {
                return -1;
            }
            count++;
        }

        return count;
    }
    // t is subsequence of s, and return the position of t ends in s
    public int isSubsequence(String s, String t, int startIdx) {
        int j = startIdx;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (j < t.length() && t.charAt(j) == c) {
                j++;
            }
        }

        return j;
    }
}
