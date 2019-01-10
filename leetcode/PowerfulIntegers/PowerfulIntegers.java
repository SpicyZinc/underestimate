/*
Given two non-negative integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.
Return a list of all powerful integers that have value less than or equal to bound.
You may return the answer in any order.  In your answer, each value should occur at most once.

Example 1:
Input: x = 2, y = 3, bound = 10
Output: [2,3,4,5,7,9,10]
Explanation: 
2 = 2^0 + 3^0
3 = 2^1 + 3^0
4 = 2^0 + 3^1
5 = 2^1 + 3^1
7 = 2^2 + 3^1
9 = 2^3 + 3^0
10 = 2^0 + 3^2

Example 2:
Input: x = 3, y = 5, bound = 15
Output: [2,4,6,8,10,14]
 

Note:
1 <= x <= 100
1 <= y <= 100
0 <= bound <= 10^6

idea:
see comments
*/

class PowerfulIntegers {
	public List<Integer> powerfulIntegers(int x, int y, int bound) {
		Set<Integer> hs = new HashSet<>();
		// i, j respectively refer to power result of x 
		// x or y == 0 then power of them is 1
		// 注意 break的两个地方 否则就陷入循环出不来了
		for (int i = x == 0 ? 0 : 1; i <= bound; i = x == 0 ? i + 1 : i * x) {
			for (int j = y == 0 ? 0 : 1; i + j <= bound; j = y == 0 ? j + 1 : j * y) {
				hs.add(i + j);
				if (y == 1 || j == 1 && y == 0) {
					break;
				}
			}
			if (x == 1 || i == 1 && x == 0) {
				break;
			}
		}
		
		return new ArrayList<Integer>(hs);
	}
}