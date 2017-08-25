/*
Given a collection of intervals, merge all overlapping intervals.
The collection of intervals has some intervals overlapping each other, re-arrange to a more tight collection

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

idea:
method 1
main idea is to pick one after one interval in the arraylist
then compare 1st interval with the ones after it
if no overlapping, then move to the next interval, add the interval into the return result
if overlapping, update e, no adding to result, just move to the next interval by for loop repeating this

method 2
1. use a self written Comparator to sort intervals 
2. insert 1st one to interval, compare current interval with previous interval
3. if overlap, redefine interval end, 
4. if not overlap, add to the result list
*/

import java.util.*;

// Definition for an interval
class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { 
		start = s; end = e; 
	}
	public String toString() {
		return "[ " + start + " " + end + " ]";
	}
}

// the algorithm takes at least nlogn time.
// Because this is essentially duplicate detection (when all of interval.start == interval.end)
public class MergeIntervals {
	public static void main(String[] args) {
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1,3));
		intervals.add(new Interval(2,6));
		intervals.add(new Interval(8,10));
		intervals.add(new Interval(15,18));
		
		MergeIntervals mi = new MergeIntervals();
		List<Interval> newList = mi.merge(intervals);

		for (int i = 0; i < newList.size(); i++) {	
			System.out.println(newList.get(i).toString());
		}
	}
	// method 1
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return intervals;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        List<Interval> result = new ArrayList<Interval>();
        Interval firstInterval = intervals.get(0);
        int start = firstInterval.start; 
        int end = firstInterval.end;
        for (int i = 0; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (current.start <= end) {
                end = Math.max(end, current.end);
            }
            else {
                result.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }
        }
        // last one
        result.add(new Interval(start, end));

        return result;
    }
    // method 2
    public List<Interval> merge(List<Interval> intervals) {
    	List<Interval> result = new ArrayList<Interval>();
    	if (intervals.size() == 0) {
    		return result;
    	}
    	// sort intervals based on the start
    	Collections.sort(intervals, new Comparator<Interval>() {
    		@Override
    		public int compare(Interval a, Interval b) {
    			return a.start - b.start;
    		}
    	});
    	result.add(intervals.get(0));
    	for (int i = 1; i < intervals.size(); i++) {
    		Interval curr = intervals.get(i);
    		Interval prev = result.get(result.size() - 1);
    		if (curr.start > prev.end) {
    			result.add(curr);
    		}
    		else {
    			if (curr.end > prev.end) {
    				// note: to extend the interval which is already in result or create new interval
    				prev.end = curr.end;
    			}
    		}
    	}
    	return result;
    }
}