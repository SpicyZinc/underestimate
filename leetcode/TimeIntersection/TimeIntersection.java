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
sweep line did not figure out
use different method
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
}