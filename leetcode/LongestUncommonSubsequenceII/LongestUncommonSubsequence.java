/*
Given a list of strings, you need to find the longest uncommon subsequence among them.
The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.
A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements.
Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence.
If the longest uncommon subsequence doesn't exist, return -1.

Example 1:
Input: "aba", "cdc", "eae"
Output: 3

Note:
All the given strings' lengths will not exceed 10.
The length of the given list will be in the range of [2, 50].

idea:
Simply maintain a map of all subsequence frequencies and get the subsequence with frequency 1 that has longest length.
frequency of 1 means this is uncommon subsequence
this solution is not optimal
*/
public class LongestUncommonSubsequence {
    public int findLUSlength(String[] strs) {
        Map<String, Integer> subseqFreq = new HashMap<String, Integer>();
        for (String s: strs) {
            for (String subSeq : getSubseqs(s)) {
                subseqFreq.put(subSeq, subseqFreq.getOrDefault(subSeq, 0) + 1);
            }
        }
        int longest = -1;
        for (Map.Entry<String, Integer> entry : subseqFreq.entrySet()) {
            if (entry.getValue() == 1) {
                longest = Math.max(longest, entry.getKey().length());
            }
        }
        return longest;
    }
    
    public Set<String> getSubseqs(String s) {
        Set<String> res = new HashSet<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        Set<String> subRes = getSubseqs(s.substring(1));
        for (String seq : subRes) {
            res.add(s.charAt(0) + seq);
        }
        res.addAll(subRes);
        return res;
    }
}