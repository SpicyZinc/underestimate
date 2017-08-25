/*
Permutation Sequence
The set [1,2,3,бн,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):
    "123"
    "132"
    "213"
    "231"
    "312"
    "321"

Given n and k, return the kth permutation sequence.

idea:
1. 
	Exactly the same as N-Queens problem
	because N-Queens problem can be thought of as a "Permutation" problem
	each column array must have N elements, and at each position of the column array
	differen number, no need to consider the diagnal condition, only check column number

	so this problem is the same as N-Queens problem
	but cannot pass Judge Large, time limit exceeded

	Attention:
	Apply N-Queens solution to this permutation problem

	1. inside "for" loop recursion
	2. imagine a picture of serpentine, a column array, it picks up one elements from each row,
	when this column array is full (index == n), one possible solution (one permutation) appears

2. http://blog.csdn.net/linhuanmars/article/details/22028697
   http://blog.unieagle.net/2012/10/16/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Apermutation-sequence/
*/
import java.util.*;

public class PermutationSequence {	
	public static void main(String[] args) {
		new PermutationSequence();
	}
	public PermutationSequence() {
		String result = getPermutation(4, 2);
		System.out.println("2nd of permutation of 4 is " + result);
	}

	// code starts from here
	// timeout 
	public String getPermutation(int n, int k) {
        ArrayList<String> one = getPermutation(n);
        return one.get(k-1);
    }
	public ArrayList<String> getPermutation(int n) {
		ArrayList<String> ret = new ArrayList<String>();
		int[] solution = new int[n];
		compute(ret, solution, 0);
		return ret;
    }
	
	private void compute(ArrayList<String> ret, int[] solution, int index) {
		int n = solution.length;
		if (index == n) {			
			ret.add(convert(solution));
			return;
		}
		else {
			for (int i=1; i<=n; i++) {
				if (!isRepeat(solution, index, i)) {
					solution[index] = i;
					compute(ret, solution, index+1);
				}				
			}
		}		
	}
	
	private boolean isRepeat(int[] solution, int row, int col) {		
		for (int i=0; i<row; i++) {
			// check column
			if (solution[i] == col) {
				return true;
			}
		}
		return false;
	}
	
	private String convert(int[] solution) {
		int n = solution.length;
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<n; i++) {			
			sb.append(solution[i] + "");			
		}		
		return sb.toString();
	}

	// close to understanding 
	public String getPermutation(int n, int k) {
	    if (n <= 0) {
	        return "";
	    }
	    StringBuilder res = new StringBuilder();
	    int factorial = 1;
	    ArrayList<Integer> nums = new ArrayList<Integer>();
	    for (int i=2; i<n; i++) {
	        factorial *= i;
	    }
	    for (int i=1; i<=n; i++) {
	        nums.add(i);
	    }
	    k--;
	    int round = n-1;
	    while (round>=0) {
	        int index = k / factorial;
	        k %= factorial;
	        res.append(nums.get(index));
	        nums.remove(index);
	        if (round>0) {
	            factorial /= round;
	        }
	        round--;
	    }

	    return res.toString();
	}
	// not fully understand at all
	public String getPermutation(int n, int k) {
		char[] arr = new char[n];
        int pro = 1;
        for (int i = 0; i < n; i++) {
            arr[i] = (char)('1' + i);
            pro *= (i + 1);
        }
        k = k - 1;
        k %= pro;
        pro /= n; // pro = 1 * 2 * ... * (n-1)
        for (int i = 0; i < n-1; i++) {
            int selectI = k / pro;
            k = k % pro;
            pro /= (n - i - 1);
            char temp = arr[selectI + i];
            for (int j = selectI; j > 0; j--) {
                arr[i + j] = arr[i + j - 1];
            }
            arr[i] = temp;
        }
        return new String(arr, 0, n);        
    }
}