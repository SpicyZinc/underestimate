/*
Nearly every one have used the


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
