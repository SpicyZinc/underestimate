/*
Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

idea:
1. borrow quick sort idea
2. priority queue
3. sort the array first
*/

import java.util.*;

public class FindKthLargest {
	public static void main(String[] args) {
		int[] nums = {4, 2, 6, 5, 3, 9};
		FindKthLargest eg = new FindKthLargest();
		// 3th largest
		System.out.println(eg.findKthLargest(nums, 3));
	}

	// 0. best quick sort
	public int findKthLargest(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length == 0) {
			return 0;
		}
        // Kth largest is n - k + 1 th smallest
        return findKthSmallest(nums, nums.length - k + 1, 0, nums.length - 1);
    }
    
    // find Kth smallest
    public int findKthSmallest(int[] nums, int k, int start, int end) {
        // use start as pivot
        int pivot = start;
        int i = start;
        int j = end;
        while (i <= j) {
            while (i <= j && nums[i] <= nums[pivot]) i++; 
            while (i <= j && nums[j] >= nums[pivot]) j--;
            if (i <= j) swap(nums, i, j);
        }
        swap(nums, pivot, j); // whichever is pivot, swap again
        if (k == j + 1) {
            return nums[j];
        }
        else if (k > j + 1) {
            return findKthSmallest(nums, k, j + 1, end);
        }
        else {
            return findKthSmallest(nums, k, start, j - 1);
        }
    }
    // swap
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

	// 1. quick sort
    public int findKthLargest(int[] nums, int k) {
		if (k <= 0 || nums == null || nums.length == 0) {
			return 0;
		}
	 
		return getKth(nums.length - k + 1, nums, 0, nums.length - 1);
	}
	 
	public int getKth(int k, int[] nums, int start, int end) {
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
		} 
		else if (k < left + 1) {
			return getKth(k, nums, start, left - 1);
		} 
		else {
			return getKth(k, nums, left + 1, end);
		}
	}
	 
	private void swap(int[] nums, int n1, int n2) {
		int tmp = nums[n1];
		nums[n1] = nums[n2];
		nums[n2] = tmp;
	}

	// 2. priority queue
	public int findKthLargest(int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
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
    // demonstrate how to use priority queue
	public static void main(String[] args) {
		FindKthLargest eg = new FindKthLargest();
		int[] nums = { 3,2,1,5,6,4 };
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > 3) {
                pq.poll();
            }
        }

        while (pq.size() != 0) {
            int j = pq.remove();
            System.out.println(j);
        }
	}
}