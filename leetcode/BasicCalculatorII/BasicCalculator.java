/*

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.

idea:
http://www.programcreek.com/2014/05/leetcode-basic-calculator-ii-java/
+5+6 every number must be associated an operator
stack is used, positive number push back
negative push -number
for * /, pop, then push back the result of the popped with the current

*/
public class BasicCalculator {
    public int calculate(String s) {
    	int res = 0;
    	int d = 0;
    	int sign = '+';
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < s.length(); i++) {
        	char ch = s.charAt(i);
        	if (ch >= '0') {
        		d = d * 10 + ch - '0';
        	}
        	if (isOperator(ch) || i == s.length() - 1) {
        		if (sign == '+') {
        			stack.push(d);
        		}
        		if (sign == '-') {
        			stack.push(-d);
        		}
        		if (sign == '*' || sign == '/') {
        			int tmp = sign == '*' ? stack.pop() * d : stack.pop() / d;
        			stack.push(tmp);
        		}
        		sign = ch;
        		d = 0;
        	}
        }
        while (!stack.empty()) {
            res += stack.pop();
        }

        return res;
    }

    public boolean isOperator(char operator) {
    	String s = "+-*/";
    	return s.indexOf(operator) != -1;
    }
}