/*
Given an array of size n, find the majority element. 
The majority element is the element that appears more than [ n / 2 ] times.
You may assume that the array is non-empty and the majority element always exist in the array.

idea:
http://www.programcreek.com/2014/02/leetcode-majority-element-java/
0. sort the array first
1. hashmap is the effective way to solve the problem without sorting the array
2. sort the array first, then if an element appear more than half size of the array times, then one of them should be
in the position length / 2
3. the easiest and fast method
if an element appears more than n/2 times, it can only be one element, impossible to have two or more such elements
*/

public class MajorityElement  {
	public int majorityElement(int[] nums) {
		Arrays.sort(nums);

		return nums[nums.length / 2];
	}

	// hashmap
	public int majorityElement(int[] nums) {
		Map<Integer, Integer> hm = new HashMap<>();
		for ( int i = 0; i < nums.length; i++ ) {
			hm.put(nums[i], hm.getOrDefault(nums[i], 0) + 1);
		}

		int result = 0;
		for ( Map.Entry<Integer, Integer> entry : hm.entrySet() ) {
			if ( entry.getValue() > nums.length / 2 ) {
				result = entry.getKey();
			}
		}
		
		return result;
	}

	// best method
	public int majorityElement(int[] nums) {
		int major = 0;
		int cnt = 0;

		for (int num : nums) {
			if (cnt == 0) {
				major = num;
				cnt++;
			} else if (num == major) {
				cnt++;
			} else {
				cnt--;
			}
		}

		return major;
	}
}