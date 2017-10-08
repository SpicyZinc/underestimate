/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
You need to return the number of important reverse pairs in the given array.

Example1:
Input: [1,3,2,3,1]
Output: 2

Example2:
Input: [2,4,3,5,1]
Output: 3

Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

1. brute force, TLE
2. borrow idea from merge sort, gradually back, the two subarrays are sorted, it is easy to get satisfying count
http://blog.csdn.net/kakitgogogo/article/details/55097648
*/

class ReversePairs {
	// TLE
	public int reversePairs(int[] nums) {
		int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
        	for (int j = i + 1; j < nums.length; j++) {
                long a = (long) nums[i];
                long b = (long) 2 * nums[j];
        		if (a > b) {
        			cnt++;
        		}
        	}
        }

        return cnt;
    }
    // method 2
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length-1);
    }
    private int mergeSort(int[] nums, int start, int end) {
    	if (start >= end) return 0;

    	int mid = start + (end - start) / 2;
        int cnt = mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end);
        // 因为已经排好序 所以容易得到符合要求的cnt
        for (int i = start, j = mid + 1; i <= mid; i++) {
        	// /2.0 is avoid integer overflow
            while (j <= end && nums[i] / 2.0 > nums[j]) {
            	j++;
            }
            cnt += j - (mid + 1);
        }
        // make array sorted, after backtracking, for loop, two pointers can advance without any worrying about back.
        // end + 1 exclusive, actually sort [start, end]
        Arrays.sort(nums, start, end + 1);

        return cnt; 
    }
}