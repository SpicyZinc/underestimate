/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.

idea:
binary search
made 栽在 michelle shi 的手中 赖自己居然不会 找最小 在 rotated sorted array
记住 最小的一定在 rotated part 
note, use mid to update left or right
note, the minimum element's previous one is bigger than the minimum, which breaks the fact that the array is sorted
*/
public class FindMinimumInSortedArray {
    // 03/04/2019
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // nums already sorted
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        
        while (left < right) {
            if (left + 1 == right) {
                return nums[right];
            }
            
            int mid = left + (right - left) / 2;
            // left part is sorted
            if (nums[left] < nums[mid]) {
                left = mid;
            } else { // right part is sorted
                right = mid ;
            }
        }

        return nums[left];
    }

    // if (left + 1 == right) {
    //     return nums[right];
    // }
    // replaced with two cases
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // nums already sorted
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            // mid at least == left + 1
            // mid - 1 == left, so return is valid in the range    
            if (mid > left && nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            // save thing for right
            if (mid < right && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            
            // left part is sorted
            if (nums[left] < nums[mid]) {
                left = mid;
            } else { // right part is sorted
                right = mid ;
            }
        }

        return nums[left];
    }

    public int findMin(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int start = 0;
        int end = nums.length - 1;
        // nums already sorted
        if (nums[start] < nums[end]) {
            return nums[start];
        }
        // good example [3, 1, 2]
        while (start < end) {
            if (start + 1 == end) {
                return nums[end];
            }
            int mid = start  + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        return nums[start];
    }
    // recursion
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }
    
    public int findMin(int[] nums, int start, int end) {
        // two cases:
        // if two pointers are equal, no matter which one is picked
        // if value at start < one at end, the array is sorted, so 1st element is min
        if (start == end || nums[start] < nums[end]) {
            return nums[start];
        }
        // if start + 1 == end, means only two elements
        if (start + 1 == end) {
            return nums[end];
        }
        
        int mid = start + (end - start) / 2;
        return nums[mid] > nums[end] ? findMin(nums, mid, end) : findMin(nums, start, mid);
    }
}