/*

Given a non negative integer number num. 
For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n * sizeof(integer)). 
But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
Hint:

You should make use of what you have produced already.
Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
Or does the odd/even status of the number help you in calculating the number of 1s?


idea:
https://www.hrwhisper.me/leetcode-counting-bits/

1. direct method
helper method to count how many 1 digits in a number
loop from 1 to n inclusive, save to the result array,
return result array

2. 2 to exact integer has 1 one bit
1	0001
2	0010
4	0100
8	1000
1 1 2 1, (+1) 4
		2 2 3 1, (+1) 8
				2 2 3 2 3 3 4 1, (+1) 16

 9-1 = 8 = 1000
10-2 = 8 = 1000
11-3 = 8 = 1000
+1 is because of "2 to exact number" having 1 one bit
*/

public class CountingBits {

	public static void main(String[] args) {
		CountingBits eg = new CountingBits();
		int[] results = eg.countBits(15);
	}
// 	// method 1
//     public int[] countBits(int num) {
// 		int[] result = new int[num + 1];
// 		for (int i = 1; i <= num; i++) {
// 			result[i] = cntOneBit(i);
// 		}
// 		return result;
//     }

//     private int cntOneBit(int n) {
//     	int count = 0;
//         while ( n != 0 ) {
//             n &= (n-1);
//             count++;
//         }

//         return count;
//     }
//     
	// method 2     
    public int[] countBits(int num) {
		int[] res = new int[num + 1];
        int pow2 = 1, before = 1;
        for (int i = 1; i <= num; i++) {
            if (i == pow2) {
                before = res[i] = 1;
                pow2 <<= 1;
            }
            else {
                res[i] = res[before++] + 1;
            }
        }

        return res;
    }
    // method 3
    public int[] countBits(int num) {
		int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
         	res[i] = res[i / 2] + (i & 1);
        }

        return res;
    }
}