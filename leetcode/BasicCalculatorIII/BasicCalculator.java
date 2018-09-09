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
need to go back
*/


class BasicCalculator {
	public int calculate(String s) {
		int l1 = 0;
		int o1 = 1;

		int l2 = 1;
		int o2 = 1;

		int n = s.length();

		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);

			if (Character.isDigit(c)) {
				int num = c - '0';
				// get number with more then 1 digit
				while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
					num = num * 10 + (s.charAt(i + 1) - '0');
					i++;
				}

				l2 = (o2 == 1 ? l2 * num : l2 / num);
			} else if (c == '(') {
				int j = i;
				// count balanced parentheses
				for (int cnt = 0; i < n; i++) {
					if (s.charAt(i) == '(') {
						cnt++;
					}
					if (s.charAt(i) == ')') {
						cnt--;
					}
					if (cnt == 0) {
						break;
					}
				}
				int num = calculate(s.substring(j + 1, i));

				l2 = (o2 == 1 ? l2 * num : l2 / num);
			} else if (c == '*' || c == '/') {
				o2 = (c == '*' ? 1 : -1);
			} else if (c == '+' || c == '-') {
				l1 = l1 + o1 * l2;
				o1 = (c == '+' ? 1 : -1);

				l2 = 1;
				o2 = 1;
			}
		}
		
		return (l1 + o1 * l2);
	}
}