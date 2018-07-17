/*
Evaluate the value of an arithmetic expression in ***REVERSE*** Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

idea:

Integer.parseInt("String") get int type
Integer.valueOf("String") get int type
String.valueOf("Integer") get String type

This problem can be solved by using a stack.
loop through each element in the given array:
when it is a number, push it to the stack;
when it is an operator, pop two numbers from the stack, do the calculation, and push back the result.
*/
public class EvaluateReversePolishNotation  {
    // 07/14/2018
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (token.matches("-*\\d+")) {
                stack.push(token);
            } else {
                int second = Integer.parseInt(stack.pop());
                int first = Integer.parseInt(stack.pop());
                int val = 0;
                switch(token) {
                    case "+":
                        val = first + second;
                        break;
                    case "-":
                        val = first - second;
                        break;
                    case "*":
                        val = first * second;
                        break;
                    case "/":
                        val = first / second;
                        break;
                }
                stack.push("" + val);
            }
        }
        
        return Integer.parseInt(stack.pop());
    }

    public int evalRPN(String[] tokens) {
        String operators = "+-*/";
        Stack<String> stack = new Stack<String>();
        for (String token : tokens) {
            // push all operands into stack
            if (!operators.contains(token)) {
                stack.push(token);
            } else {
                int second = Integer.valueOf(stack.pop());
                int first = Integer.valueOf(stack.pop());
                switch(token) {
                    case "+":
                        stack.push(String.valueOf(first + second));
                        break;
                    case "-":
                        stack.push(String.valueOf(first - second));
                        break;
                    case "*":
                        stack.push(String.valueOf(first * second));
                        break;
                    case "/":
                        stack.push(String.valueOf(first / second));
                        break;
                    default:
                        break;
                }
            }
        }
        
        return Integer.parseInt(stack.pop());
    }
}