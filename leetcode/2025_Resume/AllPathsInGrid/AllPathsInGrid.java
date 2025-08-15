/*
this is meta 2025 2nd coding session
not that hard
idea:
both bfs and dfs works, just need remember path
and direction only 2 directions
*/

import java.util.*;

class State {
    int down, right;
    String path;

    State(int d, int r, String p){ 
        down = d; right = r; path = p;
    }
}

public class AllPathsInGrid {
    public static void main(String[] args) {
        AllPathsInGrid eg = new AllPathsInGrid();
        List<String> result = eg.generatePathsDFS(3);
        System.out.println(result);
    }


    public List<String> generatePathsDFS(int n) {
        int[][] directions = {
            {1, 0}, // right
            {0, 1} // down
        };
        List<String> result = new ArrayList<>();
        dfs(n, 0, 0, "", result);

        return result;
    }

    public void dfs(int n, int x, int y, String path, List<String> result ) {
        if (x == n || y == n) {
            return;
        }
        if (x == n - 1 && y == n - 1) {
            result.add(path);
            return;
        }

        dfs(n, x + 1, y, path + "R", result);
        dfs(n, x, y + 1, path + "D", result);
    }


    public List<String> generatePaths(int n) {
        int[][] directions = {
            {1, 0}, // right
            {0, 1} // down
        };

        List<String> results = new ArrayList<>();
        
        // Queue<State> queue = new LinkedList<>();
        // queue.add(new State(0, 0, ""));
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        Queue<String> path = new LinkedList<>();
        path.add("");
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            String p = path.poll();

            if (x == n - 1 && y == n - 1) {
                results.add(p);
                continue;
            }

            for (int i = 0; i < directions.length; i++) {
                int[] dir = directions[i];
                int nextX = x + dir[0];
                int nextY = y + dir[1];

                if (nextX < n && nextY < n) {
                    queue.add(new int[] {nextX, nextY});

                    if (i == 0) {
                        path.add(p + "R");
                    }
                    if (i == 1) {
                        path.add(p + "D");
                    }
                }
            }
        }

        return results;
    }
}
