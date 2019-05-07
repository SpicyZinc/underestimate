/*
Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.
(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.
For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

Example 1:
https://assets.leetcode.com/uploads/2019/01/30/interval1.png
Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.

Note:
0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

idea
sweep line
多少架飞机在天上
两人的共同有空时间段

还有 双指针 pop
*/

import java.util.*;

class Point {
	int val;
	boolean isStart;

	public Point(int val, boolean isStart) {
		this.val = val;
		this.isStart = isStart;
	}
}

class IntervalListIntersections {
	public static void main(String[] args) {
		IntervalListIntersections eg = new IntervalListIntersections();

		int[][] A = {{0,2},{5,10},{13,23},{24,25}};
		int[][] B = {{1,5},{8,12},{15,24},{25,26}};

		int[][] result = eg.intervalIntersection(A, B);

		for (int[] row : result) {
			System.out.println(Arrays.toString(row));
		}
	}

	public int[][] intervalIntersection(int[][] A, int[][] B) {
		List<Point> points = new ArrayList<>();

		for (int[] interval : A) {
			int start = interval[0];
			int end = interval[1];

			points.add(new Point(start, true));
			points.add(new Point(end, false));
		}

		for (int[] interval : B) {
			int start = interval[0];
			int end = interval[1];

			points.add(new Point(start, true));
			points.add(new Point(end, false));
		}

		Collections.sort(points, (a, b) -> (a.val == b.val) ? (a.isStart ? -1 : 1) : a.val - b.val);

		int count = 0;
		int start = -1;

		List<int[]> result = new ArrayList<>();
		for (Point point : points) {
			if (point.isStart) {
				count++;
			} else {
				count--;
			}

			if (count == 2) {
				start = point.val;
			}
			if (count == 1 && start != -1) {
				result.add(new int[] {start, point.val});
				start = -1;
			}
		}

		int[][] intersections = new int[result.size()][2];
		for (int i = 0; i < result.size(); i++) {
			intersections[i] = result.get(i);
		}

		return intersections;
	}
}