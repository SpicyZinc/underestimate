/*
Print numbers from 1 to the largest number with N digits by recursion.
Notice: It's pretty easy to do recursion like:
recursion(i) {
    if i > largest number:
        return
    results.add(i)
    recursion(i + 1)
}
however this cost a lot of recursion memory as the recursion depth maybe very large. Can you do it in another way to recursive with at most N depth?

Example
Given N = 1, return [1,2,3,4,5,6,7,8,9].
Given N = 2, return [1,2,3,4,5,6,7,8,9,10,11,12,...,99].
 
Do it in recursion, not for-loop.

idea:
recurse at most N depth
*/

public class PrintNumbersByRecursion {
    /**
     * @param n: An integer
     * @return: An array storing 1 to the largest number with n digits.
     */
    public List<Integer> numbersByRecursion(int n) {
        List<Integer> result = new ArrayList<Integer>();
        int i = 1;
        while (true) {
            if (String.valueOf(i).length() > n) {
                break;
            }
            result.add(i++);
        }
        return result;
    }

	public List<Integer> numbersByRecursion(int n) {
		List<Integer> result = new ArrayList<Integer>();
		dfs(n, 0, result);
		return result;
	}

	// every digit (number * 10) loop 10 time from 0 through 9
	public void dfs(int depth, int number, List<Integer> result) {
		if (depth == 0) {
			if (number > 0) {
				result.add(number);
			}
			return;
		}

		for (int i = 0; i <= 9; i++) {
			dfs(depth - 1, number * 10 + i, result);
		}
	}
}