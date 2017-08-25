/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.

Example:
Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:
[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

idea:
1. DFS
2. BFS
populate two hashsets,
visited points from Pacific 
visited points from Atlantic

use queue, build queue from the hashset
any points in the hashset water can flow to the side (Pacific or Atlantic)
so the intersection of should be points from which water can flow to both sides
*/

public class PacificAtlanticWaterFlow {
	public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<int[]>();

        if (matrix.length == 0 || matrix == null) {
        	return result;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        HashSet<String> visited_from_pacific = new HashSet<String>();
        HashSet<String> visited_from_atlantic = new HashSet<String>();
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (i == 0 || j == 0) {
        			String p = Point(i, j);
        			visited_from_pacific.add(p);
        		}
        		if (i == m-1 || j == n-1) {
        			String p = Point(i, j);
        			visited_from_atlantic.add(p);
        		}
        	}
        }
        bfs(visited_from_pacific, matrix);
        bfs(visited_from_atlantic, matrix);
        // find intersection of two sets
		for (String p : visited_from_pacific) {
        	if (visited_from_atlantic.contains(p)) {
        		result.add(getArray(p));
        	}
        }

        return result;
	}

	public void bfs(HashSet<String> visited, int[][] matrix) {
		int m = matrix.length;
        int n = matrix[0].length;
		int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
		Queue<String> queue = new LinkedList<String>();
		for (String s : visited) {
		    queue.offer(s);
		}

		while (!queue.isEmpty()) {
			String current = queue.poll();
			int[] point = getArray(current);

			for (int[] direction : directions) {
				int nx = point[0] + direction[0];
				int ny = point[1] + direction[1];

				if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
					if (matrix[nx][ny] >= matrix[point[0]][point[1]]) {
						String newPoint = Point(nx, ny);
						if (!visited.contains(newPoint)) {
							queue.offer(newPoint);
							visited.add(newPoint);
						}
					}
				}
			}
		}
	}

	private String Point(int x, int y) {
    	return x + "," + y;
    }

    private int[] getArray(String p) {
        String[] s = p.split("\\,");
        return new int[] {Integer.valueOf(s[0]), Integer.valueOf(s[1])};
    }
}

// not sure why this dfs fail to pass OJ
public class PacificAtlanticWaterFlow {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<int[]>();
        if (matrix.length == 0 || matrix == null) {
        	return result;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        HashSet<String> visited_from_pacific = new HashSet<String>();
        HashSet<String> visited_from_atlantic = new HashSet<String>();

        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (i == 0 || j == 0) {
        			String p = Point(i, j);
        			visited_from_pacific.add(p);
        			dfs(p, visited_from_pacific, matrix);
        		}
        		if (i == m-1 || j == n-1) {
        			String p = Point(i, j);
        			visited_from_atlantic.add(p);
        			dfs(p, visited_from_atlantic, matrix);
        		}
        	}
        }

        for (String p : visited_from_pacific) {
        	if (visited_from_atlantic.contains(p)) {
        		result.add(getArray(p));
        	}
        }

        return result;
    }
    // populate the visited hashset
    private void dfs(String start, HashSet<String> visited, int[][] matrix) {
    	int m = matrix.length;
    	int n = matrix[0].length;

    	int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
    	for (int[] direction : directions) {
    		int dx = direction[0];
    		int dy = direction[1];
            
            int[] curStart = getArray(start);
    		String newPoint = Point(curStart[0] + dx, curStart[1] + dy);
    		int nextX = curStart[0] + dx;
    		int nextY = curStart[1] + dy;

    		if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
    			if (curStart[0] <= nextX && curStart[1] <= nextY) {
    				if (!visited.contains(newPoint)) {
    					visited.add(newPoint);
    					dfs(newPoint, visited, matrix);
    				}
    			}
    		}
    	}
    }


    private String Point(int x, int y) {
    	return x + "," + y;
    }

    private int[] getArray(String p) {
        String[] s = p.split("\\,");
        return new int[] {Integer.valueOf(s[0]), Integer.valueOf(s[1])};
    }
}
