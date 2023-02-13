/*
There are N students in a class. Some of them are friends, while some are not.
Their friendship is transitive in nature.
For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C.
And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class.
If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.

Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.

idea:

1. BFS, visited boolean to avoid visiting visited friends
find # of connected circle
like A->B->C, put into queue
when B->C gets visited, it will not be counted again

2. DFS

*/

public class FriendCircles {
    // BFS
	public int findCircleNum(int[][] M) {
        int n = M.length;
        boolean[] visited = new boolean[n];
        int circles = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // a person is a circle, if he knows others, still same circle
                circles++;
                // only for marking person to be visited
                bfs(M, i, visited);
            }
        }
        return circles;
    }
    
    public void bfs(int[][] M, int index, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);

        while (!queue.isEmpty()) {
            int directStart = queue.poll();
            for (int j = 0; j < M[directStart].length; j++) {
                if (M[directStart][j] == 1 && !visited[j]) {
                    queue.offer(j);
                    visited[j] = true;
                }
            }
        }
    }

    // DFS
    public int findCircleNum(int[][] M) {
        int n = M.length;
        boolean[] visited = new boolean[n];
        int circles = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                circles++;
                dfs(M, i, visited);
            }
        }
        return circles;
    }
    
    public void dfs(int[][] M, int person, boolean[] visited) {
        for (int j = 0; j < M[person].length; j++) {
            if (M[person][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, j, visited);
            }
        }
    }
    // union find
    public int findCircleNum(int[][] M) {
        int n = M.length;
        int circles = n; // at most each friend has a circle, only know itself
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;           
        }
        // union
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // direct friend
                if (M[i][j] == 1) {
                    int rootI = getRoot(root, i);
                    int rootJ = getRoot(root, j);
                    // if not in same group, union it
                    if (rootI != rootJ) {
                        root[rootJ] = rootI;
                        circles--;
                    }
                }
            }
        }

        return circles;
    }

    public int getRoot(int[] root, int i) {
        while (root[i] != i) {
            i = root[i];
        }
        return i;
    }
}