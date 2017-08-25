/*
Given a sorted integer array where the range of elements are [0, 99] inclusive, 
return its missing ranges.

For example, given [0, 1, 3, 50, 75], 
return ["2", "4->49", "51->74", "76->99"]

idea:
https://segmentfault.com/a/1190000003790309

in this case, start == 0, end == 99

start, end are the lower and upper bound in which to find the missing ranges
use two pointers curr and prev to loop through the array
if missing ranges are found, here is the range prev + 1, curr - 1
if not, step one forward by assigning curr to prev

opposite to summary ranges
*/

import java.util.*;

public class MissingRanges {
    public static void main(String[] args) {
        MissingRanges eg = new MissingRanges();
        int[] vals = {0, 1, 3, 50, 75};
        List<String> result = eg.findMissingRanges(vals, 0, 99);
        for (String s : result) {
            System.out.print(s + "  ");
        }
        System.out.println();
    }

    public List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> ranges = new ArrayList<String>();
        int prev = start - 1;
        int curr = vals[0];
        for (int i = 0; i <= vals.length; i++) {
            if (i == vals.length) {
                curr = end + 1;
            } else {
                curr = vals[i];
            }
            if ( curr - prev >= 2 ) {
                ranges.add(getRange(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return ranges;
    }
 
    private String getRange(int from, int to) {
        return (from == to) ? from + "" : from + "->" + to;
    }
}