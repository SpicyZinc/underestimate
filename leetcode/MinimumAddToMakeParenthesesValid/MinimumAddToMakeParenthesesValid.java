/*
Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions )
so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:
It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

Example 1:
Input: "())"
Output: 1

Example 2:
Input: "((("
Output: 3

Example 3:
Input: "()"
Output: 0

Example 4:
Input: "()))(("
Output: 4
 
Note:
S.length <= 1000
S only consists of '(' and ')' characters.

idea:
use stack
) pushed to stack
pop() 一定要看空不空
所以 空 作为一个 if clause
表明缺少一个matching的 )

if encounter '(', push '(' into stack;
otherwise, ')', check if there is a matching '(' on top of stack; if no, increase the counter by 1; if yes, pop it out;
after the loop, count in the un-matched characters remaining in the stack.
*/

class MinimumAddToMakeParenthesesValid {
	public int minAddToMakeValid(String S) {
		int count = 0;
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);

			if (c == '(') {
				stack.push(c);
			} else if (stack.isEmpty()) {
				// 缺少matching的 ')'
				count++;
			} else {
				stack.pop();
			}
		}

		return stack.size() + count;
	}
}
