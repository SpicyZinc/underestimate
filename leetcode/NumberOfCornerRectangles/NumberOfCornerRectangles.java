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
1. 最直接的方法就是找四个顶点 每个顶点都可以作为 rectangle 的顶点
2. 原理是两行同时遍历
如果两行中相同列位置的值都为1
则计数器cnt自增1
那么最后就相当于有了(cnt - 1)个相邻的格子
问题就转化为了求cnt-1个相邻的格子能组成多少个矩形
就变成了初中数学问题了
共有cnt*(cnt-1)/2个
*/

class NumberOfCornerRectangles {
	// Thu Apr 11 22:18:07 2019
	public int countCornerRectangles(int[][] grid) {
		int result = 0;
		int m = grid.length;
		int n = grid[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				int cnt = 0;
				for (int k = 0; k < n; k++) {
					if (grid[i][k] == grid[j][k] && grid[i][k] == 1) {
						cnt++;
					}
				}
				// 有 cnt 列 上下两行相等, 可以组成多少矩形
				result += ((1 + (cnt - 1)) * (cnt - 1) / 2);
			}
		}

		return result;
	}

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