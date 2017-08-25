/*
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 1 and b = 2, return 3.

idea:
http://zhedahht.blog.163.com/blog/static/254111742011125100605/

part 1, use & to get carry value, must left shift one position
part 2, use ^ to get sum without carry
add part 1 and part 2

e.g. 15 + 07 = 22
1. it is 12 without considering carry, 
2. carry is 1, but left shift 10
3. 12 + 10 = 22
*/

public class SumOfTwoIntegers {
	// iterative
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        while (b != 0) {
            int carry = a & b;
            a ^= b;
            b = carry << 1;
        }

        return a;
    }
	// recursive
	int getSum(int num1, int num2) {
	    if (num2 == 0) {
	        return num1;
	    }

	    return getSum(num1 ^ num2, (num1 & num2) << 1);
	}
}