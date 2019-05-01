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
	// Sun Apr 28 16:06:30 2019
	public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hm = new HashMap<>();
        
        for (String str : strs) {
            String unique = normalize(str);
            hm.computeIfAbsent(unique, (x -> new ArrayList<>())).add(str);
        }
        
        List<List<String>> result = new ArrayList<>();
        for (List<String> value : hm.values()) {
            result.add(value);
        }
        
        return result;
    }
    
    public String normalize(String s) {
        char[] letters = new char[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            letters[c]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > 0) {
                append(sb, (char) i, letters[i]);
            }
        }
        
        return sb.toString();
    }
    
    public void append(StringBuilder sb, char c, int times) {
        for (int i = 0; i < times; i++) {
            sb.append(c);
        }
    }
	// 02/07/2019
	public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hm = new HashMap<>();

        for (String str : strs) {
            String unique = normalize(str);
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
    
    public String normalize(String s) {
        char[] letters = new char[256];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            letters[c]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] > 0) {
                append(sb, (char) i, letters[i]);
            }
        }
        
        return sb.toString();
    }
    
    public void append(StringBuilder sb, char c, int times) {
        for (int i = 0; i < times; i++) {
            sb.append(c);
        }
    }

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