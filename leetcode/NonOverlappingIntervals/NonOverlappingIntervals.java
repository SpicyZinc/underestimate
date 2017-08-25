/*
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.

Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.

Example 2:
Input: [ [1,2], [1,2], [1,2] ]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.

Example 3:
Input: [ [1,2], [2,3] ]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

idea:
greedy algorithm
[ [1,2], [2,3], [3,4], [1,3] ] => [ [1,2], [1,3], [2,3], [3,4] ]
=> [ [1,2], [1,3], [2,3], [3,4] ]
end 2
start >= end's interval will be considered
skip [1,3]
record cntNonOverlapping
result = intervals.length - cntNonOverlapping
note: when compare, which is the 1st preference
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0 || intervals == null) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
        	@Override
        	public int compare(Interval a, Interval b) {
        	    if (a.end == b.end) {
                   return Integer.compare(a.start, b.start);
                }
                else {
                   return Integer.compare(a.end, b.end);
                }
        	}
        });
        int end = Integer.MIN_VALUE;
        int cntNonOverlapping = 0;
        for (Interval interval : intervals) {
        	if (interval.start >= end) {
        		end = interval.end;
        		cntNonOverlapping++;
        	}
        }

        return intervals.length - cntNonOverlapping;
    }
}