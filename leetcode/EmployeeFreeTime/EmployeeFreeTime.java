/*
We are given a list schedule of employees, which represents the working time for each employee.
Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:
Input: schedule = 
[
	[[1,2],[5,6]],
	[[1,3]],
	[[4,10]]
]
Output: [[3,4]]

Explanation:
There are a total of three employees, and all common free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.

Example 2:
Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we would not include intervals like [5, 5] in our answer, as they have zero length.

Note:
schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.

NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

idea:
it does not matter how many employees here
dump all intervals by addAll() to get all intervals,
set first as prev, update prev, then find all gaps between intervals
*/

class Interval {
	int start;
	int end;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

class EmployeeFreeTime {
	// Sun Apr 28 23:23:01 2019
	public int[][] employeeFreeTime(int[][][] schedule) {
		List<Interval> schedules = new ArrayList<>();

		for (int[][] employee : schedule) {
			for (int[] span : employee) {
				int start = span[0];
				int end = span[1];

				schedules.add(new Interval(start, end));
			}
		}
		Collections.sort(schedules, (a, b) -> a.start - b.start);

		List<int[]> result = new ArrayList<>();
		Interval prev = schedules.get(0);

		for (Interval interval : schedules) {
			if (prev.end < interval.start) {
				result.add(new int[] {prev.end, interval.start});
				// update prev as current
				prev = interval;
			} else {
				prev = prev.end < interval.end ? interval : prev;
			}
		}

		int[][] freeTimes = new int[result.size()][2];
		for (int i = 0; i < freeTimes.length; i++) {
			freeTimes[i] = result.get(i);
		}

		return freeTimes;
	}
}
