/*
Sigma Computing

I got myself trapped in this question


rows infinite
cols from constructor
*/

import java.util.*;


class SpreadSheet {
    String[] columns;
    List<List<Integer>> table;

    public SpreadSheet(String[] columns) {
        this.columns = columns;
        this.table = new LinkedList<>();

        for (int i = 0; i < columns.length; i++) {
            this.table.add(new LinkedList<Integer>());
        }
    }

    void set(int row, String column, int value) {
        int index = findIndex(column);
        List<Integer> col = table.get(index);

        for (int i = 0; i < row; i++) {
            if (i == row - 1) {
                col.add(value);
            } else {
                col.add(0);
            }
        }
    }

    int get(int row, String column) {
        int index = findIndex(column);
        if (index == - 1) {
            return 0;
        }

        List<Integer> col = table.get(index);
        if (col.size() == 0 || col == null) {
            return 0;
        } else {
            return col.get(row - 1);
        }
    }

    void printFirstN(int n) {
        int m = columns.length;

        int[][] matrix = new int[n][m];
        for (int j = 0; j < m; j++) {
            List<Integer> col = table.get(j);
            for (int i = 0; i < col.size(); i++) {
                // transpose to matrix for easier print
                if (i < n) {
                    matrix[i][j] = table.get(j).get(i);
                }
            }
        }

        for (int ii = 0; ii < n; ii++) {
            System.out.println();
            for (int jj = 0; jj < m; jj++) {
                System.out.print(matrix[ii][jj] + " ");
            }
        }

        System.out.println();
    }

    int findIndex(String column) {
        for (int i = 0; i < columns.length; i++) {
            if (columns[i] == column) {
                return i;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        String[] columns = new String[] {"aa", "bb", "cc"};
        SpreadSheet ss = new SpreadSheet(columns);

        ss.set(2, "aa", 111);
        ss.set(3, "bb", 222);
        ss.set(4, "cc", 333);

        ss.get(3, "bb");

        ss.printFirstN(3);
    }
}


