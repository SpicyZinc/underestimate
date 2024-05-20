/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number).
Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

idea:
https://discuss.leetcode.com/topic/58321/java-ac-solution-with-explanation
actually graph problem
u-v
v-u
都要populate到图中
note value 要reverse
然后dfs找下去 注意几种base return 情况

a / b = x
a / c = y
变成这样
[
	a: 
	[b:x]
	[c:y]
]

need to come back
similar to https://leetcode.com/discuss/interview-question/483660/google-phone-currency-conversion
*/

public class EvaluateDivision {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		Map<String, Map<String, Double>> numMap = new HashMap<>();
		int i = 0;

		for (List<String> equation : equations) {
			double value = values[i];

			String dividend = equation.get(0);
			String divisor = equation.get(1);

			numMap.computeIfAbsent(dividend, x -> new HashMap<>()).put(divisor, value);
			numMap.computeIfAbsent(divisor, x -> new HashMap<>()).put(dividend, 1.0 / value);

			i++;
		}

		i = 0;
		double[] result = new double[queries.size()];

		for (List<String>query : queries) {
			Double resObj = handleQuery(query.get(0), query.get(1), numMap, new HashSet<>());
			result[i++] = resObj != null ? resObj : -1.0;
		}

		return result;
	}

	public Double handleQuery(String num, String denom, Map<String, Map<String, Double>> numMap, Set<String> visited) {
		String dupeKey = num + "/" + denom;

		if (visited.contains(dupeKey)) {
			return null;
		}
		if (!numMap.containsKey(num) || !numMap.containsKey(denom)) {
			return null;
		}
		if (num.equals(denom)) {
			return 1.0;
		}

		Map<String, Double> denomMap = numMap.get(num);

		for (String key : denomMap.keySet()) {
			visited.add(dupeKey);
			Double res = handleQuery(key, denom, numMap, visited);
			if (res != null) {
				return denomMap.get(key) * res;
			}
			visited.remove(dupeKey);
		}

		return null;
	}
}
