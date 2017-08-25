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

 		int i = num1.length() - 1;
 		int j = num2.length() - 1;
 		int carry = 0;
 		StringBuilder sb = new StringBuilder();

 		while (i >= 0 && j >= 0) {
 			char a = num1.charAt(i);
 			char b = num2.charAt(j);

 			int temp = (a - '0') + (b - '0');
 			temp += carry;
 			if (temp >= 10) {
 				carry = temp / 10;
 				temp = temp % 10;
 			}
 			else {
 			    carry = 0;
 			}
 			sb.append(temp);
 			i--;
 			j--;
 		}

 		while (i >= 0) {
 			char a = num1.charAt(i);
 			int temp = a - '0' + carry;
 			System.out.println(temp);
 			if (temp > 9) {
 				carry = temp / 10;
 				temp = temp % 10;
 			}
 			else {
 			    carry = 0;
 			}
 			sb.append(temp);
 			i--;
 		}

 		while (j >= 0) {
 			char b = num2.charAt(j);
 			int temp = b - '0' + carry;
 			if (temp > 9) {
 				carry = temp / 10;
 				temp = temp % 10;
 			}
 			else {
 			    carry = 0;
 			}
 			sb.append(temp);
 			j--;
 		}
 		if (carry > 0) {
 		    sb.append(carry);
 		}
 		
 		return sb.reverse().toString();
    }
}
