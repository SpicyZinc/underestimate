/*
Given a list of array, return a list of arrays, each array is a combination of one element in each given array.

For example, given the following image:
input is [[1, 2, 3], [4], [5, 6]], the output should be
[
	[1, 4, 5]
	[1, 4, 6]
	[2, 4, 5]
	[2, 4, 6]
	[3, 4, 5]
	[3, 4, 6]
].

idea:
Uber
dfs
*/

import java.util.*;

class PermutationsOfAnArrayOfArrays {
	public static void main(String[] args) {
		List<List<Integer>> complexList = new ArrayList<>();

		List<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(3);
		
		List<Integer> b = new ArrayList<>();
		b.add(4);
		
		List<Integer> c = new ArrayList<>();
		c.add(5);
		c.add(6);

		complexList.add(a);
		complexList.add(b);
		complexList.add(c);

		PermutationsOfAnArrayOfArrays eg = new PermutationsOfAnArrayOfArrays();
		List<List<Integer>> result = eg.combinations(complexList);

		for (List<Integer> row : result) {
			System.out.println(row);
		}
	}

	public List<List<Integer>> combinations(List<List<Integer>> complexList) {
		List<List<Integer>> result = new ArrayList<>();

		dfs(complexList, 0, new ArrayList<Integer>(), result);

		return result;
	}

	public void dfs(List<List<Integer>> complexList, int pos, List<Integer> path, List<List<Integer>> result) {
		int m = complexList.size();

		if (path.size() == m) {
			result.add(new ArrayList<>(path));

			return;
		}

		for (int i = pos; i < m; i++) {
			List<Integer> row = complexList.get(i);
			for (int num : row) {
				path.add(num);
				dfs(complexList, i + 1, path, result);
				path.remove(path.size() - 1);
			}
		}
	}
}