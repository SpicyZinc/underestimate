/*
Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 
Return 0 if there is no mountain.

Example 1:

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.

Example 2:
Input: [2,2,2]
Output: 0
Explanation: There is no mountain.

Note:
0 <= A.length <= 10000
0 <= A[i] <= 10000

Follow up:
Can you solve it using only one pass?
Can you solve it in O(1) space?

idea:
left and right respectively find the longest decreasing array cnt
both left > 0 and right > 0
length = left + right

http://zxi.mytechroad.com/blog/dynamic-programming/leetcode-845-longest-mountain-in-array/
*/
class LongestMountainInArray {
    // this is what I looked for, 7ms
    public int longestMountain(int[] A) {
        int n = A.length;
        int[] inc = new int[n];
        int[] dec = new int[n];
        // consecutive increasing subarray length so far to i
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                inc[i] = inc[i - 1] + 1;
            }
        }

        for (int i = n - 2; i > 0; i--) {
            if (A[i] > A[i + 1]) {
                dec[i] = dec[i + 1] + 1;
            }
        }

        int max = 0;
        for (int i = 0; i < n; ++i) {
            if (inc[i] > 0 && dec[i] > 0) {
                max = Math.max(max, inc[i] + dec[i] + 1);
            }
        }

        return max >= 3 ? max : 0;
    }

    // self 86ms
    public int longestMountain(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int length = 0;
        for (int i = 0; i < A.length; i++) {
            int left = decresingLeftCnt(A, i);
            int right = decresingRightCnt(A, i);
            // only both sides cnt > 0
            if (left > 0 && right > 0) {
                length = Math.max(length,  left + right);    
            }
        }

        return length == 0 ? 0 : length + 1;
    }

    public int decresingLeftCnt(int[] A, int i) {
        // left
        int cnt = 0;
        if (i <= 0) {
            return cnt;
        }
        
        while (i > 0 && A[i] > A[i - 1]) {
            cnt++;
            i--;
        }

        return cnt;
    }

    public int decresingRightCnt(int[] A, int i) {
        // right
        int cnt = 0;
        if (i >= A.length - 1) {
            return cnt;
        }
        
        while (i < A.length - 1 && A[i] > A[i + 1]) {
            cnt++;
            i++;
        }

        return cnt;
    }
}