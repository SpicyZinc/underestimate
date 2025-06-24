/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

Note:
The length of both num1 and num2 is < 110.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

idea:
product length will at most be sum of two strings length
from tail to head, insert to head through tail in product[]
main idea is single digit multiplies single digit
*/
import java.util.*;

public class MultiplyStrings {
    public static void main(String[] args) {
        MultiplyStrings eg = new MultiplyStrings();
        String s1 = "155";
        String s2 = "22";
        
        String product = eg.multiply(s1, s2);
        System.out.println("The product of two nums are " + product);
    }
    // best
    // Wed Jun 19 01:03:28 2019
    public String multiply(String num1, String num2) {
        int n1 = num1.length();
        int n2 = num2.length();
        int[] product = new int[n1 + n2];
        
        for (int i = n1 - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';

            for (int j = n2 - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';

                int index = (n1 - i - 1) + (n2 - j - 1);

                product[index] += a * b;
                product[index + 1] += product[index] / 10;
                product[index] %= 10;
            }
        }
        System.out.println(Arrays.toString(product));
        StringBuilder sb = new StringBuilder();

        for (int i = product.length - 1; i >= 0; i--) {
            // 最后的结果是倒着存的 所以开始的0⃣️重要 最后的0⃣️是当时富余的初始化数组 at most
            // get rid of all zeros except i == 0
            if (sb.length() == 0 && i > 0 && product[i] == 0) {
                continue;
            }
            sb.append(product[i]);
        }

        return sb.toString();
    }

    // public String multiply(String num1, String num2) {
    //  int n1 = num1.length();
    //  int n2 = num2.length();
    //  // product array
    //  int[] product = new int[n1 + n2];

    //  for (int i = n1 - 1; i >= 0; i--) {
    //      int carry = 0;

    //      for (int j = n2 - 1; j >= 0; j--) {
    //          int a = num1.charAt(i) - '0';
    //          int b = num2.charAt(j) - '0';

    //          int index = (n1 - 1 - i) + (n2 - 1 - j);

    //          product[index] += (carry + a * b);
    //          carry = product[index] / 10;
    //          product[index] %= 10;
    //      }
    //      // do not forget to update 
    //      product[(n1 - 1 - i) + n2] += carry;
    //  }
    //  // find where is not-zero position
    //  // ATTENTION: i>0 not i>=0, use while(), be careful
    //  int i = product.length - 1;
    //  while (i > 0 && product[i] == 0) {
    //      i--;
    //  }
    //  // result
    //  StringBuilder sb = new StringBuilder();
    //  for (int k = i; k >= 0; k--) {
    //      sb.append((char) (product[k] + '0'));
    //  }
        
    //  return sb.toString();
    // }
}