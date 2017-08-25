/*
Given n points in the plane that are all pairwise distinct,
a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input: [[0,0],[1,0],[2,0]]
Output: 2

Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

idea:
nested for loop
use hashmap to record a distance => # of point pair (one point, every other point)
based on permutation, n * n-1 to get result
*/

public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        if (points.length == 0 || points == null) {
        	return res;
        }
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < points.length; i++) {
        	for (int j = 0; j < points.length; j++) {
        		if (i == j) continue;
        		int distance = getDistance(points[i], points[j]);
        		Integer number = hm.get(distance);
        		if (number == null) {
        			hm.put(distance, 1);
        		}
        		else {
        			hm.put(distance, number + 1);
        		}
        	}
        	for (int number : hm.values()) {
        		res += number * (number - 1);
        	}
        	hm.clear();
        }

        return res;
    }

    private int getDistance(int[] a, int[] b) {
    	int dx = a[0] - b[0];
    	int dy = a[1] - b[1];

    	return dx * dx + dy * dy;
    }
}