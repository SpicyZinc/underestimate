/*
Two images A and B are given, represented as binary, square matrices of the same size. (A binary matrix has only 0s and 1s as values.)
We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.
After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)
What is the largest possible overlap?

Example 1:
Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.

Notes: 
1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1

idea:
find the number of points in A having the same distance to points in B
convert 2d index to 1d index

想着如果 两个 images 重合
重合的部分 每个点 相当于平移 距离是一样的
怎么计算呢 1d 最好算
就是找重复出现最多 某个距离 就是最多重复的点
*/

class ImageOverlap {
	public int largestOverlap(int[][] A, int[][] B) {
		int n = A.length;

		List<Integer> pointsA = new ArrayList<Integer>();
		List<Integer> pointsB = new ArrayList<Integer>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int idx = i * n + j;
				if (A[i][j] == 1) {
					pointsA.add(idx);
				}
				if (B[i][j] == 1) {
					pointsB.add(idx);
				}
			}
		}
		// use point (x, y) in A, point (x, y) in B 横 纵坐标 之差 结成 string 作为 key
		Map<String, Integer> hm = new HashMap<>();
		int cnt = 0;

		for (int a : pointsA) {
			for (int b : pointsB) {

				int xA = a / n;
				int yA = a % n;
				int xB = b / n;
				int yB = b % n;

				int diffX = (xB - xA);
				int diffY = (yB - yA);

				String key = diffX + "_" + diffY;

				hm.put(key, hm.getOrDefault(key, 0) + 1);

				cnt = Math.max(cnt, hm.get(key));
			}
		}

		return cnt;
	}
}