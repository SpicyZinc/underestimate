/*
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
Find the kth positive integer that is missing from this array.

Example 1:
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

Example 2:
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 

Constraints:
1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length

idea:
similar to MissingElementInSortedArray
difference is this one starts from 1

1. hash set direct way
2. since increasing and consecutive
第二个missing
要把那些没有missing的算上
3. binary search, https://leetcode.com/problems/kth-missing-positive-number/discuss/779999/JavaC%2B%2BPython-O(logN)
*/

import java.util.*;

class KthMissingPositiveNumber {
    public static void main(String[] args) {
        KthMissingPositiveNumber eg = new KthMissingPositiveNumber();
        // int[] arr = {2,3,4,7,11};
        int[] arr = {1,2,3,4};
        // int k = 5;
        int k = 2;

        int result = eg.findKthPositive(arr, k);

        System.out.println(result);
    }

    // direct way, look around, and based arr[i + 1] - arr[i] - 1 between i + 1 and i
    public int findKthPositive(int[] arr, int k) {
        // 开始 arr[0] - 1 缺少的num 如果它都大于k 直接return
        int missingBeforeFirstNum = arr[0] - 1;
        if (missingBeforeFirstNum >= k) {
            return k;
        }
        k -= missingBeforeFirstNum;

        int n = arr.length - 1;
        for (int i = 0; i < n - 1; i++) {
            int currentMissing = arr[i + 1] - arr[i] - 1;
            // gap 太宽了 差的太多
            if (currentMissing >= k) {
                return arr[i] + k;
            }
            k -= currentMissing;
        }

        return arr[n - 1] + k;
    }
    // binary search
    public int findKthPositive(int[] arr, int k) {
        if (arr[0] - 1 >= k) {
            return k;
        }

        // 牢记 missing count == arr[idx] - nums[0] - (idx - 0);

        int offset = 1; // 0 position上的value 但是不一定是1 所以 hard code

        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int missingCount = arr[mid] - offset - (mid - 0);
            if (missingCount < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // The number of integers missing between 0 and right is arr[right] - arr[0] - (right - 0)
        return arr[right] + k - (arr[right] - offset - (right - 0));
    }

    public int findKthPositive(int[] arr, int k) {
        Set<Integer> hs = new HashSet<>();
        for (int val : arr) {
            hs.add(val);
        }

        int size = arr.length;

        int i = 0;
        int start = 1;

        while (true) {
            if (hs.contains(start)) {
                i++;
            } else {
                k--;
            }

            if (i == size) {
                break;
            }
            if (k == 0) {
                break;
            }

            start++;
        }

        return i == size ? arr[size - 1] + k : start;
    }

    // different angle idea, smart
    public int findKthPositive(int[] arr, int k) {
        for (int val : arr) {
            if (val <= k) {
                k++; 
            } else {
                break;
            }
        }

        return k;
    }
}