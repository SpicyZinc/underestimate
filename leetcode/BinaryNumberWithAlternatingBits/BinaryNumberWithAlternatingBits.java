/*
Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

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
convert binary format, saved to list.
see if 1 and 0 is alternating
did not use bit operation
*/

import java.util.*;

class BinaryNumberWithAlternatingBits {
	public static void main(String[] args) {
		BinaryNumberWithAlternatingBits eg = new BinaryNumberWithAlternatingBits();
		boolean isAlternating = eg.hasAlternatingBits(4);
		System.out.println(isAlternating);
	}

	public boolean hasAlternatingBits(int n) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 32; i++) {
			if (isSetBit(n, i)) {
				list.add(1);
			} else {
				list.add(0);
			}
		}
		// position to last '1'
		int pos = list.size() - 1;
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i) == 1) {
				pos = i;
                break;
			}
        }
        for (int i = pos; i >= 0; i--) {
            if (pos % 2 == 0) {
                if (i % 2 == 1 && list.get(i) != 0) {
                    return false;
                } else if (i % 2 == 0 && list.get(i) != 1) {
                	return false;
                }
            } else {
                if (i % 2 == 0 && list.get(i) != 0) {
                    return false;
                } else if (i % 2 == 1 && list.get(i) != 1) {
                	return false;
                }
            }
		}

		return true;
	}

	public boolean isSetBit(int n, int pos) {
		return (n & (1 << pos)) != 0;
	}
}