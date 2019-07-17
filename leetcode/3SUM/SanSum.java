/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

idea:
note: how to avoid duplicate, HashSet is used to remove duplicate
.add() returns true if this set did not already contain the specified element
.contains returns true if this set contains the specified element

Collections.addAll()

HashSet extends AbstractSet and implements the Set interface.

In hashing, the informational content of a key is used to determine a unique value, called its hash code. 
The hash code is then used as the index at which the data associated with the key is stored. 
The transformation of the key into its hash code is performed automatically-you never see the hash code itself. 
Also, your code can't directly index the hash table. 
The advantage of hashing is that it allows the execution time of basic operations, 
such as add( ), contains( ), remove( ), and size( ), to remain constant even for large sets.
*/

import java.util.*;

public class SanSum {
	public static void main(String[] args) {
		SanSum eg = new SanSum();
		int[] nums = {-1, 0, 1, 2, -1, -4};
		List<List<Integer>> result = eg.threeSum(nums);
		for (List<Integer> path : result) {
			System.out.println(path);
		}
	}
	// Fri Jul 12 11:08:05 2019
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();

		for (int i = 0; i < nums.length - 1; i++) {
			Map<Integer, Integer> hm = new HashMap<>();

			for (int j = i + 1; j < nums.length; j++) {
				int val = 0 - (nums[i] + nums[j]);

				if (hm.containsKey(val)) {
					List<Integer> path = new ArrayList<>();
					path.add(nums[i]);
					path.add(val);
					path.add(nums[j]);
					
					result.add(path);
				} else {
					hm.put(nums[j], j);
				}
			}
		}

		return result;
	}

	public boolean tripletZero(int[] nums) {
		boolean found = false;

		for (int i = 0; i < nums.length - 1; i++) {
			Set<Integer> hs = new HashSet<>();

			for (int j = i + 1; j < nums.length; j++) {
				int val = 0 - (nums[i] + nums[j]);

				if (hs.contains(val)) {
					found = true;

					return found;
				} else {
					hs.add(nums[j]);
				}
			}
		}

		return found;
	}

	// Sun May 19 23:43:30 2019
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		
		int n = nums.length;
		
		List<List<Integer>> result = new ArrayList<>();
		
		Set<List<Integer>> hs = new HashSet<>();
		
		for (int i = 0; i < n; i++) {
			for (int j = i + 1, k = n - 1; j < k;) {
				int sum = nums[i] + nums[j] + nums[k];
				if (sum < 0) {
					j++;
				} else if (sum > 0) {
					k--;
				} else {
					List<Integer> path = new ArrayList<>();
					path.add(nums[i]);
					path.add(nums[j]);
					path.add(nums[k]);
					
					if (hs.add(path)) {
						result.add(path);
					}
					
					j++;
					k--;
				}
			}
		}

		return result;
	}

	// 10/12/2018
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);

		List<List<Integer>> result = new ArrayList<>();
		Set<List<Integer>> hs = new HashSet<>();

		if (nums == null || nums.length < 3) {
			return result;
		}

		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1, k = nums.length - 1; j < k;) {
				List<Integer> path = new ArrayList<Integer>();
				int sum = nums[i] + nums[j] + nums[k];
				if (sum == 0) {
					// path.add(nums[i]);
					// path.add(nums[j]);
					// path.add(nums[k]);
					Collections.addAll(path, nums[i], nums[j], nums[k]);
					if (hs.add(path)) {
						result.add(path);
					}
					j++;
					k--;
				} else if (sum > 0) {
					k--;
				} else {
					j++;
				}
			}
		}
		
		return result;
	}
}