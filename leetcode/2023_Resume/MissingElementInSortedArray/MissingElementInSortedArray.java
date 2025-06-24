/*
Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k, return the kth missing number starting from the leftmost number of the array.

Example 1:
Input: nums = [4,7,9,10], k = 1
Output: 5
Explanation: The first missing number is 5.

Example 2:
Input: nums = [4,7,9,10], k = 3
Output: 8
Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.

Example 3:
Input: nums = [1,2,4], k = 3
Output: 6
Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.

Constraints:
1 <= nums.length <= 5 * 10^4
1 <= nums[i] <= 10^7
nums is sorted in ascending order, and all the elements are unique.
1 <= k <= 10^8

Follow up: Can you find a logarithmic time complexity (i.e., O(log(n))) solution?

idea:
similar to KthMissingPositiveNumber
this one is starting from nums[0]
*/
class MissingElementInSortedArray {
    // Mon May 20 02:06:27 2024
    // self
    public int getMissingCount(int[] nums, int idx) {
        return nums[idx] - nums[0] - (idx - 0);
    }

    public int missingElement(int[] nums, int k) {
        int base = nums[0];
        int n = nums.length;
        int i = 1;
        for (; i < n; i++) {
            int missingCount = getMissingCount(nums, i);
            if (missingCount >= k) {
                break;
            }
        }
        return nums[i - 1] + k - getMissingCount(nums, i - 1);
    }

    // Sun May 19 00:01:56 2024
    // Elegant direct way
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int missingCount = nums[i] - nums[i - 1] - (i - (i - 1));
            if (missingCount >= k) {
                return nums[i - 1] + k;
            }

            k -= missingCount;
        }

        return nums[n - 1] + k;
    }

    // Binary
    public int missingElement(int[] nums, int k) {
        if (nums.length == 0) {
            return -1;
        }

        int offset = nums[0];

        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int missingCount = nums[mid] - offset - (mid - 0);
            if (missingCount < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // pointer 来到了right
        return nums[right] + k - (nums[right] - offset - (right - 0));
        // return right + offset + k;
    }

    // Return how many numbers are missing until nums[idx]
    // 这是通式 因为是连续的没有重复的 应该差 idx - 0 但是差了 nums[idx] - nums[0], 两者的difference 就是 missing count
    // nums[mid] - nums[left] - (mid - left) means the total number of missing numbers between nums[mid] and nums[left]
    public int missing(int idx, int[] nums) {
        return nums[idx] - nums[0] - (idx - 0);
    }

    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        // If kth missing number is larger than the last element of the array
        if (k > missing(n - 1, nums)) {
            return nums[n - 1] + (k - missing(n - 1, nums));
        }

        int idx = 1;
        // find idx such that missing(idx - 1) < k <= missing(idx)
        while (missing(idx, nums) < k) {
            idx++;
        }

        // kth missing number is greater than nums[idx - 1] and less than nums[idx]
        return nums[idx - 1] + k - missing(idx - 1, nums);
    }
}


class MissingElementInSortedArray {
    // Sun Feb 19 17:26:35 2023
    // Direct way
    public int missingElement(int[] nums, int k) {
        int startIndex = 1;
        int start = nums[startIndex - 1];    
        
        while (k > 0) {
            if (startIndex < nums.length) {
                // No missing, so proceed to next index
                if (start + 1 == nums[startIndex]) {
                    startIndex++;
                } else if (start + 1 < nums[startIndex]) {
                    k--;
                }
            } else {
                k--;
            }
                
            start++;
        }

        return start;
    }
}
