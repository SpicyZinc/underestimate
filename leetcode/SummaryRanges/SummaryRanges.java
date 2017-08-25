/*
Given a sorted integer array without duplicates, return the summary of its ranges.
For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

idea:
loop through the array one time,
keep track of current element, last visited element, head element

*/

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<String>();
        if ( nums.length == 0 || nums == null ) {
            return ret;
        }
        
        int last = nums[0];
        int head = nums[0];
        for ( int i = 1; i < nums.length; i++ ) {
            int current = nums[i];
            if ( last + 1 != current ) {
                ret.add( format(head, last) );
                head = last = current;
            }
            else {
                last = current;
            }
        }
        // add the last range
        ret.add( format(head, last) );
        
        return ret;
    }
    
    public String format(int s, int t) {
        if ( s == t ) {
            return s + "";
        }
        else {
            return s + "->" + t;
        }
    }
}