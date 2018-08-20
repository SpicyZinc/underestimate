/*
You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
0 represents the obstacle can't be reached.
1 represents the ground can be walked through.

The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first.
And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees.
If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
Example 1:
Input: 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6

Example 2:
Input: 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1

Example 3:
Input: 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.

Hint: size of the given matrix will not exceed 50x50.

idea:
https://www.cnblogs.com/grandyang/p/8379506.html
不断更新 sr sc, 从而计算 (sr, sc) 到 target的distance

*/
import java.util.*;

class CutOffTreesForGolfEvent {
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();

        List<int[]> trees = new ArrayList();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = forest.get(i).get(j);
                if (val >= 1) {
                    trees.add(new int[] {val, i, j});
                }
            }
        }

        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0;
        int sr = 0;
        int sc = 0;

        for (int[] tree : trees) {
            int d = getDistance(forest, sr, sc, tree[1], tree[2]);
            if (d < 0) {
                return -1;
            }

            ans += d;
            sr = tree[1];
            sc = tree[2];
        }

        return ans;
    }
    // correct get distance method
    private int getDistance(List<List<Integer>> forest, int i, int j, int di, int dj) {

        if (i == di && j == dj) {
            return 0;
        }

        int m = forest.size();
        int n = forest.get(0).size();

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
        };

        int cnt = 0;

        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});

        while (!queue.isEmpty()) {
            cnt++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();
                for (int[] dir : directions) {
                    int nextX = cell[0] + dir[0];
                    int nextY = cell[1] + dir[1];

                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY] || forest.get(nextX).get(nextY) == 0) {
                        continue;
                    }
                    if (nextX == di && nextY == dj) {
                        return cnt;
                    }

                    visited[nextX][nextY] = true;
                    queue.add(new int[] {nextX, nextY});
                }
            }
        }

        return -1;
    }
    // failed 21 / 53 test cases passed
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();

        List<int[]> trees = new ArrayList();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = forest.get(i).get(j);
                if (val >= 1) {
                    trees.add(new int[] {val, i, j});
                }
            }
        }

        Collections.sort(trees, (a, b) -> Integer.compare(a[0], b[0]));

        int ans = 0;
        int sr = 0;
        int sc = 0;

        for (int[] tree : trees) {
            int d = dist(forest, sr, sc, tree[1], tree[2]);
            if (d < 0) {
                return -1;
            }

            ans += d;
            sr = tree[1];
            sc = tree[2];
        }

        return ans;
    }

    private int dist(List<List<Integer>> forest, int i, int j, int di, int dj) {
        if (i == di && j == dj) {
            return 0;
        }

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
        };

        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x == di && y == dj) {
                return 1;
            }
        }

        return -1;
    }
}