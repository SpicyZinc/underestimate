/*
Given a set of non-overlapping & sorted intervals, insert a new interval into the intervals (merge if necessary).

idea:
summarize 3 cases. Whenever there is intersection, update the new interval.
                            |----currnet----|

case1:  |------new------|
case2:                                              |------new------|
case3:                        |----new----|
                    |----new----|
                                        |----new----|
                          |--------new--------|

        overlap 
        newInterval inside current interval
        newInterval outside current interval

对于case1 and case2 要分别处理
一个是insert
一个是更新 newInterval
*/

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { 
        start = 0; 
        end = 0; 
    }
    Interval(int start, int end) { 
        this.start = start; 
        this.end = end; 
    }
}

public class InsertInterval {
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<Interval>();

        for (Interval interval : intervals) {
            if (newInterval.start > interval.end) {
                result.add(interval);
            } else if (interval.start > newInterval.end) {
                result.add(newInterval);
                newInterval = interval;
            } else {
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }
        // do not forget to the new interval
        result.add(newInterval);
        
        return result;
    }
}