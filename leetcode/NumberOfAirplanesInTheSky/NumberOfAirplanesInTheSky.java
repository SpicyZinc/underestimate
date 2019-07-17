/*
Given an list interval, which are taking off and landing time of the flight.
How many airplanes are there at most at the same time in the sky?

Example

Example 1:
Input: [(1, 10), (2, 3), (5, 8), (4, 7)]
Output: 3
Explanation:
The first airplane takes off at 1 and lands at 10.
The second ariplane takes off at 2 and lands at 3.
The third ariplane takes off at 5 and lands at 8.
The forth ariplane takes off at 4 and lands at 7.
During 5 to 6, there are three airplanes in the sky.

Example 2:
Input: [(1, 2), (2, 3), (3, 4)]
Output: 1
Explanation: Landing happen before taking off.

idea:
sweep line
*/

class Interval {
	int start;
	int end;

	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

class TimePoint {
	int time;
	int flag;

	TimePoint(int time, int flag) {
		this.time = time;
		this.flag = flag;
	}
}

public class NumberOfAirplanesInTheSky {
	public int countOfAirplanes(List<Interval> airplanes) {
		List<TimePoint> list = new ArrayList<>();
		for (Interval interval : airplanes) {
			list.add(new TimePoint(interval.start, 1));
			list.add(new TimePoint(interval.end, -1));
		}

		Collections.sort(list, new Comparator<TimePoint>() {
			@Override
			public int compare(TimePoint a, TimePoint b) {
				if (a.time != b.time) {
					return a.time - b.time;
				} else {
					return a.flag - b.flag;
				}
			}
		});

		int cnt = 0;
		int max = 0;
		for (TimePoint item : list) {
			// 巧妙利用flag +1 -1
			cnt += item.flag;
			max = Math.max(max, cnt);
		}

		return max;
	}
}