/*
Given two integers n and k, you need to construct a list which contains n different positive integers ranging from 1 to n and obeys the following requirement:
Suppose this list is [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] has exactly k distinct integers.

If there are multiple answers, print any of them.

Example 1:
Input: n = 3, k = 1
Output: [1, 2, 3]
Explanation: The [1, 2, 3] has three different positive integers ranging from 1 to 3, and the [1, 1] has exactly 1 distinct integer: 1.

Example 2:
Input: n = 3, k = 2
Output: [1, 3, 2]
Explanation: The [1, 3, 2] has three different positive integers ranging from 1 to 3, and the [2, 1] has exactly 2 distinct integers: 1 and 2.
Note:
The n and k are in the range 1 <= k < n <= 104.

idea:
https://www.cnblogs.com/grandyang/p/7577878.html

首先这样的排列一定会存在
k = n - 1 at most
找出规律
take k = [1, 7] as an example
k=6: 1 7 2 6 3 5 | 4
k=5: 1 7 2 6 3 | 4 5
k=4: 1 7 2 6 | 5 4 3
k=3: 1 7 2 | 3 4 5 6
k=2: 1 7 | 6 5 4 3 2
k=1: 1 | 2 3 4 5 6 7
*/

class BeautifulArrangement {
	public int[] constructArray(int n, int k) {
		int[] result = new int[n];
		
		int i = 1;
		int j = n;
		int idx = 0;

		while (i <= j) {
			int val = i;
			if (k > 1) {
				val = k % 2 == 1 ? i++ : j--;
				k--;
			} else {
				i++;
			}
			result[idx] = val;
			idx++;
		}

		return result;
	}
}