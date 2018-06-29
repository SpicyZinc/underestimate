/*
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].

Note:
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100

idea:
dp[i][j] represents the length of common subarray (substring) in first i numbers in A and first j numbers in B 
https://www.cnblogs.com/grandyang/p/7801533.html
*/

import java.util.*;

class MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {
        MaximumLengthOfRepeatedSubarray eg = new MaximumLengthOfRepeatedSubarray();
        int[] A = {70,39,25,40,7};
        int[] B = {52,20,67,5,31};
        int len = eg.findLength(A, B);
        System.out.println(len);
    }

    public int findLength(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m + 1][n + 1];

        int maxLen = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                maxLen = Math.max(maxLen, dp[i][j]); 
            }
        }

        return maxLen;
    }

    // fail to pass
    public int findLength(int[] A, int[] B) {
        int max = 0;
        String joined = "";
        for (int i = 0; i < B.length; i++) {
            String temp = (i == B.length - 1) ? "" : ",";
            joined += B[i] + temp;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j <= A.length; j++) {
                String subStr = getString(A, i, j);
                int index = joined.indexOf(subStr);
                int end = index + subStr.length();
                if (index != -1) {
                    if (index > 0 && joined.charAt(index - 1) == ',' || index == 0 && end < joined.length() && joined.charAt(end) == ',') {
                        max = Math.max(max, subStr.split(",").length);
                    }
                }
            }
        }
        
        return max;
    }
    
    private String getString(int[] nums, int a, int b) {
        String[] s = new String[b - a];
        for (int i = a; i < b; i++) {
            s[i - a] = "" + nums[i];
        }
        return String.join(",", s);
    }
}