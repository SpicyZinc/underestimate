
class MaxSumPath {
    int[][] directions = new int[][] {
        {1, 1},
        {0, 1},
        {-1, 1}
    };

    public static void main(String[] args) {
        int[][] matrix = {
            { 10, 10, 2, 0, 20, 4 },
            { 1,   0, 0, 30, 2, 5 },
            { 0,  10, 4,  0, 2, 0 },
            { 1,   0, 2, 20, 0, 4 }
        };

        MaxSumPath eg = new MaxSumPath();
        System.out.println(eg.maxSumPath(matrix));
    }

    public int maxSumPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] memo = new int[m][n];
        int maxSumPath = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxSumPath = Math.max(maxSumPath, dfs(matrix, i, j, memo));
            }
        }

        return maxSumPath;
    }

    // 以 i, j为起点的path的最大sum
    public int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int m = matrix.length;
        int n = matrix[0].length;

        for (int[] dir : directions) {
            int nextX = i + dir[0];
            int nextY = j + dir[1];
    
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && matrix[nextX][nextY] > matrix[i][j]) {
                memo[i][j] = Math.max(memo[i][j], dfs(matrix, nextX, nextY, memo));
            }
        }

        memo[i][j] += matrix[i][j];

        return memo[i][j];
    }
}