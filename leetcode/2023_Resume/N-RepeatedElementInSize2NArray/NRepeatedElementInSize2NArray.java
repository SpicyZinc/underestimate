/*
You are given an integer array nums with the following properties:

nums.length == 2 * n.
nums contains n + 1 unique elements.
Exactly one element of nums is repeated n times.
Return the element that is repeated n times.

Example 1:
Input: nums = [1,2,3,3]
Output: 3

Example 2:
Input: nums = [2,1,2,5,3,2]
Output: 2

Example 3:
Input: nums = [5,1,5,2,5,3,5,4]
Output: 5

Constraints:
2 <= n <= 5000
nums.length == 2 * n
0 <= nums[i] <= 10^4
nums contains n + 1 unique elements and one of them is repeated exactly n times.

idea:
出现过一次的元素 再出现 肯定是
*/

class NRepeatedElementInSize2NArray {
    public int repeatedNTimes(int[] A) {
        for (int i = 2; i < A.length; i++) {
            if (A[i] == A[i - 1] || A[i] == A[i - 2]) {
                return A[i];
            }
        }

        return A[0];
    }
    // Sat Mar 18 14:04:55 2023
    public int repeatedNTimes(int[] A) {
        int[] count = new int[10000];
        for (int a : A) {
            // Note, 先比 再++
            if (count[a]++ == 1) {
                return a;
            }
        }

        return -1;
    }

    public int repeatedNTimes(int[] A) {
        Arrays.sort(A);
        int n = A.length;
        
        int right = n / 2;
        int left = n / 2 - 1;
        
        if (A[left] != A[right]) {
            if (A[0] == A[left]) {
                return A[left];
            } else {
                return A[right];
            }
        } else {
            return A[left];
        }
    }
}