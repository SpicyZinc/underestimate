/*
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

idea:
use hashmap
to save A, B all pairs sum and the number of the same sum
then loop through C and D all pairs to equal to -sum, since A, B, C, and D are the same length
*/

public class FourSum {
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int cnt = 0;
        for (int i = 0; i < A.length; i++) {
        	for (int j = 0; j < B.length; j++) {
        		int sum = A[i] + B[j];
        		hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        	}
        }

        for (int k = 0; k < C.length; k++) {
        	for (int l = 0; l < D.length; l++) {
        		int sum = C[k] + D[l];
        		cnt += hm.getOrDefault(-1 * sum, 0);
        	}
        }

        return cnt;
    }
}