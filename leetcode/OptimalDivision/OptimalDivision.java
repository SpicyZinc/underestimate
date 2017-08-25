/*
Given a list of positive integers, the adjacent integers will perform the float division.
For example, [2,3,4] -> 2 / 3 / 4.
However, you can add any number of parenthesis at any position to change the priority of operations.
You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format.
Your expression should NOT contain redundant parenthesis.

Example:
Input: [1000,100,10,2]
Output: "1000/(100/10/2)"
Explanation:
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
since they don't influence the operation priority. So you should return "1000/(100/10/2)". 

Other cases:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
Note:

The length of the input array is [1, 10].
Elements in the given array will be in range [2, 1000].
There is only one optimal division for each test case.

idea:
x1 is always numerator, maximize the numerator is the crucial part
x2 is always denominator
how? x1/(x2/x3...xn) this will flip x3...xn to be x1 * (x3...xn) / x2, which is the largest
*/

public class OptimalDivision {
	public String optimalDivision(int[] nums) {
		String s = null;
		if (nums.length == 0 || nums == null) {
			return s;
		}        
		if (nums.length == 1) {
			return nums[0] + "";
		}
		if (nums.length == 2) {
			return nums[0] + "/" + nums[1]; 
		}
		s = nums[0] + "/(";
		for (int i = 1; i < nums.length; i++) {
			s += (i == nums.length - 1) ? nums[i] : (nums[i] + "/");
		}
		s += ")";
		return s;
    }
}