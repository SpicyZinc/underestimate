/*
A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.
For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.
Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.

Example 1:
Input: "(()())(())"
Output: "()()()"
Explanation: 
The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
After removing outer parentheses of each part, this is "()()" + "()" = "()()()".

Example 2:
Input: "(()())(())(()(()))"
Output: "()()()()(())"
Explanation: 
The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".

Example 3:
Input: "()()"
Output: ""
Explanation: 
The input string is "()()", with primitive decomposition "()" + "()".
After removing outer parentheses of each part, this is "" + "" = "".
 
Note:
S.length <= 10000
S[i] is "(" or ")"
S is a valid parentheses string

idea:
count open and close parentheses count
*/

class RemoveOutermostParentheses {
	public String removeOuterParentheses(String S) {
		StringBuilder sb = new StringBuilder();
		int open = 0;
		int close = 0;
		int start = 0;

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (c == '(') {
				open++;
			}
			if (c == ')') {
				close++;
			}

			if (open == close) {
				// peel off the outmost parentheses
				sb.append(S.substring(start + 1, i));
				start = i + 1;
			}
		}

		return sb.toString();
	}
}