/*
Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle.
Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

Example 1:
Input: grid = 
[[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
 
Example 2:
Input: grid = 
[[1, 1, 1],
 [1, 1, 1],
 [1, 1, 1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
 
Example 3:
Input: grid = 
[[1, 1, 1, 1]]
Output: 0
Explanation: Rectangles must have four distinct corners.

Note:
The number of rows and columns of grid will each be in the range [1, 200].
Each grid[i][j] will be either 0 or 1.
The number of 1s in the grid will be at most 6000.

idea:
https://www.cnblogs.com/grandyang/p/8433813.html
1. 最直接的方法就是找四个顶点
*/

class NumberOfCornerRectangles {
	public int countCornerRectangles(int[][] grid) {
		int cnt = 0;
		int m = grid.length;
		int n = grid[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 左上顶点
				if (grid[i][j] == 0) {
					continue;
				}
				// via height to get 左下顶点
				for (int h = 1; h < m - i; h++) {
					if (grid[i + h][j] == 0) {
						continue;
					}
					// via width to get 右上顶点 and 右下顶点
					for (int w = 1; w < n - j; w++) {
						if (grid[i][j + w] == 1 && grid[i + h][j + w] == 1) {
							cnt++;
						}
					}
				}
			}
		}

		return cnt;
	}
}