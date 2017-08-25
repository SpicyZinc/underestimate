/*
Given an array which can be formed into pascal triangle, find the max sum path and return the max sum.

idea: 
1. recursion
each node has 3 cases, only root, root + left, root + max(left, right)
2. math calculation
a(a+1) = 2 * n, n is the size of array
infer 2a = (int)( Math.sqrt(8 * n + 1) ) - 1;
start from the second last, next to last, penultimate row
recalculate the each element
return triangle[0]
*/

public class MaximumPath {
	public static void main(String[] args) {
		MaximumPath eg = new MaximumPath();
		int[] a = new int[] {
							55,
							94, 48,
							95, 30, 96,
							77, 71, 26, 67,
							97, 13, 76, 38, 45,
							7, 36, 79, 16, 37, 68,
							48, 7, 9, 18, 70, 26, 6,
							18, 72, 79, 46, 59, 79, 29, 90,
							20, 76, 87, 11, 32, 7, 7, 49, 18,
							27, 83, 58, 35, 71, 11, 25, 57, 29, 85,
							14, 64, 36, 96, 27, 11, 58, 56, 92, 18, 55,
							2, 90, 3, 60, 48, 49, 41, 46, 33, 36, 47, 23,
							92, 50, 48, 2, 36, 59, 42, 79, 72, 20, 82, 77, 42,
							56, 78, 38, 80, 39, 75, 2, 71, 66, 66, 1, 3, 55, 72,
							44, 25, 67, 84, 71, 67, 11, 61, 40, 57, 58, 89, 40, 56, 36,
							85, 32, 25, 85, 57, 48, 84, 35, 47, 62, 17, 1, 1, 99, 89, 52,
							6, 71, 28, 75, 94, 48, 37, 10, 23, 51, 6, 48, 53, 18, 74, 98, 15,
							27, 2, 92, 23, 8, 71, 76, 84, 15, 52, 92, 63, 81, 10, 44, 10, 69, 93
						};

		System.out.println(eg.maxPath(a));
		System.out.println(eg.pathMax(a));
	}

	// method 1
	public int maxPath(int[] a) {
		return add(a, 0, 1);
	}

	public int add(int[] a, int index, int step) {
		if (index + step >= a.length) {
			return a[index];
		}
		else if (index + step + 1 >= a.length && index + step < a.length) {
			return a[index] + a[index + step];
		}
		else {
			return a[index] + Math.max(add(a, index + step, step + 1), add(a, index + step + 1, step + 1));
		}
	}

	// method 2
	public int pathMax(int[] tri) {
		int len = tri.length;
	    int base = (int)(Math.sqrt(8 * len + 1) - 1) / 2;
	    int step = base - 1;
	    int index = 0;
	 
	    for (int i = len - base - 1; i >= 0; i--) {
	        tri[i] += Math.max(tri[i + step], tri[i + step + 1]);
	        if (++index == step) {
	            step--;
	            index = 0;
	        }
	    }

	    return tri[0];
	}
}