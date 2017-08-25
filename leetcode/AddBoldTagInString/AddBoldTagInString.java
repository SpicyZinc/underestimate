/*
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict.
If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:
Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"

Example 2:
Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"

Note:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].

idea:
1. borrow from merge intervals
note: interval's start is where exactly the word starts
end is one step more then where word ends
*/

public class AddBoldTagInString {
    public String addBoldTag(String s, String[] dict) {
    	List<Interval> intervals = new ArrayList<Interval>();
        for (String str : dict) {
        	for (int i = 0; i < s.length(); i++) {
        		if (s.startsWith(str, i)) {
        			intervals.add(new Interval(i, i + str.length()));
        		} 
        	}
        }

        List<Interval> result = merge(intervals);
        StringBuilder sb = new StringBuilder();
        int last = 0;
        for (Interval interval : result) {
        	int start = interval.start;
        	int end = interval.end;
        	sb.append(s.substring(last, start));
        	sb.append("<b>");
        	sb.append(s.substring(start, end));
        	sb.append("</b>");
        	last = end;
        }
        sb.append(s.substring(last));
        return sb.toString();
    }

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