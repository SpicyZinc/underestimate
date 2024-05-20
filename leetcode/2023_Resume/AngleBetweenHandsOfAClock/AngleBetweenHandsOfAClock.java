/*
idea:

转化成度 以12点为基准
一个小时30degree
一个小格6 degree
*/

class AngleBetweenHandsOfAClock {
    // 一个小时30degree
    public double angleClock(int hour, int minutes) {
        // Degree covered by hour hand (hour area + minutes area)
        double h = (hour % 12 * 30) + ((double) minutes / 60 * 30);
        // Degree covered by minute hand (Each minute = 6 degree)
        double m = minutes * 6;
        
        // Absolute angle between them
        double angle = Math.abs(m - h);

        // If the angle is obtuse (>180), convert it to acute (0<=x<=180)
        return angle > 180 ? (360 - angle) : angle;
    }
}
