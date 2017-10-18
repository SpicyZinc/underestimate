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
dfs
even better dfs with binary search
https://discuss.leetcode.com/topic/30621/1ms-concise-java-binary-search-dfs-is-4ms
*/
public class SmallestRectangleEnclosingBlackPixels {
	private int blX;
	private int blY;
	private int trX;
	private int trY;
	private int area;

	public int minArea(char[][] image, int x, int y) {
		this.blX = x;
		this.blY = y;
		this.trX = x;
		this.trY = y;
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

		if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
			return;
		}
		if (image[x][y] == '0') {
			return;
		}
		// set this (x,y) to be true
		visited[x][y] = true;
		// update bl and tr
		blX = Math.min(blX, x);
		blY = Math.min(blY, y);
		trX = Math.max(trX, x);
		trY = Math.max(trY, y);

		area = Math.max(area, (trX - blX + 1) * (trY - blY + 1));
		// 4 directions
		for (int[] dir : directions) {
			dfs(image, visited, x + dir[0], y + dir[1]);
		}
	}

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
}