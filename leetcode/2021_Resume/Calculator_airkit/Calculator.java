/*
Variation of calculator

20   = ( II + III ) * IV
4083 = MMMCMXCIX - V + LXXXIX
1987 = MCMLXXXVII
2    = ( II * ( V - II ) ) / III 
14   = II + III * IV
4    = X - V - I
6    = I + II + III
2    = IV / II
2    = I * II
1    = V - IV
2    = I + I
4    = IV
6    = VI
10   = X
5    = V
1    = I

idea:
roman + calculator III (+ - * / and parenthesis)

from Airkit online codepad, cannot figure out how refer static function in Java
*/

import java.util.*;

class Calculator {
    Map<Character, Integer> hm;

    public Calculator() {
        hm = new HashMap<>();

        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
    }

    public static void main(String[] args) {
        Calculator eg = new Calculator();

        String[] list = new String[] {"( II + III ) * IV", "MMMCMXCIX - V + LXXXIX", "MCMLXXXVII", "( II * ( V - II ) ) / III ", "II + III * IV", "X - V - I", "I + II + III", "IV / II", "I * II", "V - IV", "I + I", "IV", "VI", "X", "V", "I"};
        for (String s : list) {
            int result = eg.solution(s);
            System.out.println(result);
        }
    }

    public int solution(String expression) {
        int result = calculate(expression);
        return result;
    }

    public int calculate(String s) {
        int size = s.length();
        int result = 0;
        int currResult = 0;

        int num = 0;
        String intStr = "";
        char prevOperator = '+';

        for (int i = 0; i < size; i++) {
            char c = s.charAt(i);

            if (this.hm.containsKey(c)) {
                intStr += c;
                num = romanToInt(intStr);
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
                // Remember to reset
                prevOperator = c;
                num = 0;
                intStr = "";
            }
        }

        return result;
    }

    public boolean isOperator(char c) {
        String operators = "+-*/";
        return operators.indexOf(c) != -1;
    }

    public int romanToInt(String s) {        
        int len = s.length();
        int val = 0;
        
        for (int i = 0; i < len; i++) {
            boolean isLastPos = i == len - 1;
            char currChar = s.charAt(i);
            // i overflow the len
            // char nextChar = s.charAt(i + 1);

            int currVal = hm.get(currChar);
            int nextVal = isLastPos ? 0 : this.hm.get(s.charAt(i + 1));
            
            if (currVal >= nextVal || isLastPos) {
                val += currVal;
            } else {
                val -= currVal;
            }
        }
        
        return val;
    }
}
