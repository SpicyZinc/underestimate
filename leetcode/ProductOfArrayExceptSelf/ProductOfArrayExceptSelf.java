/*
Given an array of n integers where n > 1, nums, 
return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
For example, given [1,2,3,4], return [24,12,8,6].

Solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity?
(Note: The output array does not count as extra space for the purpose of space complexity analysis.)

idea:
from 2nd position of result array, save product of all elements before current element
then scan the array again, do the same thing, two result multiply together, then get the result
*/

public class ProductOfArrayExceptSelf {
    // 2025
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] leftProduct = new int[n];
        int[] rightProduct = new int[n];

        int[] result = new int[n];

        leftProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProduct[i] = leftProduct[i - 1] * nums[i - 1];
        }

        rightProduct[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = leftProduct[i] * rightProduct[i];
        }

        return result;
    }
    // optimal solution with constant space
    public int[] productExceptSelf(int[] nums) {
        int size = nums.length;
        int[] product = new int[size];

        product[0] = 1;
        for (int i = 1; i < size; i++) {
            product[i] = product[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int j = size - 1; j >= 0; j--) {
            product[j] *= right;
            right *= nums[j]; 
        }

        return product;
    }

    // self written version wasting space
    // but easy to understand
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] leftProduct = new int[n];
        int[] rightProduct = new int[n];
        
        leftProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProduct[i] = nums[i - 1] * leftProduct[i - 1];
        }
        
        rightProduct[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightProduct[i] = nums[i + 1] * rightProduct[i + 1];
        }
        
        int[] product = new int[n];
        for (int i = 0; i < n; i++) {
            product[i] = leftProduct[i] * rightProduct[i];
        }
        
        return product;
    }
}