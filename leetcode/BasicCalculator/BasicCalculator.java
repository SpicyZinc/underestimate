/*
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
"2-1 + 2" = 3
"(1+(4+5+2)-3)+(6+8)" = 23

idea:
1. sign, number two variables
sign to signify + -, when case + -, updated sign for next step use
2. convert infix to RPN (postfix), calculate the postfix by stack
*/

import java.util.*;

public class BasicCalculator {
    public static void main(String[] args) {
        BasicCalculator eg = new BasicCalculator();
        // String s = "1 + 1 + 1+ 1";
        String s = "(1+(4+5+2)-3)+(6+8)";
        // String s = "1 + 1";
        int result = eg.calculate(s);
        System.out.println(result);
    }
    // 24 / 37 test cases passed
    public int calculate(String s) {
        if (s.equals("0")) {
            return 0;
        }
        String[] matches = s.split("(?=[+-])");
        int result = 0;
        for (String match : matches) {
            result += Integer.parseInt( match.replaceAll("[()\\s]", "") );
        }
        return result;
    }
    // method 1
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int sign = 1;
        int number = 0;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (int) (c - '0');
            }
            else if (c == '+') {
                result += sign * number;
                sign = 1;
                number = 0;
            }
            else if (c == '-') {
                result += sign * number;
                sign = -1;
                number = 0;
            }
            else if (c == '(') {
                stack.push(result); // saved to stack, so reset result = 0
                stack.push(sign);
                // reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            }
            else if (c == ')') {
                result += sign * number;
                number = 0; // set number to signify if last character is )
                result = stack.pop() * result;
                result = stack.pop() + result;
            }
        }
        if (number != 0) result += sign * number;

        return result;
    }

    public int calculate(String s) {
        String tokens[] = toRPN(s).split("\\s+");
        int returnValue = 0;
        String operators = "+-";

        Stack<String> stack = new Stack<String>();

        for (String t : tokens) {
            if (!operators.contains(t)) {
                stack.push(t);
            }
            else {
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                int index = operators.indexOf(t);
                switch(index) {
                    case 0:
                        stack.push(String.valueOf(a+b));
                        break;
                    case 1:
                        stack.push(String.valueOf(b-a));
                        break;
                }
            }
        }

        returnValue = Integer.valueOf(stack.pop());

        return returnValue;
    }
    // must remember how convert infix expression to postfix expression
    // this is simple, only + - ( ) no * /
    public static String toRPN(String input) {
        char[] in = input.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < in.length; i++)
            switch (in[i]) {
                case '+':
                case '-':
                    while (!stack.empty() && stack.peek() != '(') {
                        out.append(' ');
                        out.append(stack.pop());
                    }
                    out.append(' ');
                    stack.push(in[i]);
                    break;
                case '(':
                    stack.push(in[i]);
                    break;
                case ')':
                    while (!stack.empty() && stack.peek() != '(') {
                        out.append(' ');
                        out.append(stack.pop());
                    }
                    stack.pop();
                    break;
                case ' ':
                    break;
                default:
                    out.append(in[i]);
                    break;
            }

        while (!stack.isEmpty()) {
            out.append(' ');
            out.append(stack.pop());

        }
        return out.toString();
    }
}