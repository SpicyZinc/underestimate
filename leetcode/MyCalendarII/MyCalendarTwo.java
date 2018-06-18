/*
Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
Your class will have one method, book(int start, int end).
Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.
A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)

For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking.
Otherwise, return false and do not add the event to the calendar.

Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)

Example 1:
MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(50, 60); // returns true
MyCalendar.book(10, 40); // returns true
MyCalendar.book(5, 15); // returns false
MyCalendar.book(5, 10); // returns true
MyCalendar.book(25, 55); // returns true
Explanation: 
The first two events can be booked.  The third event can be double booked.
The fourth event (5, 15) can't be booked, because it would result in a triple booking.
The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.

Note:
The number of calls to MyCalendar.book per test case will be at most 1000.
In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].

idea:
overlapping intervals cannot overlap again; if so, triple booking
one calendar only keep double booking interval, [overlappedStart, overlappedEnd)
*/

class MyCalendarTwo {
	List<int[]> calendar;

	public MyCalendarTwo() {
		calendar = new ArrayList<int[]>();
	}

	public boolean book(int start, int end) {
		CalendarOverlappingInterval overlapped = new CalendarOverlappingInterval();
		for (int[] interval : calendar) {
			int s = interval[0];
			int e = interval[1];
			
			int overlappedStart = Math.max(s, start);
			int overlappedEnd = Math.min(e, end);
			// overlap exists, it is okay to have double booking
			if (overlappedStart < overlappedEnd) {
				if (!overlapped.book(overlappedStart, overlappedEnd)) {
					return false;
				}
			}
		}
		calendar.add(new int[] {start, end});

		return true;
	}

	// class to save some overlapping intervals
	// the overlapping intersection will be each int[] in this list
	private class CalendarOverlappingInterval {
		List<int[]> calendar;

		public CalendarOverlappingInterval() {
			calendar = new ArrayList<int[]>();
		}

		public boolean book(int overlappedStart, int overlappedEnd) {
			for (int[] overlapped : calendar) {
				int start = overlapped[0];
				int end = overlapped[1];
				if (Math.max(start, overlappedStart) < Math.min(end, overlappedEnd)) {
					return false;
				}
			}
			calendar.add(new int[] {overlappedStart, overlappedEnd});

			return true;
		}
	}

	// brute force
	List<int[]> calendar;
	List<int[]> overlapped;

	public MyCalendarTwo() {
		calendar = new ArrayList<int[]>();
		overlapped = new ArrayList<int[]>();
	}

	public boolean book(int start, int end) {
		for (int[] overlappedInterval : overlapped) {
			// newStart >= newEnd
			if (Math.max(start, overlappedInterval[0]) < Math.min(end, overlappedInterval[1])) {
				return false;
			}
		}

		for (int[] booked : calendar) {
			int overlappedIntervalStart = Math.max(start, booked[0]);
			int overlappedIntervalEnd = Math.min(end, booked[1]);

			if (overlappedIntervalStart < overlappedIntervalEnd) {
				overlapped.add(new int[] {overlappedIntervalStart, overlappedIntervalEnd});
			}
		}

		calendar.add(new int[] {start, end});
		return true;
	}
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */