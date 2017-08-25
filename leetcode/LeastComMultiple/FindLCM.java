/**
find the least common multiple

idea:
x divides y "equals to" y is divisible by x

*/

public class FindLCM {
        public static int determineLCM(int a, int b) {
                int num1, num2;
                if (a > b) {
                        num1 = a;
                        num2 = b;
                } 
				else {
                        num1 = b;
                        num2 = a;
                }
                for (int i = 1; i <= num2; i++) {
					// the first multiple of the larger number of the two 
					// which can also be divisible by the smaller number	
					if ((num1 * i) % num2 == 0) { 
							// key point where lcm is
							return i * num1; 
					}
                }
                throw new Error("Error");
        }

        public static void main(String[] args) {
                FindLCM lcmTest = new FindLCM();
                System.out.println("6 and 7 lcm is " + lcmTest.determineLCM(6, 7));
				System.out.println("15 and 12 lcm is " + lcmTest.determineLCM(15, 12));
        }
}