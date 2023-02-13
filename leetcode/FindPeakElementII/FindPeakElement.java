/*
There is an integer matrix which has the following features:

The numbers in adjacent positions are different.
The matrix has n rows and m columns.
For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].

We define a position P is a peek if:
A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
Find a peak element in this matrix. Return the index of the peak.
The matrix may contains multiple peeks, find any of them.

Example
Given a matrix:
[
  [1 ,2 ,3 ,6 ,5],
  [16,41,23,22,6],
  [15,17,24,21,7],
  [14,18,19,20,10],
  [13,14,11,10,9]
]
return index of 41 (which is [1,1]) or index of 24 (which is [2,2])

Challenge
Solve it in O(n+m) time.
If you come up with an algorithm that you thought it is O(n log m) or O(m log n),
can you prove it is actually O(n+m) or propose a similar but O(n+m) algorithm?

idea:
two binary on column and row
but for column I borrow Find Peak Element I

https://zhengyang2015.gitbooks.io/lintcode/find_peak_element_ii_390.html
*/

public class FindPeakElement {
    /*
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        List<Integer> result = new ArrayList<>();

        int m = A.length;
        int n = A[0].length;

        // why 1 and m - 2, because 1st and last line cannot be peak
        int left = 1;
        int right = m - 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int col = findPeak(A[mid]);
            if (A[mid][col] < A[mid + 1][col]) {
                left = mid + 1;
            } else if (A[mid][col] < A[mid - 1][col]) {
                right = mid - 1;
            } else {
                result.add(mid);
                result.add(col);
                break;
            }
        }

        return result;
    }

    public int findPeak(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }

        return nums.length - 1;
    }
}