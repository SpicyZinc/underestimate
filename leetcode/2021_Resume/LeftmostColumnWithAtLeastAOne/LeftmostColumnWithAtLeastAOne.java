/*
A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.
You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.

Example 1:
https://assets.leetcode.com/uploads/2019/10/25/untitled-diagram-5.jpg
Input: mat = [[0,0],[1,1]]
Output: 0

Example 2:
https://assets.leetcode.com/uploads/2019/10/25/untitled-diagram-4.jpg
Input: mat = [[0,0],[0,1]]
Output: 1

Example 3:
https://assets.leetcode.com/uploads/2019/10/25/untitled-diagram-3.jpg
Input: mat = [[0,0],[0,0]]
Output: -1

Example 4:
https://assets.leetcode.com/uploads/2019/10/25/untitled-diagram-6.jpg
Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
Output: 1
 
Constraints:
rows == mat.length
cols == mat[i].length
1 <= rows, cols <= 100
mat[i][j] is either 0 or 1.
mat[i] is sorted in non-decreasing order.

idea:
1. for columns do binary search
2. linear search on matrix
从 第一个row 和 最后一个 col 开始
*/

// This is the BinaryMatrix's API interface.
// You should not implement it, or speculate about its implementation
interface BinaryMatrix {
    public int get(int row, int col) {}
    public List<Integer> dimensions {}
};

class Solution {
    // method 1, binary search on column
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimen = binaryMatrix.dimensions();
        int m = dimen.get(0);
        int n = dimen.get(1);

        int answer = -1;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int midCol = left + (right - left) / 2;
            if (existOneInColumn(binaryMatrix, midCol)) {
                answer = midCol;
                right = midCol - 1;
            } else {
                left = midCol + 1;
            }
        }

        return answer;
    }

    boolean existOneInColumn(BinaryMatrix binaryMatrix, int col) {
        List<Integer> dimen = binaryMatrix.dimensions();
        int m = dimen.get(0);

        for (int row = 0; row < m; row++) {
            if (binaryMatrix.get(row, col) == 1) {
                return true;
            }
        }

        return false;
    }
    // method 2, linear search on matrix
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimen = binaryMatrix.dimensions();
        int m = dimen.get(0);
        int n = dimen.get(1);

        int answer = -1;
        int row = 0;
        int col = n - 1;

        while (row < m && col >= 0) {
            if (binaryMatrix.get(row, col) == 1) {
                answer = col;
                col--;
            } else {
                row++;
            }
        }

        return answer;
    }
}