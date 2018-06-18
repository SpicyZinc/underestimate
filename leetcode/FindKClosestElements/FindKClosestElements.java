/*
Given a sorted array, two integers k and x, find the k closest elements to x in the array.
The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]

Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 10^4
Absolute value of elements in the array and x will not exceed 10^4

idea:
sort the array based on the distance to the target and grab the top k elements
List.subList(0, k); k is not inclusive
*/

public class FindKClosestElements {
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> nums = new ArrayList<Integer>();
        for (int i : arr) {
            nums.add((Integer) i);
        }
        Collections.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a == b ? 0 : Math.abs(a - x) - Math.abs(b - x);
            }
		});
		nums = nums.subList(0, k);
        Collections.sort(nums);
		return nums;
    }
    // not sure this works or not
	public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
		int min = arr.get(0);
		int max = arr.get(arr.size() - 1);
		List<Integer> result = new ArrayList<Integer>();
		if (x < min) {
			for (int i = 0; i < k; i++) {
				result.add(arr.get(i));
			}
		} else if (x >= max) {
			for (int i = arr.size() - k; i < arr.size(); i++) {
				result.add(arr.get(i));
			}
		} else {
			int pos = FindInsertionPosition(arr, x);
			int start = 0;
			while (start + k < pos) {
				start++;
			}

			for (int i = start; i < start + k; i++) {
				result.add(arr.get(i));
			}
		}
		return result;
    }

    public int FindInsertionPosition(List<Integer> arr, int T) {
        int start = 0;
		int end = arr.size() - 1;
		
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (arr.get(mid) > T) {
				end = mid;
			} else {
				start = mid + 1;
			}
        }
        if (arr.get(start) > T) {
			return start;
        } else {
			return start + 1;
        } 
	}
}