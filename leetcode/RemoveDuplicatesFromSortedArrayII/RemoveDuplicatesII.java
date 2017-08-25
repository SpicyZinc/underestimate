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
*/
public class RemoveDuplicatesII {
    public int removeDuplicates(int[] nums) {
        int index = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            }
            else {
                count = 1;
            }
            if (count <= 2) {
                nums[index++] = nums[i];
            }
        }

        return index;
    }
}