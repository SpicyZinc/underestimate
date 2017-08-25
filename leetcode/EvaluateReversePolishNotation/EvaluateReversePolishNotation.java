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
    public int evalRPN(String[] tokens) {
        String operator = "+-*/";

        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < tokens.length; i++) {
        	String tmp = tokens[i];
        	if (!operator.contains(tmp)) {
        		stack.push(tmp);
        	}
        	else {
	        	int b = Integer.parseInt( stack.pop() );
	        	int a = Integer.parseInt( stack.pop() );

        		if ( tmp.equals("+") ) {
        			stack.push( String.valueOf(a + b) );
        		}
        		else if ( tmp.equals("-") ) {
        			stack.push( String.valueOf(a - b) );
        		}
        		else if ( tmp.equals("*") ) {
        			stack.push( String.valueOf(a * b) );
        		}
        		else if ( tmp.equals("/") ) {
        			stack.push( String.valueOf(a / b) );
        		}        		
        	}
        }

        return Integer.valueOf( stack.pop() );
    }

    // self written version passed test
    public class EvaluateReversePolishNotation {
        public int evalRPN(String[] tokens) {
            String operators = "+-*/";
            Stack<String> stack = new Stack<String>();
            for (int i = 0; i < tokens.length; i++) {
                String temp = tokens[i];
                // push all operands into stack
                if (!operators.contains(temp)) {
                    stack.push(temp);
                }
                else {
                    int b = Integer.parseInt(stack.pop());
                    int a = Integer.parseInt(stack.pop());
                    switch (temp) {
                        case "+" :
                            stack.push(a + b + "");
                            break;
                        case "-" :
                            stack.push(a - b + "");
                            break;
                        case "*" :
                            stack.push(a * b + "");
                            break;
                        case "/" :
                            stack.push(a / b + "");
                            break;
                    }
                }
            }
            
            return Integer.parseInt(stack.pop());
        }
    }
}