/*
On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.
Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.
Now, we walk in a clockwise spiral shape to visit every position in this grid. 
Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.) 
Eventually, we reach all R * C spaces of the grid.
Return a list of coordinates representing the positions of the grid in the order they were visited.

Example 1:
Input: R = 1, C = 4, r0 = 0, c0 = 0
Output: [[0,0],[0,1],[0,2],[0,3]]
https://s3-lc-upload.s3.amazonaws.com/uploads/2018/08/24/example_1.png

Example 2:
Input: R = 5, C = 6, r0 = 1, c0 = 4
Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
https://s3-lc-upload.s3.amazonaws.com/uploads/2018/08/24/example_2.png

Note:
1 <= R <= 100
1 <= C <= 100
0 <= r0 < R
0 <= c0 < C

idea:
步长 is like 1, 1, 2, 2, 3, 3, 4, 4, 5, 5

When we go east, we do c++ (column increases),
when we go west, we do c--,
when we go south, we do r++ (row increases),
when we go north, we do r--.

need to come back figure out why commented out code caused TLE
我不应该 先++ 应该先传进去然后 ++
*/

class SpiralMatrix {
	public static void main(String[] args) {
		SpiralMatrix eg = new SpiralMatrix();
		eg.test(); 
	}

	int n = 5;
	public void test() {
		for (int i = 0; i < 3; i++) {
			System.out.println("before " + n);
			increases();
			System.out.println("after " + n);
		}
	}

	public void increases() {
		n++;
		System.out.println("called " + n);
	}

	int index = 0;
	int[][] result;

	public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
		this.index = 0;
		this.result = new int[R * C][2];
		
		int i = r0;
		int j = c0;
		
		int steps = 1;

		while (index < R * C) {
			for (int k = 0; k < steps; k++) {
				// j++;
				add(i, j++, R, C);
			}
			
			for (int k = 0; k < steps; k++) {
				// i++;
				add(i++, j, R, C);
			}
			
			steps++;
			
			for (int k = 0; k < steps; k++) {
				// j--;
				add(i, j--, R, C);
			}
			
			for (int k = 0; k < steps; k++) {
				// i--;
				add(i--, j, R, C);
			}

			steps++;
		}
		
		return result;
	}
	
	public void add(int i, int j, int m, int n) {
		if (i >= 0 && i < m && j >= 0 && j < n) {
			result[index] = new int[] {i, j};
			index++;
		}
	}
}