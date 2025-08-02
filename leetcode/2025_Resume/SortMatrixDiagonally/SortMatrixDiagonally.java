/*
A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column
and going in the bottom-right direction until reaching the matrix's end.
For example, the matrix diagonal starting from mat[2][0],
where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

idea:
from
top
left
of the matrix
作为起点
沿着对角线
一个个对角线的sort
*/

class SortMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        for (int col = 0; col < n; col++) {
            sortDiagonal(mat, 0, col);
        }

        for (int row = 1; row < m; row++) {
            sortDiagonal(mat, row, 0);
        }

        return mat;
    }

    public void sortDiagonal(int[][] mat, int row, int col) {
        int m = mat.length;
        int n = mat[0].length;

        List<Integer> diagonal = new ArrayList<>();

        int i = row;
        int j = col;

        while (i < m && j < n) {
            diagonal.add(mat[i++][j++]);
        }

        Collections.sort(diagonal);

        i = row;
        j = col;
        for (int val : diagonal) {
            mat[i++][j++] = val;
        }
    }
}
