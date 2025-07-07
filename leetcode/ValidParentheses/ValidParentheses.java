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
    // 2025
    public boolean isValid(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (c == ']' && stack.peek() == '[' ||
                    c == ')' && stack.peek() == '(' ||
                    c == '}' && stack.peek() == '{'
                    ) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }
    // 2025
    public boolean isValid(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();
        int openCount = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
                openCount++;
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (c == ']' && stack.peek() == '[' ||
                    c == ')' && stack.peek() == '(' ||
                    c == '}' && stack.peek() == '{'
                    ) {
                        stack.pop();
                    }
                }
            }
        }

        return openCount * 2 == n && stack.isEmpty();
    }
    // Mon Apr 10 13:38:37 2023
    public boolean isValid(String s) {
        Map<Character, Character> hm = new HashMap<>();
        hm.put('[', ']');
        hm.put('(', ')');
        hm.put('{', '}');

        Stack<Character> stack =new Stack<>();

        String leftParts = "({[";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (leftParts.indexOf(c) != -1) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char popped = stack.pop();
                    if (hm.get(popped) != c) {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }
    // Bloomberg NYC
    public boolean isValid(String s) {
        if (s.length() == 0 || s == null) {
            return true;
        }

        if (s.length() % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<Character>();
        Map<Character, Character> hm = new HashMap<>();
        hm.put(')', '(');
        hm.put(']', '[');
        hm.put('}', '{');
        
        String openParts = "([{";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty() && openParts.indexOf(c) == -1) {
                return false;
            } else if (openParts.indexOf(c) != -1) {
                stack.push(c);
            } else {
                char supposedMatch = hm.get(c);
                char top = stack.peek();
                
                if (top != supposedMatch) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        return stack.isEmpty();        
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
            } else {
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
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                char temp = stack.pop();
                if (ch == ')') {
                    if (temp != '(') return false;
                } else if (ch == '}') {
                    if (temp != '{') return false;
                } else if (ch == ']') {
                    if (temp != '[') return false;
                }
            }
        }

        return stack.size() == 0;
    }

    // self best version
    // Thu May 27 19:54:09 2021
    public boolean isValid(String s) {
        // "()"
        Map<Character, Character> match = new HashMap<>();
        match.put(')', '(');
        match.put(']', '[');
        match.put('}', '{');
        
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
 
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char supposedToMatch = match.get(c);
                char popped = stack.pop();
                if (popped != supposedToMatch) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}