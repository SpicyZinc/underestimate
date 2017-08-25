/*
Given a positive integer n and you can do operations as follow:

If n is even, replace n with n/2.
If n is odd, you can replace n with either n + 1 or n - 1.
What is the minimum number of replacements needed for n to become 1?

Example 1:
Input: 8
Output: 3
Explanation:
8 -> 4 -> 2 -> 1

Example 2:
Input: 7
Output: 4

Explanation:
7 -> 8 -> 4 -> 2 -> 1
or
7 -> 6 -> 3 -> 2 -> 1

idea:
1. direct recursion, pay attention to big number overflow
2. bit manipulation http://bookshadow.com/weblog/2016/09/11/leetcode-integer-replacement/
*/

public class IntegerReplacement {
    // why this version overflow
    public int integerReplacement(int n) {
        if (n <= 2) {
            return n - 1;
        }
        return (int)longConvert(n);
    }

    public long longConvert(long n) {
        if (n % 2 == 0) {
            return longConvert(n / 2) + 1;
        }
        else {
            return Math.min(longConvert(n - 1), longConvert(n + 1)) + 1;
        }
    }
    // OJ passed
    public int integerReplacement(int n) {
        return (int)longConvert(n);
    }

    public long longConvert(long n) {
        if (n <= 2) {
            return n - 1;
        }
        if (n % 2 == 0) {
            return longConvert(n / 2) + 1;
        }
        else {
            return Math.min(longConvert(n - 1), longConvert(n + 1)) + 1;
        }
    }
}
