/*
On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
We store logs in timestamp order that describe when a function is entered or exited.

Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".
For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.

A function's exclusive time is the number of units of time spent in this function.
Note that this does not include any recursive calls to child functions.

Return the exclusive time of each function, sorted by their function id.

Example 1:
https://assets.leetcode.com/uploads/2019/04/05/diag1b.png
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]

Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time. 
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.

Note:
1 <= n <= 100
Two functions won't start or end at the same time.
Functions will always log when they exit.

idea:
use stack
*/

import java.util.*;

public class ExclusiveTimeOfFunctions {
    public static void main(String[] args) {
        List<String> logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("0:end:6");

        ExclusiveTimeOfFunctions eg = new ExclusiveTimeOfFunctions();
        eg.exclusiveTime(2, logs);
    }

    // Fri May 17 23:08:03 2024
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;

        for (String log : logs) {
            String[] matches = log.split(":");
            int fnId = Integer.parseInt(matches[0]);
            String action = matches[1];
            int time = Integer.parseInt(matches[2]);

            if (action.equals("start")) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += time - prevTime;
                }
                stack.push(fnId);
                prevTime = time;
            } else if (action.equals("end")) {
                // note, must pop and use FnId, not necessarily top of stack is the same as current fnId
                int prevFnId = stack.pop();
                result[prevFnId] += time - prevTime + 1;
                prevTime = time + 1;
            }
        }

        return result;
    }

    // Mon May 13 23:01:02 2019
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        // save fnId to stack
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;

        for (String log : logs) {
            String[] parts = log.split(":");

            int fnId = Integer.parseInt(parts[0]);
            String startOrEnd = parts[1];
            int currTime = Integer.parseInt(parts[2]);

            if (startOrEnd.equals("start")) {
                if (!stack.isEmpty()) {
                    // record for 当前 fn stack 中 最上部的 fnId 的一段运行时间结束了
                    result[stack.peek()] += currTime - prevTime;
                }

                stack.push(fnId);
                prevTime = currTime;
            } else if (startOrEnd.equals("end")) { // must be 前一个开始的 就是 stack 最上端的 pop 出来就可以了
                int prevFnId = stack.pop();
                result[prevFnId] += (currTime + 1) - prevTime; 
                prevTime = currTime + 1;
            }
        }

        return result;
    }

    // only 4/120 passed
    public int[] exclusiveTime(int n, List<String> logs) {
        Map<Integer, List<String>> hm = new HashMap<>();

        int prevTime = 0;
        for (int i = 0; i < logs.size(); i++) {
            String log = logs.get(i);

            String[] parts = log.split(":");

            int fnId = Integer.parseInt(parts[0]);
            String startOrEnd = parts[1];
            int ts = Integer.parseInt(parts[2]);

            if (hm.containsKey(fnId)) {
                List<String> intervals = hm.get(fnId);
                intervals.add(prevTime + "," + ts);
                hm.put(fnId, intervals);
            } else {
                List<String> intervals = new ArrayList<>();
                intervals.add(ts + "," + (ts + 1));

                hm.put(fnId, intervals);
            }

            prevTime = ts + 1;
        }

        int[] result = new int[n];
        int i = 0;
        for (Map.Entry<Integer, List<String>> entry : hm.entrySet()) {
            int key = entry.getKey();
            List<String> list = entry.getValue();

            result[key] = getTime(list);
        }

        return result;
    }

    public int getTime(List<String> list) {
        int total = 0;
        for (String s : list) {
            String[] matches = s.split(",");
            int start = Integer.parseInt(matches[0]);
            int end = Integer.parseInt(matches[1]);

            total += (end - start) == 0 ? 1 : (end - start);
        }

        return total + 1;
    }
}