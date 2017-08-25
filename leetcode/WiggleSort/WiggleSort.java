/*
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

idea:
https://segmentfault.com/a/1190000003783283

this is wiggle sort I, allowing equal sign

1. sort first, start from 3rd element, swap 3rd with 2nd, swap 5th with 4th
2. O(n), just swap()
i % 2 == 1 nums[i] >= nums[i - 1]
i % 2 == 0 nums[i] <= nums[i - 1]
*/

import java.util.*;

public class WiggleSort {
	public static void main(String[] args) {
		WiggleSort eg = new WiggleSort();
		// int[] nums = {1, 5, 1, 1, 6, 4};
		int[] nums = {3, 5, 2, 1, 6, 4};
		eg.wiggleSort(nums);
		for (int num : nums)  {
			System.out.print( num + "    ");
		}
		System.out.println();
	}

	// method 1
    // public void wiggleSort(int[] nums) {
    //     if (nums.length <= 1 || nums == null) {
    //         return;   
    //     }
    //     Arrays.sort(nums);
    //     for (int i = 2; i < nums.length; i += 2) {
    //     	swap(nums, i - 1, i);
    //     }
    // }

    // method 2
    public void wiggleSort(int[] nums) {
        if (nums.length <= 1 || nums == null) {
            return;   
        }
        for (int i = 1; i < nums.length; i++) {
        	if ( (i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1]) ) {
        		swap(nums, i-1, i);
        	}
        }
    }
    private void swap(int[] nums, int i, int j) {
    	int temp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = temp;
    }
}