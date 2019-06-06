// Math.random() [0.0, 1)
// 要平移

class GenerateRandomPointInACircle {
	double radius;
	double x_center;
	double y_center;

	public Solution(double radius, double x_center, double y_center) {
		this.radius = radius;
		this.x_center = x_center;
		this.y_center = y_center;
	}

	public double[] randPoint() {
		// 平移后的圆心
		double x_center_normalized = x_center - radius;
		double y_center_normalized = y_center - radius;

		while (true) {
			double x_in_circle = x_center_normalized + Math.random() * radius * 2;
			double y_in_circle = y_center_normalized + Math.random() * radius * 2;

			double distanceToCenter = Math.pow(x_in_circle - x_center, 2) + Math.pow(y_in_circle - y_center, 2);
			if (distanceToCenter <= radius * radius) {
				return new double[] {x_in_circle, y_in_circle};
			}
		}
	}
}