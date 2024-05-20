/*
There are two sorted arrays A and B of size m and n respectively. 
Find the median of the two sorted arrays. 

The overall run time complexity should be O(log(m+n)).

idea:
https://www.cnblogs.com/grandyang/p/4465932.html

1. my idea is using O(m+n) time which is bigger than O(log(m+n)),
first merge 2 sorted array then find the median of the final sorted array
2. O(log(m+n)) is below:
based on Find the Kth element in merged sorted arrays
median, K is NOT array index
odd length, K is total / 2
even length, K is total / 2, total / 2 + 1
*/
public class MedianOfTwoSortedArrays {
    // direct method, merge two sorted array, then find the median of the array
    // space cost (m + n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] sorted = new int[m + n];

        int i = 0, j = 0, idx = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                sorted[idx++] = nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                sorted[idx++] = nums2[j++];
            } else {
                sorted[idx++] = nums1[i++];
                sorted[idx++] = nums2[j++];
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
            return (double) nums[n / 2];
        } else {
            return (double) (nums[n / 2 - 1] + nums[n / 2]) / 2;
        }
    }
    // easy to understand solution, O(m + n)
    // constant space cost
    // 要求 median, 要么hit中值 要么两个值
    public double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length;
        int n = B.length;
        int total = m + n;

        int i = 0;
        int j = 0;

        if (total < 2) {
            if (m == 0 && n == 0) {
                return 0;
            }
            if (m == 1) {
                return A[0];
            } else {
                return B[0];
            }
        }

        double prev = 0;
        double curr = 0;

        while (i + j <= total / 2) {
            prev = curr;
            if (i >= m) {
                curr = B[j];
                j++;
            } else if (j >= n) {
                curr = A[i];
                i++;
            } else if (A[i] < B[j]) {
                curr = A[i];
                i++;
            } else {
                curr = B[j];
                j++;
            }
        }

        if (total % 2 == 0) {
            return (prev + curr) / 2.0;
        } else {
            return curr;
        }
    }

    // O(log(min(M, N)))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;

        if (N1 < N2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int lo = 0;
        int hi = N2 * 2;

        while (lo <= hi) {
            int mid2 = lo + (hi - lo) / 2;   // Try Cut 2 
            int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly
            
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];
            double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1) / 2];
            double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2) / 2];
            
            if (L1 > R2) {
                lo = mid2 + 1;   
            } else if (L2 > R1) {
                hi = mid2 - 1;
            } else {
                return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
            }
        }

        return -1;
    }

    // time, O(log(m + n))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        if (total % 2 == 0) {
            return (double) (findKth(nums1, 0, m - 1, nums2, 0, n - 1, total / 2) + findKth(nums1, 0, m - 1, nums2, 0, n - 1, total / 2 + 1)) / 2.0;
        } else {
            return findKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, total / 2 + 1);
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
        // note k == 1, a good stopping case
        if (k == 1) return Math.min(a[s1], b[s2]);  

        int midA = Math.min(k / 2, m);
        int midB = k - midA;
        // midA length is found out, so k - midA
        if (a[s1 + midA - 1] < b[s2 + midB - 1]) {
            return findKth(a, s1 + midA, e1, b, s2, e2, k - midA);
        } else if (a[s1 + midA - 1] > b[s2 + midB - 1]) { // midB length is found out, so k - midB
            return findKth(a, s1, e1, b, s2 + midB, e2, k - midB);
        } else {
            return a[s1 + midA - 1];
        }
    }
}