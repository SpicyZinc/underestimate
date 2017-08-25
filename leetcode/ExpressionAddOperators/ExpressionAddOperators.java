/*
Given a string that contains only digits 0-9 and a target value, 
return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123",        6 -> ["1+2+3", "1*2*3"] 
"232",        8 -> ["2*3+2", "2+3*2"]
"105",        5 -> ["1*0+5","10-5"]
"00",         0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []

idea:
http://blog.csdn.net/u013027996/article/details/48713751

DFS basic idea
1. overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum
2. 0 sequence: because we can't have numbers with multiple digits started with zero, skip it
3. a little trick is that we should save the value that is to be multiplied in the next recursion.

*/

import java.util.*;

public class ExpressionAddOperators {
    public static void main(String[] args) {
        ExpressionAddOperators eg = new ExpressionAddOperators();
        List<String> res = eg.addOperators("123", 6);

        for ( int i = 0; i < res.size(); i++ ) {
            System.out.println(res.get(i));
        }
    }

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<String>();
        dfsSearch(num, target, "", 0, 0, res);
        return res;
    }
    
    private void dfsSearch(String num, int target, String path, long currRes, long prevNum, List<String> res) {
        if (currRes == target && num.length() == 0) {
            String exp = new String(path);
            res.add(exp);
            return;
        }
        for (int i = 1; i <= num.length(); i++) {
            String currStr = num.substring(0, i);
            // exclude any number which has a leading 0
            if (currStr.length() > 1 && currStr.charAt(0) == '0') {
                return;
            }
            long currNum = Long.parseLong(currStr);
            String next = num.substring(i);
            if (path.length() == 0) {
                dfsSearch(next, target, currStr, currNum, currNum, res);
            } else {
                // multiplication
                dfsSearch(next, target, path + "*" + currNum, (currRes - prevNum) + prevNum * currNum, prevNum * currNum, res);
                // add
                dfsSearch(next, target, path + "+" + currNum, currRes + currNum, currNum, res);
                // subtract
                dfsSearch(next, target, path + "-" + currNum, currRes - currNum, -currNum, res);                    
            }
        }
    }
    // public List<String> addOperators(String num, int target) {
    //     List<String> list = new ArrayList<String>();
    //     dfs(list, num, "", 0, 0, 0, target);
    //     return list;
    // }

    // private void dfs(List<String> list, String num, String path, int pos, long sum, long lastNum, int target) {
    //     int len = num.length();
    //     if (pos == len && sum == target) {
    //         list.add(path);
    //         return;
    //     }
    //     if (pos >= len) {
    //         return;
    //     }
    //     for (int i = pos; i < len; i++) {
    //         if (i != pos && num.charAt(pos) == '0') {
    //             break;
    //         }
    //         long curNum = Long.parseLong(num.substring(pos, i + 1));
    //         if (pos == 0) {
    //             dfs(list, num, path + "" + curNum, i + 1, curNum, curNum, target);
    //         } 
    //         else {
    //             dfs(list, num, path + "+" + curNum, i + 1, sum + curNum, curNum, target);
    //             dfs(list, num, path + "-" + curNum, i + 1, sum - curNum, -curNum, target);
    //             dfs(list, num, path + "*" + curNum, i + 1, sum - lastNum + lastNum * curNum, lastNum * curNum, target);
    //         }
    //     }
    // }
}