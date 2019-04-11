// follow up 是如果array里有line跟Point的话怎么过滤掉



import java.io.*; 
import java.util.*; 

public class MatrixAndRectangle {
	// Driver Code 
    public static void main(String args[]) {
        int[][] matrix = new int[][] {
			{1,1,1,1,1,1},
			{0,0,1,0,1,1},
			{0,0,1,0,1,0},
			{1,1,1,0,1,0},
			{1,0,0,1,1,1}
		};

        int[][] matrix2 = new int[][]{
			{1,1,1,1,1,1},
			{0,0,1,0,0,1},
			{0,0,1,0,1,0},
			{0,1,0,0,1,0},
			{1,0,0,1,0,1}
		};

        printArray("only one " ,rectangle1(matrix));
        rectangleMulti(matrix);
        // irregular(matrix2);
    }

    // only one 0's rectangle 左上 and 右下
	public static int[] rectangle1 (int[][] matrix) {
		int[] result = new int[4];
		int m = matrix.length;
		int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    int iRight = i;
                    int jDown = j;

                    while (iRight < m) {
                        if (matrix[iRight][j] == 0) {
                            iRight++;
                        } else {
                            break;
                        }
                    }

                    while (jDown < n) {
                        if (matrix[i][jDown] == 0) {
                            jDown++;
                        } else {
                            break;
                        }
                    }

                    return new int[] {i, j, iRight - 1, jDown - 1};
                }
            }
        }

        return result;
    }

	// all 0's rectangle 左上 and 右下
    public static List<int[]> rectangleMulti(int[][] matrix) {
        List<int[]> result = new ArrayList<>();

        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    int iRight = i, jDown = j;
                    while (iRight < m) {
                        if (matrix[iRight][j] == 0) {
                            iRight++;
                        } else {
                            break;
                        }
                    }
                    while (jDown < n) {
                        if (matrix[i][jDown] == 0) {
                            jDown++;
                        } else {
                            break;
                        }
                    }

                    result.add(new int[] {i, j, iRight-1, jDown-1});
                    fill(i, j, iRight - 1, jDown - 1, matrix);
                }
            }
        }
        printListArray("", result);

        return result;
    }
    
    static List<List<int[]>> irregular(int[][] matrix) {
        List<List<int[]>> res = new ArrayList<>();
        int row = matrix.length, col = matrix[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 0) {
                    List<int[]> cur = new ArrayList<>();
                    dfs(matrix, i, j, row, col, cur);
                    res.add(cur);
                }
            }
        }
        for(List<int[]> r: res) {
            printListArray("", r);
        }
        return res;
    }
    
    static void dfs(int[][] matrix, int i, int j, int row, int col, List<int[]> cur) {
        int[][] dir = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}};
        if (i<0 || i>= row || j<0 || j>=col || matrix[i][j] == 1) {
            return;
        }
        cur.add(new int[]{i,j});
        matrix[i][j] = 1;
        for(int[] k: dir) {
            int i2 = i+k[0], j2 = j+k[1];
            dfs(matrix, i2, j2, row, col, cur);
        }
    }
    
    static void fill(int i1, int j1, int i2, int j2, int[][] matrix) {
        for(int i = i1; i <= i2; i++) {
            for(int j = j1; j <= j2; j++) {
                matrix[i][j] = 1;
            }
        } 
    }
    
    static void printArray(String s, int[] array) {
        System.out.println(s);
        for(int i: array){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    static void printSet(String s, Set<String> set) {
        System.out.println(s);
        for(String i: set){
            System.out.println(i + " ");
        }
        System.out.println();
    }
    
    static void printList(String s, List<String> list) {
        System.out.println(s);
        for(String i: list){
            System.out.println(i + " --> ");
        }
        System.out.println();
    }
    
    static void printListArray(String s, List<int[]> list) {
        System.out.println(s);
        for(int[] i: list){
            printArray("",i);
        }
        System.out.println();
    }
    
    static void printListInt(String s, List<Integer> list) {
        System.out.println(s);
        for(Integer i: list){
            System.out.println(i + " --> ");
        }
        System.out.println();
    }
    
    static void printMap(String s, Map<String, List<Integer>> map) {
        System.out.println(s);
        for(String ss: map.keySet()){
            printListInt(ss, map.get(ss));
        }
        System.out.println();
    }
}
