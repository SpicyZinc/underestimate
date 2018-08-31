/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

idea: 
http://blog.csdn.net/linhuanmars/article/details/23230657

For triangle, the bottom row length is equal to the height of triangle, 
so use pathSum to hold the bottom row's value, then from bottom to up, find minimum path

DP note: 
1. it is "adjacent"
2. ArrayList.add(index, val) is like set() method, suppress others to the behind

1. think of the shape as a shape like two antenna, there are two elements at the top of current element
in the two elements, the smaller element can give rise to a smaller sum,
so Math.min(left, right) + current element, put it in the current position in the memory arraylist

2. for each row in the triangle, starting from the right to left

3. bottom up

difference between method 2 and 3 is from top or from bottom
*/
import java.util.*;

public class Triangle {
	public static void main(String[] args) {	
		ArrayList<ArrayList<Integer>> delta = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> line1 = new ArrayList<Integer>();
		line1.add(2);
		
		ArrayList<Integer> line2 = new ArrayList<Integer>();
		line2.add(3);
		line2.add(4);
		
		ArrayList<Integer> line3 = new ArrayList<Integer>();
		line3.add(6);
		line3.add(5);
		line3.add(7);
		
		ArrayList<Integer> line4 = new ArrayList<Integer>();
		line4.add(4);
		line4.add(1);
		line4.add(8);
		line4.add(3);
		
		delta.add(line1);
		delta.add(line2);
		delta.add(line3);
		delta.add(line4);
		
		Triangle eg = new Triangle();
		int minimum = eg.minimumTotal(delta);
		System.out.println(minimum);		
	}
	// method 1, bottom up
	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null || triangle.size() == 0) {
			return 0;
		}

		int depth = triangle.size();
        int[][] minSum = new int[depth][depth];
        // initialize last row, bottom up
        for (int i = 0; i < triangle.get(depth - 1).size(); i++) {
            minSum[depth - 1][i] = triangle.get(depth - 1).get(i);
        }

        // loop through triangle from second last level from bottom to top
        for (int i = depth - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                minSum[i][j] = triangle.get(i).get(j) + Math.min(minSum[i + 1][j], minSum[i + 1][j + 1]);
            }
        }

        return minSum[0][0];
    }

	// best method and easily understand
    // method 2: for each row in the triangle, starting from the right to left, and use array as result
    // sum[i] current row to position i, min sum accumulated
	public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }

        int m = triangle.size();
        int n = triangle.get(n - 1).size();
        int[] sum = new int[n];

        sum[0] = triangle.get(0).get(0);

        for (int i = 1; i < n; i++) {
            List<Integer> level = triangle.get(i);
            for (int j = level.size() - 1; j >= 0; j--) {
                if (j == 0) {
                    sum[j] = sum[j] + level.get(j);
                } else if (j == level.size() - 1) {
                    sum[j] = sum[j - 1] + level.get(j);
                } else {
                    sum[j] = Math.min(sum[j], sum[j - 1]) + level.get(j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int a : sum) {
            min = Math.min(min, a);
        }

        return min;
    }

    // TLE, 42 / 43 test cases passed.
    // http://blog.csdn.net/guixunlong/article/details/8850169
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }

        return add(triangle, 0, 0);
    }

    public int add(List<List<Integer>> triangle, int pos, int level) {
        if (level == triangle.size()) {
            return 0;
        }

        List<Integer> curr = triangle.get(level);
        int value = curr.get(pos);
        return value + Math.min(add(triangle, pos, level + 1), add(triangle, pos + 1, level + 1));
    }


    // lintcode version
    public int minimumTotal(int[][] triangle) {
        if (triangle.length == 0 || triangle[0].length == 0) {
            return 0;
        }
        
        int height = triangle.length;
        int[][] minSum = new int[height][height];
        // initialize the bottom
        for (int i = 0; i < triangle[height - 1].length; i++) {
            minSum[height - 1][i] = triangle[height - 1][i];
        }
        // transition formula
        for (int i = height - 2; i >= 0; i--) {
            // minSum has extra space, not used at all,
            // triangle has a feature row index + 1 is the length of this row
            // no need to worry about j + 1 == i + 1 == height - 1 (max), minSum max is height - 1
            for (int j = 0; j <= i; j++) { // for (int j = 0; j < triangle[i].length; j++) { also work
                minSum[i][j] = Math.min(minSum[i + 1][j], minSum[i + 1][j + 1]) + triangle[i][j];
            }
        }
        
        return minSum[0][0];
    }

	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
		if (triangle.size() == 0 || triangle == null) {
			return 0;
		}
		int height = triangle.size();
		int[] pathSum = new int[height];
		for (int i = 0; i < height; i++) {
			pathSum[i] = triangle.get(height - 1).get(i);
		}

		for (int i = height - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				pathSum[j] = Math.min(pathSum[j], pathSum[j + 1]) + triangle.get(i).get(j);
			}
		}

		return pathSum[0];
	}
}
