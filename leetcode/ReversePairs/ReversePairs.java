/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
You need to return the number of important reverse pairs in the given array.

Example 1:
Input: [1,3,2,3,1]
Output: 2

Example 2:
Input: [2,4,3,5,1]
Output: 3

Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

1. brute force, TLE
2. borrow idea from merge sort, gradually back, the two subarrays are sorted, it is easy to get satisfying count
http://blog.csdn.net/kakitgogogo/article/details/55097648
*/
import java.util.*;

class ReversePairs {
    public static void main(String[] args) {
        int[] nums = {2,4,3,5,1};
        MergeSort ms = new MergeSort();
        ms.mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

        int[] A = {2,4,3,5,1};

        ReversePairs eg = new ReversePairs();
        long cnt = eg.reversePairs(A);
        System.out.println(cnt);
    }

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
        return mergeSort(nums, 0, nums.length - 1);
    }
    
    // called mergeSort, it has to have a sort method
    public int mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }
        
        int mid = start + (end - start) / 2;
        // sort left and right parts
        int cnt = mergeSort(nums, start, mid) + mergeSort(nums, mid + 1, end);
        // both left and right are sorted till now, it is easy to get satisfying cnt
        for (int i = start, j = mid + 1; i <= mid; i++) {
            // / 2.0 is avoid integer overflow
            while (j <= end && nums[i] / 2.0 > nums[j] ) {
                j++;
            }
            cnt += j - (mid + 1);
        }
        // make array sorted, after backtracking, for loop, two pointers can advance without any worrying about back.
        // end + 1 exclusive, actually sort [start, end]
        Arrays.sort(nums, start, end + 1);
        
        return cnt;
    }

    // lintcode version, i < j and nums[i] > nums[j]
    public long reversePairs(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        return mergeSort(A, 0, A.length - 1);
    }

    private long mergeSort(int[] A, int start, int end) {
        if (start >= end) {
            return 0;
        }

        long sum = 0;
        int mid = start + (end - start) / 2;
        sum += mergeSort(A, start, mid);
        sum += mergeSort(A, mid + 1, end);
        sum += merge(A, start, mid, end);

        return sum;
    }

    private long merge(int[] A, int start, int mid, int end) {
        long sum = 0;

        int i = start;
        int j = mid + 1;

        int[] temp = new int[A.length];
        int index = start;        

        while (i <= mid && j <= end) {
            if (A[i] <= A[j]) {
                temp[index++] = A[i++];
            } else {
                temp[index++] = A[j++];
                sum += mid - i + 1;
            }
        }

        while (i <= mid) {
            temp[index++] = A[i++];
        }

        while (j <= end) {
            temp[index++] = A[j++];
        }

        for (int idx = start; idx <= end; idx++) {
            A[idx] = temp[idx];
        }

        return sum;
    }
}


class MergeSort {
    public void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);

            merge(nums, left, mid, mid + 1, right);
        }
    }

    public void merge(int[] nums, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int i = leftStart;
        int j = rightStart;

        int[] temp = new int[nums.length];
        int idx = leftStart;

        while (i <= leftEnd && j <= rightEnd) {
            if (nums[i] < nums[j]) {
                temp[idx] = nums[i++];
            } else {
                temp[idx] = nums[j++];
            }
            idx++;
        }


        while (i <= leftEnd) {
            temp[idx++] = nums[i++];
        }

        while (j <= rightEnd) {
            temp[idx++] = nums[j++];
        }

        for (int k = leftStart; k <= rightEnd; k++) {
            nums[k] = temp[k];
        }
    }
}