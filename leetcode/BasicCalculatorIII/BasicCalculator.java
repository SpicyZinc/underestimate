/*
Implement a basic calculator to evaluate a simple expression string.
The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces.

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces.
The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:
"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

Note: Do not use the eval built-in library function.

idea:
previous operator
*/

class BasicCalculator {
	// Sun Jun  9 14:58:12 2019
	public int calculate(String s) {
		int size = s.length();
		int result = 0;
		int currResult = 0;

		int num = 0;
		char prevOperator = '+';

		for (int i = 0; i < size; i++) {
			char c = s.charAt(i);

			if (Character.isDigit(c)) {
				num = num * 10 + c - '0';
			} else if (c == '(') {
				int j = i;
				int parenthesesCnt = 0;

				while (i < size) {
					if (s.charAt(i) == '(') {
						parenthesesCnt++;
						
					}
					if (s.charAt(i) == ')') {
						parenthesesCnt--;
					}
					if (parenthesesCnt == 0) {
						break;
					}
					i++;

				}
				// find balanced () and retrieve the inside part, recurse on it
				num = calculate(s.substring(j + 1, i)); 
			}

			if (isOperator(c) || i == size - 1) {
				switch (prevOperator) {
					case '+':
						currResult += num;
						break;
					case '-':
						currResult -= num;
						break;
					case '*':
						currResult *= num;
						break;
					case '/':
						currResult /= num;
						break;
				}

				if (c == '+' || c == '-' || i == size - 1) {
					result += currResult;
					currResult = 0;
				}

				prevOperator = c;
				num = 0;
			}
		}

		return result;
	}

	public boolean isOperator(char c) {
		String operators = "+-*/";
		return operators.indexOf(c) != -1;
	}

	// 02/12/2019
	public int calculate(String s) {
		int n = s.length();
		int result = 0;
		int currResult = 0;

		int num = 0;
		char prevOperator = '+';

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

			if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
				switch (prevOperator) {
					case '+':
						currResult += num;
						break;
					case '-':
						currResult -= num;
						break;
					case '*':
						currResult *= num;
						break;
					case '/':
						currResult /= num;
						break;
				}

				if (c == '+' || c == '-' || i == n - 1) {
					result += currResult;
					currResult = 0;
				}

				prevOperator = c;
				num = 0;
			}
		}

		return result;
	}
}