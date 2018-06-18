/*
Given two sorted integer arrays A and B, 
merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. 
The number of elements initialized in A and B are m and n respectively.

idea:
from the back of array to do the merge
位置个数正好 剩下的在A的不动就可以了
*/

public class MergeSortedArray {
    public void merge(int A[], int m, int B[], int n) {
        while (m > 0 && n > 0) {
            if (A[m-1] > B[n-1]) {
                A[m+n-1] = A[m-1];
                m--;
            }
            else {
                A[m+n-1] = B[n-1];
                n--;
            }
        }
        while (n > 0) {
            A[m+n-1] = B[n-1];
            n--;
        }
    }

    // self written, one time passed
    public void merge(int A[], int m, int B[], int n) {
        while (m >= 1 && n >= 1) {
            if (A[m-1] > B[n-1]) {
                A[m-1 + n-1 + 1] = A[m-1];
                m--;
            }
            else {
                A[m-1 + n-1 + 1] = B[n-1];
                n--;
            }
        }
        
        while (n >= 1) {
            A[m-1 + n-1 + 1] = B[n-1];
            n--;
        }
    }
}