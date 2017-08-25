/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given set of points.

Example 1:
Given points = [[1,1],[-1,1]], return true.

Example 2:
Given points = [[1,1],[-1,-1]], return false.

Follow up:
Could you do better than O(n2)?

Hint:
Find the smallest and largest x-value for all points.
If there is a line then it should be at y = (minX + maxX) / 2.
For each point, make sure that it has a reflected point in the opposite side.

idea:
首先我们找到所有点的横坐标的最大值和最小值, 那么二者的平均值就是中间直线的横坐标, 然后我们遍历每个点, 如果都能找到直线对称的另一个点, 则返回true, 反之返回false

use HashMap<Integer, HashSet<Integer>>
x-axis value <-> all y-axis values, put them in a set

*/
import java.util.*;

public class LineReflection {
	public static void main(String[] args) {
		LineReflection eg = new LineReflection();
		int[][] pointsOne = {{1,1},{-1,1}};
		int[][] pointsTwo = {{1,1},{-1,-1}};
		// boolean reflected = eg.isReflected(pointsOne);
		boolean reflected = eg.isReflected(pointsTwo);
		System.out.println(reflected);
	}
	public boolean isReflected(int[][] points) {
		HashMap<Integer, HashSet<Integer>> hm = new HashMap<Integer, HashSet<Integer>>();
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int[] point : points) {
			int x = point[0];
			int y = point[1];
			max = Math.max(max, x);
			min = Math.min(min, x);
			HashSet<Integer> yValues = hm.get(x);
			if (yValues == null) {
				yValues = new HashSet<Integer>();
				hm.put(x, yValues);
			}
			yValues.add(y);
		}

		int total = min + max;

		for (int[] point : points) {
			int x = point[0];
			int y = point[1];

			if ( !hm.containsKey(total - x) || !hm.get(total - x).contains(y) ) {
				return false;
			}
		}
		return true;
	}
}