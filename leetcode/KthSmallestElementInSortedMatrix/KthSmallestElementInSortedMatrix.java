/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8, return 13.

Note: 
You may assume k is always valid, 1 ≤ k ≤ n2.

idea:
1. binary search
kthSmallest, since the matrix is sorted,
binary search to find position in each row, add together
note: matrix is not serpentine sorted, it is another way sorted
2. PriorityQueue, http://www.jiuzhang.com/solutions/kth-smallest-number-in-sorted-matrix/
*/

public class KthSmallestElementInSortedMatrix {
    // method 1
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int L = matrix[0][0];
        int R = matrix[0][n-1];

        for (int i = 1; i < n; i++) {
        	L = Math.min(L, matrix[i][0]);
        	R = Math.max(R, matrix[i][n-1]);
        }

        while (L < R) {
        	int mid = (L + R) / 2;
        	int midPosition = 0;
        	for (int i = 0; i < n; i++) {
        		midPosition += binarySearch(matrix[i], mid);
        	}
        	if (midPosition < k) {
        		L = mid + 1;
        	}
        	else {
        		R = mid;
        	}
        }

        return L;
    }


    public int binarySearch(int[] arr, int target) {
    	int start = 0;
    	int end = arr.length;

    	while (start < end) {
    		int mid = (start + end) / 2;
    		if (arr[mid] > target) {
    			end = mid;
    		}
    		else {
    			start = mid + 1;
    		}
    	}

    	return start;
    }

    // method 2
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        PriorityQueue<Node> queue = new PriorityQueue<Node>(k, new NodeComparator());
        boolean[][] isVisited = new boolean[m][n];
        queue.offer(new Node(0, 0, matrix[0][0]));
        isVisited[0][0] = true;
        int[][] directions = {{0,1}, {1,0}};

        for (int i = 0; i < k - 1; i++) {
            Node current = queue.poll();
            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                if ( isValid(matrix, newX, newY, isVisited) ) {
                    isVisited[newX][newY] = true;
                    queue.offer(new Node(newX, newY, matrix[newX][newY]));
                }
            }
        }
        return queue.peek().value;
    }

    public boolean isValid(int[][] matrix, int x, int y, boolean[][] isVisited) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (x < m && y < n && !isVisited[x][y]) {
            return true;
        }
        return false;
    }
}

class Node {
    public int x;
    public int y;
    public int value;

    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node a, Node b) {
        return a.value - b.value;        
    }
}
