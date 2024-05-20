/*
Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:

answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
Note that the integers in the lists may be returned in any order.


Example 1:
Input: nums1 = [1,2,3], nums2 = [2,4,6]
Output: [[1,3],[4,6]]
Explanation:
For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].

Example 2:
Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
Output: [[3],[]]
Explanation:
For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
Every integer in nums2 is present in nums1. Therefore, answer[1] = [].

Constraints:
1 <= nums1.length, nums2.length <= 1000
-1000 <= nums1[i], nums2[i] <= 1000

idea:
hashset to find common
also result should not contain duplicates
*/
class FindTheDifferenceOfTwoArrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> hs = new HashSet<>();
        Set<Integer> common = new HashSet<>();

        for (int value : nums1) {
            hs.add(value);
        }

        for (int value : nums2) {
            if (hs.contains(value)) {
                common.add(value);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        for (int value : nums1) {
            if (!common.contains(value) && !one.contains(value)) {
                one.add(value);
            }
        }
        List<Integer> two = new ArrayList<>();
        for (int value : nums2) {
            if (!common.contains(value) && !two.contains(value)) {
                two.add(value);
            }
        }

        result.add(one);
        result.add(two);

        return result;
    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        for (int i : nums1) s1.add(i);
        Set<Integer> s2 = new HashSet<>();
        for (int i : nums2) s2.add(i);

        Set<Integer> common = new HashSet<>();
        for (int i : s1) {
            if (s2.contains(i)) {
                common.add(i);
            }
        }

        s1.removeAll(common);
        s2.removeAll(common);

        return Arrays.asList(new ArrayList<>(s1), new ArrayList<>(s2));
    }
}