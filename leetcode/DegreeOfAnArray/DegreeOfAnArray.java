/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.

Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6

Note:
nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.

idea:
use two hashmaps
element : frequency
element : [1st position to appear, 2nd position to appear]
note: simple mistake when writing the code, minSize = n
*/

import java.util.*;

class DegreeOfAnArray {
	public static void main(String[] args) {
		DegreeOfAnArray eg = new DegreeOfAnArray();
		int[] nums = {1, 2, 2, 3, 1};
		int minSize = eg.findShortestSubArray(nums);

		System.out.println(minSize);
	}

	public int findShortestSubArray(int[] nums) {
    	int degree = 0;
    	int minSize = nums.length;

        Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();
        Map<Integer, int[]> positions = new HashMap<Integer, int[]>();

        for (int i = 0; i < nums.length; i++) {
        	int num = nums[i];
        	frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        	degree = Math.max(degree, frequencies.get(num));

        	int[] range = positions.get(num);
        	if (range == null) {
        		range = new int[2];
        		range[0] = i;
        	}
        	range[1] = i;
            positions.put(num, range);
        }

        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
        	if (entry.getValue() != degree) {
        		continue;
        	}
        	int[] range = positions.get(entry.getKey());
        	minSize = Math.min(minSize, range[1] - range[0] + 1);
        }

        return minSize;
    }
}
