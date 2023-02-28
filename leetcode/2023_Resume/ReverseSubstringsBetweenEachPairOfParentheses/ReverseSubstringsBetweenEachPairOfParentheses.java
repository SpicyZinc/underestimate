/*
You are given a string s that consists of lower case English letters and brackets.
Reverse the strings in each pair of matching parentheses, starting from the innermost one.
Your result should not contain any brackets.


Example 1:
Input: s = "(abcd)"
Output: "dcba"

Example 2:
Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.

Example 3:
Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.


Constraints:
1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.

idea:
stack is the clear way
*/

class ReverseSubstringsBetweenEachPairOfParentheses {
    // Tue Feb 14 18:26:15 2023
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        String substringBetweenEachPair = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ')') {
                while (stack.peek() != '(') {
                    substringBetweenEachPair += stack.pop();
                }
                // Pop the '('
                stack.pop();
                for (int j = 0; j < substringBetweenEachPair.length(); j++) {
                    stack.push(substringBetweenEachPair.charAt(j));
                }
                // Reset substringBetweenEachPair
                substringBetweenEachPair = "";
            } else {
                stack.push(c);
            }
        }

        String result = "";
        while (!stack.empty()) {
            result = stack.pop() + result;
        }

        return result;
    }

    // Smart, not sure, O(n)
    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> opened = new Stack<>();
        int[] pair = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(')
                opened.push(i);
            if (s.charAt(i) == ')') {
                int j = opened.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int d = 1;
        for (int i = 0; i < n; i += d) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pair[i];
                d = -d;
            } else {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}



