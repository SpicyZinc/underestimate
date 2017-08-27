/*
Given two integer arrays sorted in ascending order and an integer k.
Define sum = a + b, where a is an element from the first array and b is an element from the second one.
Find the kth smallest sum out of all possible sums.

Example
Given [1, 7, 11] and [2, 4, 6].
For k = 3, return 7.
For k = 4, return 9.
For k = 8, return 15.
Challenge Do it in either of the following time complexity:
O(k log min(n, m, k)). where n is the size of A, and m is the size of B.
O( (m + n) log maxValue). where maxValue is the max number in A and B.

Tags
Heap Priority Queue Sorted Matrix
Related Problems
Medium Kth Smallest Number in Sorted Matrix Medium Search a 2D Matrix II

idea:
1. same as find Kth smallest element in a sorted matrix
the two arrays will form a sorted matrix
the element will be sum of two elements from each array

M  | 1,  7, 11
---|----------
2, | 3,  9, 13
4, | 5, 11, 15
6, | 7, 13, 17

note: use minHeap, implemented by PriorityQueue
3,5,7,9,11,13,13,15,17
note: minHeap.poll() same line, same column could have the smallest value

2. binary search
*/

import java.util.*;

class Node {
	public int x;
	public int y;
	public int sum;

	public Node(int x, int y, int sum) {
		this.x = x;
		this.y = y;
		this.sum = sum;
	}
}

class NodeComparator implements Comparator<Node> {
	@Override
	public int compare(Node a, Node b) {
		return a.sum - b.sum;
	}
}

public class KthSmallestSumInTwoSortedArrays {
	public static void main(String[] args) {
		KthSmallestSumInTwoSortedArrays eg = new KthSmallestSumInTwoSortedArrays();
		int[] A = {1, 7, 11};
		int[] B = {2, 4, 6};
		int k = 4;
		int kthSmallestSum = eg.kthSmallestSum(A, B, k);
		System.out.println(kthSmallestSum);
	}

    public int kthSmallestSum(int[] A, int[] B, int k) {
        if (A.length == 0 || B.length == 0 || A == null || B == null) return -1;

        int m = A.length;
        int n = B.length;
        if (m * n < k) return -1;
        
        int[][] directions = { {0, 1}, {1, 0} };
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(k, new NodeComparator());
        minHeap.offer(new Node(0, 0, A[0] + B[0]));
        visited[0][0] = true;
        // already added one node to minHeap
        int idx = 1;
        while (idx < k) {
            Node smallest = minHeap.poll();
            for (int[] dir : directions) {
                int nextX = smallest.x + dir[0];
                int nextY = smallest.y + dir[1];
                if (isValid(A, B, nextX, nextY, visited)) {
                    visited[nextX][nextY] = true;
                    minHeap.offer(new Node(nextX, nextY, A[nextX] + B[nextY]));
                }
            }
            idx++;
        }

        return minHeap.peek().sum;
    }

    public boolean isValid(int[] A, int[] B, int i, int j, boolean[][] visited) {
        int m = A.length;
        int n = B.length;
        return i < m && j < n && !visited[i][j];
    }

    // method 2
    public int kthSmallestSum(int[] A, int[] B, int k) {
        int m = A.length;
        int n = B.length;

        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = A[j] + B[i];
            }
        }

        return kthSmallest(matrix, k);
    }

    // find Kth smallest Element in sorted matrix
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
}