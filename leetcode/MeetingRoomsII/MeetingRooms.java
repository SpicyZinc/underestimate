/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
find the minimum number of conference rooms required.
For example, Given [[0, 30],[5, 10],[15, 20]], return 2.

idea:
greedy algorithm
sort the intervals first
if overlap, open a new room by recording the current end time
find the the earliest end time of all meeting rooms, can loop, can use PriorityQueue
which one is the earlier, then this one will be the one to
either as a standard to open a new room
or hold more meetings
at the end, the size of priority queue (end time) will be the number of meeting rooms needed

2. sweep line can work too
*/
public class MeetingRooms {
	// Tue May 14 23:38:37 2019
	class Point {
		int val;
		int flag;

		public Point(int val, int flag) {
			this.val = val;
			this.flag = flag;
		}
	}

	public int minMeetingRooms(int[][] intervals) {
		List<Point> list = new ArrayList<>(intervals.length * 2);

		for (int[] interval : intervals) {
			list.add(new Point(interval[0], 1));
			list.add(new Point(interval[1], 0));
		}

		Collections.sort(list, (a, b) -> a.val == b.val ? a.flag - b.flag : a.val - b.val);

		int answer = 0;
		int count = 0;

		for (Point p : list) {
			if (p.flag == 1) {
				count++;
			} else {
				count--;
			}

			answer = Math.max(answer, count);
		}

		return answer;
	}

	// Sun Jun  9 18:25:52 2019
	public int minMeetingRooms(int[][] intervals) {
		if (intervals.length == 0 || intervals ==  null) {
			return 0;
		}

		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

		PriorityQueue<Integer> endTimes = new PriorityQueue<>();
		endTimes.add(intervals[0][1]);
		
		for (int i = 1; i < intervals.length; i++) {
			int[] interval = intervals[i];
			// 如果当前时间段的开始时间大于最早结束的时间, 则可以更新这个最早的结束时间为当前时间段的结束时间
			// 如果小于的话, 就加入一个新的结束时间, 表示新的房间
			if (interval[0] >= endTimes.peek()) {
				endTimes.poll();
			}
			
			endTimes.offer(interval[1]);
		}

		return endTimes.size();
	}
}