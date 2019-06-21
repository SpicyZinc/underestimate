/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

idea:
use map to save value to index pair
[3,2,4]
6
*/
public class TwoSum {
	public static void main(String[] args) {
		int[] numbers = {722,600,905,55,55};
		int target = 110;
		TwoSum eg = new TwoSum();
		int[] ret = eg.twoSum(numbers, target);
		System.out.println(ret[0] + " " + ret[1]);
	}

	public int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		
		Map<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			// note, where to put
			// hm.put(nums[i], i);
			// avoid 3, 6 - 3 case
			if (hm.containsKey(target - nums[i])) {
				result[0] = hm.get(target - nums[i]);
				result[1] = i;

				break;
			}
			
			hm.put(nums[i], i);
		}
		
		return result;
	}
}