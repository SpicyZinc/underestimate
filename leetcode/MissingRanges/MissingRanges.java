/*
Given a sorted integer array where the range of elements are [0, 99] inclusive, 
return its missing ranges.

For example, given [0, 1, 3, 50, 75], 
return ["2", "4->49", "51->74", "76->99"]

idea:
use next = lower
next > < = current num 3 cases
damn overflow issue, sb


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

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ranges = new ArrayList<>();

        int next = lower;

        for (int i = 0; i < nums.length; i++) {
            // 1. We don't need to add [Integer.MAX_VALUE, ...] to result
            if (lower == Integer.MAX_VALUE) return ranges;
            int num = nums[i];
            // not within [lower, upper]
            if (num < next) {
                continue;
            }

            if (num == next) {
                next++;
                continue;
            }

            String missing = getRange(next, num - 1);
            ranges.add(missing);
            // 2. We don't need to proceed after we have process Integer.MAX_VALUE in array
            if (num == Integer.MAX_VALUE) return ranges;
            next = num + 1;
        }

        if (next <= upper) {
            ranges.add(getRange(next, upper));
        }

        return ranges;
    }

    // should use long, overflow
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ranges = new ArrayList<>();

        int prev = lower - 1;
        
        for (int i = 0; i <= nums.length; i++) {
            int curr = i == nums.length ? upper + 1 : nums[i];
            if (curr > prev + 1) {
                String range = getRange(prev + 1, curr - 1);
                ranges.add(range);
            }
            prev = curr;
        }
        
        return ranges;
    }
    
    public String getRange(int start, int end) {
        if (start == end) {
            return "" + start;
        }
        return start + "->" + end;
    }
}