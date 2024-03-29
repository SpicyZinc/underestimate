/*
Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.

Example:

Input: [1,2,3]
Output: 3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

idea:
do it in a reverse way,
a move is incrementing n - 1 elements by 1
is equal to decrease one element per time
*/
public class MinimumMovesToEqualArrayElements {
    public int minMoves(int[] nums) {
        if (nums.length == 0  || nums == null) {
        	return 0;
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
        	sum += num;
        	min = Math.min(min, num);
        }

        return sum - min * nums.length;
    }
}