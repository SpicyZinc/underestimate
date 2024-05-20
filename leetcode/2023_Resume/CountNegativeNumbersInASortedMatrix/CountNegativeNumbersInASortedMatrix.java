
// Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.

class CountNegativeNumbersInASortedMatrix {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            int j = 0;
            for (; j < n; j++) {
                if (grid[i][j] < 0) {
                    break;
                    
                }
            }
            count += n - j;
        }

        return count;
    }
}