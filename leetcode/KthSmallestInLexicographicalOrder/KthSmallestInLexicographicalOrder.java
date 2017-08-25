/*
Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
Note: 1 ≤ k ≤ n ≤ 109.

Example:
Input: n: 13   k: 2
Output:10

Explanation:
The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

idea:
https://discuss.leetcode.com/topic/64624/concise-easy-to-understand-java-5ms-solution-with-explaination/2

denary tree
Initially, image you are at node 1 (variable: curr),
the goal is move (k - 1) steps to the target node x 
*/
public class KthSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        int current = 1;
        k = k - 1;

        while (k > 0) {
        	int steps = calculateSteps(n, current, current + 1);
        	int stepsCanMove = (steps <= k) ? steps : 1;
        	if (steps <= k) {
        		current += 1;
        	}
        	else {
        		current *= 10;
        	}
        	k -= stepsCanMove;
        }
        return current;
    }

    private int calculateSteps(int n, long n1, long n2) {
    	int steps = 0;
    	while (n1 <= n) {
    		steps += Math.min(n + 1, n2) - n1;
    		n1 *= 10;
    		n2 *= 10;
    	}

    	return steps;
    }
}