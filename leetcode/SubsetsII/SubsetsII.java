/*
Given a collection of integers that might contain duplicates, S, return all possible subsets.
Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.

For example,
If S = [1,2,2], a solution is:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

idea:
http://blog.csdn.net/u011095253/article/details/9158397
the same as Subsets, except adding one line to determine if add next integer into the sub arraylist while() to continue

Note: do not forget to add "[]" empty arraylist, it is one result also
*/

public class SubsetsII {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());

        Arrays.sort(nums);
        dfs(result, new ArrayList<Integer>(), nums, 0);

        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> path, int[] nums, int start) {
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            result.add(new ArrayList<Integer>(path));
            dfs(result, path, nums, i + 1);
            path.remove(path.size() - 1);
            
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }
}