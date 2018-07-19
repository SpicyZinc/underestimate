/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements that appear twice in this array.
Could you do it without extra space and in O(n) runtime?

Example:
Input: [4,3,2,7,8,2,3,1]
Output: [2,3]

idea:
similar to FindAllNumbersDisappearedInAnArray
there has detailed explanation

different from Find the Duplicate Number
*/

import java.util.*;

public class FindAllDuplicatesInAnArray {
    public static void main(String[] args) {
        FindAllDuplicatesInAnArray eg = new FindAllDuplicatesInAnArray();
        int nums[] = {4,3,2,7,8,2,3,1};
        eg.findDuplicates(nums);

    }
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for (int num : nums) {
            int idx = Math.abs(num) - 1;
            if (nums[idx] > 0) {
                nums[idx] = -1 * nums[idx];
            } else {
                result.add(idx + 1);
            }
        }

        return result;
    }
}