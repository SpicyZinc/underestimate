/*
Given a set of distinct integers, S, return all possible subsets.
Note:
    Elements in a subset must be in non-descending order.
    The solution set must not contain duplicate subsets.
	
idea:

BEST VERSION

totally there is pow(2.0, S.length) of combinations (results)
loop through 0 to pow(2.0, S.length)-1
each number if converted to a binary format '1010', will denote which position in the set gets selected
right shift the length of set times 


build up a new array having the same length as original set is
initial value is all false, corresponding to if the element is in the subset
add binary 1 at the end of boolean array, repeat until overflow

OR:
DFS, no explanation, must remember
*/
import java.util.*;

public class Subsets {
	public static void main(String[] args) {
		int[] source0 = {1, 2, 3, 4};
		int[] source1 = {1, 3, 3};
		Subsets aTest = new Subsets();
		ArrayList<ArrayList<Integer>> res = aTest.subsets(source0);
		System.out.println("res size == " + res.size());
		for (int i=0; i<res.size(); i++) {
			System.out.print("Element " + (i+1) + ":  "); 
			ArrayList<Integer> tmp = res.get(i);
			for (int j=0; j<tmp.size(); j++) {
				System.out.print(tmp.get(j) + "  ");
			}
			System.out.print("\n");
		}		
	}
	
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		Arrays.sort(S);
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int length = (int)Math.pow(2.0, S.length);
		for (int i=0; i<length; i++) {
			ArrayList<Integer> element = new ArrayList<Integer>();
			int temp = i;
			// walk through all elements in array, use binary format to decide which position of the set gets selected
			for (int j=0; j<S.length; j++) { 
				if ((temp & 1) == 1) {
					element.add(S[j]);
				}
				temp >>= 1;				
			}
			res.add(element);
		}
		
		return res;
	}

    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
		if (S.length == 0 || S == null) {
			return null;
		} 
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		boolean[] b = new boolean[S.length];
		while (true) {
			ArrayList<Integer> element = new ArrayList<Integer>();
			for (int i=0; i<S.length; i++) {
				if (b[i] == true) {
					element.add(S[i]);
				}
			}
			res.add(element);
			// update boolean array
            boolean carry = true;
            for (int i = 0; carry && i < b.length; i++) {
                carry = carry && b[i];
                b[i] = !b[i];
            }
            if (carry) {
				break;
            }
		}
		
		return res;
    }

    // typical DFS
    // if no duplicates in S, sort or not sort does not matter, but leetcode requires all subsets in [smaller, bigger]
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {  
        ArrayList<ArrayList<Integer>>   list = new ArrayList<ArrayList<Integer>>();  
        ArrayList<Integer>              path = new ArrayList<Integer>();  
          
        list.add(new ArrayList<Integer>());  
        Arrays.sort(S);  
        dfs(0, S, path, list);  
        
        return list;  
    }  
      
    public void dfs(int index, int[] S, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> list) {  
        for (int i = index; i < S.length; i++) {  
            path.add(S[i]);  
            list.add(new ArrayList<Integer>(path));  
            dfs(i + 1, S, path, list);  
            path.remove(path.size() - 1);  
        }  
    }
}