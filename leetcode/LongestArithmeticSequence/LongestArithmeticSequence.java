/*
Given an array A of integers, return the length of the longest arithmetic subsequence in A.
Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1,
and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

Example 1:
Input: [3,6,9,12]
Output: 4
Explanation: 
The whole array is an arithmetic sequence with steps of length = 3.

Example 2:
Input: [9,4,7,2,10]
Output: 3
Explanation: 
The longest arithmetic subsequence is [4,7,10].

Example 3:
Input: [20,1,15,3,10,5,8]
Output: 4
Explanation: 
The longest arithmetic subsequence is [20,15,10,5].
 
Note:
2 <= A.length <= 2000
0 <= A[i] <= 10000

idea:

array of hashmap
理解的关键
dp[i] ends at A[i] {1: 2, 2: 3, ..., 6: 1}
等差为1的数列长为 2
等差为2的数列长为 3
...
等差为6的数列长为 1



Map of Map
key => diff
value => map (key => till index, value => length of arithmetic sequence)

very similar to ArithmeticSlicesSubsequence
*/

class LongestArithmeticSequence {
	public int longestArithSeqLength(int[] A) {
		if (A.length <= 1) {
			return A.length;
		}

		int n = A.length;
		int maxLen = 0;
		
		// Declare a dp array that is an array of hashmaps.
		// The map for each index maintains an element of the form-
		// (difference, length of max chain ending at that index with that difference).        
		HashMap<Integer, Integer>[] dp = new HashMap[n];
		
		for (int i = 0; i < n; i++) {
			// Initialize the map.
			dp[i] = new HashMap<Integer, Integer>();
		}
		
		for (int i = 1; i < n; i++) {
			int x = A[i];

			// Iterate over values to the left of i.
			for (int j = 0; j < i; j++) {
				int y = A[j];

				int d = x - y;
				
				// We at least have a minimum chain length of 2 now,
				// given that (A[j], A[i]) with the difference d, 
				// by default forms a chain of length 2.
				int len = 2;  
				
				if (dp[j].containsKey(d)) {
					// At index j, if we had already seen a difference d,
					// then potentially, we can add A[i] to the same chain and extend it by length 1.
					// by +1 refers to A[i]
					len = dp[j].get(d) + 1;
				}
				
				// Obtain the maximum chain length already seen so far at index i for the given difference d
				int prevLen = dp[i].getOrDefault(d, 0);
				
				// Update the max chain length for difference d at index i.
				dp[i].put(d, Math.max(prevLen, len));
				
				// Update the global max.
				maxLen = Math.max(maxLen, dp[i].get(d));
			}
		}
		
		return maxLen;
	}

	// TLE
	public int longestArithSeqLength(int[] A) {
		Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
		int n = A.length;
		int maxLen = 2;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int diff = A[j] - A[i];

				Map<Integer, Integer> hm = dp.computeIfAbsent(diff, d -> new HashMap<>());
				hm.put(j, hm.getOrDefault(i, 1) + 1);

				maxLen = Math.max(maxLen, hm.get(j));
			}
		}

		return maxLen;
	}
}