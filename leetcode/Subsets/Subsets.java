/*
Given a set of distinct integers, nums, return all possible subsets.
Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

idea:
since no duplicate elements, no need to sort
dfs for sure
in for loop, recursion

note:
don't forget the empty one []
*/

public class Subsets {
    // 2025
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Dont forget this base case
        result.add(new ArrayList<>());
        dfs(nums, 0, new ArrayList<>(), result);

        return result;
    }

    public void dfs(int[] nums, int pos, List<Integer> path, List<List<Integer>> result) {
        for (int i = pos; i < nums.length; i++) {
            path.add(nums[i]);
            result.add(new ArrayList<>(path));
            dfs(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> path, int[] nums, int start) {
        result.add(new ArrayList<Integer>(path));
        
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(result, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    // slightly different method
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());
        dfs(result, new ArrayList<Integer>(), nums, 0);

        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> path, int[] nums, int index) {
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            result.add(new ArrayList<Integer>(path));
            dfs(result, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
    // a more decent method
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        int size = (int) Math.pow(2, n);

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            // save i as combination, convert it to 32-bit
            int combination = i;

            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                if ((combination & 1) == 1) {
                    path.add(num);
                }
                combination >>= 1;
            }

            result.add(path);
        }

        return result;
    }
}