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
1. overflow: use a long type once it is larger than Integer.MAX_VALUE or minimum
2. 0 sequence: because we can't have numbers with multiple digits started with zero, skip it
3. a little trick is that we should save the prev operand that could be multiplied in the next recursion.
*/

import java.util.*;

public class ExpressionAddOperators {
    public static void main(String[] args) {
        ExpressionAddOperators eg = new ExpressionAddOperators();
        List<String> res = eg.addOperators("123", 6);
        for (String s : res) {
            System.out.println(s);
        }
    }

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<String>();
        dfs(num, target, 0, 0, "", result);

        return result;
    }
    // the net value put on the currVal
    public void dfs(String num, int target, long currVal, long prevOperand, String path, List<String> result) {
        if (num.length() == 0 && currVal == target) {
            result.add(path);
            return;
        }

        for (int i = 1; i <= num.length(); i++) {
            String currStr = num.substring(0, i);
            // exclude number of more than 1 digit having leading zero
            // for 1 digit number and it is zero, no need to exclude, because 1 * 0 + 5 is a good case
            if (currStr.length() > 1 && currStr.charAt(0) == '0') {
                return;
            }
            long currNum = Long.parseLong(currStr);

            String restNum = num.substring(i);
            // dfs for the 1st time
            if (path.length() == 0) {
                dfs(restNum, target, currNum, currNum, currStr, result);
            } else {
                // add * before currNum
                dfs(restNum, target, currVal - prevOperand + prevOperand * currNum, prevOperand * currNum, path + "*" + currNum, result);
                // add + before currNum
                dfs(restNum, target, currVal + currNum, currNum, path + "+" + currNum, result);
                // add - before currNum
                dfs(restNum, target, currVal - currNum, -1 * currNum, path + "-" + currNum, result);
            }
        }
    }

    // this one also works
    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<String>();
        dfs(list, num, "", 0, 0, 0, target);
        return list;
    }

    private void dfs(List<String> list, String num, String path, int pos, long sum, long lastNum, int target) {
        int len = num.length();
        if (pos == len && sum == target) {
            list.add(path);
            return;
        }
        if (pos >= len) {
            return;
        }
        for (int i = pos; i < len; i++) {
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }
            long curNum = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                dfs(list, num, path + "" + curNum, i + 1, curNum, curNum, target);
            } else {
                dfs(list, num, path + "+" + curNum, i + 1, sum + curNum, curNum, target);
                dfs(list, num, path + "-" + curNum, i + 1, sum - curNum, -curNum, target);
                dfs(list, num, path + "*" + curNum, i + 1, sum - lastNum + lastNum * curNum, lastNum * curNum, target);
            }
        }
    }
}
