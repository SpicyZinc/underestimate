/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].

idea: for those duplicates, only count 1st unique one, skip the rest duplicates
*/
public class PermutationsII {
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permuteUnique(num, 0, result);
		return result;
	}
	 
	private void permuteUnique(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
		if (start == num.length - 1) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for (int x : num) {
				temp.add(x);
			}

			result.add( temp );
		}
		else {
			for (int j = start; j < num.length; j++) {
				if ( !containsDuplicate(num, start, j) ) {
					swap(num, start, j);
					permuteUnique(num, start + 1, result);
					swap(num, start, j);
				}
			}
		}
	}
	 
	private boolean containsDuplicate(int[] arr, int start, int end) {
		for (int i = start; i < end; i++) {
			if (arr[i] == arr[end]) {
				return true;
			}
		}

		return false;
	}
	 
	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}



	public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums==null || nums.length==0) { return ans; }
        permute(ans, nums, 0);
        return ans;
    }
    
    private void permute(List<List<Integer>> ans, int[] nums, int index) {
        if (index == nums.length) { 
            List<Integer> temp = new ArrayList<>();
            for (int num: nums) { temp.add(num); }
            ans.add(temp);
            return;
        }
        Set<Integer> appeared = new HashSet<>();
        for (int i=index; i<nums.length; ++i) {
            if (appeared.add(nums[i])) {
                swap(nums, index, i);
                permute(ans, nums, index+1);
                swap(nums, index, i);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int save = nums[i];
        nums[i] = nums[j];
        nums[j] = save;
    }
}