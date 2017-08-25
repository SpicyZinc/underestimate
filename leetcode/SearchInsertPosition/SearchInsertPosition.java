/*
Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.

Assume no duplicates in the array.

[1,3,5,6], 5 -> 2
[1,3,5,6], 2 -> 1
[1,3,5,6], 7 -> 4
[1,3,5,6], 0 -> 0 

idea:
http://heartfire.cc/wp/search-insert-position/
if a sorted array, first think of binary search

Assume no duplicates in the array.
return the index of the biggest element smaller than or equal to x
if equal to target, also the index before the target, not target's index (need to take care of this case)

Difference 
end = mid

end = mid - 1; corresponds to while(start <= end) 
*/

public class SearchInsertPosition {
	public static void main(String[] args) {
		SearchInsertPosition eg = new SearchInsertPosition();
		int[] a = {1,3,5,5,5,6};
		int target = Integer.parseInt(args[0]);
		System.out.println("target insertion position is " + eg.searchInsert(a, target));
	}
	// passed OJ
    public int searchInsert(int[] A, int target) {
		int position = bs(A, 0, A.length-1, target);
		return position + 1;
    }
	// return the position of biggest number which is less than target
	public int bs(int[] A, int start, int end, int target) {
		int ret = -1;
		int mid = (start + end) / 2;
		if (target <= A[start]) {
			return ret;
		}
		while (start <= end) {
			mid = (start + end) / 2;
			if (A[mid] > target) {
				end = mid -1;
			}
			else if (A[mid] < target) {
				ret = mid;
				start = mid + 1;
			}
			// in order to handle the case if target is in the array
			else {
				ret = mid - 1;
				start = mid + 1;
			}
		}
		
		return ret;
	}
	// return the index of smallest number greater than target
	// return directly, no need to add 1
	public int FindInsertionPosition(int[] A, int T) {
        int start = 0;
		int end = A.length - 1;
		
		while (start < end) {
			int mid = (start + end) / 2;
			if (A[mid] > T) {
				end = mid;
				// end = mid - 1; corresponds to while (start <= end) 
			}
			else {
				start = mid + 1;
			}
        }
        if (A[start] > T) {
			return start;
        }
        else {
			return start + 1;
        } 
	}
}