/*
Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces.
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.

idea:
http://www.programcreek.com/2014/05/leetcode-basic-calculator-ii-java/
+5+6 every number must be associated an operator
only for + and -
positive number push to stack as itself
negative push -number

there is not some rule for this question
when current char is an operator, time to calculate the previous
for + -, push to stack with sign
for * /, pop to calculate and push back
*/

public class BasicCalculator {
    public int calculate(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<Integer>();
        int number = 0;
        int preOperator = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            }
            // current char is operator or last digit, need to calculate previous
            if (isOperator(c) || i == s.length() - 1) {
                if (preOperator == '+') {
                    stack.push(number);
                }
                if (preOperator == '-') {
                    stack.push(-number);
                }
                if (preOperator == '*' || preOperator == '/') {
                    int tmp = preOperator == '*' ? stack.pop() * number : stack.pop() / number;
                    stack.push(tmp);
                }
                preOperator = c;
                number = 0;
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

    // fails "1-1+1"
    // 86 / 109 test cases passed
    public int calculate(String s) {
        Stack<Integer> nums = new Stack<Integer>();
        Stack<Character> operators = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int val = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    val = val * 10 + s.charAt(i) - '0';
                    i++;
                }
                nums.push(val);
                if (!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')) {
                    char operator = operators.pop();
                    int first = nums.pop();
                    int second = nums.pop();
                    int temp = operator == '*' ? second * first : second / first;
                    nums.push(temp);
                }
                i--;
            } else if (isOperator(c)) {
                operators.push(c);
            }
        }
        while (!operators.isEmpty()) {
            char operator = operators.pop();
            int first = nums.pop();
            int second = nums.pop();
            int temp = operator == '+' ? second + first : second - first;
            nums.push(temp);
        }

        return nums.peek();
    }
}