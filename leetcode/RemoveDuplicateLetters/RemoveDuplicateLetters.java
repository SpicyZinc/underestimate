/*
Given a string which contains only lowercase letters, 
remove duplicate letters so that every letter appear once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"

idea:
先建立一个哈希表来统计每个字母出现的次数, 
还需要一个visited数字来纪录每个字母是否被访问过, 
遍历整个字符串, 
对于遍历到的字符, 
先在哈希表中将其值减一, 
然后看visited中是否被访问过, 
若访问过则继续循环, 
说明该字母已经出现在结果中并且位置已经安排妥当
如果没访问过, 
我们和结果中最后一个字母比较, 
如果该字母的ASCII码小并且结果中的最后一个字母在哈希表中的值不为0(说明后面还会出现这个字母), 
那么我们此时就要在结果中删去最后一个字母且将其标记为未访问, 
然后加上当前遍历到的字母, 
并且将其标记为已访问, 
以此类推直至遍历完整个字符串s, 
此时结果里的字符串即为所求

http://www.cnblogs.com/grandyang/p/5085379.html
https://discuss.leetcode.com/topic/31413/easy-to-understand-iterative-java-solution/2
*/
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        
        boolean[] visited = new boolean[256];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            hm.put(curr, hm.get(curr) - 1);
            
            if (visited[curr]) {
                continue;
            }
        
            int lastPos = sb.length() - 1;
            while (sb.length() > 0 && sb.charAt(lastPos) > curr && hm.get(sb.charAt(lastPos)) >= 1) {
                visited[sb.charAt(lastPos)] = false;
                sb.deleteCharAt(lastPos);
                lastPos = sb.length() - 1;
            }
            
            sb.append(curr);
            visited[curr] = true;
        }
        
        return sb.toString();
    }
}

