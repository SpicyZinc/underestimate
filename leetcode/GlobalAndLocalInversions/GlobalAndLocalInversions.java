/*
We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

Return true if and only if the number of global inversions is equal to the number of local inversions.

Example 1:
Input: A = [1,0,2]
Output: true
Explanation: There is 1 global inversion, and 1 local inversion.

Example 2:
Input: A = [1,2,0]
Output: false
Explanation: There are 2 global inversions, and 1 local inversion.

Note:
A will be a permutation of [0, 1, ..., A.length - 1].
A will have length in range [1, 5000].
The time limit for this problem has been reduced.

idea:
cannot find pure global inversion
all local inversions should be global inversions
but global inversions are not necessarily local

1. make all local inversion to be correct, so swap used
2. then it should be sorted
2. if i - 1 != A[i - 1], not sorted, more pure global inversion exists
*/

class GlobalAndLocalInversions {
	// method 1
	public boolean isIdealPermutation(int[] A) {
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] == A[i] + 1) {
				swap(A, i - 1, i);
			} else if (i - 1 != A[i - 1]) {
				return false;
			}
		}
		return true;
	}

	private void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	// method 2
	public boolean isIdealPermutation(int[] A) {
		for (int i = 0; i < A.length; i++) {
			// found a pure global inversion
			if (Math.abs(A[i] - i) > 1) {
				return false;
			}
		}
		return true;
	}
}