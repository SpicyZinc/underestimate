/*
Implement an iterator to flatten a 2d vector.
For example, Given 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
] 
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].


idea:
https://segmentfault.com/a/1190000003791233
https://www.cnblogs.com/grandyang/p/5209621.html
1. 压成1D array
2. 利用Java iterator
*/

public class Vector2D {
	public static void main(String[] args) {
		List<Integer> row1 = new ArrayList<>();
		List<Integer> row2 = new ArrayList<>();
		List<Integer> row3 = new ArrayList<>();
		row1.add(1);
		row1.add(2);
		row2.add(3);
		row3.add(4);
		row3.add(5);
		row3.add(6);

		List<List<Integer>> vec2d = new ArrayList<>();
		vec2d.add(row1);
		vec2d.add(row2);
		vec2d.add(row3);

		Vector2D eg = new Vector2D(vec2d);

	}

	int index = 0;
	int[] list;

	public Vector2D(List<List<Integer>> vec2d) {
		// get size
		int len = 0;
		for (List<Integer> row : vec2d) {
			len += row.size();
		}
		// build new list
		list = new int[len];
		int idx = 0;
		for (List<Integer> row : vec2d) {
			for (int num : row) {
				list[idx++] = num;
			}
		}
	}

	public int next() {
		return list[index++];
	}

	public boolean hasNext() {
		return index < list.length;
	}

	// use Java iterator
    int curr = 0;
    List<Iterator<Integer>> iterators;

    public Vector2D(List<List<Integer>> vec2d) {
        this.iterators = new ArrayList<Iterator<Integer>>();
        for (List<Integer> list : vec2d) {
            // 只将非空的迭代器加入数组
            if (list.size() > 0) {
               this.iterators.add(list.iterator()); 
            }
        }
    }

    public int next() {
        Integer res = iterators.get(curr).next();
        // 如果该迭代器用完了, 换到下一个
        if (!iterators.get(curr).hasNext()) {
            curr++;
        }

        return res;
    }

    public boolean hasNext() {
        return curr < iterators.size() && iterators.get(curr).hasNext();
    }
}