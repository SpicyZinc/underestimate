/*
Given an integer array nums and two integers firstLen and secondLen, return the maximum sum of elements in two non-overlapping subarrays with lengths firstLen and secondLen.
The array with length firstLen could occur before or after the array with length secondLen, but they have to be non-overlapping.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
Output: 20
Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.

Example 2:
Input: nums = [3,8,1,3,2,1,8,9,0], firstLen = 3, secondLen = 2
Output: 29
Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.

Example 3:
Input: nums = [2,1,5,6,0,9,5,0,3,8], firstLen = 4, secondLen = 3
Output: 31
Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [0,3,8] with length 3.

Constraints:
1 <= firstLen, secondLen <= 1000
2 <= firstLen + secondLen <= 1000
firstLen + secondLen <= nums.length <= 1000
0 <= nums[i] <= 1000

idea:
prefix sum => firstLen range sum
suffix sum => secondLen range sum

https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/solutions/616171/readable-java-solution-with-o-n-time-and-o-n-space/

https://www.lintcode.com/problem/1850/solution/18294
*/

class MaximumSumOfTwoNonOverlappingSubarrays {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        return Math.max(calculate(nums, firstLen, secondLen), calculate(nums, secondLen, firstLen));
    }

    public int calculate(int[] a, int m, int n) {
        int size = a.length;

        int[] prefixSum = new int[size];
        prefixSum[0] = a[0];
        for (int i = 1; i < size; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }

        int[] suffixSum = new int[size];
        suffixSum[size - 1] = a[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + a[i];
        }
        // DP
        // till i, subarray of m 最大sum
        int[] leftRangeSum = new int[size];
        leftRangeSum[m - 1] = prefixSum[m - 1];
        for (int i = m; i < size; i++) {
            // 可能是 前一个 可也能是 要更新
            leftRangeSum[i] = Math.max(leftRangeSum[i - 1], prefixSum[i] - prefixSum[i - m]);
        }

        int[] rightRangeSum = new int[size];
        rightRangeSum[size - n] = suffixSum[size - n];
        for (int i = size - n - 1; i >= 0; i--) {
            rightRangeSum[i] = Math.max(rightRangeSum[i + 1], suffixSum[i] - suffixSum[i + n]);
        }

        // m and n length of sub-array, non-overlapping, max sum
        int max = Integer.MIN_VALUE;
        for (int i = m - 1; i <= size - n - 1; i++) {
            // non-overlapping, i and i + 1
            max = Math.max(max, leftRangeSum[i] + rightRangeSum[i + 1]);
        }

        return max;
    }
}


public class Solution {
    /**
     * @param A: a list of integer
     * @param K: a integer
     * @param L: a integer
     * @return: return the maximum number of apples that they can collect.
     */
    public int PickApples(int[] A, int K, int L) {
        int n = A.length;
        if (n < K + L) {
            return - 1;
        }
        int[] prefixSum = new int[n];
        //计算前缀和
        prefixSum[0] = A[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = A[i] + prefixSum[i - 1];
        }
        
        // prefixK 代表前 i 个数中，长度为 K 的子区间和的最大值
        int[] prefixK = new int[n];
        int[] prefixL = new int[n];
        
        // postfixK 代表后面 [i, n - 1] 中，长度为 K 的子区间和的最大值
        int[] postfixK = new int[n];
        int[] postfixL = new int[n];
        
        // 计算前缀值
        for (int i = 0; i < n; i++) {
            if (i == K - 1) {
                prefixK[i] = rangeSum(prefixSum, i - K + 1, i);
            }
            else if (i > K - 1) {
                prefixK[i] = Math.max(rangeSum(prefixSum, i - K + 1, i), prefixK[i - 1]);
            }
            if (i == L - 1) {
                prefixL[i] = rangeSum(prefixSum, i - L + 1, i);
            }
            else if (i > L - 1) {
                prefixL[i] = Math.max(rangeSum(prefixSum, i - L + 1, i), prefixL[i - 1]);
            }
        }
        
        // 计算后缀值
        for (int i = n - 1; i >= 0; i--) {
            if (i + K - 1 == n - 1) {
                postfixK[i] = rangeSum(prefixSum, i, i + K - 1);
            }
            else if (i + K - 1 < n - 1) {
                postfixK[i] = Math.max(rangeSum(prefixSum, i, i + K - 1), postfixK[i + 1]);
            }
            if (i + L - 1 == n - 1) {
                postfixL[i] = rangeSum(prefixSum, i, i + L - 1);
            }
            else if (i + L - 1 < n - 1) {
                postfixL[i] = Math.max(rangeSum(prefixSum, i, i + L - 1), postfixL[i + 1]);
            }
        }
        
        int result = 0;
        // 枚举分界点，计算答案
        for (int i = 0; i < n - 1; i++) {
            result = Math.max(result, prefixK[i] + postfixL[i + 1]);
            result = Math.max(result, prefixL[i] + postfixK[i + 1]);
        }
        return result;
    }
    private int rangeSum(int[] prefixSum, int l, int r) {
        if (l == 0) {
            return prefixSum[r];
        }
        return prefixSum[r] - prefixSum[l - 1];
    }
}
