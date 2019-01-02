/*
Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression.
You can always assume that the given expression is valid and only consists of digits 0-9, ?, :,
T and F (T and F represent True and False respectively).

Note:
The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.

Example 1:
Input: "T?2:3"
Output: "2"
Explanation: If true, then result is 2; otherwise result is 3.

Example 2:
Input: "F?1:T?4:5"
Output: "4"
Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"

Example 3:
Input: "T?T?F:5:3"
Output: "F"
Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"

idea:
Since the input is promised to be valid, use stack
note, check from the end of the string to the front.

As long as the next char is not '?', push it to the stack.
If we see a '?', check the next char (left of '?' in the string), 
    pop the top 3 elements from the stack.
    if the next char is T
        push the first element of the 3 elements back to stack
    else 
        push the 3rd element of the 3 elements back to stack
pop the only item left in the stack
*/

import java.util.*;

public class TernaryExpressionParser {
	public static void main(String[] args) {
		TernaryExpressionParser eg = new TernaryExpressionParser();
		String s = "F ? 1 : T ? 4 : 5";
		String t = "T ? T ? F : 5 : 3";
		String resultS = eg.parseTernary(s);
		String resultT = eg.parseTernary(t);

		System.out.println("s evaluated to be == " + resultS);
		System.out.println("t evaluated to be == " + resultT);
	}

	public String parseTernary(String expression) {
		if (expression.length() == 0 || expression == null) {
			return null;
		}

		expression = expression.trim();
		Stack<Character> stack = new Stack<>();
		// from back to front to walk through the string
		int i = expression.length() - 1;
		while (i >= 0) {
			char c = expression.charAt(i);
			if (!stack.isEmpty() && stack.peek() == '?') {
				// first, pop ? out of stack
				stack.pop();
				char first = stack.pop();
				// second, pop : out of stack
				stack.pop();
				char second = stack.pop();
				char evaluatedTernaryExpression = c == 'T' ? first : second;
				stack.push(evaluatedTernaryExpression);
			} else {
				stack.push(c);
			}
			i--;
		}

		return String.valueOf(stack.peek());
	}
}
