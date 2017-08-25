/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.

idea:
HashSet is used to remove duplicate
.add() returns true if this set did not already contain the specified element
.contains returns true if this set contains the specified element

Sort array first in a ascending order
once target < sum, l--
once target > sum, k++

once target == sum, 
l-- and k++ together

use hashset to judge if this result exists or not

for (int i=0; i<num.length-1; i++) {
	for (int j=i+1; j<num.length; j++) {
		for (int k=j+1, l=num.length-1; k<l; ) {
			
		}
	}
}
*/
import java.util.*;
public class FourSum {
    public ArrayList<ArrayList> fourSum(int[] num, int target) {
        Arrays.sort(num);
        HashSet<ArrayList> hs = new HashSet<ArrayList>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (num.length < 4) {
			return result;
        }
		
        for (int i = 0; i < num.length - 1; i++) {
            for (int j = i + 1; j < num.length; j++) {
                for (int k = j + 1, l = num.length - 1; k < l;) {                     
					int sum = num[i] + num[j] + num[k] + num[l];                     
					if (sum > target) {
                        l--;
                    }
                    else if (sum < target) {
                        k++;
                    }
                    else if (sum == target) {
                        ArrayList<Integer> found = new ArrayList<Integer>();
                        found.add(num[i]);
                        found.add(num[j]);
                        found.add(num[k]);
                        found.add(num[l]);
                        if (hs.add(found)) {                            
                            result.add(found);
                        } 
                        k++;
                        l--; 
                    }
                }
            }
        }

        return result;
    }
    // self written version passed test
    public List<List<Integer>> fourSum(int[] num, int target) {
        HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int n = num.length;
        if (n < 4) {
            return ret;
        }
        
        Arrays.sort(num);
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1, l = n - 1; k < l;) {
                    int temp = num[i] + num[j] + num[k] + num[l];
                    if ( temp < target ) {
                        k++;
                    }
                    else if ( temp > target ) {
                        l--;
                    }
                    else {
                        List<Integer> item = new ArrayList<Integer>();
                        item.add(num[i]);
                        item.add(num[j]);
                        item.add(num[k]);
                        item.add(num[l]);
                        if (hs.add(item)) {
                            ret.add(item);
                        }
                        k++;
                        l--;
                    }
                }
            }
        }
        
        return ret;
    }
    // most recent
    public List<List<Integer>> fourSum(int[] numbers, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> hs = new HashSet<List<Integer>>();
        Arrays.sort(numbers);
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1, l = n - 1; k < l;) {
                    int sum = numbers[i] + numbers[j] + numbers[k] + numbers[l];
                    if (sum > target) {
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        List<Integer> path = new ArrayList<Integer>();
                        Collections.addAll(path, numbers[i], numbers[j], numbers[k], numbers[l]);
                        if (hs.add(path)) {
                            result.add(path);
                        }
                        k++;
                        l--;
                    }
                }
            }
        }

        return result;
    }
}