/*
Median of Two Sorted Arrays
There are two sorted arrays A and B of size m and n respectively. 
Find the median of the two sorted arrays. 

The overall run time complexity should be O(log(m+n)).

idea:
https://www.cnblogs.com/grandyang/p/4465932.html

1. my idea is using O(m+n) time which is bigger than O(log(m+n)), first merge 2 sorted array then find the median of the final sorted array
2. O(log(m+n)) is below:
based on Find the Kth element in merged sorted arrays
median, K is NOT array index
odd length, K is total/2
even length, K is total/2, total/2 + 1
*/
public class MedianOfTwoSortedArrays {
	// time: O(log(m + n))
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
        int total = A.length + B.length;
        if (total % 2 == 0) {
        	return (double) ( findKth(A, 0, m - 1, B, 0, n - 1, total / 2) + findKth(A, 0, m - 1, B, 0, n - 1, total / 2 + 1) ) / 2.0;
        }
        else {
        	return findKth(A, 0, A.length - 1, B, 0, B.length - 1, total/2 + 1);
        }
    }
    // find kth, parameter is k
    public double findKth(int a[], int s1, int e1, int b[], int s2, int e2, int k) {  
        int m = e1 - s1 + 1;  
        int n = e2 - s2 + 1;  
        // always assume that a is equal or smaller than b
        if (m > n) return findKth(b, s2, e2, a, s1, e1, k); 
        if (s1 > e1) return b[s2 + k - 1];   
        if (s2 > e2) return a[s1 + k - 1];  
        if (k == 1) return Math.min(a[s1], b[s2]);  

        int midA = Math.min(k/2, m), midB = k - midA;   
        if (a[s1 + midA - 1] < b[s2 + midB - 1]) {
            return findKth(a, s1 + midA, e1, b, s2, e2, k - midA);
        }
        else if (a[s1 + midA - 1] > b[s2 + midB - 1]) {
            return findKth(a, s1, e1, b, s2 + midB, e2, k - midB);
        }
        else {
            return a[s1 + midA - 1];
        }
    }
    // easy to understand solution, time: O(m + n)
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;

	    int i = 0, j = 0, total = m + n;
	    double prev = 0, last = 0;

	    if (total < 2) {
	        if (m == 0 && n == 0) {
	        	return 0;
	        }
	        if (m == 1) {
	        	return A[0];
	        }
	        else {
	        	return B[0];
	        }
	    }

	    while ( (i + j) <= (total / 2) ) {
	        prev = last;
	        if (i >= m) {
	            last = B[j];
	            j++;
	        }
	        else if (j >= n) {
	            last = A[i];
	            i++;
	        }
	        else if (A[i] < B[j]) {
	            last = A[i];
	            i++;
	        }
	        else {
	            last = B[j];
	            j++;
	        }
	    }

	    if (total % 2 == 0) {
	        return (prev + last) / 2.0;
	    }
	    else {
	        return last;
	    }
	}

	// self direct method, merge two sorted array, then find the median of the array
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] sorted = new int[m + n];
        int i = 0, j = 0, idx = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                sorted[idx++] = nums1[i];
                i++;
            }
            else if (nums1[i] > nums2[j]) {
                sorted[idx++] = nums2[j];
                j++;
            }
            else {
                sorted[idx++] = nums1[i];
                i++;
                sorted[idx++] = nums2[j];
                j++;
            }
        }

        while (i < m) {
            sorted[idx++] = nums1[i++];
        }

        while (j < n) {
            sorted[idx++] = nums2[j++];
        }
        return getMedian(sorted);
    }
    private double getMedian(int[] nums) {
        int n = nums.length;
        if (n % 2 == 1) {
            return (double)nums[n / 2];
        }
        else {
            return (double) (nums[n / 2] + nums[n / 2 - 1]) / 2;
        }
    }
}