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

this version works better

inside for loop recursive call find(), when finding one, copy it, and add to "ret" list, 
meanwhile remove(oneList.size() - 1)

1. do not forget
while() {
	********
	Do not forget increment condition
}

2. 
	remove(Object o) 
	Removes the first occurrence of the specified element from this list, if it is present.
				
Make a copy of each oneList which is one example of ArrayList<String>()
to avoid messed reference

Do not forget remove() otherwise messed up
*/

import java.util.*;

public class PalindromePartition {
	public static void main(String[] args) {
		new PalindromePartition();
	}
	// constructor
	public PalindromePartition() {
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
		find(s, 0, oneList, ret);
			
		return ret;
    }
	
    public void find(String s, int start, ArrayList<String> oneList, ArrayList<ArrayList<String>> ret) {
		if (getLength(oneList) == s.length()) {
			ArrayList<String> copy = new ArrayList<String>();
			for (int j = 0; j < oneList.size(); j++) {
				copy.add(oneList.get(j));
			}			
			// ret.add(oneList);
			ret.add(copy);
		}
		
        for (int i = start; i < s.length(); i++) {
			String tmp = s.substring(start, i+1);
			if (isPalindrom(tmp)) {
				oneList.add(tmp);
				find(s, i+1, oneList, ret);
				// only 2nd works
				// oneList.remove(tmp);
				// remove(Object o) 
				// Removes the first occurrence of the specified element from this list, if it is present.
				oneList.remove(oneList.size() - 1);
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


