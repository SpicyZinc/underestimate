/*

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.


idea:
1. HashSet
2. sort the arrays, skip duplicates, binary search
*/


public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
    	HashSet<Integer> hs = new HashSet<Integer>();
    	HashSet<Integer> res = new HashSet<Integer>();

        for (int i : nums1) {
        	hs.add(i);
        }
        for (int i : nums2) {
        	if (hs.contains(i)) {
        		res.add(i);
        	}
        }

        int i = 0;
        int[] result = new int[res.size()];
        for (int num : res) {
        	result[i++] = num;
        }
        return result;
    }

    // binary search
    // public int[] intersect(int[] nums1, int[] nums2) {
    //     Arrays.sort(nums1);
    //     Arrays.sort(nums2);
    //     int l1 = nums1.length;
    //     List<Integer> intersect = new ArrayList<Integer>();

    //     for (int i=0; i<l1; i++) {
    //     	if (i > 0 && nums1[i] == nums1[i-1]) {
    //     		continue;
    //     	}
    //         if (binarySearch(nums2,nums1[i])) {
    //             intersect.add(nums1[i]);
    //         } 
    //     }

    //     int[] intersect_arr = new int[intersect.size()];
    //     for (int i=0; i<intersect.size(); i++) {
    //         intersect_arr[i] = intersect.get(i).intValue();
    //     }

    //     return intersect_arr;
    // }

    // public boolean binarySearch(int[] nums, int target) {
    // 	int start = 0;
    // 	int end = nums.length - 1;
    // 	int mid;
    // 	while (start <= end) {
    // 		mid = (start + end) / 2;
    // 		if (nums[mid] == target) {
    // 			return true;
    // 		}
    // 		else if (nums[mid] > target) {
    // 			end = mid - 1;
    // 		}
    // 		else {
    // 			start = mid + 1;
    // 		}
    // 	}
    // 	return false;
    // }
}