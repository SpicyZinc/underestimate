/*
There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.


Example 1:
Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true


Constraints:
1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105

idea:
meeting room 3
https://leetcode.com/problems/car-pooling/solutions/1669644/well-explained-2-ways-java-c-python-javascript-easy-for-mind-to-accept-it/
*/

class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        // Because from and to is between 0 and 1000. Idea is to store counts in an array of size 1001.
        int lengthOfTrip[] = new int[1001];
        for (int trip[] : trips) {
            int count = trip[0];
            int from = trip[1];
            int to = trip[2];

            lengthOfTrip[from] += count; // Increment when passenger is on board
            lengthOfTrip[to] -= count; // decrement when they depart
        }
        // Count total passenger for each bus top
        int currentCarLoad = 0;
        // we have the count array, we perform a line sweep from 0 to 1000 and track its total
        for (int i = 0; i < 1001; i++){
            currentCarLoad += lengthOfTrip[i];
            // Reject at any point where the car is overloaded somewhere
            if (currentCarLoad > capacity) {
                return false;
            }
        }

        return true;
    }

    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> m = new TreeMap<>();
        for (int[] t : trips) {
            m.put(t[1], m.getOrDefault(t[1], 0) + t[0]);
            m.put(t[2], m.getOrDefault(t[2], 0) - t[0]);
        }
        for (int v : m.values()) {
            capacity -= v;
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }

    // Self failed
    public boolean carPooling(int[][] trips, int capacity) {
        int[] prev = trips[0];
        int prevTo = prev[2];
        int prevCount = prev[0];
        capacity -= prevCount;

        int i = 1;
        for (; i < trips.length; i++) {
            int[] trip = trips[i];
            int count = trip[0];
            int from = trip[1];
            int to = trip[2];

            capacity -= count;

            if (from < prevTo) {
                if (capacity < 0) {
                    return false;
                }
            } else {
                capacity += prevCount;
                if (capacity < 0) {
                    return false;
                }
            }

            prevTo = to;
        }

        return i == trips.length && capacity >= 0;
    }
}