/*
Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
Each process only has one parent process, but may have one or more children processes. This is just like a tree structure.
Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes,
where the first list contains PID for each process and the second list contains the corresponding PPID.
Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end.
You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example 1:
Input: 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation: 
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.
Note:
The given kill id is guaranteed to be one of the given PIDs.
n >= 1.

idea:
1. loop parent pid list, hit the thread to kill, use Queue, TLE
2. construct the tree-like structure
*/
import java.util.*;

public class KillProcess {
	public static void main(String[] args) {
		KillProcess eg = new KillProcess();
		List<Integer> pid = new ArrayList<Integer>(); // {1,3,10,5}
		pid.add(1);
		pid.add(3);
		pid.add(10);
		pid.add(5);

		List<Integer> ppid = new ArrayList<Integer>(); // {3,0,5,3}
		ppid.add(3);
		ppid.add(0);
		ppid.add(5);
		ppid.add(3);
		int kill = 5;
		List<Integer> result = eg.killProcess(pid, ppid, kill);
		for (int i : result) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}
	// method 2
	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // kill root, all will be killed
        if (kill == 0) {
            return pid;
        }
        
        List<Integer> result = new ArrayList<Integer>();
        // build map based on pid. why? process to kill can be the key
        Map<Integer, Set<Integer>> hm = new HashMap<>();
        for (int process : pid) {
            hm.put(process, new HashSet<Integer>());
        }
        
        for (int i = 0; i < ppid.size(); i++) {
            int process = ppid.get(i);
            if (hm.containsKey(process)) {
                hm.get(process).add(pid.get(i));
            }
        }
        
        dfs(kill, hm, result);
        
        return result;
    }
    
    public void dfs(int kill, Map<Integer, Set<Integer>> hm, List<Integer> result) {
        result.add(kill);
        for (int processToKill : hm.get(kill)) {
            dfs(processToKill, hm, result);
        }
    }
    // TLE
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> result = new ArrayList<Integer>();
        if (pid.size() == 0 || pid == null || ppid.size() == 0 || ppid == null) {
        	return result;
        }
        if (kill == 0) {
        	return pid;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(kill);
        result.add(kill);
        while ( !queue.isEmpty() ) {
        	int temp = queue.poll();
        	List<Integer> positions = findPos(ppid, temp);
        	if (positions != null) {
	        	for (int i = 0; i < positions.size(); i++) {
	        		int pos = positions.get(i);
		        	if (pos != -1) {
		        		int process = pid.get(pos);
		        		result.add(process);
		        		queue.offer(process);
		        	}
	        	}
        	} else {
        		break;
        	}
        }
        return result;
    }

 	private List<Integer> findPos(List<Integer> ppid, int kill) {
 		List<Integer> pos = new ArrayList<Integer>();
 		for (int i = 0; i < ppid.size(); i++) {
 			if (ppid.get(i) == kill) {
 				pos.add(i);
 			}
 		}

 		return pos;
 	}
}