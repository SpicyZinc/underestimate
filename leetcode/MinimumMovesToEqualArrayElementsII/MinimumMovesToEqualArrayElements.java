/*
Given a non-empty integer array, find the minimum number of moves required to make all array elements equal,
where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:

Input: [1,2,3]
Output: 2
Explanation: Only two moves are needed (remember each move increments or decrements one element):
[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

idea:
sort, and find from two ends add up the difference
*/
public class MinimumMovesToEqualArrayElements {
    public int minMoves2(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        Arrays.sort(nums);
        int moves = 0;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            moves += (nums[j] - nums[i]);
            i++;
            j--;
        }

        return moves;
    }
}