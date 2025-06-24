/*
Given a sorted array arr[] of size N, the task is to find the number of unique elements in this array. 

Note: The array is very large, and unique numbers are significantly less.
i.e., (unique elements <<size of the array).

Example 1:
Input: arr[] = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 5, 5, 7, 7, 8, 8, 9, 9, 10, 11, 12}
Output: 10

idea:
made Dongyan Su
5/29/ Meta coding question 2nd
binary search
https://www.geeksforgeeks.org/count-of-unique-elements-in-a-very-large-sorted-array/

*/

class CountOfUniqueElementsInSortedArray {
    int cnt = 0;
    public static void main(String[] args) {
        CountOfUniqueElementsInSortedArray eg = new CountOfUniqueElementsInSortedArray();
        // int[] nums = new int[] {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 5, 5, 7, 7, 8, 8, 9, 9, 10, 11, 12};
        int[] nums = new int[] {1, 1, 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 5, 5, 5, 9, 9, 9, 10, 10, 10};
        int count = eg.getUniqueCount(nums);

        System.out.println("unique count = " + count);

    }

    public int getUniqueCount(int[] nums) {
        int target = nums[0];

        int count = 0;
        int n = nums.length;
        int i = -1;
        while (i < n - 1) {
            i = searchForRightMost(nums, i + 1, nums[i + 1]);
            count++;
        }

        return count;
    }

    public int searchForRightMost(int[] nums, int left, int target) {
        int n = nums.length;
        int right = n - 1;

        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public int getUniqueCount2(int[] nums) {
        UniqueElements(nums, 0, nums.length - 1, false);
        System.out.println("unique count = " + cnt);
        return cnt;
    }
    // recursion with binary search
    void UniqueElements(int arr[], int s, int e, boolean isDuplicate) {
        // Both start and end are same
        if (arr[s] == arr[e]) {
            // If the element is duplicate
            if (!isDuplicate) {
                cnt++;
            }
        } else {
            int mid = s + (e - s) / 2;
            UniqueElements(arr, s, mid, isDuplicate);
            UniqueElements(arr, mid + 1, e, arr[mid] == arr[mid + 1]);
        }
    }
}
