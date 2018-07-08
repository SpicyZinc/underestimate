/*
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:
Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.

Example 2:
Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.

idea:
first add 1 at the end of array, which is small significant bit
sequentially
carry to bigger significant bit
if (carry > 0)
create a new array of length of old length+1
newDigits[0] = 1
note, if carry == 0, return early
*/

class PlusOne {
	public static void main(String[] args) {
		PlusOne eg = new PlusOne();
		int[] a = {9,9,9,9,9,9};
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
		a = eg.plusOne(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	public int[] plusOne(int[] digits) {
        int[] result = null;
        int size = digits.length;
        int carry = 1;
        for (int i = size - 1; i >= 0; i--) {
            int digit = digits[i];
            int val = digit + carry;
            digits[i] = val % 10;
            carry = val / 10;
            if (carry == 0) {
            	return digits;
            }
        }
        if (carry == 0) {
            result = new int[size];
            for (int i = 0; i < size; i++) {
                result[i] = digits[i];
            }
        } else {
            result = new int[size + 1];
            result[0] = carry;
            for (int i = 1; i <= size; i++) {
                result[i] = digits[i - 1];
            }
        }

        return result;
    }
}