class FirstAndLastIndex {
	// good method
	// (target + 1)'s position - 1
	// use same function binarySearch is enough
    public int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target);
        if (left >= nums.length || nums[left] != target) {
            return new int[] {-1, -1};
        }

        return new int[] {left, binarySearch(nums, target + 1) - 1};
    }

    // first Greater or Equal
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}