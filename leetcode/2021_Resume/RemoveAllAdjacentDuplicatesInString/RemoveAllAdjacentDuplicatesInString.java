/*
You are given a string s consisting of lowercase English letters.
A duplicate removal consists of choosing two adjacent and equal letters and removing them.
We repeatedly make duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.


Example 1:
Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".

Example 2:
Input: s = "azxxzy"
Output: "ay"
 

Constraints:
1 <= s.length <= 105
s consists of lowercase English letters.


idea: use stack
2. 直接用 StringBuilder 可以从后面操作

*/
import java.util.*;


class RemoveAllAdjacentDuplicatesInString {
    public static void main(String[] args) {
        RemoveAllAdjacentDuplicatesInString eg = new RemoveAllAdjacentDuplicatesInString();

        String s = "abbaca";
        String result = eg.removeDuplicates(s);

        System.out.println(result);

    }

    public String removeDuplicates(String s) {
        if (s.length() == 0 || s == null) {
            return s;
        }

        int size = s.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < size ; i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && c == stack.peek()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String removeDuplicates(String s) {
        int i = 0, n = s.length();
        char[] res = s.toCharArray();
        for (int j = 0; j < n; j++, i++) {
            res[i] = res[j];
            if (i > 0 && res[i - 1] == res[i]) {
                i -= 2;
            }
        }

        return new String(res, 0, i);
    }
}