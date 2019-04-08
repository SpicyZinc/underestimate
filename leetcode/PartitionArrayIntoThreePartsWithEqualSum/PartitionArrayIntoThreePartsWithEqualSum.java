/*
Given an array A of integers, return true if and only if we can partition the array into three non-empty parts with equal sums.
Formally, we can partition the array if we can find indexes i+1 < j with
(A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 

Example 1:
Input: [0,2,1,-6,6,-7,9,1,2,0,1]
Output: true
Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1

Example 2:
Input: [0,2,1,-6,6,7,9,-1,2,0,1]
Output: false

Example 3:
Input: [3,3,6,5,-2,2,5,1,-9,4]
Output: true
Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4

Note:
3 <= A.length <= 50000
-10000 <= A[i] <= 10000

idea:
利用counter来数是否三个parts
每个parts是连续的
*/

class PartitionArrayIntoThreePartsWithEqualSum {
	public boolean canThreePartsEqualSum(int[] A) {
		if (A.length < 3) {
			return false;
		}

		int n = A.length;
		int sum = 0;
		for (int num : A) {
			sum += num;
		}

		if (sum % 3 != 0) {
			return false;
		}

		int counter = 0;
		int acc = 0;
		for (int i = 0; i < n; i++) {
			acc += A[i];
			if (acc == sum / 3) {
				counter++;
				acc = 0;
			}
		}

		return counter == 3;
	}
}