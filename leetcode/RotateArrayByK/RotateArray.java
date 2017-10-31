/*
Rotate an array of n elements to the right by k steps.
For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, 
there are at least 3 different ways to solve this problem.

idea:
1. open a new intermediate array
find the partition point
copy the 2nd part to first part of the new array
copy the 1st part to second part of the new array

Time: O(n)
Space: O(n)

2. Bubble rotate, that is how rotate to the right by k times
Space: O(1)
Time: O(n * k)

3. Partition the array to two parts
reverse the first half
reverse the second half
reverse the whole array
*/
public class RotateArray {
    // 1. open new array
    public void rotate(int[] nums, int k) {
        if ( k > nums.length ) {
            k = k % nums.length;
        }
        int[] newNums = new int[nums.length];
        int i = 0;
        for ( i = 0; i < k; i++ ) {
            newNums[i] = nums[nums.length - k + i];
        }
        int j = 0;
        for ( i = k; i < nums.length; i++ ) {
            newNums[i] = nums[j];
            j++;
        }

        System.arraycopy(newNums, 0, nums, 0, nums.length);
    }

    // 2. bubble rotate, rotate by one, then call k times
    public void rotate(int[] nums, int k) {
        for ( int i = 0; i < k; i++ ) {
            for ( int j = nums.length - 1; j > 0; j-- ) {
                int temp = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = temp;
            }
        }
        for (int i = 0; i < order; i++) {
            for (int j = arr.length - 1; j > 0; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }

    // 3. rotate trick
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1 );
        reverse(nums, 0, k - 1 );
        reverse(nums, k, nums.length - 1 );
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}