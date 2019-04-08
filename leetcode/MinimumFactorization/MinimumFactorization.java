/*
Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.
If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1
Input:
48 
Output:
68

Example 2
Input:
15
Output:
35

idea:
https://discuss.leetcode.com/topic/92854/java-solution-result-array/3
https://www.cnblogs.com/grandyang/p/7076026.html

rethink, based on the description, the smallest,
so the biggest factor should be in the last position
start from 9 through 2, divides input a

考虑成数字的 concatenation 从小到大
*/

import java.util.*;

public class MinimumFactorization {
	public static void main(String[] args) {
		MinimumFactorization eg = new MinimumFactorization();
		int num1 = 48;
		int num2 = 15;

		int smallest1 = eg.smallestFactorization(num1);
		int smallest2 = eg.smallestFactorization(num2);

		System.out.println(smallest1 + " " + smallest2);
	}

	// 03/04/2019
	public int smallestFactorization(int a) {
        if (a < 10) {
        	return a;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 9; i >= 2; i--) {
        	while (a % i == 0) {
        		a /= i;
        		sb.append(i);
        	}
        }

        // a != 1, no one in [2 - 9] can divide a, no answer
        if (a != 1) {
        	return 0;
        }
        
        long result = Long.parseLong(sb.reverse().toString());
        
        if (result > Integer.MAX_VALUE) {
            return 0;
        }

        return (int) result;
    }

    public int smallestFactorization(int a) {
        if (a < 10) {
        	return a;
        }
	
		List<Integer> result = new ArrayList<Integer>();
        for (int i = 9; i > 1; i--) {
        	while (a % i == 0) {
        		result.add(i);
        		a /= i;
        	}
        }
        // can divide exactly, should be 1; if not, return 0
        if (a != 1) {
        	return 0;
        }

        long smallest = 0;
        for (int i = result.size() - 1; i >= 0; i--) {
        	smallest = smallest * 10 + result.get(i);
        	if (smallest > Integer.MAX_VALUE) {
        		return 0;
        	}
        }

        return (int) smallest;
    }
}