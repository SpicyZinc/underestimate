/*
You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24

Example 2:
Input: [1, 2, 1, 2]
Output: False

Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator.
For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

idea:
dfs or backtracking
note:
choose 2 numbers (with order) in 12 ways and perform one of 4 operations (12 * 4). Then, with 3 remaining numbers,
choose 2 of them and perform one of 4 operations (6 * 4)
finally 2 numbers left and make a final choice of 2 * 4 possibilities.

12 * 4
6 * 4
2 * 4
possibilities

*/
class 24Game {
	public boolean judgePoint24(int[] nums) {
		List<Double> numbers = new ArrayList<Double>();
        for (int num : nums) {
            numbers.add((double) num);
        }
		return dfs(numbers);
	}

    public boolean dfs(List<Double> nums) {
		if (nums.size() == 0) return false;
		if (nums.size() == 1) {
			return Math.abs(nums.get(0) - 24) < 0.000006;
		}

		int n = nums.size();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					// choose 2 operands, whatever chosen, leftover need to be in another list
					List<Double> remainingNums = new ArrayList<Double>();
					for (int k = 0; k < n; k++) {
						if (k != i && k != j) {
							remainingNums.add(nums.get(k));
						}
					}
					// 4 operations
					for (int k = 0; k < 4; k++) {
						// if +, * operations, no need to calculate different arrangement
						// like a + b == b + a, this way reduce some time
						if (k < 2 && j > i) continue;
						double a = nums.get(i);
						double b = nums.get(j);

						if (k == 0) remainingNums.add(a + b);
						if (k == 1) remainingNums.add(a * b);
						if (k == 2) remainingNums.add(a - b);
						if (k == 3) {
							if (b != 0) {
								remainingNums.add(a / b);
							} else {
								continue;
							}
						}
                        
                        if (dfs(remainingNums)) {
                            return true;
                        }
                        remainingNums.remove(remainingNums.size() - 1);
					}
				}
			}
		}

		return false;
	}
}