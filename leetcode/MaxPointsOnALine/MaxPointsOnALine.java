/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

idea:
two points form a line
each point can be one of the two, 
the other one is any one of the rest points

outside loop all points
for each point, treat as starting point, get the max number of points which compose a line starting from the starting point

Use hashmap<slope,  Integer(# of points)>
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

		HashMap<Double, Integer> slopes = new HashMap<Double, Integer>();
    	int size = points.length;
    	double slope;    	
    	int tmp = 1;
    	int max = 1;
    	for (int i = 0; i < size; i++) {
    		tmp = 1; // count current zero (starting) point in 
    		slopes.clear();
    		Point zero = points[i];
    		for (int j = 0; j < size; j++) {
    			Point other = points[j];
    			if (i == j) {
    				continue;
    			}
    			if (zero.x == other.x && zero.y == other.y) {
    				tmp++;
    			}    			
    			else {
    				if (zero.x == other.x) {
    					slope = Double.MAX_VALUE;
    				}
    				else {
						slope = getSlope(other, zero);
    				}

    				if ( slopes.containsKey(slope) ) {
    					slopes.put(slope, slopes.get(slope) + 1);
    				}
    				else {
    					slopes.put(slope, 1);
    				}
    			}
    		}
    		int cnt = 0; // key point
    		for (Integer tempCnt : slopes.values()) {
    			if (cnt < tempCnt) {
    				cnt = tempCnt;
    			}
    		}
    		cnt = cnt + tmp;
            
    		if (cnt > max) {
    			max = cnt;
    		}
    	}

        return max;
    }

    private double getSlope(Point A, Point B) {
        return (double)(B.y - A.y) / (double)(B.x - A.x);
    }
}