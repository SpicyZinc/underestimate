/*
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]

idea: 
http://blog.csdn.net/linhuanmars/article/details/23230657

For triangle, the bottom row length is equal to the height of triangle, 
so use pathSum to hold the bottom row's value, then from bottom to up, find minimum path

DP Attention: 
1. it is "adjacent"
2. ArrayList.add() is not set() method, suppress others to the behind

FIRST:
think of the shape as a shape like two antenna, there are two elements at the top of current element
in the two elements, the smaller element can give rise to a smaller sum,
so Math.min(left, right) + current element, put it in the current position in the memory arraylist

SECOND:
for each row in the triangle, starting from the right to left

THIRD:
bottom up

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
		
		Triangle aTest = new Triangle();
		int minimum = aTest.minimumTotal(delta);
		System.out.println(minimum);		
	}
	// method 1: uses arraylist as result
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
			return 0;
        }
		// res is used to memorize
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i=0; i<triangle.size(); i++) {
            ArrayList<Integer> temp = triangle.get(i);
			// first
			// min(left above, right above) left above is virtual, not existing
			// directly self + temp.get(0)
			// same when meeting last 
			// only need to pay attention to the very first beginning, no adding, direct temp.get(0)
            res.add(0, i > 0 ? temp.get(0) + res.get(0) : temp.get(0));
            for (int j=1; j<res.size()-1; j++) {
				res.set(j, Math.min(res.get(j) + temp.get(j), res.get(j + 1) + temp.get(j)));
            }
			// last
            if (res.size() > 1) {
            	res.set(res.size() - 1, res.get(res.size() - 1) + temp.get(res.size() - 1));
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (Integer temp : res) {
            min = Math.min(temp, min);
        }
		
        return min;
    }
	// method 2: for each row in the triangle, starting from the right to left, and uses array as result
	// best method and easily understand
	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle.size() == 0) {
    		return 0;
		}

		int[] f = new int[triangle.get(triangle.size()-1).size()];
		f[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
        	List<Integer> level = triangle.get(i);
        	// note must start from the back of the array
            for (int j = level.size() - 1; j >= 0; j--) {
                if (j == 0) {
					f[j] = f[j] + level.get(j);
                }
                else if (j == level.size() - 1) {
					f[j] = f[j-1] + level.get(j);
                }
                else {
                    f[j] = Math.min(f[j-1], f[j]) + level.get(j);
                }
            }
        }
                    
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < f.length; i++) {
            ret = Math.min(ret, f[i]);
        }
            
        return ret;
	}
	// method 3: bottom up
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
	// method 4: recursion, TLE
	// http://blog.csdn.net/guixunlong/article/details/8850169
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        return add(triangle, 0, 0);
    }

    public int add(List<List<Integer>> triangle, int level, int pos) {
        if (level < triangle.size()) {
            List<Integer> curr = triangle.get(level);
            int value = curr.get(pos);
            return value + Math.min( add(triangle, level + 1, pos), add(triangle, level + 1, pos + 1) );
        }
        else {
            return 0;
        }
    }
    // self written again, passed test
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        int[] sum = new int[triangle.get(triangle.size() - 1).size()];
        sum[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> level = triangle.get(i);
            for (int j = level.size() - 1; j >= 0; j--) {
                if (j == 0) {
                    sum[j] = sum[j] + level.get(j);
                }
                else if (j == level.size() - 1) {
                    sum[j] = sum[j - 1] + level.get(j);
                }
                else {
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
}
