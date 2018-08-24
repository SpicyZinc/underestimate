/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:
Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4

Example 2:
Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6


idea:
two points form a line
each point can be one of the two, 
the other one is any one of the rest points

outside loop all points
for each point, treat as starting point, get the max number of points which compose a line starting from the starting point

Use hashmap<slope, Integer(# of points)>
to record one slope how many points.
*/

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

public class MaxPointsOnALine {
	public int maxPoints(Point[] points) {
        if (points.length == 0 || points == null) {
    		return 0;
    	}
    	if (points.length == 1 || points.length == 2) {
    		return points.length;
    	}

		Map<Double, Integer> slopes = new HashMap<Double, Integer>();
    	int size = points.length;
    	double slope;    	
    	int maxCnt = 1;

    	for (int i = 0; i < size; i++) {
    		Point zero = points[i];
            // 把起始点算进去 但是原点不算
            int cnt = (zero.x != 0 || zero.y != 0) ? 1 : 0;
            slopes.clear();

    		for (int j = 0; j < size; j++) {
    			Point other = points[j];
                // 相同的点
    			if (i == j) {
    				continue;
    			}
                // 不同的点 有同样的坐标 肯定在一条线上
    			if (zero.x == other.x && zero.y == other.y) {
    				cnt++;
    			} else {
    				if (zero.x == other.x) {
    					slope = Double.MAX_VALUE;
    				} else {
						slope = getSlope(zero, other);
    				}

                    slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
                }
    		}

    		int currMax = 0; // key point
    		for (Integer count : slopes.values()) {
                currMax = Math.max(currMax, count);
    		}
    		currMax += cnt;
		maxCnt = Math.max(maxCnt, currMax);
    	}

        return maxCnt;
    }

    private double getSlope(Point A, Point B) {
        return (double) (B.y - A.y) / (double) (B.x - A.x);
    }
}
