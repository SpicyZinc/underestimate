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