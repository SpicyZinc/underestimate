/*
Given two sorted arrays A, B of size m and n respectively. 
Find the k-th smallest element in the union of A and B. 
You can assume that there are no duplicate elements.

idea:
http://leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html
1. O(k)
2. binary search, O(log m + log n)
*/

public class KthSmallestInUnionTwoSortedArrays {
	public static void main(String[] args) {
		int[] A = {1,3,5,7,9};
    	int[] B = {2,4,6,8,10,12,14,16,18,20,22,24,30,40,50,56,77,35};

    	KthSmallestInUnionTwoSortedArrays eg = new KthSmallestInUnionTwoSortedArrays();
    	int result = eg.findKthSmallest(A, B, 11);
    	System.out.println(result);
	}

	public int findKthSmallest(int[] A, int[] B, int k) {
		int m = A.length;
		int n = B.length;

		int KthSmallest = Integer.MAX_VALUE;
		if (k > m + n) {
			return Math.max(A[m - 1], B[n - 1]);
		} else {
			int idx = 0;
			int i = 0, j = 0;
			while (idx < k) {
				if (A[i] < B[j]) {
					KthSmallest = A[i];
					i++;
				} else if (A[i] > B[j]) {
					KthSmallest = B[j];
					j++;
				} else {
					KthSmallest = A[i];
					i++;
					j++;
				}
				idx++;
				if (i == m || j == n) break;
			}
			if (idx < k) {
				if (i < m) {
					while (idx < k) {
						KthSmallest = A[i++];
						idx++;
					}
				}
				if (j < n) {
					while (idx < k) {
						KthSmallest = B[j++];
						idx++;
					}
				}
			}
		}

		return KthSmallest;
	}

	// method 2, binary
	public int findKthSmallest(int[] A, int[] B, int k) {
		if (k > A.length + B.length) return -1;
		return findKth(A, 0, B, 0, k);
	}
	// helper, note: default is A is smaller length than B
	public int findKth(int[] A, int i, int[] B, int j, int k) {
		// if A is longer than B
		if (A.length - i > B.length - j) return findKth(B, j, A, i, k);

		if (i >= A.length) return B[j + k - 1];
		if (k == 1) return Math.min(A[i], B[j]);

		int aMid = Math.min(k / 2, A.length - i);
		int bMid = k - aMid;
		if (A[i + aMid - 1] <= B[j + bMid - 1]) {
			return findKth(A, i + aMid, B, j, k - aMid);
		} else {
			return findKth(A, i, B, j + bMid, k - bMid);
		}
	}
}