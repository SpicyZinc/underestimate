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

public class SearchInRotatedSortedArray  {
    public boolean search(int[] A, int target) {
        if (A.length == 0 || A == null) {
            return false;
        }
          
        int left = 0;  
        int right = A.length - 1;
          
        while (left <= right) {  
            int middle = left + (right - left) / 2;  

            if (A[middle] == target) {
                return true;
            }
            if (A[middle] < A[left]) {  
                if (target > A[middle] && target <= A[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                } 
            } else {  
                // case of A[middle] is a duplicate of left most element  
                if (A[middle] == A[left] && middle != left) {  
                    left++;  
                    continue;  
                }  
                  
                if (target < A[middle] && target >= A[left]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }  
        }  

        return false;    
    }

    // 07/08/2018 unified with I, 271/275 passed, note left should be advanced always
    public boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // happen to find target, return early
            if (nums[mid] == target) {
                return true;
            }

            // left part is sorted
            if (nums[mid] > nums[end]) {
                // case of A[mid] is a duplicate of left most element
                if (nums[mid] == nums[end] && mid != end) {  
                    mid++;  
                    continue;  
                }  

                if (nums[start] <= target && target <= nums[mid]) { // target in the sorted left part
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else { // right part is sorted
                if (nums[mid] <= target && target <= nums[end]) { // target in the sorted right part
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        
        return false;
    }
}