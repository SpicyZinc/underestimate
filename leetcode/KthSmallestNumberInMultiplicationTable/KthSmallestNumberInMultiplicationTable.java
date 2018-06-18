/*
Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?
Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output: 
Explanation: 
The Multiplication Table:
1   2   3
2   4   6
3   6   9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).
Example 2:
Input: m = 2, n = 3, k = 6
Output: 
Explanation: 
The Multiplication Table:
1   2   3
2   4   6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]


idea:
1. direct, Memory Limit Exceeded
2. same as KthSmallestElementInSortedMatrix, MLE
3. 
*/


class KthSmallestNumberInMultiplicationTable {
	// method 1
	public int findKthNumber(int m, int n, int k) {
        int[] nums = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[i * n + j] = (i + 1) * (j + 1);
            }
        }
        Arrays.sort(nums);

        return nums[k - 1];
    }
    // method 2
    public int findKthNumber(int m, int n, int k) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        
        return kthSmallest(matrix, k);
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int min = matrix[0][0];
        int max = matrix[n - 1][matrix[0].length - 1];

        while (min < max) {
        	int midPosition = 0;
            int midVal = min + (max - min) / 2;
        	for (int i = 0; i < n; i++) {
        		midPosition += binarySearch(matrix[i], midVal);
        	}
        	if (midPosition < k) {
        		min = midVal + 1;
        	} else {
        		max = midVal;
        	}
        }

        return min;
    }
    // slightly different from search insertion position
    public int binarySearch(int[] nums, int target) {
    	int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // method 3
    public int findKthNumber(int m, int n, int k) {
    	int low = 1;
    	int high = m * n;

    	while (low <= high) {
    		int mid = low + (high - low) / 2;
    		int cnt = count(m, n, mid);
    		if (cnt < k) {
    			low = mid + 1;
    		} else {
    			high = mid - 1;
    		}
    	}
    	return low;
    }

    public int count(int m, int n, int val) {
    	int count = 0;
    	for (int i = 0; i < m; i++) {
    		int rowCnt = Math.min(val / (i + 1), n);
    		count += rowCnt;
    	}
    	return count;
    }
}
