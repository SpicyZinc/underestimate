/*
Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

idea:
1. borrow quick sort idea
2. By default the Priority Queue works as min-Heap, which is min at the top
3. sort the array first
*/

import java.util.*;

public class FindKthLargest {
	public static void main(String[] args) {
		int[] nums = {4, 2, 6, 5, 3, 9};
		FindKthLargest eg = new FindKthLargest();
		// 3rd largest
		System.out.println(eg.findKthLargest(nums, 3));
	}

	// 1. best quick sort
	public int findKthLargest(int[] nums, int k) {
        if (k <= 0 || nums.length == 0 || nums == null) {
            return 0;
        }
        // Kth largest == (n - K + 1)th smallest
        return findKthSmallest(nums, nums.length - k + 1, 0, nums.length - 1);
        // return getKth(nums, nums.length - k + 1, 0, nums.length - 1);
    }
    
    public int findKthSmallest(int[] nums, int k, int start, int end) {
        // imaginary pivot, this case, use start, but start will be still compared
        int pivotIdx = start;
        int pivotVal = nums[pivotIdx];
        
        int i = start;
        int j = end;
        while (i <= j) {
            while (i <= j && nums[i] <= pivotVal) i++;
            while (i <= j && nums[j] >= pivotVal) j--;
            // i j stop moving, but there are still values bigger than pivotVal on the left
            // or values smaller than pivotVal on the right
            if (i <= j) swap(nums, i, j);
        }
        // put the real pivotVal into the position
        swap(nums, pivotIdx, j);
        // where recursion applies
        // j is index, j + 1 is comparable with k
        if (k > j + 1) {
            return findKthSmallest(nums, k, j + 1, end);
        } else if (k < j + 1) {
            return findKthSmallest(nums, k, start, j - 1);
        } else {
            return nums[j];
        }
    }

    public int getKth(int[] nums, int k, int start, int end) {
		int pivot = nums[end];
		int left = start;
		int right = end;
		while (true) {
			while (left < right && nums[left] < pivot) {
				left++;
			}
			while (right > left && nums[right] >= pivot) {
				right--;
			}
			if (left == right) {
				break;
			}
			swap(nums, left, right);
		}
		swap(nums, left, end);
	 
		if (k == left + 1) {
			return pivot;
		} else if (k < left + 1) {
			return getKth(nums, k, start, left - 1);
		} else {
			return getKth(nums, k, left + 1, end);
		}
	}
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

	// 2. priority queue
	public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
	}
    // 3. sort the array first
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}