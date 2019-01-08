/*
There's a tree, a squirrel, and several nuts.
Positions are represented by the cells in a 2D grid.
Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one.
The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell.
The distance is represented by the number of moves.

Example 1:
Input: 
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12
Explanation:
​​​​​https://assets.leetcode.com/uploads/2018/10/22/squirrel_simulation.png

Note:
All given positions won't overlap.
The squirrel can take at most one nut at one time.
The given positions of nuts have no order.
Height and width are positive integers. 3 <= height * width <= 10,000.
The given positions contain at least one nut, only one tree and one squirrel.

idea:
http://www.cnblogs.com/grandyang/p/6919923.html
关键是 一旦到了🌲 去任何其他的nut的距离和都是一个定值了
去了🌲 到其他点的距离应该是更小的
2X - X + y = 2X - (X - y)
*/

class SquirrelSimulation {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int totalDistance = 0;
        int maxDiff = Integer.MIN_VALUE;
        int idx = 0;
        
        int treeX = tree[0];
        int treeY = tree[1];
        
        int sX = squirrel[0];
        int sY = squirrel[1];

        for (int[] nut : nuts) {
            int nutX = nut[0];
            int nutY = nut[1];

            int distance = Math.abs(treeX - nutX) + Math.abs(treeY - nutY); // X
            totalDistance += 2 * distance;

            maxDiff = Math.max(maxDiff, distance - (Math.abs(sX - nutX) + Math.abs(sY - nutY))); // X - Y
        }
        
        return totalDistance - maxDiff;
    }
}