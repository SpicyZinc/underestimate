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
	// DFS
	public List<int[]> pacificAtlantic(int[][] matrix) {
		List<int[]> result = new ArrayList<>();
        
        if (matrix.length == 0 || matrix[0].length == 0 || matrix == null) {
            return result;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        boolean[][] canVisitPacific = new boolean[m][n];
        boolean[][] canVisitAtlantic = new boolean[m][n];

        for (int i = 0; i < m; i++) {
        	dfs(matrix, canVisitPacific, Integer.MIN_VALUE, i, 0);
        	dfs(matrix, canVisitAtlantic, Integer.MIN_VALUE, i, n - 1);
        }

        for (int i = 0; i < n; i++) {
        	dfs(matrix, canVisitPacific, Integer.MIN_VALUE, 0, i);
        	dfs(matrix, canVisitAtlantic, Integer.MIN_VALUE, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (canVisitPacific[i][j] && canVisitAtlantic[i][j]) {
        			result.add(new int[] {i, j});
        		}
        	}
        }

        return result;
    }

	public void dfs(int[][] matrix, boolean[][] visit, int prev, int x, int y) {
        int m = matrix.length;
        int n = matrix[0].length;

        if (x < 0 || x >= m || y < 0 || y >= n || visit[x][y] || prev > matrix[x][y]) {
        	return;
        }

        visit[x][y] = true;

        int[][] directions = new int[][] {
			{0,-1}, {-1,0}, {0,1}, {1,0}
        };

        for (int[] dir : directions) {
        	int newX = x + dir[0];
        	int newY = y + dir[1];
        	dfs(matrix, visit, matrix[x][y], newX, newY);
        }
	}
    // BFS
	public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<int[]>();

        if (matrix.length == 0 || matrix == null) {
        	return result;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        Set<String> visited_from_pacific = new HashSet<String>();
        Set<String> visited_from_atlantic = new HashSet<String>();
        
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

	public void bfs(Set<String> visited, int[][] matrix) {
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
