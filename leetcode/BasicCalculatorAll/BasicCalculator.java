class BasicCalculator {
    public int calculate(String s) {
        int n = s.length();
        Stack<Integer> stack = new Stack<>();

        int result = 0;
        int num = 0;
        char prevOperator = '+';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '(') {
                int j = i;
                int openCount = 0;

                while (j < n) {
                    if (s.charAt(j) == '(') {
                        openCount++;
                    }
                    if (s.charAt(j) == ')') {
                        openCount--;
                    }
                    if (openCount == 0) {
                        break;
                    }
                    j++;
                }
                String ss = s.substring(i + 1, j);
                // because for loop will increase i, no need to +1 here
                i = j;
                num = calculate(ss);
            }

            if (isOperator(c) || i == n - 1) {
                switch(prevOperator) {
                    case '+' :
                        stack.push(num);
                        break;
                    case '-' :
                        stack.push(-num);
                        break;
                    case '*' : {
                        int first = stack.pop();
                        stack.push(first * num);
                        break;
                    }
                    case '/' : {
                        int first = stack.pop();
                        stack.push(first / num);
                        break;
                    }
                    default:
                        break;
                }

                prevOperator = c;
                num = 0;
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public boolean isOperator(char c) {
        String operators = "+-*/";
        return operators.indexOf(c) != -1;
    }
}