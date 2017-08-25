/*
idea:
direct thought O(n^2), brute force
not index, it is position to be returned
*/
public class TwoSum {
	public static void main(String[] args) {
		new TwoSum();
	}
	public TwoSum() {
		int[] numbers = {722,600,905,55,55};
		int target = 110;
		int[] ret = twoSum(numbers, target);
		System.out.println(ret[0] + " " + ret[1]);
	}
    // timeout
    public int[] twoSum(int[] numbers, int target) {
        int[] ret = new int[2];
        for (int i=0; i<numbers.length; i++) {
            for (int j=i+1; j<numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    ret[0] = i+1;
                    ret[1] = j+1;
                }                    
            }           
        }

        return ret;
    }
}