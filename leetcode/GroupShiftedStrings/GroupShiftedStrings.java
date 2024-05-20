/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]

Note: For the return value, each inner list's elements must follow the lexicographic order.

idea:
https://www.cnblogs.com/grandyang/p/5204770.html
key is to write get the solo string as key for a shifted group
how to sort?
abc xyz 规律就是
第一个char是base
校正 去燥
*/

import java.util.*;

class GroupShiftedStrings {
    public static void main(String[] args) {
        GroupShiftedStrings eg = new GroupShiftedStrings();
        String[] strings = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        List<List<String>> result = eg.groupStrings(strings);

        for (List<String> list : result) {
            System.out.println(list.toString());
        }
    }
    // Mon Apr 22 01:34:15 2024
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> hm = new HashMap<>();

        for (String s : strings) {
            String key = getSoloString(s);
            hm.computeIfAbsent(key, x -> new ArrayList<>()).add(s);
        }

        List<List<String>> result = new ArrayList<>();
        result.addAll(hm.values());

        return result;
    }

    public String getSoloString(String s) {
        char[] chars = s.toCharArray();
        int base = chars[0];
        for (int i = 0; i < chars.length; i++) {
            int shift = chars[i] - base;
            if (shift < 0) {
                chars[i] = (char) (shift + 26);
            } else {
                chars[i] = (char) shift;
            }
        }

        return new String(chars);
    }

    // Wed Apr 10 02:33:23 2024
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> hm = new HashMap<>();

        for (String s : strings) {
            String key = getSoloString(s);
            hm.computeIfAbsent(key, x -> new ArrayList<>()).add(s);
        }

        List<List<String>> result = new ArrayList<>();
        result.addAll(hm.values());

        return result;
    }

    public String getSoloString(String s) {
        char[] arr = s.toCharArray();
        if (arr.length > 0) {
            int diff = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] - diff < 0) {
                    arr[i] = (char) (arr[i] - diff + 26);
                } else {
                    arr[i] = (char) (arr[i] - diff);
                }
            }
        }

        return new String(arr);
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> hm = new HashMap<>();
        for (String string : strings) {
            String key = getSoloString(string);
            if (hm.containsKey(key)) {
                hm.get(key).add(string);
            } else {
                List<String> group = new ArrayList<String>();
                group.add(string);
                hm.put(key, group);
            }
        }

        List<List<String>> result = new ArrayList<>();
        result.addAll(hm.values());

        return result;
    }

    public String getSoloString(String s) {
        char[] arr = s.toCharArray();
        if (arr.length > 0) {
            int diff = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] - diff < 0) {
                    arr[i] = (char) (arr[i] - diff + 26);
                } else {
                    arr[i] = (char) (arr[i] - diff);
                }
            }
        }

        return new String(arr);
    }
}