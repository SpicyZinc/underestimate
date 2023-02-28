/*
We have a two-dimensional board game involving snakes.  The board has two types of squares on it: +'s represent impassable squares where snakes cannot go, and 0's represent squares through which snakes can move.

Snakes may move in any of four directions - up, down, left, or right - one square at a time, but they will never return to a square that they've already visited.  If a snake enters the board on an edge square, we want to catch it at a different exit square on the board's edge.

The snake is familiar with the board and will take the route to the nearest reachable exit, in terms of the number of squares it has to move through to get there. Note that there may not be a reachable exit.

Here is an example board:

    col-->        0  1  2  3  4  5  6  7  8
               +---------------------------
    row      0 |  +  +  +  +  +  +  +  0  0
     |       1 |  +  +  0  0  0  0  0  +  +
     |       2 |  0  0  0  0  0  +  +  0  +
     v       3 |  +  +  0  +  +  +  +  0  0
             4 |  +  +  0  0  0  0  0  0  +
             5 |  +  +  0  +  +  0  +  0  +

Write a function that takes a rectangular board with only +'s and O's, along with a starting point on the edge of the board, and returns the coordinates of the nearest exit to which it can travel.  If there is a tie, return any of the nearest exits.
-----------------------------------------------------
Sample inputs:
board1 = [['+', '+', '+', '+', '+', '+', '+', '0', '0'],
          ['+', '+', '0', '0', '0', '0', '0', '+', '+'],
          ['0', '0', '0', '0', '0', '+', '+', '0', '+'],
          ['+', '+', '0', '+', '+', '+', '+', '0', '0'],
          ['+', '+', '0', '0', '0', '0', '0', '0', '+'],
          ['+', '+', '0', '+', '+', '0', '+', '0', '+']]
start1_1 = (2, 0) # Expected output = (5, 2) 
start1_2 = (0, 7) # Expected output = (0, 8)
start1_3 = (5, 2) # Expected output = (2, 0) or (5, 5)
start1_4 = (5, 5) # Expected output = (5, 7)

board2 = [['+', '+', '+', '+', '+', '+', '+'],
          ['0', '0', '0', '0', '+', '0', '+'],
          ['+', '0', '+', '0', '+', '0', '0'],
          ['+', '0', '0', '0', '+', '+', '+'],
          ['+', '+', '+', '+', '+', '+', '+']]
start2_1 = (1, 0) # Expected output = null (or a special value representing no possible exit)
start2_2 = (2, 6) # Expected output = null 

board3 = [['+', '0', '+', '0', '+',],
          ['0', '0', '+', '0', '0',],
          ['+', '0', '+', '0', '+',],
          ['0', '0', '+', '0', '0',],
          ['+', '0', '+', '0', '+']]
start3_1 = (0, 1) # Expected output = (1, 0)
start3_2 = (4, 1) # Expected output = (3, 0)
start3_3 = (0, 3) # Expected output = (1, 4)
start3_4 = (4, 3) # Expected output = (3, 4)

board4 = [['+', '0', '+', '0', '+',],
          ['0', '0', '0', '0', '0',],
          ['+', '+', '+', '+', '+',],
          ['0', '0', '0', '0', '0',],
          ['+', '0', '+', '0', '+']]
start4_1 = (1, 0) # Expected output = (0, 1)
start4_2 = (1, 4) # Expected output = (0, 3)
start4_3 = (3, 0) # Expected output = (4, 1)
start4_4 = (3, 4) # Expected output = (4, 3)

board5 = [['+', '0', '0', '0', '+',],
          ['+', '0', '+', '0', '+',],
          ['+', '0', '0', '0', '+',],
          ['+', '0', '+', '0', '+']]
start5_1 = (0, 1) # Expected output = (0, 2)
start5_2 = (3, 1) # Expected output = (0, 1)

All test cases:
findExit(board1, start1_1) => (5, 2)
findExit(board1, start1_2) => (0, 8)
findExit(board1, start1_3) => (2, 0) or (5, 5)
findExit(board1, start1_4) => (5, 7)
findExit(board2, start2_1) => null (or a special value representing no possible exit)
findExit(board2, start2_2) => null
findExit(board3, start3_1) => (1, 0)
findExit(board3, start3_2) => (3, 0)
findExit(board3, start3_3) => (1, 4)
findExit(board3, start3_4) => (3, 4)
findExit(board4, start4_1) => (0, 1)
findExit(board4, start4_2) => (0, 3)
findExit(board4, start4_3) => (4, 1)
findExit(board4, start4_4) => (4, 3)
findExit(board5, start5_1) => (0, 2)
findExit(board5, start5_2) => (0, 1)

Complexity Analysis:

r: number of rows in the board
c: number of columns in the board

*/

