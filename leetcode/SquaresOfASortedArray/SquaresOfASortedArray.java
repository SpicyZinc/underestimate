/*
Given an array of integers A sorted in non-decreasing order,
return an array of the squares of each number, also in sorted non-decreasing order.

Example 1:
Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]

Example 2:
Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 

Note:
1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.

idea:
找到正负转换节点
然后再依次 left-- or right++
就像 merge sorted array
*/

class SquaresOfASortedArray {
	public int[] sortedSquares(int[] A) {
		int n = A.length;
		// from negative to position position
		int pos = 0;
		for (int i = 0; i < n - 1; i++) {
			if (A[i] * A[i + 1] <= 0) {
				pos = i;
			}
		}

		int[] result = new int[n];

		int idx = 0;
		int left = pos;
		int right = pos + 1;

		while (left >= 0 && right < n) {
			int leftVal = A[left] * A[left];
			int rightVal = A[right] * A[right];

			if (leftVal < rightVal) {
				result[idx++] = leftVal;
				left--;
			} else if (leftVal > rightVal) {
				result[idx++] = rightVal;
				right++;
			} else {
				result[idx++] = leftVal;
				left--;
				result[idx++] = rightVal;
				right++;
			}
		}

		while (left >= 0) {
			result[idx++] = A[left] * A[left];
			left--;
		}

		while (right < n) {
			result[idx++] = A[right] * A[right];
			right++;
		}

		return result;
	}
}