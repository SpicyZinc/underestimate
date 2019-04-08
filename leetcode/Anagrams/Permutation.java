/*
Try each of the letters in turn as the first letter 
then find all the permutations of the remaining letters using a recursive call.
The base case is when the input is an empty string the only permutation is the empty string.
*/

import java.util.Scanner;

class Permutation {
	private static int count = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();

		Permutation eg = new Permutation();
		eg.permutation("", str); 
	}

	private void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0) {
			System.out.println(++count + " " + prefix);
		} else {
			for (int i = 0; i < n; i++) {
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
			}
		}
	}
}