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
	public static void main(String[] args) {
		
	}

	public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        if (newInterval == null) {            
            return intervals;
        }
        if (intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }	

        for (Interval interval : intervals) {
            if (newInterval.end < interval.start) {
                // make sure the result is sorted
                result.add(newInterval);
                newInterval = interval;
            }
            else if (newInterval.start > interval.end) {
                result.add(interval);
            }
            else if (newInterval.start <= interval.end || newInterval.end >= interval.start) {
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
        }
        // do not forget to the new interval
        result.add(newInterval); 
 
        return result;
	}
}

