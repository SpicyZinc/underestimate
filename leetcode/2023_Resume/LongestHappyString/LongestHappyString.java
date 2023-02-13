/*
A string s is called happy if it satisfies the following conditions:

s only contains the letters 'a', 'b', and 'c'.
s does not contain any of "aaa", "bbb", or "ccc" as a substring.
s contains at most a occurrences of the letter 'a'.
s contains at most b occurrences of the letter 'b'.
s contains at most c occurrences of the letter 'c'.
Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

A substring is a contiguous sequence of characters within a string.

Example 1:
Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.

Example 2:
Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It is the only correct answer in this case.

Constraints:
0 <= a, b, c <= 100
a + b + c > 0

idea:
greedy
not very generic
*/

class LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int n = a + b + c;
        int continuousA = 0;
        int continuousB = 0;
        int continuousC = 0;

        for (int i = 0; i < n; i++) {
            if (a >= b && a >= c && continuousA != 2 || continuousB == 2 && a > 0 || continuousC == 2 && a > 0) {
                sb.append("a");
                a--;
                continuousA++;
                continuousB = 0;
                continuousC = 0;
            } else if (b >= a && b >= c && continuousB != 2 || continuousA == 2 && b > 0 || continuousC == 2 && b > 0) {
                sb.append("b");
                b--;
                continuousB++;
                continuousA = 0;
                continuousC = 0;
            } else if (c >= a && c >= b && continuousC != 2 || continuousA == 2 && c > 0 || continuousB == 2 && c > 0) {
                sb.append("c");
                c--;
                continuousC++;
                continuousA = 0;
                continuousB = 0;
            }
        }

        return sb.toString();
    }
}