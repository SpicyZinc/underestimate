/*
Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

Example 2:
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].

Constraints:
1 <= nums.length <= 2 * 104
1 <= nums[i], k <= nums.length

idea:
This problem will be a very typical sliding window,
if it asks the number of subarrays with at most K distinct elements.

exactly(K) = atMost(K) - atMost(K-1)

https://leetcode.com/problems/subarrays-with-k-different-integers/solutions/523136/java-c-python-sliding-window/
*/

class SubarraysWithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K - 1);
    }

    int atMostK(int[] A, int K) {
        int i = 0, res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < A.length; ++j) {
            if (count.getOrDefault(A[j], 0) == 0) K--;
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);

            while (K < 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0) K++;
                i++;
            }
            res += j - i + 1;
        }

        return res;
    }

    // https://leetcode.com/problems/subarrays-with-k-different-integers/solutions/235235/c-java-with-picture-prefixed-sliding-window/
    public int subarraysWithKDistinct(int[] A, int K) {
        int res = 0, prefix = 0;
        int start = 0;
        int distinctCount = 0;
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int right = 0; right < A.length; right++) {
            int rightNum = A[right];

            if (!countMap.containsKey(rightNum) || countMap.get(rightNum) == 0) {
                distinctCount++;
            }

            countMap.put(rightNum, countMap.getOrDefault(rightNum, 0) + 1);

            if (distinctCount > K) {
                int startNum = A[start];
                start++;
                prefix = 0;
                countMap.put(startNum, countMap.get(startNum) - 1);
                distinctCount--;
            }

            while (countMap.get(A[start]) > 1) {
                int startNum = A[start];
                start++;
                countMap.put(startNum, countMap.get(startNum) - 1);
                prefix++;
            }

            if (distinctCount == K) {
                res += prefix + 1;
            }
        }

        return res;
    }
}

