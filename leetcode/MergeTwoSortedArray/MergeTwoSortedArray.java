/*
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space to hold additional elements from B. 
The number of elements initialized in A and B are m and n respectively.

idea:
m is in fact how many elements in the long array
n is in fact how many elements in the short array

requires no extra space to merge these two sorted arrays
so start from m+n-1 at long array
go through the two array 
one must be empty after the while loop
then check which one is still have elements left, and copy them all to the result array


*/
public class MergeTwoSortedArray {
	public static void main(String[] args) {
		new MergeTwoSortedArray();
	}
	// constructor
	public MergeTwoSortedArray() {
		int[] longArray = {1, 3, 5, 7, 0, 0, 0, 0, 0};
		int[] shortArray = {2, 6, 9, 0};
		int longUsed = 4;
		int shortUsed = 3;
		System.out.println("Before merging the two sorted arrays: the two Array are ");
		for(int i: longArray)
			System.out.printf("%d ", i);
		System.out.println();
		for(int i: shortArray)
			System.out.printf("%d ", i);
			
		System.out.println();
		
		merge(longArray, longUsed, shortArray, shortUsed);
		System.out.println("After merging the two sorted arrays: the longArray is ");
		for(int i: longArray)
			System.out.printf("%d ", i);
	}
    public void merge(int A[], int m, int B[], int n) {
		// the number of elements initialized in A and B 
		// m and n
		// start from m+n-1, even though A is much longer than m+n, it is OK
		
		while (m >= 1 && n >= 1) {
			if (A[m-1] > B[n-1]) {
				A[(m-1)+(n-1)+1] = A[m-1];
				m--;
			}
			else {
				A[(m-1)+(n-1)+1] = B[n-1];
				n--;
			}
		}
		
		while (m >= 1) {
			A[(m-1)+(n-1)+1] = A[m-1];
			m--;
		}
		
		while (n >= 1) {
			A[(m-1)+(n-1)+1] = B[n-1];
			n--;
		}
    }
}