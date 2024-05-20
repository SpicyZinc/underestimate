/*
You are given a string s and an integer array indices of the same length. The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
Return the shuffled string.

Example 1:
https://assets.leetcode.com/uploads/2020/07/09/q1.jpg
Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
Output: "leetcode"
Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.

Example 2:
Input: s = "abc", indices = [0,1,2]
Output: "abc"
Explanation: After shuffling, each character remains in its position.


Constraints:
s.length == indices.length == n
1 <= n <= 100
s consists of only lowercase English letters.
0 <= indices[i] < n
All values of indices are unique.

idea:
direct method
*/

// Sun Mar  5 00:57:34 2023
class ShuffleString {
    // self
    public String restoreString(String s, int[] indices) {
        StringBuilder sb = new StringBuilder();
        
        char[] chars = s.toCharArray();

        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < indices.length; i++) {
            hm.put(indices[i], i);
        }

        Arrays.sort(indices);

        for (int index : indices) {
            int idx = hm.get(index);
            sb.append(chars[idx]);
        }

        return sb.toString();
    }
    // nice way
    public String restoreString(String s, int[] indices) {
        char[] shuffledArray = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            shuffledArray[indices[i]] = s.charAt(i);
        }
        return String.valueOf(shuffledArray);
    }
}
