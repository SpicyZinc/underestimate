/*
Given a positive integer, output its complement number.
The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.

Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits),
and its complement is 010. So you need to output 2.

Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits),
and its complement is 0. So you need to output 0.

idea:
http://www.cnblogs.com/grandyang/p/6275742.html
note: cannot easily use ~ sign, because zeros before 1 should not be flipped
how to flip, ^ 1 == 异或一个1

method 2
由于位操作里面的取反符号～本身就可以翻转位,
如果直接对num取反的话就是每一位都翻转了,
而最高位1之前的0是不能翻转的,
所以我们只要用一个mask来标记最高位1前面的所有0的位置,
然后对mask取反后,
与操作上～num
*/

public class NumberComplement {
	public static void main(String[] args) {
		int x = 5;
		int y = 2;
		NumberComplement eg = new NumberComplement();
		System.out.println(eg.findComplement(5));

		int number = 5;
		int count = 0;
		for (int i =0; i<32; i++) { 
			if ( (number & 1) == 1) {
				count++;
			}
			number = number >>> 1;
		}
		number = 5;
		count = 0;
		for (int i = 32; i > 0; i--) {
			if ( (number & (1 << (i-1))) != 0 ) {
			// if ( (number & (1 << (i-1))) == 1 ) { not working
				count++;
			}
		}

		System.out.println(count);
	}

    public int findComplement(int num) {
        boolean startFlip = false;
        for (int i = 31; i >= 0; i--) {
        	if ((num & (1 << i)) != 0) {
        		startFlip = true;
        	}
        	if (startFlip) {
        		num ^= (1 << i);
        	}
        }
        return num;
    }


    public int findComplement(int num) {
		int mask = Integer.MAX_VALUE;
       	while ( (mask & num) != 0 ) {
       		mask <<= 1;
       	}

       	return ~mask & ~num;    
	}
}





