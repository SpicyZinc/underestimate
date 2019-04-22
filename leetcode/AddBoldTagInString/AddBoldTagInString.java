/*
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict.
If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:
Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"

Example 2:
Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"

Note:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].

idea:
borrow idea from merge intervals
to merge intervals, either extend current interval or create new interval

note: interval's start is where exactly the word starts
end is one step more then where word ends
*/

import java.util.*;

public class AddBoldTagInString {
    public static void main(String[] args) {
        AddBoldTagInString eg = new AddBoldTagInString();
        // String s = "abcxyz123";
        String s = "aaabbcc";
        // String[] dict = {"abc","123"};
        String[] dict = {"aaa","aab","bc"};
        String result = eg.addBoldTag(s, dict);

        System.out.println(result);
    }

    public String addBoldTag(String s, String[] dict) {
        if (dict.length == 0) {
            return s;
        }
        
        List<Interval> intervals = new ArrayList<Interval>();

        for (String word : dict) {
            for (int i = 0; i < s.length(); i++) {
                if (s.startsWith(word, i)) {
                    intervals.add(new Interval(i, i + word.length()));
                }
            }
            
            // int pos = s.indexOf(word);
            // if (pos != -1) {
            //     intervals.add(new Interval(pos, pos + word.length()));
            // }
        }
        
        List<Interval> merged = merge(intervals);
        
        StringBuilder sb = new StringBuilder();
        int last = 0;
        for (Interval interval : merged) {
            int start = interval.start;
            int end = interval.end;
            // append str not in dict
            sb.append(s.substring(last, start));
            // append str in dict, wrap it in <b></b>
            sb.append("<b>");
            sb.append(s.substring(start, end));
            sb.append("</b>");
            last = end;
        }
        sb.append(s.substring(last));

        return sb.toString();
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> merged = new ArrayList<Interval>();
        if (intervals.size() == 0) {
            return merged;
        }

        // sort intervals based on the start
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        
        merged.add(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            Interval prev = merged.get(merged.size() - 1);
            Interval curr = intervals.get(i);
            if (curr.start > prev.end) {
                merged.add(curr);
            } else {
                prev.end = Math.max(prev.end, curr.end);
            }
        }

        return merged;
    }
}

class Interval {
    int start;
    int end;
    
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}