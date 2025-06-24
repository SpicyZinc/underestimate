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
        Set<Integer> hs = new HashSet<Integer>();
        Set<Integer> result = new HashSet<Integer>();

        for (int num : nums1) {
            hs.add(num);
        }
        for (int num : nums2) {
            if (hs.contains(num)) {
                result.add(num);
            }
        }

        int i = 0;
        int[] common = new int[result.size()];
        for (int num : result) {
            common[i++] = num;
        }

        return common;
    }

    // binary search
    public int[] intersection(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int m = nums1.length;
        List<Integer> intersect = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            if (i > 0 && nums1[i] == nums1[i - 1]) {
                continue;
            }
            if (binarySearch(nums2, nums1[i])) {
                intersect.add(nums1[i]);
            }
        }

        int[] result = new int[intersect.size()];
        for (int i = 0; i < intersect.size(); i++) {
            result[i] = intersect.get(i);
        }

        return result;
    }

    public boolean binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return false;
    }
}
