/*
Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

idea: big number addition
direct idea with StringBuilder, then reverse()
*/

public class AddStrings {
    public String addStrings(String num1, String num2) {
 		if (num1.length() == 0 || num1 == null) {
 			return num2;
 		}
 		if (num2.length() == 0 || num2 == null) {
 			return num1;
 		}

 		StringBuilder sb = new StringBuilder();

 		int i = num1.length() - 1;
 		int j = num2.length() - 1;
 		int carry = 0;

 		while (i >= 0 && j >= 0) {
 			char a = num1.charAt(i);
 			char b = num2.charAt(j);

 			int sum = (a - '0') + (b - '0') + carry;
 			sb.append(sum % 10);
 			carry = sum / 10;

 			i--;
 			j--;
 		}

		while (i >= 0) {
			char a = num1.charAt(i);
			int sum = a - '0' + carry;
			sb.append(sum % 10);
			carry = sum / 10;
			i--;
		}

		while (j >= 0) {
			char b = num2.charAt(j);
			int sum = b - '0' + carry;
			sb.append(sum % 10);
			carry = sum / 10;
			j--;
		}


 		if (carry > 0) {
 		    sb.append(carry);
 		}
		
 		return sb.reverse().toString();
    }
}
