/*
Implement atoi to convert a string to an integer.

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
	public int atoi(String str) {
        if (str == null || str.length() == 0) {
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
        
        double result = 0;
        int len = str.length();
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