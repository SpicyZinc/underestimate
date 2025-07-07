/*
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs,
return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them.
If it is impossible to finish all courses, return an empty array.

For example:
2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

Hints:
This problem is equivalent to finding the topological order in a directed graph.
If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.

idea:
1. BFS
use queue, first in first out
first in's are indegree small courses
gradually their indegree getting bigger

loop self-built graph 
2. DFS
*/

public class CourseSchedule {
    // 2025
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        int[] depending = new int[n];

        for (int[] pre : prerequisites) {
            int toFinish = pre[0];
            int dependOn = pre[1];
            depending[toFinish]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (depending[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> list = new ArrayList<>();
        int finishedCount = 0;

        while (!queue.isEmpty()) {
            int finished = queue.poll();
            list.add(finished);
            finishedCount++;


            for (int[] pre : prerequisites) {
                int toFinish = pre[0];
                int dependOn = pre[1];
                
                if (dependOn == finished) {
                    depending[toFinish]--;
                    if (depending[toFinish] == 0) {
                        queue.add(toFinish);
                    }
                }
            }
        }

        int[] orders = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            orders[i] = list.get(i);
        }

        return finishedCount == n ? orders : new int[0];
    }
    // Thu Feb 23 23:05:59 2023 prepare for eBay
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int n = numCourses;

        int[] depending = new int[n];
        for (int[] prere : prerequisites) {
            // int first = prere[1];
            int second = prere[0];
            // depending to finish a course i, how many needed is the value
            depending[second]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        // find the starting point
        // add to queue
        for (int i = 0; i < n; i++) {
            if (depending[i] == 0) {
                queue.add(i);
            }
        }

        int[] order = new int[n];
        int index = 0;
        int finishCount = 0;

        while (!queue.isEmpty()) {
            int courseWithoutDependencies = queue.poll();
            order[index++] = courseWithoutDependencies;
            finishCount++;

            for (int[] prere : prerequisites) {
                int first = prere[1];
                int second = prere[0];
                
                if (first == courseWithoutDependencies) {
                    // 完成一门课的依赖 减掉
                    depending[second]--;
                    if (depending[second] == 0) {
                        queue.add(second);
                    }
                }
            }
        }

        return finishCount == n ? order : new int[0];
    }

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] degree = new int[numCourses];

        List<Integer>[] edges = new List[numCourses];        
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }
            
        for (int[] prerequisite : prerequisites) {
            degree[prerequisite[0]]++ ;
            edges[prerequisite[1]].add(prerequisite[0]);
        }

        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }
        
        int count = 0;
        int[] order = new int[numCourses];

        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[count] = course;
            count++;
            int n = edges[course].size();
            for (int i = n - 1; i >= 0; i--) {
                int pointer = edges[course].get(i);
                degree[pointer]--;
                if (degree[pointer] == 0) {
                    queue.add(pointer);
                }
            }
        }
        
        return count == numCourses ? order : new int[0];
    }

	// 02/09/2019
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int n = numCourses;
		int[] dependencyCnt = new int[n];

		for (int[] prerequisite : prerequisites) {
			int successive = prerequisite[0];
			dependencyCnt[successive]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			// course i, no dependency
			if (dependencyCnt[i] == 0) {
				queue.add(i);
			}
		}

		int[] order = new int[n];
		int idx = 0;
		int finishedCnt = 0;

		while (!queue.isEmpty()) {
			int courseWithoutDependency = queue.poll();
			order[idx++] = courseWithoutDependency;
			finishedCnt++;

			for (int[] prerequisite : prerequisites) {
				int base = prerequisite[1];
				int successive = prerequisite[0];
				// 如果 successive 依赖的课base 没有依赖性了 那 successive的课也okay了
				if (base == courseWithoutDependency) {
					dependencyCnt[successive]--;
					
					if (dependencyCnt[successive] == 0) {
						queue.add(successive);
					}
				}
			}
		}

		return n == finishedCnt ? order : new int[0];
	}


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] order = new int[numCourses];

        int[] preCourses = new int[numCourses];
        for (int[] pre : prerequisites) {
            int courseAfter = pre[0];
            // take courseAfter, the number of other courses
            preCourses[courseAfter]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (preCourses[i] == 0) {
                queue.add(i);
            }
        }

        int index = 0;
        int numOfPrecourses = 0;

        while (!queue.isEmpty()) {
            int top = queue.poll();
            // put it in Topological order
            order[index++] = top;
            // mark finished one course
            numOfPrecourses++;

            for (int[] pre : prerequisites) {
                int successive = pre[0];
                int base = pre[1];
                // 如果恰巧base 依赖的课程 已经 没有出度
                if (base == top) {
                    preCourses[successive]--;
                    // 变成了0 就放入queue
                    if (preCourses[successive] == 0) {
                        queue.add(successive);
                    }
                }
            }
        }

        return numOfPrecourses == numCourses ? order : (new int[0]);
    }
}