/*
A Range Module is a module that tracks ranges of numbers. Your task is to design and implement the following interfaces in an efficient manner.

addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval.
Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.

queryRange(int left, int right) Returns true if and only if every real number in the interval [left, right) is currently being tracked.
removeRange(int left, int right) Stops tracking every real number currently being tracked in the interval [left, right).

Example 1:
addRange(10, 20): null
removeRange(14, 16): null
queryRange(10, 14): true (Every number in [10, 14) is being tracked)
queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite the remove operation)

Note:
A half open interval [left, right) denotes all real numbers left <= x < right.
0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.
The total number of calls to addRange in a single test case is at most 1000.
The total number of calls to queryRange in a single test case is at most 5000.
The total number of calls to removeRange in a single test case is at most 1000.

idea:
A list of ranges
add 和 remove 都是再产生新的 list 然后覆盖 class variable list
add == insert
remove 分两种情况 1. one range 分成两个的 2. no change
query 看cover不cover
*/

class RangeModule {  
	class Range {
		int start;
		int end;

		public Range(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public String toString() {
			return "Range(" + start + "," + end + ")";
		}
	}

	List<Range> list;

    public RangeModule() {
        list = new ArrayList<Range>();
    }
    
    public void addRange(int left, int right) {
    	List<Range> newList = new ArrayList<Range>();
        Range newRange = new Range(left, right);
        for (Range range : list) {
        	int start = range.start;
        	int end = range.end;

        	if (newRange.start > end) {
        		newList.add(range);
        	} else if (newRange.end < start) {
        		newList.add(newRange);
        		newRange = range;
        	} else if (Math.max(start, newRange.start) <= Math.min(newRange.end, end)) {
        		newRange.start = Math.min(start, newRange.start);
        		newRange.end = Math.max(newRange.end, end);
        	}
        }

        newList.add(newRange);
        list = newList;
    }
    
    public boolean queryRange(int left, int right) {
        for (Range r : list) {
        	if (r.start <= left && right <= r.end) {
        		return true;
        	}
        }
        return false;
    }
    
    public void removeRange(int left, int right) {
        List<Range> newList = new ArrayList<Range>();
        
        for (Range range : list) {
        	if (range.end <= left || range.start >= right) {
        		newList.add(range);
        	} else if (range.start < left && right < range.end) {
                newList.add(new Range(range.start, left));
                newList.add(new Range(right, range.end));
            } else {
        		if (range.start < left) {
        			range.end = left;
                    newList.add(range);
        		}
        		if (range.end > right) {
        			range.start = right;
                    newList.add(range);
        		}
        	}
        }

        list = newList;
    }
}


/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */