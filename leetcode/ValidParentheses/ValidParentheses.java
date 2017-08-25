/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

idea:
Stack is used to only store left parentheses
Scan the String,
whenever meeting left parenthesis, push to stack, pop whenever meeting right parenthesis.
if not match, return false;
if stack not empty, return false.
*/

import java.util.*;

public class ValidParentheses {
    public static void main(String[] args) {
        ValidParentheses eg = new ValidParentheses();
        boolean result = eg.isValid("()");
        System.out.println(result);
    }
    // self recent written
    public boolean isValid(String s) {
        if (s.length() == 0 || s == null) {
            return false;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        String left = "({[";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (left.indexOf(c) != -1) {
                stack.push(c);
            }
            else {
                if (!stack.isEmpty()) {
                    char temp = stack.pop();
                    switch (temp) {
                        case '(':
                            if (c != ')') {
                                return false;
                            }
                            break;
                        case '[':
                            if (c != ']') {
                                return false;
                            }
                            break;
                        case '{':
                            if (c != '}') {
                                return false;
                            }
                            break;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } 
            else {
                if (stack.size() == 0) {
                    return false;
                }
                char temp = stack.pop();
                if (ch == ')') {
                    if (temp != '(') return false;
                }
                else if (ch == '}') {
                    if (temp != '{') return false;
                }
                else if (ch == ']') {
                    if (temp != '[') return false;
                }
            }
        }

        return stack.size() == 0;
    }
}