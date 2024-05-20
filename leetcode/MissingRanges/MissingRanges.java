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
        System.out.println(result);
    }
    // personal ugly written
    // Sun May 19 21:21:42 2024
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0 || nums == null) {
            List<Integer> r = new ArrayList<>();
            r.add(lower);
            r.add(upper);
            result.add(r);
            return result;
        }

        int n = nums.length;
        if (n == 1) {
            int x = nums[0];
            if (x < lower) {
                List<Integer> r = new ArrayList<>();
                r.add(lower);
                r.add(upper);
                result.add(r);
                return result;
            }

            if (x == lower) {
                List<Integer> r = new ArrayList<>();
                if (lower + 1 > upper) {
                    return result;
                } else {
                    r.add(lower + 1);
                    r.add(upper);
                    result.add(r);
                    return result;
                }
            }

            if (x > lower && x < upper) {
                List<Integer> r1 = new ArrayList<>();
                r1.add(lower);
                r1.add(x - 1);
                result.add(r1);

                List<Integer> r2 = new ArrayList<>();
                r2.add(x + 1);
                r2.add(upper);
                result.add(r2);
                return result;
            }
        }

        if (nums[0] > lower) {
            List<Integer> r = new ArrayList<>();
            r.add(lower);
            r.add(nums[0] - 1);
            result.add(r);
        }

        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] > nums[i] + 1) {
                List<Integer> r = new ArrayList<>();
                r.add(nums[i] + 1);
                r.add(nums[i + 1] - 1);
                result.add(r);
            }
        }
        if (nums[n - 1] + 1 <= upper) {
            List<Integer> r = new ArrayList<>();
            r.add(nums[n - 1] + 1);
            r.add(upper);
            result.add(r);
        }

        return result;
    }
    // Mon May 20 00:12:11 2024
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> result = new ArrayList<>();
        int current = lower;
        for (int num : nums) {
            if (current == num) {
                current++;
            } else {
                result.add(Arrays.asList(current, num - 1));
                current = num + 1;
            }
        }
        if (current <= upper) {
            result.add(Arrays.asList(current, upper));
        }

        return result;
    }

    // Mon Apr  8 02:27:17 2024
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> result = new ArrayList<>();

        int step = lower;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // not within lower, so need to count
            if (step > num) {
                continue;
            }
            // it is not missed range, so continue
            if (step == num) {
                step++;
                continue;
            }
            if (step < num) {
                result.add(getRange(step, num - 1));
                step = num + 1;
            }
        }

        if (step <= upper) {
            result.add(getRange(step, upper));
        }

        return result;
    }

    public List<Integer> getRange(int start, int end) {
        List<Integer> range = new ArrayList<>();
        range.add(start);
        range.add(end);
        return range;
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ranges = new ArrayList<>();
        // 每个数 前一个(minus 1)  的下一个
        int next = lower;

        for (int i = 0; i < nums.length; i++) {
            // 1. We don't need to add [Integer.MAX_VALUE, ...] to result
            if (lower == Integer.MAX_VALUE) {
                return ranges;
            }

            int num = nums[i];
            // not within [lower, upper]
            if (num < next) {
                continue;
            }

            if (num == next) {
                next++;
                continue;
            }
            
            if (num > next) {
                String missing = getRange(next, num - 1);
                ranges.add(missing);
            }

            // 2. We don't need to proceed after we have process Integer.MAX_VALUE in array
            if (num == Integer.MAX_VALUE) {
                return ranges;
            }

            next = num + 1;
        }

        if (next <= upper) {
            ranges.add(getRange(next, upper));
        }

        return ranges;
    }

    public String getRange(int s, int e) {
        return s == e ? "" + s : s + "->" + e;
    }

    // Fri Jul 12 22:58:31 2019
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        long low = lower;
        long up = upper;

        List<String> result = new ArrayList<>();

        long prev = low - 1;

        for (int i = 0; i <= nums.length; i++) {
            long curr = i == nums.length ? up + 1 : nums[i];

            if (curr > prev + 1) {
                result.add(getMissingRange(prev + 1, curr - 1));
            }

            prev = curr;
        }

        return result;
    }

    public String getMissingRange(long s, long e) {
        return s == e ? "" + s : s + "->" + e;
    }
}