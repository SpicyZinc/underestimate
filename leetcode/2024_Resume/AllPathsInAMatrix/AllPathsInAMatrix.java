
import java.util.*;

class AllPathsInAMatrix {
    public static void main(String[] args) {
        AllPathsInAMatrix eg = new AllPathsInAMatrix();
        int m = 3;
        int n = 4;
        int[][] grid = new int[m][n];

        List<String> result = eg.allPathsSourceTarget(grid);

        for (String p : result) {
            System.out.println(p);
        }
    }

    public List<String> allPathsSourceTarget(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        List<String> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];

        dfs(grid, 0, 0, visited, path, result);

        return result;
    }

    public void dfs(int[][] grid, int i, int j, boolean[][] visited, List<Integer> path, List<String> result) {
        int m = grid.length;
        int n = grid[0].length;

        if (i == m - 1 && j == n - 1) {
            path.add(i * n + j);
            result.add(path.toString());
            path.remove(path.size() - 1);
            return;
        }

        int[][] directions = new int[][] {
            {0, -1},
            {0, 1},
            {1, 0},
            {-1, 0},
        };

        for (int[] dir : directions) {
            int nextX = i + dir[0];
            int nextY = j + dir[1];

            // 也可以 就是不需要visited
            // if (path.contains(nextX * m + nextY)) {
            //     continue;
            // }

            visited[i][j] = true;
            path.add(i * n + j);

            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]) {
                dfs(grid, nextX, nextY, visited, path, result);
            }

            path.remove(path.size() - 1);
            visited[i][j] = false;
        }
    }
}
