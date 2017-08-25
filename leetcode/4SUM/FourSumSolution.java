import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class FourSumSolution {
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		ArrayList<ArrayList<Integer>> aList = new ArrayList<ArrayList<Integer>>();
		int N = num.length;
		for (int i=0; i<N; i++) {
			for (int j=i+1; j<N; j++) {
				for (int k=j+1; k<N; k++) {
					for (int l=k+1; l<N; l++) {
						int sum = num[i] + num[j] + num[k] + num[l];						
						if (sum == target) {
							ArrayList<Integer> tempArray = new ArrayList<Integer>();
							Collections.addAll(tempArray, num[i], num[j], num[k], num[l]);
							aList.add(tempArray);
						}
					}
				}
			}
		}
		return aList;	
    }
	
	public static void main(String[] args) {
		FourSumSolution test = new FourSumSolution();
		int num[] = {1, 2, 3, 4, 12, 43, 32, 53, 8, -10, 4};
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		
		result = test.fourSum(num, 17);		
		System.out.println(result.size());
		for (int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
			
		System.out.println("==========");
		
		result = test.fourSum(num, 55);
		System.out.println(result.size());
		for (int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
    }
}