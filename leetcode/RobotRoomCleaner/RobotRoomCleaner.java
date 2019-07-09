/*
Given a robot cleaner in a room modeled as a grid.
Each cell in the grid can be empty or blocked.
The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

Example:
Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.

Notes:
The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.

idea:
visited hashset to track the cells the robot has cleaned, start from position (i, j) 这个坐标是 相对于 左上角
go up, i-1;
go down, i+1;
go left, j-1;
go right, j+1.

Also use DIR to record the current direction of the robot
			
				0
				^
				|
				|
				|
				|
				|
270-------------------------------->90
				|
				|
				|
				|
				|
				|
				180

其他很像 经典 dfs()

还需要一个visited set to record visited cells
if visited, means cleaned, no need to clean again

https://leetcode.com/explore/interview/card/google/61/trees-and-graphs/1340/discuss/135734/java-recursive-solution-14ms
https://blog.csdn.net/u013167299/article/details/49278129
*/


// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {
	// Returns true if the cell in front is open and robot moves into the cell.
	// Returns false if the cell in front is blocked and robot stays in the current cell.
	public boolean move();

	// Robot will stay in the same cell after calling turnLeft/turnRight.
	// Each turn will be 90 degrees.
	public void turnLeft();
	public void turnRight();

	// Clean the current cell.
	public void clean();
}

class RobotRoomCleaner {

	public void cleanRoom(Robot robot) {
		Set<String> visited = new HashSet<>();
		int currentDir = 0; // 0, 90, 180, 270

		dfs(robot, visited, 0, 0, currentDir);
	}

	public void dfs(Robot robot, Set<String> visited, int x, int y, int currentDir) {
		String path = x + "->" + y;
		
		if (visited.contains(path)) {
			return;
		}
		// clean current cell (x, y)
		robot.clean();
		visited.add(path);

		// the robot can to four directions, we use right turn
		for (int dir = 0; dir < 4; dir++) {
			// can go directly. Find the (x, y) for the next cell based on current direction
			if (robot.move()) {
				// initialize nextX and nextY
				int nextX = x;
				int nextY = y;

				// update nextX and nextY
				switch(currentDir) {
					case 0:
						// go up
						nextY = y + 1;
						break;
					case 90:
						// go right
						nextX = x + 1;
						break;
					case 180:
						// go down
						nextY = y - 1;
						break;
					case 270:
						// go left
						nextX = x - 1;
						break;
					default:
						break;
				}

				dfs(robot, visited, nextX, nextY, currentDir);

				// 典型的 path.remove(paht.size() - 1);
				// go back to the starting position
				robot.turnLeft();
				robot.turnLeft();
				robot.move();
				robot.turnRight();
				robot.turnRight();
			}
			// 不能进入面前的 cell
			// cannot move to front cell, change direction clockwise
			// turn to next direction
			robot.turnRight();
			currentDir = (currentDir + 90) % 360;
		}
	}
}