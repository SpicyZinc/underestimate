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
*/
public class MeetingRooms {
    // 01/31/2019
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals.size() == 0 || intervals ==  null) {
            return 0;
        }
        
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
    
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals.get(0).end);    
        
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            
            if (interval.start >= pq.peek()) {
                pq.poll();
            }
            
            pq.offer(interval.end);
        }
        
        return pq.size();
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>();
        endTimes.offer(intervals[0].end);
        for (int i = 1; i < intervals.length; i++) {
            // 如果当前时间段的开始时间大于最早结束的时间, 则可以更新这个最早的结束时间为当前时间段的结束时间
            // 如果小于的话, 就加入一个新的结束时间, 表示新的房间
            if (intervals[i].start >= endTimes.peek()) {
                endTimes.poll();
            }
            endTimes.offer(intervals[i].end);
        }

        return endTimes.size();
    }
    // self written
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>();
        endTimes.offer(intervals[0].end);
        for (int i = 1; i < intervals.length; i++) {
            Interval current = intervals[i];
            if ( current.start >= endTimes.peek() ) {
                endTimes.poll();
            }
            endTimes.offer(current.end);
        }

        return endTimes.size();
    }
}