/*
You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.

Example:
Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
Output: 2
Explanation:
The five points are show in the figure below. The red triangle is the largest.
https://s3-lc-upload.s3.amazonaws.com/uploads/2018/04/04/1027.png

Notes:
3 <= points.length <= 50.
No points will be duplicated.
-50 <= points[i][j] <= 50.
Answers within 10^-6 of the true value will be accepted as correct.

idea:
3 for loops, need to know a formula to calculate the area of triangle if only know the 3 points
one thing to note: x / 2 != x * 0.5, promote x to be double and then * 0.5
*/

class LargestTriangleArea {
	public double largestTriangleArea(int[][] points) {
		double area = 0.0;
		for (int[] A : points) {
			for (int[] B : points) {
				for (int[] C : points) {

					int[] A = points[i];
					int[] B = points[j];
					int[] C = points[k];

					area = Math.max(area, getArea(A, B, C));
				}
			}
		}

		return area;
	}

	public double getArea(int[] A, int[] B, int[] C) {
		int AX = A[0];
		int AY = A[1];

		int BX = B[0];
		int BY = B[1];

		int CX = C[0];
		int CY = C[1];

		return 0.5 * Math.abs(AX * BY + BX * CY + CX * AY - (AX * CY + CX * BY + BX * AY));
	}
}