/*
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements of [1, n] inclusive that do not appear in this array.
Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:
Input: [4,3,2,7,8,2,3,1]
Output: [5,6]

idea:
in place change value to negative value
[4,3,2,7,8,2,3,1] => [-4,-3,-2,-7,8,2,-3,-1]

那些没有被弄成负的数 位置 + 1 就是 missing numbers
为什么 因为有些数出现了两次 以他们-1为位置 找到这个位置上面的数置成负
最后出现正数的位置 就是 missing numbers
*/
import java.util.*;

public class FindAllNumbersDisappearedInAnArray {
	public static void main(String[] args) {
		FindAllNumbersDisappearedInAnArray eg = new FindAllNumbersDisappearedInAnArray();
		int[] nums = new int[] {4,3,2,7,8,2,3,1};
		eg.findDisappearedNumbers(nums);
	}
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();

        for (int num : nums) {
            int idx = Math.abs(num) - 1;
            if (nums[idx] > 0) {
                nums[idx] = -1 * nums[idx];
            }
        }
        // kind of going through 0 - (n - 1), 变相的 1 - n
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }

        return result;
    }
}