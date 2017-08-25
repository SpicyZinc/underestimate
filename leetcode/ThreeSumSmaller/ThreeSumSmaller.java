/*
Given an array of n integers nums and a target,
find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:
[-2, 0, 1]
[-2, 0, 3]

Follow up: Could you solve it in O(n^2) runtime?

idea:
https://segmentfault.com/a/1190000003794736

similar to Valid Triangle Number

sort first
index left = 3, right = 5
3 4
3 5
2 combinations == 5 - 3 = 2
*/

import java.util.*;

public class ThreeSumSmaller {
	public static void main(String[] args) {
		ThreeSumSmaller eg = new ThreeSumSmaller();
		int[] nums = {-2, 0, 1, 3};
		int target = 2;
		System.out.println(eg.threeSumSmaller(nums, target));
	}
    // recently written
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum >= target) {
                    k--;
                } else {
                    cnt += k - j;
                    j++;
                }
            }
        }

        return cnt;
    }
}