/*
Now we also have treasures, denoted by 1.
Given a board and start and end positions for the player, write a function to return the shortest simple path from start to end that includes all the treasures, if one exists.

A simple path is one that does not revisit any location.

board3 = [
    [  1,  0,  0, 0, 0 ],
    [  0, -1, -1, 0, 0 ],
    [  0, -1,  0, 1, 0 ],
    [ -1,  0,  0, 0, 0 ],
    [  0,  1, -1, 0, 0 ],
    [  0,  0,  0, 0, 0 ],
]


treasure(board3, (5, 0), (0, 4)) -> None

treasure(board3, (5, 1), (2, 0)) -> 
[(5, 1), (4, 1), (3, 1), (3, 2), (2, 2), (2, 3), (1, 3), (0, 3), (0, 2),
(0, 1), (0, 0), (1, 0), (2, 0)]
*/

import java.io.*; 
import java.util.*; 

public class Treasure { 
    
    static List<int[]> treasure(int[][] matrix, int starti, int startj, int endi, int endj) {
        int row = matrix.length, col = matrix[0].length, totalTreasure = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 1) {
                    totalTreasure++;
                }
            }
        }
        
        List<List<int[]>> res = new ArrayList<>();
        dfs(matrix, starti, startj, 0, totalTreasure, endi, endj, res, new ArrayList<>());
        System.out.println(res.size());
        if(res.size() < 1) return new ArrayList<>();
        List<int[]> shortest = res.get(0);
        for(int i = 1; i < res.size(); i++) {
            if(res.get(i).size() < shortest.size()) {
                shortest = res.get(i);
            }
        }
        printListArray("", shortest);
        return shortest;
    }
    
    static void dfs(int[][] matrix, int i, int j, int count, int totalTreasure, int endi, int endj, List<List<int[]>> res, List<int[]> cur) {
        int row = matrix.length, col = matrix[0].length;
        int[][] dir = new int[][]{{1,0},{-1,0},{0,-1},{0,1}};

        if(i >= 0 && i < row && j >= 0 && j < col) {
            if(matrix[i][j] == 1 || matrix[i][j] == 0) {
                cur.add(new int[]{i,j});
                if(matrix[i][j] == 1) {
                    count++;    
                }
                int temp = matrix[i][j];
                matrix[i][j] = 2;
                if(i == endi && j == endj && count == totalTreasure) {
                    res.add(new ArrayList<>(cur));
                    cur.remove(cur.size()-1);
                    matrix[i][j] = temp;
                    return;
                }
                for(int[] d: dir){
                   int newi = i+d[0], newj = j+d[1];
                //   System.out.println(i + " " + j + " " + newi + " " + newj);
                  dfs(matrix, newi, newj, count, totalTreasure, endi, endj, res, cur);
                } 
                matrix[i][j] = temp;
                cur.remove(cur.size()-1);
            }
        }
    }

    
    // Driver Code 
    public static void main(String args[]) { 
        int[][] matrix = new int[][] {
          {0,-1,-1,0},
          {0,1,0,0},
          {0,1,0,0}
        };
        treasure(matrix, 0,0, 2,3);




        int[][] board = new int[][] {
          { 0,  0,  0, 0, -1 },
          { 0, -1, -1, 0,  0 },
          { 0,  0,  0, 0,  0 },
          { 0, -1,  0, 0,  0 },
          { 0,  0,  0, 0,  0 },
          { 0,  0,  0, 0,  0 },
        };
        int[][] board2 = new int[][] {
          {  0,  0,  0, 0, -1 },
          {  0, -1, -1, 0,  0 },
          {  0,  0,  0, 0,  0 },
          {  0, -1,  0, 0,  0 },
          { -1,  0,  0, 0,  0 },
          {  0, -1,  0, 0,  0 },
        };
        int[][] board3 = new int[][] {
          {  1,  0,  0, 0,  0 },
          {  0, -1, -1, 0, 0 },
          {  0, -1,  0, 1, 0 },
          {  -1,  0,  0, 0, 0 },
          { 0,  1, -1, 0, 0 },
          {  0,  0,  0, 0, 0 },
        };


        int[] start = new int[] { 5, 0 };
        int[] end = new int[] { 0, 4 };

        
        List<int[]> res1 = position(board, start[0], start[1]);
        
        List<int[]> res2 = position(board, 5, 4);
        
        //printList(res2);
        
        // System.out.println(connected(board2, 0, 0)); 
        // System.out.println(connected(board, 0, 0)); 
        
        List<int[]> res3 = treasure(board3, start[0], start[1], end[0], end[1]);
        
        printList(res3);
    }
} 
