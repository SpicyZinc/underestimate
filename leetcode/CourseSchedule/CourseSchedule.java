/*
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:
2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph.
If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.

idea:
http://www.cnblogs.com/grandyang/p/4484571.html
http://www.programcreek.com/2014/05/leetcode-course-schedule-java/
http://blog.csdn.net/menglinaoxiang/article/details/45623713

1. DFS
note, dfs() visited[] needs 3 status, 0表示还未访问过, 1表示已经访问了, -1表示有冲突
build graph by edge notation
construct a visit array to know which node has been visited
loop through courses
for each course, DFS,
in DFS, if there is depending relation, do DFS, recursively,
once found out some visited node, which means it is cycle, return false
otherwise, set this node (course) to be not visited.
In other words, no cycle stems from this course, set it back to be not visited
noted visited[] 1 0 -1 3 states, reduce run time

2. BFS
2d graph to represent this graph
1d to represent in degree (prerequisite) of each vertex (course from 1 through numCourses)
initialize graph and in_degree array
define queue as a list, put all in degree zero node (course) to it
all nodes or courses in queue are in degree zero, which mean all connected node has to be subtracted one
while loop this queue, find corresponding connected node from graph,
subtract 1 from in_degree first
if now it is 0, put into the queue
if still there are non-zero in degree node, cannot finish
*/

public class CourseSchedule {
    // DFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        int[] visited = new int[n];
        
        // construct the graph
        boolean[][] graph = new boolean[n][n];
        for (int[] prerequisite : prerequisites) {
            int taken = prerequisite[0];
            int prere = prerequisite[1];
            
            graph[taken][prere] = true;
        }
        
        for (int i = 0; i < n; i++) {
            if (!canFinishDFS(graph, visited, i)) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean canFinishDFS(boolean[][] graph, int[] visited, int course) {
        if (visited[course] == 1) {
            return false;
        }
        
        if (visited[course] == -1) {
            return true;
        }
        
        visited[course] = 1;
        for (int i = 0; i < graph[course].length; i++) {
            // if there is a dependency
            if (graph[course][i]) {
                if (!canFinishDFS(graph, visited, i)) {
                    return false;
                }
            }
        }
        visited[course] = -1;
        
        return true;
    }
    // correct method, but TLE, 40 / 42 test cases passed
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        boolean[] visited = new boolean[n];
        // construct the graph
        int[][] graph = new int[n][n];
        for (int[] prerequisite : prerequisites) {
            int taken = prerequisite[0];
            int prere = prerequisite[1];
            graph[taken][prere] = 1;
        }
        
        // loop through the courses, for each course dfs
        for (int i = 0; i < n; i++) {
            if (!canFinishDFS(graph, visited, i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean canFinishDFS(int[][] graph, boolean[] visited, int courseTaken) {
        if (visited[courseTaken]) return false;

        visited[courseTaken] = true;
        for (int j = 0; j < graph[courseTaken].length; j++) {
            // if there is a dependency
            if (graph[courseTaken][j] == 1) {
                if (!canFinishDFS(graph, visited, j)) {
                    return false;
                }
            }
        }
        visited[courseTaken] = false;
        return true;
    }


    // BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        int n = numCourses;
        int[] preCouNum = new int[n]; // 统计当前课程需要的预备课程数
        boolean[][] graph = new boolean[n][n]; // 记录课程关系图
        // the number of unique course pairs, course depending relationship
        int m = 0; 
        // 初始化有向图, 并统计出度
        for (int i = 0; i < prerequisites.length; i++) {
        for (int[] prerequisite : prerequisites) {
            int taken = prerequisite[0];
            int prere = prerequisite[1];
            // 避免重复的数对
            if (!graph[taken][prere]) {
                graph[taken][prere] = true;
                preCouNum[taken]++; // course taken needs how many other courses as prerequisite
                m++;
            }
        }
        // 无前向引用的课程 出度为0 可以修完的课程
        Queue<Integer> queue = new LinkedList<Integer>(); 
        for (int i = 0; i < n; i++) {
            if (preCouNum[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty() && n > 0) {
            int i = queue.remove();
            for (int j = 0; j < n; j++) {
                if (graph[j][i]) { // 将使用该课程作预备课程的 
                    graph[j][i] = false; // 科目置为false表示该课程已修, no depending relationship
                    m--;
                    preCouNum[j]--;
                    // 如果当前课程出度为0, 可以作为能修完的课程 加入队列
                    if (preCouNum[j] == 0) {
                        queue.add(j);
                    }
                }
            }
            n--;
        }
        // m指所有的数对关系均以处理完 则表示可以完成
        return m == 0;
    }
}