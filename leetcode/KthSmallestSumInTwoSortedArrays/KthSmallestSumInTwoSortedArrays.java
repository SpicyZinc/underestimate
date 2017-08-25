/*
Given two integer arrays sorted in ascending order and an integer k. Define sum = a + b, where a is an element from the first array and b is an element from the second one. Find the kth smallest sum out of all possible sums.
Example
Given [1, 7, 11] and [2, 4, 6].
For k = 3, return 7.
For k = 4, return 9.
For k = 8, return 15.
Challenge Do it in either of the following time complexity:
O(k log min(n, m, k)). where n is the size of A, and m is the size of B. O( (m + n) log maxValue). where maxValue is the max number in A and B.
Tags

Heap Priority Queue Sorted Matrix
Related Problems
Medium Kth Smallest Number in Sorted Matrix Medium Search a 2D Matrix II

idea:
similar to find Kth smallest element in a sorted matrix
the two arrays will form a sorted matrix
the element will be sum of two elements from each array

M  | 1,  7, 11
---|----------
2, | 3,  9, 13
4, | 5, 11, 15
6, | 7, 13, 17
note: use minHeap, implemented by PriorityQueue
3,5,7,9,11,13,13,15,17
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
		int kthSmallestSum = eg.kthSmallestSum(A, B, 4);
		System.out.println(kthSmallestSum);
	}

	int[] dx = new int[] {0, 1};
	int[] dy = new int[] {1, 0};

	public int kthSmallestSum(int[] A, int[] B, int k) {
        // Validation of input
        if (A == null || B == null || A.length == 0 || B.length == 0) {
            return -1;
        }
        if (A.length * B.length < k) {
            return -1;
        }

        PriorityQueue<Node> heap = new PriorityQueue<Node>(k, new NodeComparator());
        heap.offer(new Node(0, 0, A[0] + B[0]));

        boolean[][] visited = new boolean[A.length][B.length];

        for (int i = 0; i < k - 1; i++) {
            Node smallest = heap.poll();
            for (int j = 0; j < 2; j++) {
                int nextX = smallest.x + dx[j];
                int nextY = smallest.y + dy[j];

                if (isValid(nextX, nextY, A, B, visited)) {
                    visited[nextX][nextY] = true;
                    int nextSum = A[nextX] + B[nextY];
                    heap.offer(new Node(nextX, nextY, nextSum));
                }
            }
        }

        return heap.peek().sum;
    }

    private boolean isValid(int x, int y, int[] A, int[] B, boolean[][] visited) {
    	int m = A.length;
    	int n = B.length;
    	if (x < m && y < n && !visited[x][y]) {
    		return true;
    	}
    	return false;
    }
}