/*

Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers.
Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: you may assume that n is not less than 2.

Hint:

There is a simple O(n) solution to this problem.
You may check the breaking results of n ranging from 7 to 10 to discover the regularities.

idea:
7 = 2 + 2 + 3
8 = 3 + 3 + 2
9 = 3 + 3 + 3
10 = 3 + 3 + 3 + 1
11 = 3 + 3 + 3 + 2

1. no more than three 2s (< 3 2s) is the regularity
The optimal product should contain less than three 2, should be as many 3 as possible
2. dynamic programming
*/

public class IntegerBreak {
    public int integerBreak(int n) {
        if (n == 2) {
        	return 1;
        }
        if (n == 3) {
        	return 2;
        }
        if (n == 4) {
        	return 4;
        }
        int product = 1;
        while (n > 4) {
        	product *= 3;
        	n -= 3;
        }
        product *= n;

        return product;
    }
}


public int integerBreak(int n) {
    if (n <= 2) {
        return 1;
    }

    int[] maxArr = new int[n + 1];
    /** For a number i: write i as a sum of integers, then take the product of those integers.
    maxArr[i] = maximum of all the possible products */

    maxArr[1] = 0;
    maxArr[2] = 1;

    for (int i=3; i<=n; i++) {
        for (int j=1; j<i; j++) {
            /** Try to write i as: i = j + S where S=i-j corresponds to either one number or a sum of two or more numbers

            Assuming that j+S corresponds to the optimal solution for maxArr[i], we have two cases:
            (1) i is the sum of two numbers, i.e. S=i-j is one number, and so maxArr[i]=j*(i-j)
            (2) i is the sum of at least three numbers, i.e. S=i-j is a sum of at least 2 numbers,
            and so the product of the numbers in this sum for S is maxArr[i-j]
            (=maximum product after breaking up i-j into a sum of at least two integers):
            maxArr[i] = j*maxArr[i-j]
            */
            maxArr[i] = Math.max(maxArr[i], Math.max(j*(i-j), j*maxArr[i-j]));
        }
    }

    return maxArr[n];
}