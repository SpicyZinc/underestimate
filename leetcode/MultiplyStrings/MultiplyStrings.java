/*
Multiply Strings
Given two numbers represented as strings, 
return multiplication of the numbers as a string.
Note: The numbers can be arbitrarily large and are non-negative.

idea:
1. the length of product of multiplication is at most num1.length + num2.length
2. not all positions will be used, have to remove the extra positions at last
3. remember to start from Bit, to ten bit...
4. store at new array from position 0 to high position
5. remember to update out loop with carry, the position should be (i + num2.length)
6. reverse to append

*/
public class MultiplyStrings {
	public static void main(String[] args) {
		MultiplyStrings aTest = new MultiplyStrings();
		String s1 = "9";
		String s2 = "9";
		
		String product = aTest.multiply(s1, s2);
		System.out.println("The product of two nums are " + product);
	}
    public String multiply(String num1, String num2) {
        // Start typing your Java solution below
        // DO NOT write main() function
		
		// build up product num array first
		int[] num = new int[num1.length() + num2.length()];
		
		for (int i = num1.length()-1; i >= 0; i--) {
			int carry = 0;
			int a = num1.charAt(i) - '0';
			for (int j = num2.length()-1; j >= 0; j--) {
				int b = num2.charAt(j) - '0';
				num[(num1.length()-1-i) + (num2.length()-1-j)] += (carry + a * b);
				carry = num[(num1.length()-1-i) + (num2.length()-1-j)] / 10;
				num[(num1.length()-1-i) + (num2.length()-1-j)] %= 10;
			}
			// do not forget to update 
			num[(num1.length()-1-i) + num2.length()] += carry;			
		}
		// find where is not-zero position
		// ATTENTION: i>0 not i>=0, use while(), be careful
		int i = num.length - 1;
		while (i > 0 && num[i] == 0) {
			i--;
		}
		// result
		StringBuilder res = new StringBuilder();
		for (int k = i; k >= 0; k--) {
			res.append( (char)(num[k] + '0') );
		}
		
		return res.toString();
    }
}