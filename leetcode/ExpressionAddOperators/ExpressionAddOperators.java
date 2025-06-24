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

DFS
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
    // Wed May 22 01:49:08 2024
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        dfs(num, target, 0, 0, "", result);
        return result;
    }
    // note, prevOperand, 上一个运算数
    public void dfs(String s, int target, long current, long prev, String path, List<String> result) {
        int n = s.length();
        if (n == 0 && current == target) {
            result.add(new String(path));
            return;
        }

        for (int i = 1; i <= n; i++) {
            String left = s.substring(0, i);
            // 一个0行 但是有0开头的不行
            if (left.length() > 1 && left.charAt(0) == '0') {
                return;
            }
            long currentVal = Long.parseLong(left);
            String right = s.substring(i);

            if (path.isEmpty()) {
                // dont forget update path
                dfs(right, target, currentVal, currentVal, path + left, result);
            } else {
                // +
                dfs(right, target, current + currentVal, currentVal, path + "+" + left, result);
                // -
                dfs(right, target, current - currentVal, -currentVal, path + "-" + left, result);
                // *
                dfs(right, target, current - prev + prev * currentVal, prev * currentVal, path + "*" + left, result);
            }
        }
    }

    // Sat Jul  6 17:01:13 2019
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        dfs(num, target, 0, 0, "", result);
        
        return result;
    }

    // 因为 有 * 所以需要一个prevOperand
    public void dfs(String remaining, int target, long currVal, long prevOperand, String path, List<String> result) {
        int size = remaining.length();
        
        if (size == 0 && currVal == target) {
            result.add(path);
            
            return;
        }
        
        // note, starting from 1
        for (int i = 1; i <= size; i++) {
            String currStr = remaining.substring(0, i);
            // e.g., '05' not make sense
            // but if single '0', should not exclude
            if (currStr.length() > 1 && currStr.charAt(0) == '0') {
                return;
            }
            
            long currentNum = Long.parseLong(currStr);
            String leftOver = remaining.substring(i);
            
            if (path.length() == 0) {
                dfs(leftOver, target, currentNum, currentNum, currStr, result);
            } else {
                // add *
                dfs(leftOver, target, currVal - prevOperand + prevOperand * currentNum, prevOperand * currentNum, path + "*" + currStr, result);
                // add +
                dfs(leftOver, target, currVal + currentNum, currentNum, path + "+" + currStr, result);
                // add -, note, prevOperand should be minus
                dfs(leftOver, target, currVal - currentNum, -currentNum, path + "-" + currStr, result);
            }
        }
    }


    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        dfs(num, target, 0, 0, "", result);

        return result;
    }

    // the net value represented as currVal
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

            String remaining = num.substring(i);
            // dfs for the 1st time
            if (path.length() == 0) {
                dfs(remaining, target, currNum, currNum, currStr, result);
            } else {
                // add * before currNum
                dfs(remaining, target, currVal - prevOperand + prevOperand * currNum, prevOperand * currNum, path + "*" + currNum, result);
                // add + before currNum
                dfs(remaining, target, currVal + currNum, currNum, path + "+" + currNum, result);
                // add - before currNum
                dfs(remaining, target, currVal - currNum, -1 * currNum, path + "-" + currNum, result);
            }
        }
    }

    // this one also works
    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<>();
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
