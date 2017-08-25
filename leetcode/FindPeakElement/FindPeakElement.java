/*
A peak element is an element that is greater than its neighbors.
Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

Note:
Your solution should be in logarithmic complexity.

idea:
https://www.cnblogs.com/grandyang/p/4217175.html
typical binary search, a little variation


      |
	| |
  | | |
| | | |

good explanation
https://siddontang.gitbooks.io/leetcode-solution/content/array/find_peak_element.html
*/

public class FindPeakElement {
    public int findPeakElement(int[] num) {
    	int start = 0;
    	int end = num.length - 1;

    	while (start < end) {
    		int mid = start + (end - start) / 2;
    		if (num[mid] < num[mid + 1]) {
    			start = mid + 1;
    		}
    		else {
    			end = mid;
    		}
    	}

    	return end;
    }


    public int findPeakElement(int[] num) {
    	for (int i = 0; i < num.length - 1; i++) {
    		if (num[i] > num[i + 1]) {
    			return i;
    		}
    	}
    	return num.length - 1;
    }
}