import java.util.*;

class Path {
    int steps;
    int x;
    int y;

    public Path(int steps, int x, int y) {
        this.steps = steps;
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return this.x + ", " + this.y;
    }
}

class SnakeShortestExit {
    public static void main(String[] argv) {
        // char[][] board1 = {{'+', '+', '+', '0', '+', '0', '0'},
        //                   {'0', '0', '0', '0', '0', '0', '0'},
        //                   {'0', '0', '+', '0', '0', '0', '0'},
        //                   {'0', '0', '0', '0', '+', '0', '0'},
        //                   {'+', '+', '+', '0', '0', '0', '+'}};

        // char[][] board2 = {{'+', '+', '+', '0', '+', '0', '0'},
        //                   {'0', '0', '0', '0', '0', '+', '0'},
        //                   {'0', '0', '+', '0', '0', '0', '0'},
        //                   {'0', '0', '0', '0', '+', '0', '0'},
        //                   {'+', '+', '+', '0', '0', '0', '+'}};
          
        // char[][] board3 = {{'+', '+', '+', '0', '+', '0', '0'},
        //                   {'0', '0', '0', '0', '0', '0', '0'},
        //                   {'0', '0', '+', '+', '0', '+', '0'},
        //                   {'0', '0', '0', '0', '+', '0', '0'},
        //                   {'+', '+', '+', '0', '0', '0', '+'}};
          
        // char[][] board4 = {{'+'}}; 

        // Solution eg = new Solution();

        // String[] result1 = eg.findPassableLanes(board1);
        // for (String s : result1) {
        //   System.out.print(s);
        // }
        // System.out.println();

        // String[] result2 = eg.findPassableLanes(board2);
        // for (String s : result2) {
        //   System.out.print(s);
        // }
        // System.out.println();


        // String[] result3 = eg.findPassableLanes(board3);
        // for (String s : result3) {
        //   System.out.print(s);
        // }
        // System.out.println();


        // String[] result4 = eg.findPassableLanes(board4);
        // for (String s : result4) {
        //   System.out.print(s);
        // }
        // System.out.println();


        char[][] board1 = new char[][] {{'+', '+', '+', '+', '+', '+', '+', '0', '0'},
                                        {'+', '+', '0', '0', '0', '0', '0', '+', '+'},
                                        {'0', '0', '0', '0', '0', '+', '+', '0', '+'},
                                        {'+', '+', '0', '+', '+', '+', '+', '0', '0'},
                                        {'+', '+', '0', '0', '0', '0', '0', '0', '+'},
                                        {'+', '+', '0', '+', '+', '0', '+', '0', '+'}};
        int[] start1_1 = {2, 0}; // Expected output = {5, 2}
        int[] start1_2 = {0, 7}; // Expected output = {0, 8}   
        int[] start1_3 = {5, 2}; // Expected output = {2, 0} or {5, 5}
        int[] start1_4 = {5, 5}; // Expected output = {5, 7}

        char[][] board2 = new char[][] {{'+', '+', '+', '+', '+', '+', '+'},
                                        {'0', '0', '0', '0', '+', '0', '+'},
                                        {'+', '0', '+', '0', '+', '0', '0'},
                                        {'+', '0', '0', '0', '+', '+', '+'},
                                        {'+', '+', '+', '+', '+', '+', '+'}};
        int[] start2_1 = {1, 0}; // Expected output = null (or a special value representing no possible exit)
        int[] start2_2 = {2, 6}; // Expected output = null

        char[][] board3 = new char[][] {{'+', '0', '+', '0', '+'},
                                        {'0', '0', '+', '0', '0'},
                                        {'+', '0', '+', '0', '+'},
                                        {'0', '0', '+', '0', '0'},
                                        {'+', '0', '+', '0', '+'}};
        int[] start3_1 = {0, 1}; // Expected output = {1, 0}
        int[] start3_2 = {4, 1}; // Expected output = {3, 0}
        int[] start3_3 = {0, 3}; // Expected output = {1, 4}
        int[] start3_4 = {4, 3}; // Expected output = {3, 4}

        char[][] board4 = new char[][] {{'+', '0', '+', '0', '+'},
                                        {'0', '0', '0', '0', '0'},
                                        {'+', '+', '+', '+', '+'},
                                        {'0', '0', '0', '0', '0'},
                                        {'+', '0', '+', '0', '+'}};
        int[] start4_1 = {1, 0}; // Expected output = {0, 1}
        int[] start4_2 = {1, 4}; // Expected output = {0, 3}
        int[] start4_3 = {3, 0}; // Expected output = {4, 1}
        int[] start4_4 = {3, 4}; // Expected output = {4, 3}

        char[][] board5 = new char[][] {{'+', '0', '0', '0', '+'},
                                        {'+', '0', '+', '0', '+'},
                                        {'+', '0', '0', '0', '+'},
                                        {'+', '0', '+', '0', '+'}};
        int[] start5_1 = {0, 1}; // Expected output = (0, 2)
        int[] start5_2 = {3, 1}; // Expected output = (0, 1)

        SnakeShortestExit eg = new SnakeShortestExit();
        // System.out.println("board1");
        // eg.findShortestExit(board1, start1_1);
        // eg.findShortestExit(board1, start1_2);
        // eg.findShortestExit(board1, start1_3);
        // eg.findShortestExit(board1, start1_4);

        // System.out.println("board2");
        // eg.findShortestExit(board2, start2_1);
        // eg.findShortestExit(board2, start2_2);

        // System.out.println("board3");
        // eg.findShortestExit(board3, start3_1);
        // eg.findShortestExit(board3, start3_2);
        // eg.findShortestExit(board3, start3_3);
        // eg.findShortestExit(board3, start3_4);

        // System.out.println("board4");
        // eg.findShortestExit(board4, start4_1);
        // eg.findShortestExit(board4, start4_2);
        // eg.findShortestExit(board4, start4_3);
        // eg.findShortestExit(board4, start4_4);

        System.out.println("board5");
        eg.findShortestExit(board5, start5_1);
        // eg.findShortestExit(board5, start5_2);
    }

