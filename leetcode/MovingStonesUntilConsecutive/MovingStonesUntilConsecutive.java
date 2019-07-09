/*
Three stones are on a number line at positions a, b, and c.

Each turn, you pick up a stone at an endpoint (ie., either the lowest or highest position stone), and move it to an unoccupied position between those endpoints.
Formally, let's say the stones are currently at positions x, y, z with x < y < z.
You pick up the stone at either position x or position z, and move that stone to an integer position k, with x < k < z and k != y.
The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.

When the game ends, what is the minimum and maximum number of moves that you could have made?
Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]

Example 1:
Input: a = 1, b = 2, c = 5
Output: [1,2]
Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to 3.

Example 2:
Input: a = 4, b = 3, c = 2
Output: [0,0]
Explanation: We cannot make any moves.

Example 3:
Input: a = 3, b = 5, c = 1
Output: [1,2]
Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to 4.

Note:
1 <= a <= 100
1 <= b <= 100
1 <= c <= 100
a != b, b != c, c != a

idea:
https://buptwc.com/2019/05/02/Leetcode-1033-Moving-Stones-Until-Consecutive/

对于任意三个数, 我们总可以在两步之内将其变成连续的三个数, 如a,b,c
将a变成b-1, 将c变成b+1。
如果a,b,c已经有序, 那么最小移动次数是0次
如果a,b,c满足已经有两个数临近或间隔为2, 也就是 1,2,x 或 1,3,x的情况, 那么最小移动次数是1次
其余情况全为2次
*/

class Solution {
	public int[] numMovesStones(int a, int b, int c) {
		int[] positions = {a, b, c};
		Arrays.sort(positions);

		if (positions[2] - positions[0] == 2) {
			return new int[] {0, 0};
		}

		int[] moves = new int[2];
		moves[1] = positions[2] - positions[0] - 2;

		int diff = Math.min(positions[1] - positions[0], positions[2] - positions[1]);
		if (diff <= 2) {
			moves[0] = 1;
		} else {
			moves[0] = 2;
		}

		return moves;
	}
}

