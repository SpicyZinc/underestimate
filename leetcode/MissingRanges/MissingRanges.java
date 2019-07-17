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