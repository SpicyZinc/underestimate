/*
Given a sorted integer array where the range of elements are [0, 99] inclusive, 
return its missing ranges.

For example, given [0, 1, 3, 50, 75], 
return ["2", "4->49", "51->74", "76->99"]

idea:
https://segmentfault.com/a/1190000003790309

1. start, end are the lower and upper bound in which to find the missing ranges
use two pointers curr and prev (start - 1) to indicate missing range start and end
if curr - prev >= 2 which means a missing range, here is the range prev + 1, curr - 1
if not, step one forward by assigning curr to prev

2. opposite to summary ranges, another way to solve this problem
*/

import java.util.*;

public class MissingRanges {
    public static void main(String[] args) {
        MissingRanges eg = new MissingRanges();
        int[] vals = {0, 1, 3, 50, 75};
        int lower = 0;
        int higher = 99;
        List<String> result = eg.findMissingRanges(vals, lower, higher);
        for (String s : result) {
            System.out.print(s + "  ");
        }
        System.out.println();
    }

    public List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> missingRanges = new ArrayList<String>();
        int prev = start - 1;
        for (int i = 0; i <= vals.length; i++) {
            int curr = i == vals.length ? end + 1 : vals[i];
            if (curr - prev >= 2) {
                missingRanges.add(getRange(prev + 1, curr - 1));
            }
            prev = curr;
        }

        return missingRanges;
    }
 
    private String getRange(int from, int to) {
        return (from == to) ? from + "" : from + "->" + to;
    }
}