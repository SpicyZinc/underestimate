/*
Jump Game
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.
A = [3,2,1,0,4], return false. 

idea:
use greedy algorithm
just hard think
*/

import java.util.*;

public class JumpGame {
	public static void main(String[] args) {
		new JumpGame();
	}
	// constructor
	public JumpGame() {
		int[] a = {2, 3, 1, 1, 4};
		int[] b = {3,2,1,0,4};

		boolean aCanJump = canJump(a);
		boolean bCanJump = canJump(b);

		System.out.println(aCanJump);
		System.out.println(bCanJump);
	}
    // self written, best version
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            // even max reach cannot reach i position, return false
            // greedy algorithm
            if (i > maxReach) {
                return false;
            }
            if (i + nums[i] > maxReach) {
                maxReach = i + nums[i];
            }
        }

        return true;
    }
    // one version
    public boolean canJump(int[] A) {
        if (A.length <= 1) {
            return true;
        }

        int maxReach = 0;
        int canForwardMaxSteps = 1;
        for (int i = 0; i < A.length; i++) {
            canForwardMaxSteps--;
            if (i + A[i] > maxReach) {
                maxReach = i + A[i];
                canForwardMaxSteps = A[i];
            }
            if (canForwardMaxSteps == 0 && i < A.length - 1) {
                return false;
            }
        }
        return true;
    }
}