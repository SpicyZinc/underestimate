/*
Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Examples:
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0

idea:
typical binary search except that return l (left or start)
The benefit of this binary search is 
when iteration is over, if target was not found,
l must be greater than element index, and the target > this element
r must be less than element index, and the target < this element
*/

class  SearchForInsertPosition {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int l = 0;
        int r = A.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (A[mid] == target) return mid;
            if (A[mid] < target) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }

        return l;
    }

    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int l = 0;
        int r = A.length;
        while (l < r) {
        	int mid = l + (r - l) / 2;
        	if (A[mid] < target) {
        		l = mid + 1;
        	}
        	else {
        		r = mid;
        	}
        }
        return l;
    }
}