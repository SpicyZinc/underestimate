/* 
By now, you are given a secret signature consisting of character 'D' and 'I'.
'D' represents a decreasing relationship between two numbers,
'I' represents an increasing relationship between two numbers.
And our secret signature was constructed by a special integer array,
which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1).
For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2],
but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string
that can't represent the "DI" secret signature.

On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n]
could refer to the given secret signature in the input.

Example 1:
Input: "I"
Output: [1,2]
Explanation: [1,2] is the only legal initial special string can construct secret signature "I",
where the number 1 and 2 construct an increasing relationship.
 

Example 2:
Input: "DI"
Output: [2,1,3]
Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI", 
but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
 

Note:
The input string will only contain the character 'D' and 'I'.
The length of input string is a positive integer and will not exceed 10,000

idea: 
1 2 3 4 5 6 7
3 2 1 4 6 5 7
 D D I I D I

when s.charAt(i) == 'I'
i (i - 1 + 1) of D appeared before already
所以有 (i + 1) 数
要倒序地填上 (i + 1) 填到当前结果的长度 + 1


在I前面（或者整个字符串的末尾之前）出现了n次D
因为是插空嘛 所以 n + 1
在ret中填入n+1个逆序序列
这n+1个逆序序列的范围由ret当前的size确定
已经填好的不动
例如
当ret的size为0
就填入n + 1, n, ...1
当ret的size为2
就填入n + 3, n+2,...3
O(n)

or 先按顺序放数字 然后找到连续D reverse()
*/

import java.util.*;

class FindPermutation {
	public static void main(String[] args) {
		FindPermutation eg = new FindPermutation();
		String s = "DDIIDI";
		System.out.println(eg.findPermutation(s).toString());
	}
	// method 1
	public List<Integer> findPermutation(String s) {
		int size = s.length();
		List<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i <= size; i++) {
			if (i == size || s.charAt(i) == 'I') {
				int lenSoFar = result.size();
				for (int j = i + 1; j > lenSoFar; j--) {
					result.add(j);
				}
			}
		}

		return result;
	}
	// method 2
	public List<Integer> findPermutation(String s) {
		int size = s.length();
		List<Integer> result = new ArrayList<Integer>();

		for (int i = 0; i <= size; i++) {
			result.add(i + 1);
		}

		for (int i = 0; i < size; i++) {
			char c = s.charAt(i);
			if (c == 'D') {
				int start = i;
				while (s.charAt(i) == 'D' && i < size) {
					i++;
				}
				reverse(result, start, i);
				i--;
			}
		}
		
		return result;
	}

	private void reverse(List<Integer> nums, int i, int j) {
		int len = j - i;
		while (i < j) {
			int temp = nums.get(i);
			nums.set(i, nums.get(j));
			nums.set(j, temp);
			i++;
			j--;
		}		
	}
}