/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].

idea:
for those duplicates, only count 1st unique one, skip the rest duplicates
typical dfs() with hashset to remove duplicates
use hashset to filter

*/
public class Permutations {
    // Fri Apr 26 01:07:38 2024
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // sort the array is a must
        // create a boolean array which track the certain indexed integer is present in out temp list or not
        helper(ans, new ArrayList<>(), nums, new boolean[nums.length]); // helper method
        
        return ans; // return output
    }

    private void helper(List<List<Integer>> ans, List<Integer> temp, int[] nums, boolean[] vis) {
        /*
            when size of the temp list and size of the input array are same then add this temp list into the final ans list.
            this is one of our permutation for given integer array
        */
        if (nums.length == temp.size()) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        
        /*
            Traverse for whole array one by one.
        */
        for (int i = 0; i < nums.length; i++) {
        
            if (vis[i]) continue; // if current element is already present in the temp, skip the element
            if (i > 0 && vis[i - 1] && nums[i] == nums[i - 1]) continue; // if prev element and current element is equal, skip the element
            
            vis[i] = true;
            temp.add(nums[i]);
            
            helper(ans, temp, nums, vis); // recursive call
            
            // backtracking
            temp.remove(temp.size() - 1);
            vis[i] = false;
        }
    }

    // Fri Apr 26 01:07:15 2024
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>(); 
        List<Integer> path = new ArrayList<>(); 
        Set<Integer> hs = new HashSet<>();

        dfs(nums, list, path, hs);
        return list;
    } 

    public void dfs(int[] nums, List<List<Integer>> list, List<Integer> path, Set<Integer> hs) {
        if (path.size() == nums.length) {
            // here waster a lot of time
            if (!list.contains(path)) {
                list.add(new ArrayList<>(path));
            }
  
            return ;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!hs.contains(i)) {
                hs.add(i);
                path.add(nums[i]);

                dfs(nums, list, path, hs);

                path.remove(path.size() - 1);
                hs.remove(i);
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        permute(nums, 0, result);

        return result;
    }

    // dfs permute()
    private void permute(int[] nums, int pos, List<List<Integer>> result) {
        int size = nums.length;

        if (pos == size) {
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            result.add(path);

            return;
        }
        
        Set<Integer> used = new HashSet<>();
        for (int i = pos; i < size; i++) {
            if (used.add(nums[i])) {
                swap(nums, pos, i);
                permute(nums, pos + 1, result);
                swap(nums, pos, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int save = nums[i];
        nums[i] = nums[j];
        nums[j] = save;
    }
}
