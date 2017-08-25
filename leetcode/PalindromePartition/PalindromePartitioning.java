/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return
  [
    ["aa","b"],
    ["a","a","b"]
  ]
  
  
idea:


general idea:
 
inside for loop recursive call find(), when finding one, copy it, and add to "ret" list, 
meanwhile remove(oneList.size() - 1)

cannot pass "Judge Large" because time limit exceeded
in the base case of recursion
two "if" can get rid of 1st one, still working


oneList inside the final result, 
without copying, cannot get anything
*/

import java.util.*;

public class PalindromePartitioning {
	public static void main(String[] args) {
		new PalindromePartitioning();
	}
	// constructor
	public PalindromePartitioning() {
		String s = "aab";
		System.out.println("The string is: " + s);
		ArrayList<ArrayList<String>> result = partition(s);
		System.out.println("size == " + result.size());
		
		for (int i = 0; i < result.size(); i++) {
			ArrayList<String> one = result.get(i);
			for (int j = 0; j < one.size(); j++) {
				System.out.print(one.get(j) + "  ");
			}
			System.out.print("\n");
		}
	}

	public ArrayList<ArrayList<String>> partition(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
		ArrayList<String> oneList = new ArrayList<String>();
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		find(s, s.length(), oneList, ret);
			
		return ret;
    }
	
    public void find(String s, int len, ArrayList<String> oneList, ArrayList<ArrayList<String>> ret) {
		// get rid of 1st "if", still works
		if (s.length() == 0 || s == null || s.length() == 1) {
			if (getLength(oneList) == len) {
				// ret.add(oneList);
				ArrayList<String> copy = new ArrayList<String>();
				for (int j = 0; j < oneList.size(); j++) {
					copy.add(oneList.get(j));
				}			
				ret.add(copy);
			}
		}
        for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j < s.length() + 1; j++) {
				String tmp = s.substring(i, j);
				String rest = s.substring(j, s.length());
				if ( isPalindrom(tmp) ) {
					oneList.add(tmp);
					find(rest, len, oneList, ret);
					oneList.remove(oneList.size() - 1);
				}
			}
		}
    }
	
	private int getLength(ArrayList<String> aList) {
		int length = 0;
		for (int i = 0; i < aList.size(); i++) {
			length += aList.get(i).length();
		}
		return length;
	}
	
	private boolean isPalindrom(String s) {
		int i = 0;
		while (i < s.length() / 2) {
			if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
				return false;
			}
			i++;
		}
		
		return true;
	}
}


