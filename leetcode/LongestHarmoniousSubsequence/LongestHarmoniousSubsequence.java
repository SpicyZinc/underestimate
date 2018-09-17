/*
We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.
Now, given an integer array, you need to find the length of its maxLen harmonious subsequence among all its possible subsequences.

Example 1:
Input: [1,3,2,2,5,2,3,7]
Output: 5
Explanation: The longest harmonious subsequence is [3,2,2,2,3].

Note: The length of the input array will not exceed 20,000.

idea:
只有2个元素
harmonious subsequence actually contains only 2 elements
note, don't forget to check if containsKey(key + 1)
*/

public class LongestHarmoniousSubsequence {
	public int findLHS(int[] nums) {
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int num : nums) {
			hm.put(num, hm.getOrDefault(num, 0) + 1);
		}

		int maxLen = 0;
		for (Integer key : hm.keySet()) {
			if (hm.containsKey(key + 1)) {
				maxLen = Math.max(maxLen, hm.get(key) + hm.get(key + 1));
			}
		}

		return maxLen;
	}
}