/*
There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width.
You want to draw a vertical line from the top to the bottom and cross the least bricks.
The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed.
You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

Example:
Input: 
[[1,2,2,1],
 [3,1,2],
 [1,3,2],
 [2,4],
 [3,1,2],
 [1,3,1,1]]
Output: 2
Explanation: 
https://leetcode.com/static/images/problemset/brick_wall.png

Note:
The width sum of bricks in different rows are the same and won't exceed INT_MAX.
The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000].
Total number of bricks of the wall won't exceed 20,000.

idea:
note this case
each row, accumulative sum
find the max different sum's appearance frequency
which will cause the lease crossed number of bricks

找出出现最多的 accumulated sum
按照它们 draw line, 就会cut最小 number of bricks
note, 不要加 最后 一块 那样 就是 each row 总和 无所谓 cut了
*/

public class BrickWall {
	public int leastBricks(List<List<Integer>> wall) {
		Map<Integer, Integer> map = new HashMap<>();
		for (List <Integer> row : wall) {
			int sum = 0;
			for (int i = 0; i < row.size() - 1; i++) {
				sum += row.get(i);
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
		}

		int max = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			max = Math.max(max, entry.getValue());
		}

		return wall.size() - max;
	}
}