/*
idea:
N-Queens and Telephone letterCombination
for loop call recursion
*/
import java.util.*;

public class Binary {
	public static void main(String[] args) {
		new Binary();
	}
	public Binary() {		
		ArrayList<String> ret = binary(3);
		for (int i=0; i<ret.size(); i++) {
			System.out.print(ret.get(i) + "\n");
		}		
	}
	public ArrayList<String> binary(int n) {
		ArrayList<String> aList = new ArrayList<String>();
		int[] oneNum = new int[n];
		compute(aList, oneNum, 0);
		return aList;
    }

	private void compute(ArrayList<String> ret, int[] solution, int index) {
		int n = solution.length;
		if (index == n) {			
			ret.add(convert(solution));
			return;
		}
		else {
			// solution[index] = 0+1, do not have to remove, hard go back 
			// index + 1
			for (int i = 0; i <= 1; i++) {
				solution[index] = i;
				compute(ret, solution, index+1);			
			}
		}		
	}
	
	private String convert(int[] oneNum) {
		String binary = "";
		for (int i=0; i<oneNum.length; i++) {
			binary += oneNum[i];
		}
		return binary;
	}
}
