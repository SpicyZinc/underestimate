/*
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

Example 2:
Input: [4,2,1]
Output: False
Explanation: You can't get a non-decreasing array by modify at most one element.

Note: The n belongs to [1, 10,000].

idea:
not much, a so weird problem
*/
class NondecreasingArray {
    public boolean checkPossibility(int[] a) {
        int modified = 0;
        int prev = a[0];
        for (int i = 1; i < a.length; i++) {
            if (prev > a[i] && modified == 0) {
            	modified++;
            } else if (prev > a[i] && modified >= 1) {
            	return false;
            }
            if (prev > a[i] && i - 2 >= 0 && a[i - 2] > a[i]) {
            	continue;
            }
            prev = a[i];
        }

        return true;
    }

    public boolean checkPossibility(int[] a) {
    	int modified = 0;
    	for (int i = 1; i < a.length; i++) {
    		if (a[i - 1] > a[i]) {
                if (modified >= 1) {
                    return false;
                } else {
                    modified++;
                }
    			// two modification cases:
    			// 1. lower a[i - 1]
    			// 2. raise a[i]
    			if (i - 2 < 0 || a[i - 2] <= a[i]) {
    				a[i - 1] = a[i];
    			} else {
    				a[i] = a[i - 1];
    			}
    		}
    	}

    	return true;
    }
}