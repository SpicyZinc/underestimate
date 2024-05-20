/*
Given an array A of non-negative integers, return an array consisting of all the even elements of A,
followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

Example 1:
Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 

Note:
1 <= A.length <= 5000
0 <= A[i] <= 5000

idea:
two pointers, from head and tail of the array
find a mismatch which is odd before even
swap them
then advance i, and backward j
*/

class SortArrayByParity {
    // Sat Mar 18 14:22:26 2023
    public int[] sortArrayByParity(int[] nums) {
        int size = nums.length;

        int left = 0;
        int right = size - 1;

        while (left < right) {
            if (nums[left] % 2 == 1 && nums[right] % 2 == 0) {
                swap(nums, left++, right--);
            } else if (nums[left] % 2 == 0) {
                left++;
            } else {
                right--;
            }                    
        }

        return nums;
    }

    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = A.length - 1;

        while (i < j) {
            if (A[i] % 2 > A[j] % 2) {
                swap(A, i, j);
            }

            if (A[i] % 2 == 0) {
                i++;
            }
            if (A[j] % 2 == 1) {
                j--;
            }
        }

        return A;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}