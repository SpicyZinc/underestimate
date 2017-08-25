/**
given a1,b1,a2,b2,a3,b3
transform into a1,a2,a3,b1,b2,b3
*/

public class OddBeforeEven {
	public static void main(String[] args) {
		int[] a = {5,6,8,2,3,9,4};
		
	}
	
	
	public boolean isOdd(int n) {
		int a = Math.abs(n);
		return (a%2 == 1);
	}
	
	public boolean isEven(int n) {
		int a = Math.abs(n);
		return (a%2 == 0);
	}
}