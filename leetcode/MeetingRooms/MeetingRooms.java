/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
determine if a person could attend all meetings.
For example, Given [[0, 30],[5, 10],[15, 20]], return false.

idea:
sort the intervals, then there are any overlapping between two overlaps, return false
*/

public class MeetingRooms {
	public boolean canAttendMeetings(Interval[] intervals) {
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		});

		for (int i = 0; i < intervals.length - 1; i++) {
			Interval curr = intervals[i];
			Interval next = intervals[i + 1];
			if (next.start < curr.end) {
				return false;
			}
		}

		return true;
	}
}