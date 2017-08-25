/*
Given two strings representing two complex numbers.
You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.

Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.

Note:
The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.

idea:
very simple, direct thought, 二项式乘法
*/

public class ComplexNumberMultiplication {
    public String complexNumberMultiply(String a, String b) {
        int A = getReal(a);
        int B = getComplex(a);
        int C = getReal(b);
        int D = getComplex(b);

        int AC = A * C;
        int BD = B * D;
        int ADBC = A * D + B * C;

        int realPart = AC - BD;
        return realPart + "+" + ADBC + "i";
    }

    private int getReal(String s) {
        String[] matches = s.split("\\+");
        int a = Integer.parseInt(matches[0]);
        return a;
    }

    private int getComplex(String s) {
        String[] matches = s.split("\\+");
        String[] parts = matches[1].split("i");
        int b = Integer.parseInt(parts[0]);
        return b;
    }
}