/*
The set [1,2,3,...,n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
"123"
"132"
"213"
"231"
"312"
"321"

Given n and k, return the kth permutation sequence.

Note:
Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.

Example 1:
Input: n = 3, k = 3
Output: "213"

Example 2:
Input: n = 4, k = 9
Output: "2314"

idea:
1. directly get all permutations, then get kth 
2. 
对于n个数可以有n!种排列; 那么n-1个数就有(n-1)!种排列.
那么对于n位数来说, 如果除去最高位不看, 后面的n-1位就有 (n-1)!种排列.
所以, 还是对于n位数来说, 每一个不同的最高位数, 后面可以拼接(n-1)!种排列.
所以你就可以看成是按照每组(n-1)!个这样分组. 
利用 k/(n-1)! 可以取得最高位在数列中的index.这样第k个排列的最高位就能从数列中的index位取得, 此时还要把这个数从数列中删除.
然后, 新的k就可以有k%(n-1)!获得.循环n次即可.
同时, 为了可以跟数组坐标对其, 令k先--.

http://blog.csdn.net/linhuanmars/article/details/22028697
https://fisherlei.blogspot.com/2013/04/leetcode-permutation-sequence-solution.html
*/
import java.util.*;

public class PermutationSequence {	
	public static void main(String[] args) {
		PermutationSequence eg = new PermutationSequence();
		String result = eg.getPermutation(4, 2);
		System.out.println("2nd of permutation of 4 is " + result);
	}

	// TLE
	public String getPermutation(int n, int k) {
		List<String> permutations = new ArrayList<>();
		dfs(n, new ArrayList<Integer>(), permutations);

		return permutations.get(k - 1);
	}

	private void dfs(int n, List<Integer> path, List<String> permutations) {
		if (path.size() == n) {
			permutations.add(convert(path));
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (path.contains(i)) {
				continue;
			}
			path.add(i);
			dfs(n, path, permutations);
			path.remove(path.size() - 1);
		}
	}

	private String convert(List<Integer> path) {
		String s = "";
		for (int num : path) {
			s += num;
		}

		return s;
	}

	// need to go back
	public String getPermutation(int n, int k) {
	    if (n <= 0) {
	        return "";
	    }

	    StringBuilder sb = new StringBuilder();

	    int product = 1;
	    for (int i = 2; i <= n - 1; i++) {
	        product *= i;
	    }

	    List<Integer> nums = new ArrayList<Integer>();
	    for (int i = 1; i <= n; i++) {
	        nums.add(i);
	    }

	    k--;
	    int round = n - 1;
	    while (round >= 0) {
	        int index = k / product;
	        k %= product;
	        sb.append(nums.get(index));
	        nums.remove(index);

	        if (round > 0) {
	            product /= round;
	        }

	        round--;
	    }

	    return sb.toString();
	}
}