    public void findShortestExit(char[][] board, int[] start) {
        int m = board.length;
        int n = board[0].length;

        int x = start[0];
        int y = start[1];

        int[][] visited = new int[m][n];

        PriorityQueue<Path> pq = new PriorityQueue<Path>(new Comparator<Path>() {
            @Override
            public int compare(Path a, Path b) {
                return a.steps - b.steps;
            }
        });

        int minSteps = dfs(board, visited, x, y, Integer.MAX_VALUE, 0, pq);

        System.out.println(pq);
 
        if (minSteps != Integer.MAX_VALUE) {
            System.out.println(String.format("The shortest path from source to destination has length of %s, the exit is at [ %s ]", minSteps, pq.poll().toString()));
        } else {
            System.out.println("Destination can't be reached from source");
        }
    }
    // question 1
    public String[] findPassableLanes(char[][] board) {
        int m = board.length;
        int n = board[0].length;
            
            List<Integer> rows = new ArrayList<>();

        for (int i = 0; i < m; i++) {
          char[] row = board[i];
          if (isZeroRow(row)) {
            rows.add(i);
          } 
        }

        List<Integer> columns = new ArrayList<>();
        for (int j = 0; j < n; j++) {
          int i = 0;
          for (i = 0; i < m; i++) {
            
             char c = board[i][j];

             if (c == '+') {
               break;
             }
          }
          if (i == m) {
                columns.add(j);
          }
        }

        String[] result = new String[2];

        result[0] = "Rows: " + rows.toString();
        result[1] = "Cols: " + columns.toString();

        return result;
        }

    public boolean isZeroRow(char[] row) {
        for (int i = 0; i < row.length; i++) {
          if (row[i] == '+') {
            return false;
          }
        }
        return true;
    }

    public int dfs(char[][] board, int[][] visited, int startX, int startY, int minSteps, int stepsSoFar, PriorityQueue<Path> pq) {
        int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
        };

        int m = board.length;
        int n = board[0].length;

        // If the exit is at boundary row or column, return min steps
        if ((startX == m - 1 || startY == n - 1)) {
            int smallestSteps = Math.min(minSteps, stepsSoFar);
            pq.add(new Path(smallestSteps, startX, startY));

            System.out.println(String.format("smallestSteps == %d, startX == %d, startY == %d", smallestSteps, startX, startY));

            return smallestSteps;
        }

        visited[startX][startY] = 1;

        for (int[] dir : directions) {
            int nextX = startX + dir[0];
            int nextY = startY + dir[1];

            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && isSafe(board, visited, nextX, nextY)) {
                stepsSoFar += 1;
                minSteps = dfs(board, visited, nextX, nextY, minSteps, stepsSoFar, pq);
                // remember to minus 1
                stepsSoFar -= 1;
            }
        }
        // remember to minus 1
        visited[startX][startY] = 0;

        return minSteps;
    }

    public boolean isSafe(char[][] board, int[][] visited, int i, int j) {
        return board[i][j] == '0' && visited[i][j] == 0;
    }
}
