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
        SortColors eg = new SortColors();

        int[] a = {0,1,1,2,0,2,1,0,1,1,2};
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();

        eg.sortColors(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }

    // Mon Apr 10 14:07:55 2023
    public void sortColors(int[] nums) {
        int size = nums.length;
        int left = 0;
        int right = size - 1;

        int i = 0;
        // key point, i <= right   
        while (i <= right) {
            int color = nums[i];
            if (color == 0) {
                swap(nums, i++, left++);
            } else if (color == 2) {
                swap(nums, i, right--);
            } else {
                i++;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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
            } else if (color == 2) { // if belongs in the bottom, swap it with the element just above the bottom, no i++
                swap(nums, i, right);
                right--;
            } else { // if in the middle, leave it and no swap
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
