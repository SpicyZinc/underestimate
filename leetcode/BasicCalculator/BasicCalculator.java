/*
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces.

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
"2-1 + 2" = 3
"(1+(4+5+2)-3)+(6+8)" = 23

idea:
1. sign to signify + and -
when case + or -, updated sign for next step
when case ')', result =

if a value is more than one digits, while to get the value, but i--

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

    // Wed May  8 22:27:03 2019
    public int calculate(String s) {
        int n = s.length();
        char prevOperator = '+';
        int prevResult = 0;
        int result = 0;

        int num = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                int j = i;
				int parenthesesCnt = 0; // find balanced () and retrieve the inside part, recurse on it
				for (; i < n; i++) {
					if (s.charAt(i) == '(') {
						parenthesesCnt++;
					}
					if (s.charAt(i) == ')') {
						parenthesesCnt--;
					}
					if (parenthesesCnt == 0) {
						break;
					}
				}

				num = calculate(s.substring(j + 1, i));
            }

            if (isOperator(c) || i == n - 1) {
                switch (prevOperator) {
                    case '+':
                        prevResult += num;
                        break;

                    case '-':
                        prevResult -= num;
                        break;

                    case '*':
                        prevResult *= num;
                        break;

                    case '/':
                        prevResult /= num;
                        break;
                }

                if (c == '+' || c == '-' || i == n - 1) {
                    result += prevResult;
                    prevResult = 0;
                }

                prevOperator = c;
                num = 0;
            }
        }

        return result;
    }
    
    public boolean isOperator(char c) {
        return "+-*/".indexOf(c) != -1;
    }

    // method 1
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int val = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    val = val * 10 + s.charAt(i) - '0';
                    i++;
                }
                // one place to calculate result
                result += sign * val;
                // need to i--, otherwise for loop i++ will skip one i
                i--;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                // reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                // 1st pop is sign, 2nd pop is previous result
                // another place to calculate result
                result = stack.pop() * result + stack.pop();
            }
        }

        return result;
    }

    // method 2
    public int calculate(String s) {
        String tokens[] = toRPN(s).split("\\s+");
        String operators = "+-";

        int returnValue = 0;
        Stack<String> stack = new Stack<String>();

        for (String t : tokens) {
            if (!operators.contains(t)) {
                stack.push(t);
            } else {
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                int index = operators.indexOf(t);
                switch (index) {
                    case 0:
                        stack.push(String.valueOf(b + a));
                        break;
                    case 1:
                        stack.push(String.valueOf(b - a));
                        break;
                }
            }
        }

        returnValue = Integer.valueOf(stack.pop());

        return returnValue;
    }
    // must remember how convert infix expression to postfix expression
    // this is simple, only + - ( ) no * /
	public String toRPN(String input) {
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			switch (c) {
				case '+':
				case '-':
					while (!stack.isEmpty() && stack.peek() != '(') {
						sb.append(' ');
						sb.append(stack.pop());
					}
					sb.append(' ');

					stack.push(c);

					break;

				case '(':
					stack.push(c);
					break;

				case ')':
					while (!stack.isEmpty() && stack.peek() != '(') {
						sb.append(' ');
						sb.append(stack.pop());
					}
					// pop the '('
					stack.pop();

					break;

				case ' ':
					break;

				default:
					sb.append(c);
					break;
			}
		}
		// 看最后还有剩下的么
		while (!stack.isEmpty()) {
			sb.append(' ');
			sb.append(stack.pop());
		}

		return sb.toString();
	}
}