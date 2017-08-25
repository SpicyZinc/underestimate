/*
Given an integer array, your task is to find all the different possible increasing subsequences of the given array,
and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

idea:
recursion backtracking
typical and classical

unique array to keep to see if duplicates on layer recursion
*/

public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(0, nums, new ArrayList<Integer>(), result);
        return result;
    }

    public void dfs(int index, int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (list.size() >= 2) {
        	result.add(new ArrayList<Integer>(list));
        }
        List<Integer> unique = new ArrayList<Integer>();
        for (int i = index; i < nums.length; i++) {
        	int current = nums[i];
        	// decreasing sequence, continue
        	if (index > 0 && current < nums[index - 1]) {
        		continue;
        	}
        	// skip the duplicate, if not skip, must i++
        	if (unique.contains(current)) {
        		continue;
        	}
        	unique.add(current);
        	list.add(current);
        	dfs(i + 1, nums, list, result);
        	list.remove(list.size() - 1);
        }
    }
}

