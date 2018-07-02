/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically.
Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:
[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.

idea:
use bottom left and top right to represent rectangle
dfs
even better dfs with binary search
https://discuss.leetcode.com/topic/30621/1ms-concise-java-binary-search-dfs-is-4ms
*/
public class SmallestRectangleEnclosingBlackPixels {
	public static void main(String[] args) {
		char[][] image = {
			{'0', '0', '1', '0',},
			{'0', '1', '1', '0',},
			{'0', '1', '0', '0',},
		};

		int x = 0;
		int y = 2;

		SmallestRectangleEnclosingBlackPixels eg = new SmallestRectangleEnclosingBlackPixels();
		int minArea = eg.minArea(image, x, y);
		System.out.println(minArea);
	}

	// method 1
	// 题目说了 only one black pixel area
	// 所以遇到 1 就更新 各个端点坐标就行
	public int minArea(char[][] image, int x, int y) {
		int btmLeftX = x;
		int btmLeftY = y;
		int topRightX = x;
		int topRightY = y;
		int area = 0;

		int m = image.length;
		int n = image[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (image[i][j] == '1') {
					btmLeftX = Math.min(btmLeftX, i);
					btmLeftY = Math.min(btmLeftY, j);
					topRightX = Math.max(topRightX, i);
					topRightY = Math.max(topRightY, j);

					area = Math.max(area, (topRightX - btmLeftX + 1) * (topRightY - btmLeftY + 1));
				}
			}
		}

		return area;
	}

	private int btmLeftX;
	private int btmLeftY;
	private int topRightX;
	private int topRightY;
	private int area;

	public int minArea(char[][] image, int x, int y) {
		this.btmLeftX = x;
		this.btmLeftY = y;
		this.topRightX = x;
		this.topRightY = y;
		this.area = 0;

		if (image.length == 0 || image == null) {
			return 0;
		}

		int m = image.length;
		int n = image[0].length;

		boolean[][] visited = new boolean[m][n];

		dfs(image, visited, x, y);

		return area;
	}

	public void dfs(char[][] image, boolean[][] visited, int x, int y) {
		int[][] directions = {
			{0, 1},
			{0, -1},
			{1, 0},
			{-1, 0},
		};

		int m = image.length;
		int n = image[0].length;

		if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || image[x][y] == '0') {
			return;
		}

		// mark this (x,y) visited
		visited[x][y] = true;
		// 不断扩大 rectangle 范围
		btmLeftX = Math.min(btmLeftX, x);
		btmLeftY = Math.min(btmLeftY, y);
		topRightX = Math.max(topRightX, x);
		topRightY = Math.max(topRightY, y);
		area = Math.max(area, (topRightX - btmLeftX + 1) * (topRightY - btmLeftY + 1));

		for (int[] dir : directions) {
			dfs(image, visited, x + dir[0], y + dir[1]);
		}
	}
}