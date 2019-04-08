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
then, maxLen length valid parentheses is decided by two situations
1) stack is not empty, so the current length is current position i - last second position of '(' in stack,
we can calculate it through stack.pop(), then i-stack.peek() and check the length with maxLen
2) stack is empty, then the longest length we can check currently is i-last (last is the position of last invalid ')')

prevLeft represents current valid parentheses opening side starting position
*/
public class LongestValidParentheses {
	// 03/18/2019
	// dp[i]：以s[i-1]为结尾的longest valid parentheses substring的长度
	public int longestValidParentheses(String s) {
		char[] chars = s.toCharArray();
		int n = chars.length;

		int[] dp = new int[n];
		int left = 0;
		int maxLen = 0;

		for (int i = 0; i < n; i++) {
			if (chars[i] == '(') {
				left++;
			}

			if (chars[i] == ')' && left > 0) {
				dp[i] = dp[i - 1] + 2;
				if (i - 2 - dp[i - 1] > 0) {
					dp[i] = dp[i - 2 - dp[i - 1]] + dp[i - 1] + 2;
				}
				// 减少左括号的数量
				left--;
			}

			maxLen = Math.max(maxLen, dp[i]);
		}

		return maxLen;
	}

    public int longestValidParentheses(String s) {
        if (s.length() < 2 || s == null) {
            return 0;
        }

        int maxLen = 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        int prevLeft = 0;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    prevLeft = i + 1;
                } else {
                    stack.pop();

                    if (stack.isEmpty()) {
                        maxLen = Math.max(maxLen, i - prevLeft + 1);
                    } else {
                        maxLen = Math.max(maxLen, i - stack.peek());
                    }
                }
            }
        }

        return maxLen;
    }
}