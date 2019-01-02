/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:
Input: low = "50", high = "100"
Output: 3 
Explanation: 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.

idea:
https://www.cnblogs.com/grandyang/p/5203228.html

以最小 strobogrammatic number 为基础 加上 0 1 8 9 6 不断的dfs
increase count at base case 
*/

class StrobogrammaticNumber {
	int count = 0;

	public int strobogrammaticInRange(String low, String high) {

		for (int i = low.length(); i <= high.length(); i++) {
			dfs(low, high, "", i);
			dfs(low, high, "0", i);
			dfs(low, high, "1", i);
			dfs(low, high, "8", i);
		}

		return count;
	}

	public void dfs(String low, String high, String path, int len) {
		// recursion base
		if (path.length() >= len) {
			if (path.length() > len || (len > 1 && path.charAt(0) == '0')) {
				return;
			}
			if (low.length() == len && path.compareTo(low) < 0 || high.length() == len && path.compareTo(high) > 0) {
				return;
			}
			count++;
		}

		dfs(low, high, "0" + path + "0", len);
		dfs(low, high, "1" + path + "1", len);
		dfs(low, high, "6" + path + "9", len);
		dfs(low, high, "8" + path + "8", len);
		dfs(low, high, "9" + path + "6", len);
	}
}