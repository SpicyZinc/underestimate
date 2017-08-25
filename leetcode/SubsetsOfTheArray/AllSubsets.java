/*
idea:
why there is always (2 ^ s.length) of combinations
for each number, either in or not in the final combination
let's use 0 to stand for not in
use 1 to stand for in
any number between 0 - (2^s.length), get its binary format,
corresponding to array, if 1, take this number; if 0, not take
*/

import java.util.*;

class AllSubsets {
	public static void main(String[] args) {
		int[] source1 = {1, 2, 3, 4};
		int[] source2 = {1, 3, 3};
		
		ArrayList<ArrayList<Integer>> res = getSubsetsBitwise(source1);
		for (int i=0; i<res.size(); i++) {
			System.out.print("Element " + (i+1) + ":  "); 
			ArrayList<Integer> tmp = res.get(i);
			for (int j=0; j<tmp.size(); j++) {
				System.out.print(tmp.get(j) + "  ");
			}
			System.out.print("\n");
		}

		System.out.println("======================");
		printAllSubsets_2(source2);
	}

	private static ArrayList<ArrayList<Integer>> getSubsetsBitwise(int[] a) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int size = (int) Math.pow(2.0, a.length);
		for (int i = 0; i < size; i++) {
			ArrayList<Integer> element = new ArrayList<Integer>();
			int temp = i; // key point
			for (int j = 0; j < a.length; j++) { 
				if ((temp & 1) == 1) {
					element.add(a[j]);
				}
				temp >>= 1;
			}

			res.add(element);
		}
		
		return res;
	}

	private static void printAllSubsets_2(int[] a) {
		int length = a.length;
		int o_length = (int) Math.pow(2, length);
		for (int i = 0; i < o_length; i++) {
			if (i == 0) {
				System.out.println((i+1) + " { }");
			}
			else {
				System.out.print((i+1) + " {");
				for (int j=0, temp=i; j<length; j++, temp>>=1) { // how many digits of the binary number, do j times
					if ((temp&1) == 1) {
						System.out.printf("%d ", a[j]);
					}
				}
				System.out.print("}\n");
			}
		}	
	}
}