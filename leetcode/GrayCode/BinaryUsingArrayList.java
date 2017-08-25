/*
idea:
N-Queens and Telephone letterCombination

question:

why int[] array works but ArrayList<Integer> not working

*/
import java.util.*;

public class BinaryUsingArrayList {
	public static void main(String[] args) {
		new BinaryUsingArrayList();
	}
	public BinaryUsingArrayList() {		
		ArrayList<ArrayList<Integer>> ret = binary(3);		
		for (int i=0; i<ret.size(); i++) {
			ArrayList<Integer> tmp = ret.get(i);
			for (int j=0; j<tmp.size(); j++) {
				System.out.print(tmp.get(j));
			}
			System.out.print("\n");
		}
	}
	
	public ArrayList<ArrayList<Integer>> binary(int n) {
		ArrayList<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> oneNum = new ArrayList<Integer>();
		binary(0, n, oneNum, aList);
		return aList;
    }
	
	public void binary(int index, int n, ArrayList<Integer> oneNum, ArrayList<ArrayList<Integer>> ret) {
		// if(index == n) {	
		if (oneNum.size() == n) {
			ret.add(oneNum);
			// oneNum.remove(oneNum.size() - 1);
			return;
		}
		else {
			for(int i=0; i<=1; i++) {
				// oneNum.add(i);
				oneNum.set(index, new Integer(i));				
				binary(index+1, n, oneNum, ret);
				oneNum.remove(oneNum.get(index + 1));
			}			
		}
	}
		
	private int BinaryToDecimal(ArrayList<Integer> oneNum) {
		int size = oneNum.size();
		int sum = 0;
		for (int i=0; i<size; i++) {
			sum += oneNum.get(i) * (int)Math.pow(2, size-1-i);
		}
		return sum;
	}
}