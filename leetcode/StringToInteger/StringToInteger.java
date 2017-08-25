/*

Implement atoi to convert a string to an integer.

idea:
http://blog.csdn.net/linhuanmars/article/details/21145129

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
		// trim white spaces
		str = str.trim();
		// check negative or positive
		char flag = '+';
		int j = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			j++;
		} 
		else if (str.charAt(0) == '+') {
			j++;
		}

        double result = 0;
        // this is calculated from the beginning
        while (str.length() > j && str.charAt(j) >= '0' && str.charAt(j) <= '9') {
			result = result * 10 + (str.charAt(j) - '0');
			j++;
		}

	    if (flag == '-') {
			result = -result;
	    }
	 
		if (result > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
	 
		if (result < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
        
        return (int)result;
    }
}