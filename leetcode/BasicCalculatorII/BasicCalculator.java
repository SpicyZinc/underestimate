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
    public static void main(String[] args) {
        BasicCalculator bc = new BasicCalculator();
        String s = "3+2*2";
        int result = bc.calculate(s);
    }
    // Wed May  8 22:14:40 2019
    public int calculate(String s) {
        int n = s.length();
        char prevOperator = '+';
        int prevResult = 0;
        int result = 0;

        int num = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            System.out.println(c + ", " + prevResult);

            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }

            if (isOperator(c) || i == n - 1) {
                switch (prevOperator) {
                    case '+':
                        prevResult += num;
                        break;

                    case '-':
                        prevResult -= num;
                        break;

                    case '*':
                        prevResult *= num;
                        break;

                    case '/':
                        prevResult /= num;
                        break;
                }

                if (c == '+' || c == '-' || i == n - 1) {
                    result += prevResult;
                    prevResult = 0;
                }

                prevOperator = c;
                num = 0;
            }
        }

        return result;
    }

    public int calculate(String s) {
     int result = 0;

     int n = s.length();
     int number = 0;
     int prevOperator = '+';
     Stack<Integer> stack = new Stack<Integer>();

     for (int i = 0; i < n; i++) {
         char c = s.charAt(i);
         if (Character.isDigit(c)) {
             number = number * 10 + c - '0';
         }
         // current char is operator or last digit, need to calculate previous
         if (isOperator(c) || i == n - 1) {
             if (prevOperator == '+') {
                 stack.push(number);
             }
             if (prevOperator == '-') {
                 stack.push(-number);
             }
             if (prevOperator == '*' || prevOperator == '/') {
                 int tmp = prevOperator == '*' ? stack.pop() * number : stack.pop() / number;
                 stack.push(tmp);
             }

             prevOperator = c;
             number = 0;
         }
     }

    //  while (!stack.empty()) {
    //      result += stack.pop();
    //  }

    //  return result;
    // }

    public boolean isOperator(char operator) {
        String s = "+-*/";
        return s.indexOf(operator) != -1;
    }
}