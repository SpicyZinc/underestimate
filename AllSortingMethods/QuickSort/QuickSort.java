/*
idea:
In-place version
http://www.nczonline.net/blog/2012/11/27/computer-science-in-javascript-quicksort/

The quicksort algorithm basically works by partitioning the entire array,
and then recursively partitioning the left and right parts of the array until the entire array is sorted.
The swap actually does the sort


The left and right parts of the array are determined by the index returns after each partition operation.
the last spot of the left pointer


Choose a pivot value.
    Pick the middle element as pivot value, but it can be any value,
    which is in range of sorted values, even if it doesn't present in the array.
Partition.
    Rearrange elements in such a way, that all elements which are lesser than the pivot go to the left part of the array
    and all elements greater than the pivot, go to the right part of the array.
    Values equal to the pivot can stay in any part of the array.
    Notice, that array may be divided in non-equal parts.
Sort both parts.
    Apply quicksort algorithm recursively to the left and the right parts.

There are two indices i and j at the very beginning of the partition algorithm
i points to the first element in the array and j points to the last one.
Then algorithm moves i forward, until an element with value greater or equal to the pivot is found.
Index j is moved backward, until an element with value lesser or equal to the pivot is found.
If i <= j then they are swapped and i steps to the next position (i + 1), j steps to the previous one (j - 1).
when i becomes greater than j, algorithm stops

After partition, all values before i-th element are less or equal than the pivot
and all values after j-th element are greater or equal to the pivot.
*/

public class QuickSort {
    static public void main(String[] args) {
        QuickSort eg = new QuickSort();
        int[] a = {1, 12, 5, 26, 7, 14, 3, 7, 2};
        eg.quicksort(a);
        System.out.println("The Sorted Array A is: ");
        for (int num : a) {
            System.out.printf("%d  ", num);
        }
        System.out.println();
    }

    public void quicksort(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
    }

    public void quicksort(int[] nums, int left, int right) {
        int index = partition(nums, left, right);
        // Strictly performed as the QuickSort Algorithm
        // first partition and then quickSort on two halves
        // note two "if"
        if (left < index - 1) {
            quicksort(nums, left, index - 1);
        }
        if (index < right) {
            quicksort(nums, index, right);
        }
    }
    // swap is the real thing to sort
    public int partition(int[] nums, int left, int right) {
        int pivot = nums[(left + right) / 2];
        int i = left;
        int j = right;

        while (i <= j) {
            while (nums[i] < pivot) {
                i++;
            }
            while (nums[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        return i;
    }

    public void swap(int[] nums, int i, int j) {
        int cache = nums[i];
        nums[i] = nums[j];
        nums[j] = cache;
    }
}