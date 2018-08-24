/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.
Count the number of distinct islands.
An island is considered to be the same as another if they have the same shape,
or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Example 1:
11000
10000
00001
00011
Given the above grid map, return 1. 

Notice that:
11
1
and
 1
11
are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.

Example 2:
11100
10001
01001
01110
Given the above grid map, return 2.

Here are the two distinct islands:
111
1
and
1
1

Notice that:
111
1
and
1
111
are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.

Note: The length of each dimension in the given grid does not exceed 50.

idea:
dfs find, key is to normalize , how? -r0 -c0 in getStr()
by sort, return 最小的
一共8种形状
沿x
沿y
diagonal
anti-diagonal
*/

class NumberOfDistinctIslands {
	class Point {
		public int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
		int m = grid.length;
		int n = grid[0].length;

		Set<String> shapes = new HashSet<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				Set<Point> islandShape = new HashSet<>();
				dfs(grid, i, j, islandShape);
				if (!islandShape.isEmpty()) {
					shapes.add(normalize(islandShape));
				}
			}
		}

        return shapes.size();
    }

	public void dfs(int[][] grid, int i, int j, Set<Point> islandShape) {
		int m = grid.length;
		int n = grid[0].length;

		int[][] directions = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1},
		};

		if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
			grid[i][j] = -1;
			islandShape.add(new Point(i, j));

			for (int[] dir : directions) {
				int x = i + dir[0];
				int y = j + dir[1];
				dfs(grid, x, y, islandShape);
			}
        }
    }
    
    private String normalize(Set<Point> shape) {
		int[][] directions = {
			{1, 1},
			{1, -1},
			{-1, 1},
			{-1, -1}
		};

		List<List<Point>> transforms = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			List<Point> transform = new ArrayList<>();
			int[] dir = directions[i % 4];
			// shape composed of different points
			for (Point point : shape) {
                int x = point.x;
				int y = point.y;
				if (i < 4) {
					transform.add(new Point(x * dir[0], y * dir[1]));
				} else {
					transform.add(new Point(y * dir[0], x * dir[1]));
				}
			}

			transforms.add(transform);
		}
        
        List<String> sameIslands = new ArrayList<>();
        for (int i = 0; i < transforms.size(); i++) {
            List<Point> transform = transforms.get(i);
            Collections.sort(transform, (a, b) -> a.x != b.x ? Integer.compare(a.x, b.x) : Integer.compare(a.y, b.y));
            
            sameIslands.add(getStr(transform));
        }
                
        Collections.sort(sameIslands);

        return sameIslands.get(0);
    }
    
    private String getStr(List<Point> island) {    
        StringBuilder sb = new StringBuilder();
        int x = island.get(0).x, y = island.get(0).y;

        for (Point point : island) {
            sb.append((point.x - x) + " " + (point.y - y) + " ");
        }

        return sb.toString();
    }
    // 456/510 failed, when to -r0 and -c0
	public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
		int m = grid.length;
		int n = grid[0].length;

		Set<String> shapes = new HashSet<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				Set<String> islandShape = new HashSet<>();
				dfs(grid, i, j, i, j, islandShape);
				if (!islandShape.isEmpty()) {
					shapes.add(normalize(islandShape));
				}
			}
		}

        return shapes.size();
    }

	public void dfs(int[][] grid, int i, int j, int r0, int c0, Set<String> islandShape) {
		int m = grid.length;
		int n = grid[0].length;

		int[][] directions = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1},
		};

		if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
			grid[i][j] = -1;

			islandShape.add((i - r0) + "_" + (j - c0));

			for (int[] dir : directions) {
				int x = i + dir[0];
				int y = j + dir[1];
				dfs(grid, x, y, r0, c0, islandShape);
			}
        }
    }

	private String normalize(Set<String> shape) {
		int[][] directions = {
			{1, 1},
			{1, -1},
			{-1, 1},
			{-1, -1}
		};

		List<List<int[]>> transforms = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			List<int[]> transform = new ArrayList<int[]>();
			int[] dir = directions[i % 4];
			// shape composed of different points
			for (String point : shape) {
				String[] matches = point.split("_");
				int x = Integer.parseInt(matches[0]);
				int y = Integer.parseInt(matches[1]);

				if (i < 4) {
					transform.add(new int[] {x * dir[0], y * dir[1]});
				} else {
					transform.add(new int[] {y * dir[0], x * dir[1]});
				}
			}

			transforms.add(transform);
		}
        
        List<String> sameIslands = new ArrayList<>();
        for (int i = 0; i < transforms.size(); i++) {
            List<int[]> transform = transforms.get(i);
            Collections.sort(transform, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
            
            sameIslands.add(getStr(transform));
        }
                
        Collections.sort(sameIslands);

        return sameIslands.get(0);
    }
    
    
    private String getStr(List<int[]> island) {
        StringBuilder sb = new StringBuilder();
        
        for (int[] point : island) {
            sb.append((point[0]) + " " + (point[1]) + " ");
        }

        return sb.toString();
    }
}