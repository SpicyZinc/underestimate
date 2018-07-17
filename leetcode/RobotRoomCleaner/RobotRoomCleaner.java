/*
idea:
To track the cells the robot has cleaned, start a index pair (i, j) from (0, 0).
When go up, i-1; go down, i+1; go left, j-1; go right: j+1.
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

https://leetcode.com/explore/interview/card/google/61/trees-and-graphs/1340/discuss/135734/java-recursive-solution-14ms
https://blog.csdn.net/u013167299/article/details/49278129
*/


/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class RobotRoomCleaner {

	public void cleanRoom(Robot robot) {
        Set<String> set = new HashSet<>();
        int curDir = 0;
        backtrack(robot, set, 0, 0, 0);
    }

    public void backtrack(Robot robot, Set<String> set, int x, int y, int curDir) {
    	String path = x + "->" + y;
    	
		if (set.contains(path)) {
            return;
        }
        // clean current cell (x, y)
    	robot.clean();
    	set.add(path);

        // the robot can to four directions, we use right turn
    	for (int dir = 0; dir < 4; dir++) {
			// can go directly. Find the (x, y) for the next cell based on current direction
    		if (robot.move()) {
    			int nextX = x;
    			int nextY = y;

    			switch(curDir) {
    				case 0:
    					// go up, reduce i
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

    			backtrack(robot, set, nextX, nextY, curDir);
				
				// go back to the starting position
				robot.turnLeft();
    			robot.turnLeft();
    			robot.move();
    			robot.turnRight();
    			robot.turnRight();
    		}
    		// cannot move to front cell, change direction clockwise
    		// turn to next direction
    		robot.turnRight();
    		curDir += 90;
    		curDir %= 360;
    	}
    }
}