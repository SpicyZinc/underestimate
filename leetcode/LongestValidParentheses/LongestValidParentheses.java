/* 
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1: 
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"

Example 2: 
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"

idea:
http://my.oschina.net/jdflyfly/blog/284200
https://leetcode.com/problems/longest-valid-parentheses/discuss/14278/Two-Java-solutions-with-explanation.-Stack-and-DP.-Short-and-easy-to-understand.

cases:
"((()"
"()()()"
"())"

1. dp
dp[i] == 以 s[i]为结尾的 longest valid parentheses substring 的长度
动态方程 dp[i] = dp[i - 1] + 2;
()(()) i - dp[i - 1] > 0 也要考虑 dp[i - dp[i - 1]]

2. stack
Use stack to record  '('  index, then check current valid length when a ')' comes;
maxLen length valid parentheses is decided by two situations
1) stack is not empty, so the current length is current position i - last second position of '(' in stack,
we can calculate it through stack.pop(), then i - stack.peek() and check the length with maxLen
2) stack is empty, then the longest length we can check currently is i - last (last is the position of last invalid ')')

prevLeft represents current valid parentheses opening side starting position
*/
class LongestValidParentheses {
	// Fri Jul  5 15:42:49 2019
	public int longestValidParentheses(String s) {
		int size = s.length();
		int[] dp = new int[size];

		int leftCnt = 0;
		int maxLen = 0;

		for (int i = 0; i < size; i++) {
			char c = s.charAt(i);

			if (c == '(') {
				leftCnt++;
			}

			if (c == ')' && leftCnt > 0) {
				dp[i] = dp[i - 1] + 2;

				dp[i] += i - dp[i] >= 0 ? dp[i - dp[i]] : 0;
				// if (i - 2 - dp[i - 1] > 0) {
				// 	dp[i] = dp[i - 2 - dp[i - 1]] + dp[i - 1] + 2;
				// }

				// 减少左括号的数量
				leftCnt--;
			}

			maxLen = Math.max(maxLen, dp[i]);
		}

		return maxLen;
	}

	// stack
	public int longestValidParentheses(String s) {
		if (s.length() < 2 || s == null) {
			return 0;
		}

		int maxLen = 0;
		int left = 0;
		
		Stack<Integer> stack = new Stack<>();
		int size = s.length();
		
		for (int i = 0; i < size; i++) {
			char c = s.charAt(i);
			
			if (c == '(') {
				stack.push(i);
			} else {
				// always consider if stack isEmpty()
				if (stack.isEmpty()) {
					left = i + 1;
				} else {
					stack.pop();

					if (stack.isEmpty()) {
						maxLen = Math.max(maxLen, i - left + 1);
					} else {
						maxLen = Math.max(maxLen, i - stack.peek());
					}
				}
			}
		}
		
		return maxLen;
	}
}