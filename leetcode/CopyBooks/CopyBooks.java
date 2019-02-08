/*
Given n books and the ith book has A[i] pages. You are given k people to copy the n books.
n books list in a row and each person can claim a continuous range of the n books.

For example one copier can copy the books from ith to jth continuously,
but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book.
What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Example
Given array A = [3,2,4], k = 2.
Return 5
(First person spends 5 minutes to copy book 1 and book 2
and second person spends 4 minutes to copy book 3.)

idea:
binary search on answer
二分答案 估计这个 估计那个
*/

public class CopyBooks {
	public int getMaxTime(int[] pages) {
		int maxTime = 0;
		for (int bookPage : pages) {
			maxTime += bookPage;
		}
		return maxTime;
	}

	public int copyBooks(int[] pages, int k) {
		int l = 0;
		int r = getMaxTime(pages);

		while (l <= r) {
			int mid = l + (r - l) / 2;

			if (canCopyWithinK(mid, pages, k)) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}

		return l;
	}

	private boolean canCopyWithinK(int total, int[] page, int k) {
		// 至少有一个人copy, 所以count从1开始
		int personCnt = 1;
		int sum = 0;

		for (int i = 0; i < page.length;) {
			if (sum + page[i] <= total) {
				sum += page[i++];
			} else if (page[i] <= total) {
				sum = 0;
				personCnt++;
			} else {
				return false;
			}
		}

		return personCnt <= k;
	}
}