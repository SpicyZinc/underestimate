/*
Given a positive integer, check whether it has alternating bits:
namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101

Example 2:
Input: 7
Output: False
Explanation:
The binary representation of 7 is: 111.

Example 3:
Input: 11
Output: False
Explanation:
The binary representation of 11 is: 1011.

Example 4:
Input: 10
Output: True
Explanation:
The binary representation of 10 is: 1010.

idea:
convert number to binary format, saved to list.
see if 1 and 0 is alternating
remove trailing zeros
did not use bit operation

https://www.cnblogs.com/grandyang/p/7696387.html
利用了0和1的交替的特性, 进行错位相加
n是10101, 那么n>>1就是1010, 二者相加就是11111, 再加1就是100000, 二者相‘与’就是0

011111
&
100000
== 0
*/

import java.util.*;

class BinaryNumberWithAlternatingBits {
	public static void main(String[] args) {
		BinaryNumberWithAlternatingBits eg = new BinaryNumberWithAlternatingBits();
		boolean isAlternating = eg.hasAlternatingBits(4);
		System.out.println(isAlternating);
	}

	public boolean hasAlternatingBits(int n) {
        // edge case
        if (n == 1) {
            return true;
        }

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 32; i++) {
			if (isSetBit(n, i)) {
				list.add(1);
			} else {
				list.add(0);
			}
		}
        // remove trailing zeros
		int left = 0;
		int right = list.size() - 1;
		while (list.get(right) == 0) {
			right--;
		}
		List<Integer> result = list.subList(left, right + 1);
        if (result.size() == 1) {
            return false;
        }
		for (int i = 1; i < result.size(); i++) {
			if (result.get(i - 1) == result.get(i)) {
				return false;
			}
		}
		return true;
	}

	public boolean isSetBit(int n, int pos) {
		return (n & (1 << pos)) != 0;
	}

	// quick method
	// 12/03/2018
	public boolean hasAlternatingBits(int n) { 
        return ((n + (n >> 1) + 1) & (n + (n >> 1))) == 0;
    }
}