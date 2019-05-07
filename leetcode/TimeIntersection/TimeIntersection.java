/*
Give two users' ordered online time series, and each section records the user's login time point x and offline time point y.
Find out the time periods when both users are online at the same time, and output in ascending order.

We guarantee that the length of online time series meet 1 <= len <= 1e6.
For a user's online time series, any two of its sections do not intersect.
Have you met this question in a real interview?  

Example:

Given seqA = [(1,2),(5,100)], seqB = [(1,6)], return [(1,2),(5,6)].
Explanation:
In these two time periods (1,2),(5,6), both users are online at the same time.

Given seqA = [(1,2),(10,15)], seqB = [(3,5),(7,9)], return [].
Explanation:
There is no time period, both users are online at the same time.

Notice
We guarantee that the length of online time series meet 1 <= len <= 1e6.
For a user's online time series, any two of its sections do not intersect.

idea:
deepmap by Ming Cui
找到两个人的共有时间段 或是 两件飞机在天上的时间
淘汰不在 intersection 内的 by i++ or j++
*/

import java.util.*;

class Interval {
	int start, end;

	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

public class TimeIntersection {
	public static void main(String[] args) {
		TimeIntersection eg = new TimeIntersection();

		List<Interval> a = new ArrayList<>();
		List<Interval> b = new ArrayList<>();

		a.add(new Interval(1, 2));
		a.add(new Interval(5, 100));

		b.add(new Interval(1, 6));

		List<Interval> result = eg.timeIntersection(a, b);

		for (Interval interval : result) {
			System.out.print(interval.start + "  " + interval.end + "\n");
		}
	}


	public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
		List<Interval> result = new ArrayList<>();

		if (seqA.size() == 0 || seqB.size() == 0 || seqA == null || seqB == null) {
			return result;
		}

		int i = 0;
		int j = 0;

		while (i < seqA.size() && j < seqB.size()) {
			Interval a = seqA.get(i);
			Interval b = seqB.get(j);

			if (a.end < b.start) {
				i++;
			} else if (b.end < a.start) {
				j++;
			} else {
				int low = Math.max(a.start, b.start);
				int high = Math.min(a.end, b.end);
				result.add(new Interval(low, high));

				if (a.end == b.end) {
					i++;
					j++;
				} else if (a.end > b.end) {
					j++;
				} else {
					i++;
				}
			}
		}

		return result;
	}

	// 02/11/2019
	// leetcode version
	public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        List<Interval> result = new ArrayList<>();

		if (A.length == 0 || B.length == 0 || A == null || B == null) {
			return new Interval[0];
		}

		int i = 0;
		int j = 0;

		while (i < A.length && j < B.length) {
			Interval a = A[i];
			Interval b = B[j];

			if (a.end < b.start) {
				i++;
			} else if (b.end < a.start) {
				j++;
			} else {
				int low = Math.max(a.start, b.start);
				int high = Math.min(a.end, b.end);
				result.add(new Interval(low, high));

				if (a.end == b.end) {
					i++;
					j++;
				} else if (a.end > b.end) {
					j++;
				} else {
					i++;
				}
			}
		}
        
        Interval[] list = new Interval[result.size()];
        for (int idx = 0; idx < result.size(); idx++) {
            list[idx] = result.get(idx);
        }

		return list;
    }

    // 02/13/2019
	// 每当检测到用户上线, count++
	// 如果count == 2, 记录下这个时间, 作为两个用户都在线的起点
	// 每当检测到用户下线: count--
	// 如果count == 1, 记录下这个时间, 作为两个用户都在线的终点
	// 如果start和end都有了值,
	// 用start和end生成一个Interval
	// 把start和end重置

	class Point {
		int x;
		boolean ifStart;

		public Point(int x, boolean ifStart) {
			this.x = x;
			this.ifStart = ifStart;
		}
	}

	public List<Interval> timeIntersection(List<Interval> seqA, List<Interval> seqB) {
		List<Point> points = new ArrayList<>();

		for (Interval interval : seqA) {
			points.add(new Point(interval.start, true));
			points.add(new Point(interval.end, false));
		}
		for (Interval interval : seqB) {
			points.add(new Point(interval.start, true));
			points.add(new Point(interval.end, false));
		}
		
		Collections.sort(points, (p1, p2) -> p1.x - p2.x);

		int count = 0;
		// a time for the start of some intersection
		int start = -1;

		List<Interval> result = new ArrayList<>();
		for (Point p : points) {
			if (p.ifStart) {
				count++;
			} else {
				count--;
			}

			if (count == 2) {
				// record this start, this is a start time of a intersection
				start = p.x;
			}
			if (count == 1 && start != -1) {
				result.add(new Interval(start, p.x));
				// reset start
				start = -1;
			}
		}

		return result;
	}
}