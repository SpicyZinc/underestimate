/*
Implement atoi to convert a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible,
and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number,
which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number,
or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:
Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

Example 1:
Input: "42"
Output: 42

Example 2:
Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits as possible, which gets 42.

Example 3:
Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.

Example 4:
Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no valid conversion could be performed.

Example 5:
Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer. Thefore INT_MIN (−231) is returned.

idea:
http://blog.csdn.net/linhuanmars/article/details/21145129

sign
overflow

If no valid conversion could be performed, a zero value is returned. 
If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

One thing to note:
this is to calculate from the behind

for (int i = str.length() - 1; i >= 0; i--) {
    int temp = str.charAt(i) - '0';
    result += temp * Math.pow(10, str.length() - 1 - i);
}
*/

public class StringToInteger {
	// Mon Apr  1 23:58:22 2019
	public int myAtoi(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }
        
        str = str.trim();
        char sign = '+';
        int i = 0;
        
        if (str.charAt(0) == '+') {
            i++;
        } else if (str.charAt(0) == '-') {
            i++;
            sign = '-';
        }
        
        double result = 0;
        
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }
        
        if (sign == '-') {
            result *= -1;
        }
        
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        
        return (int) result;
    }

    public int myAtoi(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }

        str = str.trim();
        char sign = '+';
        int i = 0;
        // check negative or positive
        if (str.charAt(0) == '+') {
            i++;
        } else if (str.charAt(0) == '-') {
            i++;
            sign = '-';
        }

        int len = str.length();
        double result = 0;
        while (i < len && Character.isDigit(str.charAt(i))) {
            result = result * 10 + (str.charAt(i) - '0');
            i++;
        }
        
        if (sign == '-') {
            result = -1 * result;
        }

        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) result;
    }
}