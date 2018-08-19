/*
In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)
Return true if and only if after such a move,
it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.

Example :
Input: 
[1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.

Note:
The length of A will be in the range [1, 30].
A[i] will be in the range of [0, 10000].

idea:
https://leetcode.com/problems/split-array-with-same-average/discuss/120660/Java-accepted-recursive-solution-with-explanation


*/

class SplitArrayWithSameAverage {
	public boolean splitArraySameAverage(int[] A) {
        if (A.length == 1) {
        	return false;
        }

        int sum = 0;
        for (int a: A) {
        	sum += a;
        }

        Arrays.sort(A);
        int n = A.length;
        for (int lenOfB = 1; lenOfB <= n / 2; lenOfB++) {
            if ((sum * lenOfB) % n == 0) {
            	int target = (sum * lenOfB) / n;
                if (dfs(A, 0, target, lenOfB)) {
                	return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(int[] A, int pos, int remaining, int leftNum) {       
        if (leftNum == 0) {
        	return remaining == 0;
        }

        if ((A[pos]) > remaining / leftNum) {
        	return false;
        }

        for (int i = pos; i <= A.length - leftNum; i++) {
        	// duplicate, skip it
	    	if (i > pos && A[i] == A[i - 1]) {
	    		continue;
	    	}

            if (dfs(A, i + 1, remaining - A[i], leftNum - 1)) {
            	return true;
            }
        }

        return false;
    }
}
