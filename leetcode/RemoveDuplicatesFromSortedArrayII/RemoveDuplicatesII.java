/*
Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],
Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
It doesn't matter what you leave beyond the new length.

idea:
slightly different from I, added a counter
since it is sorted array, so use it to know times of each duplicates
note: each element at most appear twice
note: always nums[0] no need to consider
*/
public class RemoveDuplicatesII {
	public int removeDuplicates(int[] nums) {
        int j = 0;
        int i = 1;
        int count = 1;
        for (i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                count++;
            } else {
                count = 1;
            }
            
            if (count <= 2) {
                // increase the resultant array
                nums[++j] = nums[i];
            }
        }
        
        return j + 1;
    }

    public int removeDuplicates(int[] nums) {
        int j = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }

        return j;
    }
}