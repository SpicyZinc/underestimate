/*
Calculate the difference of two integers a and b, but you are not allowed to use the operator + and -.

Example:
Given a = 7 and b = 2, return 5.

idea:
http://blog.csdn.net/jbhand/article/details/9346595
首先，如果减数为0，则被减数即为减法的结果，运算结束。
但如果减数不为0，我们可以先把被减数和减数上同为1的位从两个数上去除。至于如何分离出值同为1的位，则可以通过求位与操作来做到；而把这些1分别中被减数和减数中去除，则可以通过按位异或来的操作来实现。
经步骤1处理后，被减数和减数在对应的位上，将或者通为0，或者分别为0和1，却不会同为1。此时：
如果对应位被减数=1，而减数=0，则所得结果对应位也为1；
如果对应位被减数=0，而减数=1，则所得结果对应位还是1，但此时须向前一位借1；
即，通过被减数与减数作位异或的操作得到临时结果，和通过对减数左移一位得到需从临时结果中减去的借数。
于是，经过步骤2后，原来的减法变成了要求：临时结果 - 借数
很明显，只要以临时结果为被减数，借数为减数，重复步骤1~3即可

*/


public class DifferenceOfTwoIntegers {
	// iterative
    public int getSubtract(int a, int b) {
        if (b == 0) {
            return a;
        }
        while (b != 0) {
            int sameBit = a & b;
            a = a ^ sameBit;
            b = b ^ sameBit;
            a = a | b;
            b = b << 1;
        }
        
        return a;
    }
	// recursive
	int getSubtract(int a, int b) {
	    return (b == 0) ? a : getSubtract(a ^ b, (~a & b) << 1);
	}

    // get negative number
    public int negate(int x) {
        return ~x + 1;
    }
}