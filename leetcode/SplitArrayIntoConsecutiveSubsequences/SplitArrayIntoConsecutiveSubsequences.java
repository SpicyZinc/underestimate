/*
You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences,
where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences: 
1, 2, 3
3, 4, 5

Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences: 
1, 2, 3, 4, 5
3, 4, 5

Example 3:
Input: [1,2,3,4,4,5]
Output: False

Note:
The length of the input is in range of [1, 10000]

idea:
https://www.cnblogs.com/grandyang/p/7525821.html
greedy
two hashmaps
each num either append existing chain or 另立山头 build up a new chain and this num as head
通过一个num计算出三个连续数字中的后两个 如果也在nums中 说明可以组成三个连续数字了
记得更新两个hashmap

http://blog.jerkybible.com/2017/08/19/LeetCode-659-Split-Array-into-Consecutive-Subsequences/
*/

class SplitArrayIntoConsecutiveSubsequences {
	public boolean isPossible(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
		Map<Integer, Integer> appendFreq = new HashMap<Integer, Integer>();

		for (int num : nums) {
			freq.put(num, freq.getOrDefault(num, 0) + 1);
		}

		for (int num : nums) {
			// num is used up, otherwise each num its frequency >= 1
			if (freq.get(num) == 0) {
				continue;
			}
			// append to already existing 3 consecutive chain
			if (appendFreq.getOrDefault(num, 0) > 0) {
				appendFreq.put(num, appendFreq.get(num) - 1);
				// make next ready
				appendFreq.put(num + 1, appendFreq.getOrDefault(num + 1, 0) + 1);
			} else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) { // build up a new 3 consecutive chain
				freq.put(num + 1, freq.get(num + 1) - 1);
				freq.put(num + 2, freq.get(num + 2) - 1);
				// make next ready
				appendFreq.put(num + 3, appendFreq.getOrDefault(num + 3, 0) + 1);
			} else { // 落单了
				return false;
			}
			// each time used a num
			freq.put(num, freq.get(num) - 1);
		}

		return true;
	}
}