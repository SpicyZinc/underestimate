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

unique array to keep to see if duplicates on the same layer
e.g. 4, 6, 7, 7, 8, no need to go the next level recursion, avoid duplicate on this level, avoid 2nd 7
*/

public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void dfs(int[] nums, int index, List<Integer> path, List<List<Integer>> result) {
        if (path.size() > 1) {
            result.add(new ArrayList<Integer>(path));
        }
        Set<Integer> hs = new HashSet<Integer>();
        for (int i = index; i < nums.length; i++) {
            // skip the duplicate, if not skip, must i++
            if (hs.contains(nums[i])) {
                continue;
            }

            if (index == 0 || index > 0 && nums[i] >= nums[index - 1]) {
                hs.add(nums[i]);
                path.add(nums[i]);
                dfs(nums, i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}