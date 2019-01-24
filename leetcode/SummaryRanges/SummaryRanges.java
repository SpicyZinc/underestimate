/*
Given a sorted integer array without duplicates, return the summary of its ranges.
For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

idea:
loop through the array one time,
keep track of current, previous, start element
province ids 1, 2, 3, 5, 10, 11, 15, 19 in MZ 
*/

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        if (nums.length == 0 || nums == null) {
            return result;
        }

        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int prev = nums[i - 1];
            int curr = nums[i];
            if (curr - prev == 1) {
                continue;
            } else {
                result.add(getRange(start, prev));
                start = curr;
            }
        }
        result.add(getRange(start, nums[nums.length - 1]));
        
        return result;
    }
    
    public String getRange(int start, int end) {
        if (start == end) return start + "";
        return start + "->" + end;
    }
}