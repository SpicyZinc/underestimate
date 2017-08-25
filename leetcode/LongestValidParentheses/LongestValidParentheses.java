/* 
Given a string containing just the characters '(' and ')', 
find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.
For ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

idea:
http://my.oschina.net/jdflyfly/blog/284200

cases:
"((()"
"()()()"
"())"

Use stack to record  '('  index, then check current valid length when a ')' comes;
then, max length valid parentheses is decided by two situations
1) stack is not empty, so the current length is current position i - last second position of '(' in stack,
we can calculate it through stack.pop(), then i-stack.peek() and check the length with max
2) stack is empty, then the longest length we can check currently is i-last (last is the position of last invalid ')')

lastStart represents current valid parentheses opening side starting position
*/
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s.length() < 2 || s == null) {
            return 0;
        }
        int max = 0;
        int lastStart = 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            else {
                if (stack.isEmpty()) {
                    lastStart = i + 1;
                }
                else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(i - lastStart + 1, max);
                    }
                    else {
                        max = Math.max(i - stack.peek(), max);
                    }
                }
            }
        }
        return max;
    }
}