/*
Implement double sqrt(double x) and x >= 0.
Compute and return the square root of x.

Example
Given n = 2 return 1.41421356

Notice
You do not care about the accuracy of the result, we will help you to output results.

idea:
precision 1e-10;
二分答案
*/

public class Sqrt {
	public double sqrt(double x) {
		// x < 1, 上限应该是1, 而不再是x
		double start = 0;
		double end = Math.max(1, x);

		double precision = 1e-10;
		while (end - start > precision) {
			double mid = (start + end) / 2;

			if (mid * mid > x) {
				end = mid;
			} else {
				start = mid;
			}
		}

		return start;
	}
}