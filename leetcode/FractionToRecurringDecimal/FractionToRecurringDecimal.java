/*
Given two integers representing the numerator and denominator of a fraction,
return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".

Hint:
No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.

idea:
two knowledge points need to pay attention to
1. [-128, 127]
overflow, so long needed
2. numerator, denominator different sign
if ( (numerator > 0) ^ (denominator > 0) ) {
	return "-";
}

use hashmap to memorize if there are duplicate reminder, once there is,
it is time to stop while() loop and return result.
don't forget to multiply 10 
*/

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder ans = new StringBuilder();
        if (denominator == 0) {
        	return "";
        }
        if (numerator == 0 || denominator == 1) {
        	return numerator + "";
        }
        // different signs negative value
        if ((numerator < 0) ^ (denominator < 0)) {
        	ans.append("-");
        }
        // convert to long type and positive number
        long num = numerator;
        long den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);

        ans.append(num / den);

        long reminder = num % den;
        if (reminder == 0) {
        	return ans.toString();
        }
        // map to record irrational number repeating position
        Map<Long, Integer> hm = new HashMap<Long, Integer>();
        ans.append(".");
    
        while (reminder != 0) {
        	hm.put(reminder, ans.length());
        	reminder *= 10;
            ans.append(reminder / den);
            reminder %= den;

            if (hm.containsKey(reminder)) {
            	int index = hm.get(reminder);
            	ans.insert(index, "(");
            	ans.append(")");
            	return ans.toString();
            }
        }

        return ans.toString();
    }
}