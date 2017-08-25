/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

idea:
1. for linear solution
consider this problem in another angel which is if the gas[i]-cost[i]<0 
which means the i can not been an start point. 
(1) is the total remaining gas > 0
(2) for each start point, is it possible to start from there by checking gas[i] - cost[i] > 0
*/
public class GasStation { 
    // self written verion passed test
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int result = 0;
        int sum = 0;
        int total = 0;
        
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            total += sum;
            if (sum < 0) {
                sum = 0;
                result = i+1;
            }
        }

        if (total < 0) {
            return -1;
        }
        return result;
    }
    // linear solution
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int index = -1;
        int sum = 0;
        int total = 0;
        
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (sum < 0) {
                sum = 0;
                index = i;
            }
        }
        
        return total >= 0 ? index + 1 : -1;
    }
    // O(n^2) solution
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i=0; i<gas.length; i++) {
            if (canCompleteCircuit(i, gas, cost)) {
                return i;
            }
        }
        return -1;
    }

    private boolean canCompleteCircuit(int start, int[] gas, int[] cost) {
        int gasRemaining = gas[start];
        int currDest = start;
        int nextDest = (start+1) % gas.length;

        while (nextDest != start) {
            if (gasRemaining < cost[currDest]) {
                return false;
            } 
            else {
                gasRemaining -= cost[currDest];
                currDest = nextDest;
                nextDest = (nextDest+1) % gas.length;
                gasRemaining += gas[currDest];
            }
        }

        return gasRemaining >= cost[currDest];
    }
}