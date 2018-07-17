/*
Given a sorted array of integers nums and integer values a, b and c.
Apply a function of the form f(x) = ax^2 + bx + c to each element x in the array.

The returned array must be in sorted order.
Expected time complexity: O(n)

Example:
nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
Result: [-23, -5, 1, 7]

idea:
1. 利用抛物线的性质 a > 0 or a < 0
才能够实现 O(n)
a > 0 最大值两边 取更大 填入数组末尾
a < 0 相反

2. use priority queue
*/

import java.util.*;

class SortTransformedArray {
	// 07/14/2018
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] transformed = new int[n];
        int idx;
        int left = 0;
        int right = n - 1;
        if (a > 0) {
            idx = n - 1;
            while (left <= right) {
                int leftVal = applyFunc(nums[left], a, b, c);
                int rightVal = applyFunc(nums[right], a, b, c);
                if (leftVal > rightVal) {
                    transformed[idx--] = leftVal;
                    left++;
                } else {
                    transformed[idx--] = rightVal;
                    right--;
                }
            }
        } else {
            idx = 0;
            while (left <= right) {
                int leftVal = applyFunc(nums[left], a, b, c);
                int rightVal = applyFunc(nums[right], a, b, c);
                if (leftVal > rightVal) {
                    transformed[idx++] = rightVal;
                    right--;
                } else {
                    transformed[idx++] = leftVal;
                    left++;
                }
            }
        }
        
        return transformed;
    }
    
    public int applyFunc(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
	
	public static void main(String[] args) {
		SortTransformedArray eg = new SortTransformedArray();
		int[] nums = {-4, -2, 2, 4};
		int a = 1;
		int b = 3;
		int c = 5;

		int[] result = eg.sortTransformedArray(nums, a, b, c);
		System.out.println(Arrays.toString(result));

		a = -1;

		result = eg.sortTransformedArray(nums, a, b, c);
		System.out.println(Arrays.toString(result));
	}

	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		int n = nums.length;
		int[] result = new int[n];
		int i = 0;
		int j = n - 1;

		int idx = a >= 0 ? n - 1 : 0;

		while (i <= j) {
			int left = calculate(nums[i], a, b, c);
			int right = calculate(nums[j], a, b, c);

			if (a >= 0) {
				if (left >= right) {
					result[idx] = left;
					i++;
				} else {
					result[idx] = right;
					j--;
				}
				idx--;
			} else {
				if (left >= right) {
					result[idx] = right;
					j--;
				} else {
					result[idx] = left;
					i++;
				}
				idx++;
			}
		}

		return result;
	}

	public int calculate(int x, int a, int b, int c) {
		return a * x * x + b * x + c;
	}

	// priority queue
	public int[] sortTransformedArrays(int[] nums, int a, int b, int c) {
		int[] res = new int[nums.length];
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for (int num : nums) {
            q.add(a * num * num + b * num + c);
        }

        int idx = 0;
        while (!q.isEmpty()) {
            res[idx++] = q.poll();
        }

        return res;
	}
}