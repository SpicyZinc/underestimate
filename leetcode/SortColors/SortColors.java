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
		SortColors eg = new SortColors();
		eg.sortColors(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
        System.out.print("\n");
	}

	// sort method, in-place, time complexity O(n)
	public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        int i = 0;
        
        while (i <= right) {
            int color = nums[i];
            // if belongs to the top group, swap it with the element just below the top
            if (color == 0) {
                swap(nums, i, left);
                i++;
                left++;
            } else if (color == 2) { // if belongs in the bottom, swap it with the element just above the bottom
                swap(nums, i, right);
                right--;
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
