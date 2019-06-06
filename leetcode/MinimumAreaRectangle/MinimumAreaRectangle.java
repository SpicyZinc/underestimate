/*
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
If there isn't any rectangle, return 0.

Example 1:
Input: [
	[1,1],
	[1,3],
	[3,1],
	[3,3],
	[2,2]
]
Output: 4

Example 2:
Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2

Note:
1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.

idea:
https://leetcode.com/problems/minimum-area-rectangle/discuss/192025/Java-N2-Hashmap
need to go back, copied, not fully understand
*/

class MinimumAreaRectangle {
	public int minAreaRect(int[][] points) {
		Map<Integer, Set<Integer>> hm = new HashMap<>();

		for (int[] point : points) {
			int x = point[0];
			int y = point[1];

			hm.computeIfAbsent(x, p -> new HashSet<>()).add(y);
		}

		int minArea = Integer.MAX_VALUE;

		for (int[] point1 : points) {
			for (int[] point2 : points) {
				int x1 = point1[0];
				int y1 = point1[1];

				int x2 = point2[0];
				int y2 = point2[1];

				if (x1 == x2 || y1 == y2) {
					continue;
				}

				if (hm.get(x1).contains(y2) && hm.get(x2).contains(y1)) {
					minArea = Math.min(minArea, Math.abs(x1 - x2) * Math.abs(y1 - y2));
				}
			}
		}

		return minArea == Integer.MAX_VALUE ? 0 : minArea;
	}
}