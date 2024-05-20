
// You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.

class CheckIfItIsAStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        int size = coordinates.length;
        if (size == 2) return true;

        int[] point1 = coordinates[0];
        int[] point2 = coordinates[1];

        for (int i = 2; i < size; i++) {
            int[] currentPoint = coordinates[i];
            int x = currentPoint[0];
            int y = currentPoint[1];

            int x1 = point1[0];
            int y1 = point1[1];
            int x2 = point2[0];
            int y2 = point2[1];

            if ((x - x1) * (y2 - y1) != (y - y1) * (x2 - x1)) {
                return false;
            }
        }

        return true;
    }
}
