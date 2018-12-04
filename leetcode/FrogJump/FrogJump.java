/*
A frog is crossing a river.
The river is divided into x units and at each unit there may or may not exist a stone.
The frog can jump on a stone, but it must not jump into the water.
Given a list of stones' positions (in units) in sorted ascending order,
determine if the frog is able to cross the river by landing on the last stone.

Initially, the frog is on the first stone and assume the first jump must be 1 unit.
If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units.
Note that the frog can only jump in the forward direction.

Note:
The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.

Example 1: [0,1,3,5,6,8,12,17]
There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. The frog can jump to the last stone by jumping 
1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
2 units to the 4th stone, then 3 units to the 6th stone, 
4 units to the 7th stone, and 5 units to the 8th stone.

Example 2: [0,1,2,3,4,8,9,11]
Return false. There is no way to jump to the last stone as 
the gap between the 5th and 6th stone is too large.

idea:
dfs(pos, prevJumpedSteps) with memorization
The river is divided into x units is greater than the number of stones
use pos + previous jumped steps as key
*/

public class FrogJump {
    // 12/04/2018
    public boolean canCross(int[] stones) {
        if (stones[1] >= 2) {
            return false;
        }

        Map<String, Boolean> hm = new HashMap<String, Boolean>();
        
        return canCross(stones, 1, 1, hm);
    }
    
    public boolean canCross(int[] stones, int pos, int prevSteps, Map<String, Boolean> hm) {
        // dont forget base case
        if (pos == stones.length - 1) {
            return true;
        }

        String key = pos + "-" + prevSteps;
        if (hm.containsKey(key)) {
            return hm.get(key);
        }
        
        for (int i = pos + 1; i < stones.length; i++) {
            int jumpedSteps = stones[i] - stones[pos];
            
            if (jumpedSteps < prevSteps - 1) {
                continue;
            }
            
            if (jumpedSteps > prevSteps + 1) {
                hm.put(key, false);
                return false;
            }
            
            if (canCross(stones, i, jumpedSteps, hm)) {
                hm.put(key, true);
                return true;
            }
        }
        hm.put(key, false);
        return false;
    }

    // 09/16/2018 LiveRamp
    public boolean canCross(int[] stones) {
        if (stones[1] >= 2) {
            return false;
        }

        if (stones.length == 2) {
            return true;
        }

        Map<String, Boolean> hm = new HashMap<String, Boolean>();

        return dfs(stones, 1, 1, hm);
    }

    public boolean dfs(int[] stones, int pos, int prevJumps, Map<String, Boolean> hm) {
        if (pos == stones.length - 1) {
            return true;
        }

        String key = pos + "-" + prevJumps;
        if (hm.containsKey(key)) {
            return hm.get(key);
        }

        for (int i = pos + 1; i < stones.length; i++) {
            int jumpedSteps = stones[i] - stones[pos];
            // if jumpedSteps in [prevSteps - 1, prevSteps + 1]
            if (jumpedSteps < prevJumps - 1) {
                continue;
            }
            if (jumpedSteps > prevJumps + 1) {
                hm.put(key, false);
                return false;
            }
            if (dfs(stones, i, jumpedSteps, hm)) {
                hm.put(key, true);
                return true;
            }
        }

        hm.put(key, false);

        return false;
    }

    // TLE, 16 / 39 test cases passed
    public boolean canCross(int[] stones) {
        // the first jump must be 1 unit
        // frog already at stone 1 which is zero-based index of 0
        // always stones[0] == 0
        if (stones[1] >= 2) {
            return false;
        }

        if (stones.length == 2) {
            return true;
        }

        // not zero based position
        return dfs(stones, 1, 1);
    }

    private boolean dfs(int[] stones, int pos, int prevSteps) {
        if (pos == stones.length - 1) {
            return true;
        }

        for (int i = pos + 1; i < stones.length; i++) {
            int jumpedSteps = stones[i] - stones[pos];
            // if jumpedSteps in [prevSteps - 1, prevSteps + 1] 
            if (jumpedSteps >= prevSteps - 1 && jumpedSteps <= prevSteps + 1) {
                if (dfs(stones, i, jumpedSteps)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}