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
1. hash set direct way
2. KthMissingPositiveNumber, since increasing and consecutive
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

    public int findKthPositive(int[] arr, int k) {
        Set<Integer> hs = new HashSet<>();
        for (int val : arr) {
            hs.add(val);
        }

        int size = arr.length;

        int start = 1;
        int i = 0;

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