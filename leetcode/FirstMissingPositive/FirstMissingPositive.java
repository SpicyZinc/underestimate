/*
Given an unsorted integer array, find the first missing positive integer.

[1,2,0] return 3
[3,4,-1,1] return 2

Your algorithm should run in O(n) time and uses constant space. 

idea:
1. 
constant space requires to use the array
change array to be like 1 2 3 in position 0 1 2

once find nums[i] != i + 1, return i + 1;
that is the first missing positive

The result is 1  2  3  4  2  -1  0, all sorted elements are placed at fore part of the array

2. direct method,
sort first
*/
import java.util.*;

public class FirstMissingPositive {
    public static void main(String[] args) {
        FirstMissingPositive eg = new FirstMissingPositive();
        int[] nums = {3, 2, 0, 2, 4, -1, 1};
        System.out.println("1st missing positive number == " + eg.firstMissingPositive(nums));
    }

    // note, use while
    // 01/22/2019
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // only care about positive number and <= n number
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }

        return n + 1;
    }
    // put i to nums[i - 1], so the array looks like: 1, 2, 3, ...
    public int firstMissingPositive(int[] nums) { 
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                // if ever meeting one of the three circumstances, break while loop and keep walking to next element in the array
                // 1. negative
                // 2. nums[i] > nums.length
                // 3. already value as index being there
                if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == nums[nums[i] - 1]) {
                    break;
                }
                swap(nums, nums[i] - 1, i);
            }
        }
 
        for (int i = 0; i < nums.length; i++) {
            // the 1st missing positive number at the place where nums[i] != i + 1
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // if this array is want 1, 2, 3 in index 0, 1, 2, then return (nums.length + 1)
        return nums.length + 1;         
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // similar to the above, except && === !||
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1 && nums[i] >= 1 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            } else {
                i++;
            }
        }
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    // direct method
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 1;
        }

        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length && nums[i] <= 0) {
            i++;                        
        }
        // missing positive, starting from 1 inclusive
        int result = 0;
        for (; i < nums.length; i++) {
            // skip repetitive elements
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // first positive number should be 1
            if (nums[i] != result + 1) {
                return result + 1;
            } else {
                result = nums[i];
            }
        }

        return result + 1;
    }
}