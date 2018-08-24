/*
Given an array with n objects colored red, white or blue, 
sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue. 

idea:
Dutch national flag problem
it also could be 3 ranges: low, middle, high
A[i] < low 
A[i] >= high

similar to quicksort to partition elements
*/

public class SortColors {
	public static void main(String[] args) {
		int[] a = {0,1,1,2,0,2,1,0,1,1,2};
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
		SortColors aTest = new SortColors();
		aTest.sortColors(a);
		for (int i : a) {
			System.out.print(i + " ");
		}		
        System.out.print("\n");
	}
	// sort method, in-place, time complexity O(n)
    public void sortColors(int[] A) {
        int l = 0; // left pointer		
		int r = A.length - 1; // right pointer
		int i = 0;
        while (i <= r) {
			// if belongs to the top group, swap it with the element just below the top
            if (A[i] == 0) {
                swap(A, i, l);                
                i++;
				l++;
            } else if (A[i] == 2) { // if belongs in the bottom, swap it with the element just above the bottom
                swap(A, i, r);
                r--;
            } else { // if in the middle, leave it
                i++;
            }
        }
    }
 
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
