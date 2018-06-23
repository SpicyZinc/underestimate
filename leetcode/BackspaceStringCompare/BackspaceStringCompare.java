/*
Given two strings S and T, return if they are equal when both are typed into empty text editors.
# means a backspace character.

Example 1:
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".

Example 2:
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".

Example 3:
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".

Example 4:
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".

Note:
1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.

Follow up:
Can you solve it in O(N) time and O(1) space?

idea:
use stack is the best
encounter #, just pop the same number of letter
if letter, push to the stack 
*/

class BackspaceStringCompare {
	public boolean backspaceCompare(String S, String T) {
		if (S.length() == 0 && T.length() == 0)  {
			return true;
		}

		if (S.length() == 0 || T.length() == 0)  {
			return false;
		}

		String ss = simplyString(S);
		String tt = simplyString(T);

		return ss.equals(tt);
	}

	public String simplyString(String S) {
		int len = S.length();
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < len; i++) {
			char c = S.charAt(i);
			if (c == '#') {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else {
				stack.push(c);
			}
		}

		return stack.toString();
	}
}