/*
Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.
If it is impossible to form any triangle of non-zero area, return 0.

Example 1:
Input: [2,1,2]
Output: 5

Example 2:
Input: [1,2,1]
Output: 0

Example 3:
Input: [3,2,3,4]
Output: 10

Example 4:
Input: [3,6,2,3]
Output: 8 

Note:
3 <= A.length <= 10000
1 <= A[i] <= 10^6

idea:
从后开始 两较小边之和大于较大边
similar, ValidTriangleNumber
*/
class LargestPerimeterTriangle {
	// Mon Jun 10 23:55:32 2019
	public int largestPerimeter(int[] A) {
		Arrays.sort(A);
		
		int perimeter = 0;
		
		for (int i = 0; i < A.length - 2; i++) {
			if (A[i] + A[i + 1] > A[i + 2]) {
				perimeter = Math.max(perimeter, A[i] + A[i + 1] + A[i + 2]);
			}
		}
		
		return perimeter;
	}

	public int largestPerimeter(int[] A) {
		Arrays.sort(A);

		for (int i = A.length - 3; i >= 0; i--) {
			if (A[i] + A[i + 1] > A[i + 2]) {
				return A[i] + A[i + 1] + A[i + 2];
			}
		}

		return 0;
	}
}