/*
Given a data stream input of non-negative integers a1, a2, ..., an, ...,
summarize the numbers seen so far as a list of disjoint intervals.

For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
[1, 1]
[1, 1], [3, 3]
[1, 1], [3, 3], [7, 7]
[1, 3], [7, 7]
[1, 3], [6, 7]

Follow up:
What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?

idea:
https://www.cnblogs.com/grandyang/p/5548284.html

after initialize the input value as an interval object, the rest is the same as "merge interval"
1. for each inserted value, make it as Interval instance
2. loop through all intervals
compare each with the inserted interval current
no overlap, add to result as what it is now (2 cases)
overlap, update the current interval (2 cases)
insert current after the for loop, but have to remember the position
*/

// Definition for an interval.
class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}
public class SummaryRanges {
	List<Interval> intervals;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        intervals = new ArrayList<Interval>();
    }
    
    public void addNum(int val) {
        Interval current = new Interval(val, val);
        List<Interval> res = new ArrayList<Interval>();
        int pos = 0;

        for (Interval a : intervals) {
            if (current.end + 1 < a.start) {
                res.add(a);
            }
            else if (current.start > a.end + 1) {
                res.add(a);
                pos++;
            }
            else if (current.start - 1 == a.end) {
                current.start = a.start;
            }
            else if (current.end + 1 == a.start) {
                current.end = a.end;
            }
            else {
                current.start = Math.min(current.start, a.start);
                current.end = Math.max(current.end, a.end);
            }
        }

        res.add(pos, current);
        intervals = res;
    }
    
    public List<Interval> getIntervals() {
        return intervals;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */