/*
A group of two or more people wants to meet and minimize the total travel distance.
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

idea:
https://segmentfault.com/a/1190000003894693

find 2d median point
because this is Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
get xPositions and yPositions separately, xPositions already sorted because it is based i increased order
note:
yPositions need to be sorted
*/

import java.util.*;

public class BestMeetingPoint {
	public static void main(String[] args) {
		BestMeetingPoint eg = new BestMeetingPoint();
		int[][] grid = {
			{1,0,0,0,1},
			{0,0,0,0,0},
			{0,0,1,0,0},
		};
		int distance = eg.minTotalDistance(grid);
		System.out.println(distance);

	}
	public int minTotalDistance(int[][] grid) {
		List<Integer> xPositions = new ArrayList<Integer>();
		List<Integer> yPositions = new ArrayList<Integer>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 1) {
					xPositions.add(i);
					yPositions.add(j);
				}
			}
		}
		int distance = 0;
		int xLen = xPositions.size();
		int yLen = yPositions.size();
		for (int i = 0; i < xLen; i++) {
			distance += Math.abs(xPositions.get(i) - xPositions.get(xLen / 2));
		}
		// because not based on y axis, so need to sort
		Collections.sort(yPositions);
		for (int j = 0; j < yLen; j++) {
			distance += Math.abs(yPositions.get(j) - yPositions.get(yLen / 2));
		}
		return distance;
	}
}