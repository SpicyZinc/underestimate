/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?
Would this affect the run-time complexity? How and why?
Write a function to determine if a given target is in the array.

idea: 
http://blog.csdn.net/u010500263/article/details/18471861 has the best explanation

to deal with this case that element at left, right and middle positions all are equal,
simply move the left pointer to the its next element

note: two equal signs
if (target > nums[mid] && target <= nums[right]) {
if (target >= nums[left] && target < nums[mid]) {

*/

public class SearchInRotatedSortedArrayII  {
    public boolean search(int[] A, int target) {
		if (A.length == 0 || A == null) {
			return false;
        }
          
        int left = 0;  
        int right = A.length-1;  
          
        while (left <= right) {  
            int middle = (left + right) / 2;  
            if (A[middle] == target) {
            	return true;
            }
            if (A[middle] < A[left]) {  
                if (target > A[middle] && target <= A[right]) {
                	left = middle + 1;
                }
                else {
                    right = middle - 1;
                } 
            }  
            else {  
                // case of A[middle] is a duplicate of left most element  
                if (A[middle] == A[left] && middle != left) {  
                    left++;  
                    continue;  
                }  
                  
                if (target < A[middle] && target >= A[left]) {
                	right= middle - 1;
                }
                else {
                    left = middle + 1;
                }
            }  
        }  

        return false;    
    }
}