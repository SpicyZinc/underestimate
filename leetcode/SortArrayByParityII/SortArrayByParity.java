/*
Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
You may return any answer array that satisfies this condition.

Example 1:

Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 

Note:
2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000


idea:
see code, explicit
*/

class SortArrayByParity {
	public int[] sortArrayByParityII(int[] A) {
		int n = A.length;
		
		int evenIdx = 0;
		int oddIdx = 1;

		while (evenIdx < n && oddIdx < n) {
			if (A[evenIdx] % 2 == 1) {
				swap(A, evenIdx, oddIdx);
				oddIdx += 2;
			} else {
				// this is expected
				// move the pointer
				evenIdx += 2;
			}
		}

		return A;
	}

	public void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}