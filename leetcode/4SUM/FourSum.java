/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.

idea:
HashSet is used to remove duplicate
.add() returns true if this set did not already contain the specified element
.contains returns true if this set contains the specified element

Sort array first in a ascending order
once target < sum, l--
once target > sum, k++

once target == sum, 
l-- and k++ together

use hashset to judge if this result exists or not

for (int i=0; i<num.length-1; i++) {
	for (int j=i+1; j<num.length; j++) {
		for (int k=j+1, l=num.length-1; k<l; ) {
			
		}
	}
}
*/

import java.util.*;

public class FourSum {
	// Sun Jul 14 20:13:46 2019
	public List<List<Integer>> fourSum(int[] numbers, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Set<List<Integer>> hs = new HashSet<>();

		Arrays.sort(numbers);
		int n = numbers.length;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1, l = n - 1; k < l;) {
					int sum = numbers[i] + numbers[j] + numbers[k] + numbers[l];

					if (sum > target) {
						l--;
					} else if (sum < target) {
						k++;
					} else {
						List<Integer> path = new ArrayList<>();
						Collections.addAll(path, numbers[i], numbers[j], numbers[k], numbers[l]);

						if (hs.add(path)) {
							result.add(path);
						}

						k++;
						l--;
					}
				}
			}
		}

		return result;
	}

	// brute force
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Set<List<Integer>> hs = new HashSet<>();

		int N = nums.length;

		for (int i=0; i<N; i++) {
			for (int j=i+1; j<N; j++) {
				for (int k=j+1; k<N; k++) {
					for (int l=k+1; l<N; l++) {
						int sum = nums[i] + nums[j] + nums[k] + nums[l];

						if (sum == target) {
							List<Integer> path = new ArrayList<>();
							Collections.addAll(path, nums[i], nums[j], nums[k], nums[l]);

							if (hs.add(path)) {
								list.add(path);
							}
						}
					}
				}
			}
		}

		return list;
	}
}