/*
Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums.
If there are multiple answers, you may return any of them.


Example 1:
Input: nums = ["01","10"]
Output: "11"
Explanation: "11" does not appear in nums. "00" would also be correct.

Example 2:
Input: nums = ["00","01"]
Output: "11"
Explanation: "11" does not appear in nums. "10" would also be correct.

Example 3:
Input: nums = ["111","011","001"]
Output: "101"
Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.


Constraints:
n == nums.length
1 <= n <= 16
nums[i].length == n
nums[i] is either '0' or '1'.
All the strings of nums are unique.

idea:
my way is dfs() to generate all possible combinations 
第i个string 第i个char
是0就选1
是1就选0
*/

class FindUniqueBinaryString {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i].charAt(i) == '0' ? "1": "0");
        }

        return new String(sb);
    }

    // Tue Mar 26 01:36:58 2024
    public String findDifferentBinaryString(String[] nums) {
        Set<String> hs = new HashSet<>();
        for (String num : nums) {
            hs.add(num);
        }

        int size = nums.length;
        StringBuilder sb = new StringBuilder();

        List<String> result = new ArrayList<>();

        dfs(size, sb, result);

        for (String s : result) {
            if (!hs.contains(s)) {
                return s;
            }
        }

        return "";
    }

    public void dfs(int n, StringBuilder path, List<String> result) {
        if (path.length() == n) {
            result.add(path.toString());
            return;
        }

        for (int i = 0; i < 2; i++) {
            path.append(i);
            dfs(n, path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}