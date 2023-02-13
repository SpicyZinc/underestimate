/*
You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.
Return the number of good splits you can make in s.

Example 1:
Input: s = "aacaba"
Output: 2
Explanation: There are 5 ways to split "aacaba" and 2 of them are good. 
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.

Example 2:
Input: s = "abcd"
Output: 1
Explanation: Split the string as follows ("ab", "cd").

Example 3:
Input: s = "aaaaa"
Output: 4
Explanation: All possible splits are good.

Example 4:
Input: s = "acbadbaada"
Output: 2

Constraints:
s contains only lowercase English letters.
1 <= s.length <= 10^5

idea:
like leftSum, rightSum
*/

class NumberOfGoodWaysToSplitAString {
    public int numSplits(String s) {
        int numberOfGoodWays = 0;

        int leftUnique = 0;
        int rightUnique = 0;

        int[] leftChars = new int[26];
        // 先记录所有的char 然后再减 循环时
        int[] rightChars = new int[26];

        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (++rightChars[c - 'a'] == 1) {
                rightUnique++;
            }
        }

        for (char c : chars) {
            if (++leftChars[c - 'a'] == 1) {
                leftUnique++;
            }
            if (--rightChars[c - 'a'] == 0) {
                rightUnique--;
            }

            // leftUnique > rightUnique
            // 没有可能 再 相等了 因为之后 是 leftUnique 只能增加
            if (leftUnique > rightUnique) {
                return numberOfGoodWays;
            }

            if (leftUnique == rightUnique) {
                numberOfGoodWays++;
            }
        }

        return numberOfGoodWays;
    }
}
