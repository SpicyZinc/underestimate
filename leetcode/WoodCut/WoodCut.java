/*
Given n pieces of wood with length L[i] (integer array).
Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length.
What is the longest length you can get from the n pieces of wood?
Given L & k, return the maximum length of the small pieces.

Notice
You couldn't cut wood into float length.
If you couldn't get >= k pieces, return 0.

Example
For L=[232, 124, 456], k=7, return 114.

Challenge
O(n log Len), where Len is the longest length of the wood.


idea:
https://www.kancloud.cn/kancloud/data-structure-and-algorithm-notes/72979
Binary Search

Thinking process:
Take the largest item
Priorities:
1. Have to get calculatedK >= givenK
2. Meanwhile, want to maximize the length of the small piece of wood
abandon the small pieces, as long as the max_small_pieces can allow calculatedK >= givenK.
Use binary search on the largest item:
1. if calculatedK < givenK: end = mid;
2. If calculated >= givenK, move start = mid as much as possible, which gives maximized small piece.

commented-out code also working
*/


public class WoodCut {
	public int woodCut(int[] L, int k) {
		if (L == null || L.length == 0 || k < 0) {
			return 0;
		}

		int maxLen = 0;
		for (int l : L) {
			maxLen = Math.max(maxLen, l);
		}

		int start = 0;
		int end = maxLen;

		// int max = 0;
		// while (start + 1 < end) {
		// 	int possibleLen = start + (end - start) / 2;
		// 	int count = getCount(L, possibleLen);
		// 	if (count < k) {
		// 		end = possibleLen;
		// 	} else {
		// 		start = possibleLen;
		// 		max = possibleLen;
		// 	}
		// }
		// return max;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			int count = getCount(L, mid);
			if (count >= k) {
				start = mid;
			} else {
				end = mid;
			}
		}

		return start;
	}

	// whether it cut with length x and get more than k pieces
	private int getCount(int[] L, int len) {
		int sum = 0;
		for (int l : L) {
			sum += l / len;
		}
		return sum;
	}
}