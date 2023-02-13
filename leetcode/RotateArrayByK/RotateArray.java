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
    public static void main(String[] args) {
        RotateArray eg = new RotateArray();
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        eg.rotate(nums, k);
        for (int val : nums) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // 1. open new array
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (k > n) {
            k = k % n;
        }
        int[] newNums = new int[n];
        int i = 0;
        for (i = 0; i < k; i++) {
            newNums[i] = nums[n - k + i];
        }
        int j = 0;
        for (i = k; i < n; i++) {
            newNums[i] = nums[j++];
        }

        System.arraycopy(newNums, 0, nums, 0, nums.length);
    }

    // 2. bubble rotate, rotate by one, then call k times
    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = nums.length - 1; j > 0; j--) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
            }
        }
    }

    // 3. rotate trick
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
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
    // 4. using left rotate
    // Wed Jun  2 22:56:40 2021
    public void rotate(int[] nums, int k) {
        leftRotate(nums, nums.length - k);
    }

    public void leftRotate(int[] nums, int d) {
        for (int i = 0; i < d; i++) {
            moveToLeftByOne(nums);
        }
    }

    public void moveToLeftByOne(int[] a) {
        int temp = a[0];
        int i;
        for (i = 0; i < a.length - 1; i++) {
            a[i] = a[i + 1];
        }
        a[i] = temp;
    }
}
