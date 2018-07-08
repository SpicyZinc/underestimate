/*
The simplest primality test is as follows: 
Given an input number n, check whether any integer m from 2 to n/2 divides n. 
If n is divisible by any m then n is composite, otherwise it is prime. 
However, rather than testing all m up to n/2, 
it is only necessary to test m up to Math.sqrt(n)
if n is composite then it can be factored into two values, 
at least one of which must be less than or equal to Math.sqrt(n).
*/
 
import java.util.*;

class PrimeNumberTest{
   public static void main(String args[]) {
        int n, status = 1, num = 3;
 
      	Scanner in = new Scanner(System.in);
      	System.out.println("Enter the number of prime numbers you want to output ");
      	n = in.nextInt();
 
      	if (n >= 1) {
      	   System.out.println("First " + n + " prime numbers are: ");
      	   System.out.println(2);
      	}
 
      	for (int count = 2; count <= n; ) {
			for (int j = 2; j <= Math.sqrt(num); j++ ) {
      	   	   	if (num % j == 0) {
      	   	   	   	status = 0;
      	   	   	   	break;
      	   	   	}
      	   	}
      	   	if (status != 0) {
      	   	   	System.out.println(num);
      	   	   	count++;
      	   	}

      	   	status = 1;
      	   	num++;
      	}         
   	}
}