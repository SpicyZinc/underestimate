/*
Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.
Can you solve it without sorting?

Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4

Constraints:
1 <= k <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

idea:
meta 2024 phone tested

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
	// Mon Apr 29 01:33:00 2024
	// self go deeper to quick sort
	public int findKthLargest(int[] nums, int k) {
        return findKth(nums, nums.length - k + 1, 0, nums.length - 1);
    }

    public int findKth(int[] nums, int k, int left, int right) {
        int i = left;
        int j = right;
        int pivotIdx = left;
        int pivotVal = nums[pivotIdx];

        while (i <= j) {
            while (i <= j && nums[i] <= pivotVal) {
                i++;
            }

            while (i <= j && nums[j] >= pivotVal) {
                j--;
            }
            // found not comply with left <= pivotVal <= right, swap them
            if (i <= j) {
                swap(nums, i, j);
            }
        }

        swap(nums, pivotIdx, j);

        if (k > j + 1) {
            return findKth(nums, k, j + 1, right);
        } else if (k < j + 1) {
            return findKth(nums, k, left, j - 1);
        } else {
            return nums[j];
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Mon Apr 29 01:33:29 2024
    // self count sort
    public int findKthLargest(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // index is the all possible num between min and max
        // value is the count
        int[] count = new int[max - min + 1];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] - min]++;
        }

        int remaining = k;
        // 从最大max 开始, 看他多少个 减去个数 都减没了 就是k大
        for (int num = count.length - 1; num >= 0; num--) {
            remaining -= count[num];
            // note <=
            if (remaining <= 0) {
                return num + min;
            }
        }

        return -1;
    }
    
	// Sun Apr  7 03:55:54 2024
	public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.peek();
    }

	// Sun Mar 31 14:24:07 2019
	public int findKthLargest(int[] nums, int k) {
		if (k <= 0 || nums.length == 0 || nums == null) {
			return 0;
		}

		int n = nums.length;

		return findKthSmallest(nums, n - k + 1, 0, n - 1);
	}

	public int findKthSmallest(int[] nums, int k, int start, int end) {
		int pivot = start;
		int pivotVal = nums[start];

		int i = start;
		int j = end;
		
		while (i <= j) {
			while (i <= j && nums[i] <= pivotVal) {
				i++;
			}
			while (i <= j && nums[j] >= pivotVal) {
				j--;
			}

			if (i <= j) {
				swap(nums, i, j);
			}
		}

		swap(nums, pivot, j);

		if (k > j + 1) {
			return findKthSmallest(nums, k, j + 1, end);
		} else if (k < j + 1) {
			return findKthSmallest(nums, k, start, j - 1);
		} else {
			return nums[j];
		}
	}

	// 1. best quick sort
	public int findKthLargest(int[] nums, int k) {
        if (k <= 0 || nums.length == 0 || nums == null) {
            return 0;
        }
        // Kth largest == (n - K + 1) th smallest
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
            if (i <= j) {
                swap(nums, i, j);
            }
        }
        // put the real pivotVal into the position
        // 后来想可能是如果不移动 下次还是作为pivotVal, 影响时间 或是 压根就不会sort 
        swap(nums, pivotIdx, j);
        // 记住 j 现在就是小于pivotVal的所有index inclusive
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
    // 4. sort with Arrays.sort() with self defined comparator
    public int findKthLargest(int[] nums, int k) {
        Integer[] a = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[i] = Integer.valueOf(nums[i]);
        }
        // Arrays.sort(a, new Comparator<Integer>() {
        //     @Override
        //     public int compare(Integer a, Integer b) {
        //         return b - a;
        //     }
        // });

        Arrays.sort(a, (x, y) -> y - x);
        return a[k - 1];
    }
}