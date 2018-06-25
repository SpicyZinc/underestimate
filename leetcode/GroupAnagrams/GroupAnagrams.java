/*
Given an array of strings, group anagrams together.

Example:
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:
All inputs will be in lowercase.
The order of your output does not matter.

idea:
hashmap, key is alphabetically increase string

*/
public class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hm = new HashMap<>();
        for (String str : strs) {
            String unique = organize(str);
            if (hm.containsKey(unique)) {
                hm.get(unique).add(str);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(str);
                hm.put(unique, list);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (List<String> value : hm.values()) {
            result.add(value);
        }
        
        return result;
    }
    
    private String organize(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}