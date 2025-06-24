/*
The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.

For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
Given an array nums, return the sum of all XOR totals for every subset of nums. 

Note: Subsets with the same elements should be counted multiple times.
An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.

Example 1:
Input: nums = [1,3]
Output: 6
Explanation: The 4 subsets of [1,3] are:
- The empty subset has an XOR total of 0.
- [1] has an XOR total of 1.
- [3] has an XOR total of 3.
- [1,3] has an XOR total of 1 XOR 3 = 2.
0 + 1 + 3 + 2 = 6

Example 2:
Input: nums = [5,1,6]
Output: 28
Explanation: The 8 subsets of [5,1,6] are:
- The empty subset has an XOR total of 0.
- [5] has an XOR total of 5.
- [1] has an XOR total of 1.
- [6] has an XOR total of 6.
- [5,1] has an XOR total of 5 XOR 1 = 4.
- [5,6] has an XOR total of 5 XOR 6 = 3.
- [1,6] has an XOR total of 1 XOR 6 = 7.
- [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28

Example 3:
Input: nums = [3,4,5,6,7,8]
Output: 480
Explanation: The sum of all XOR totals for every subset is 480.

Constraints:
1 <= nums.length <= 12
1 <= nums[i] <= 20

idea:
find all subset, and calculate XOR
*/

import java.util.*;

class SumOfAllSubsetXORTotals {
    public static void main(String[] args) {
        SumOfAllSubsetXORTotals eg = new SumOfAllSubsetXORTotals();
        int[] nums = new int[] {5,1,6};
        List<List<Integer>> r = eg.generateSubsets(nums);
        for (List<Integer> s : r) {
            System.out.println(s);
        }
    }

    public int subsetXORSum(int[] nums) {
        return XORSum(nums, 0, 0);
    }

    private int XORSum(int[] nums, int index, int currentXOR) {
        // Return currentXOR when all elements in nums are already considered
        if (index == nums.length) return currentXOR;

        // Calculate sum of subset xor with current element
        int withElement = XORSum(nums, index + 1, currentXOR ^ nums[index]);
        // Calculate sum of subset xor without current element
        int withoutElement = XORSum(nums, index + 1, currentXOR);

        // Return sum of xor totals
        return withElement + withoutElement;
    }

    // Tue May 21 17:26:21 2024
    // not fast
    public int subsetXORSum(int[] nums) {
        List<List<Integer>> result = generateSubsets(nums);
        int total = 0;
        for (List<Integer> set : result) {
            total += getXORTotal(set);
        }
        return total;
    }

    public int getXORTotal(List<Integer> nums) {
        int sum = 0;
        if (nums.size() == 0) {
            return sum;
        }
        for (int num : nums) {
            sum ^= num;
        }
        return sum;
    }

    public List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), result);
        return result;
    }

    public void dfs(int[] nums, int idx, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        int n = nums.length;
        for (int i = idx; i < n; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}
