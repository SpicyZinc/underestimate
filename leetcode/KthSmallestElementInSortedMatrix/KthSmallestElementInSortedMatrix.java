/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8, return 13.

Note: 
You may assume k is always valid, 1 ≤ k ≤ n2.

idea:
https://www.programcreek.com/2016/08/leetcode-kth-smallest-element-in-a-sorted-matrix-java/
1. binary search
kthSmallest, since the matrix is sorted,
binary search to find position in each row, add together
note: matrix is not serpentine sorted, it is another way sorted
2. PriorityQueue, over size of k, poll()
weird answer always,
http://www.jiuzhang.com/solutions/kth-smallest-number-in-sorted-matrix/
*/

import java.util.*;

public class KthSmallestElementInSortedMatrix {
	public static void main(String[] args) {
		KthSmallestElementInSortedMatrix eg = new KthSmallestElementInSortedMatrix();
		
		int[][] matrix = {
			{ 1,  5,  9},
   			{10, 11, 13},
			{12, 13, 15}
		};
		int eighth = eg.kthSmallest(matrix, 8);
		System.out.println("======");
		System.out.println(eighth);
	}

    // why fail
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int min = matrix[0][0];
        int max = matrix[m - 1][n - 1];

        int cnt = 0;
        while (min < max) {
            int mid = min + (max - min) / 2;
            for (int i = 0; i < m; i++) {
                cnt += getCntBiggerThanTarget(matrix[i], mid);
            }
            if (cnt < k) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return min;
    }

    public int getCntBiggerThanTarget(int[] a, int target) {
        if (a[0] >= target) {
            return 0;
        }
        if (a[a.length - 1] < target) {
            return a.length;
        }
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < target) {
                cnt++;
            }
        }
        return cnt;
    }
    // method 1
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int min = matrix[0][0];
        int max = matrix[n - 1][matrix[0].length - 1];

        while (min < max) {
        	int midPosition = 0;
            int midVal = min + (max - min) / 2;
        	for (int i = 0; i < n; i++) {
        		midPosition += binarySearch(matrix[i], midVal);
        	}
        	if (midPosition < k) {
        		min = midVal + 1;
        	}
        	else {
        		max = midVal;
        	}
        }

        return min;
    }
    // slightly different from search insertion position
    public int binarySearch(int[] nums, int target) {
    	int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // method 2
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        });
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pq.offer(matrix[i][j]);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }

       	for (int num : pq) {
       		System.out.println(num);
       	}
        
        return pq.poll();
    }
}
