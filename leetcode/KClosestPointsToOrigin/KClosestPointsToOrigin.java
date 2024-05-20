/*
We have a list of points on the plane. Find the K closest points to the origin (0, 0).
(Here, the distance between two points on a plane is the Euclidean distance.)
You may return the answer in any order.
The answer is guaranteed to be unique (except for the order that it is in.)

Example 1: 
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2: 
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)

Note:
1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000

idea:
priority queue of size k
or Comparator sort

要么借助 quick sort
*/

class KClosestPointsToOrigin {
    // Sun May 19 18:11:14 2024
    // bucket sort
    public int[][] kClosest(int[][] points, int K) {
        Map<Integer, List<int[]>> hm = new HashMap<>();
        int minD = 0;
        int maxD = 0;

        for (int[] p : points) {
            int d = getDistance(p);
            minD = Math.min(minD, d);
            maxD = Math.max(maxD, d);
            hm.computeIfAbsent(d, x -> new ArrayList<>()).add(p);
        }

        int[][] ans = new int[K][];
        int i = 0;
        int cnt = 0;
        for (int d = minD; d <= maxD; d++) {
            if (hm.containsKey(d)) {
                List<int[]> nodes = hm.get(d);
                cnt += nodes.size();
                for (int[] node : nodes) {
                    ans[i++] = node;
                }
                if (cnt == K) {
                    break;
                }
            }
        }

        return ans;
    }

    // Sun May 19 17:30:24 2024
    public int[][] kClosest(int[][] points, int K) {
        Map<Integer, List<int[]>> hm = new HashMap<>();
        int minD = 0;
        int maxD = 0;

        for (int[] p : points) {
            int d = getDistance(p);
            minD = Math.min(minD, d);
            maxD = Math.min(maxD, d);
            hm.computeIfAbsent(d, x -> new ArrayList<>()).add(p);
        }

        int[][] result = new int[K][];
        int i = 0;
        for (int i = minD; i <= maxD; i++) {
            if (hm.containsKey(i)) {
                List<int[]> nodes = hm.get(i);
                for (int[] node : nodes) {
                    result[i++] = node;
                    if (i == K) {
                        break;
                    }
                }
            }
        }
        return result
    }

    // Sat Apr  6 02:07:50 2024
    // quick sort, similar to find kth smallest
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        findKthSmallest(points, 0, n - 1, K);
        int[][] result = new int[K][];
        for (int i = 0; i < K; i++) {
            result[i] = points[i];
        }

        return result;
    }

    public void findKthSmallest(int[][] points, int start, int end, int k) {
        // imaginary pivot, this case, use start, but start will be still compared
        int pivotIdx = start;
        int pivotVal = getDistance(points[pivotIdx]);
        
        int i = start;
        int j = end;

        while (i <= j) {
            while (i <= j && getDistance(points[i]) <= pivotVal) i++;
            while (i <= j && getDistance(points[j]) >= pivotVal) j--;
            if (i <= j) {
                swap(points, i, j);
            }
        }
        swap(points, pivotIdx, j);
        // j is index, j + 1 is comparable with k
        if (k > j + 1) {
            findKthSmallest(points, j + 1, end, k);
        } else if (k < j + 1) {
            findKthSmallest(points, start, j - 1, k);
        }
    }

    public int getDistance(int[] point) {
        int a = point[0];
        int b = point[1];

        return a * a + b * b;
    }

    public void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    // Sun Jul  7 17:53:10 2019
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] * a[0] + a[1] * a[1] - (b[0] * b[0] + b[1] * b[1]);
            }
        });

        return Arrays.copyOfRange(points, 0, K);
    }

    // Fri Apr  5 22:36:48 2024
    public int[][] kClosest(int[][] points, int k) {
        // 大头在上面的pq 这样当满了后 大的都poll出来 那么留下的就是k个距离最近的
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> getDistance(b) - getDistance(a));
        for (int[] p : points) {
            pq.offer(p);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int[][] result = new int[k][2];
        int i = 0;
        while (!pq.isEmpty()) {
            result[i++] = pq.poll();
        }
        return result;
    }

    public int getDistance(int[] point) {
        int a = point[0];
        int b = point[1];

        return a * a + b * b;
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return getDistance(b) - getDistance(a);
            }
        });
        
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        
        int[][] result = new int[K][2];
        int i = 0;
        while (!pq.isEmpty()) {
            result[i++] = pq.poll();
        }
        
        return result;
    }

    public int getDistance(int[] point) {
        int x = point[0];
        int y = point[1];
        
        return x * x + y * y;
    }
}