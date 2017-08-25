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

typical DFS, good example to practice.
key: ret.add( new ArrayList<String>(oneList) )
have to make one copy of placeholder oneList

*/

public class PalindromePartitioningSelf  {
    public ArrayList<ArrayList<String>> partition(String s) {
    	if (s.length() == 0 || s == null) {
    		return null;
    	}
    	ArrayList<String> oneList = new ArrayList<String>();
    	ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
    	partitionHelper(s, oneList, ret);

    	return ret;
    }

    public boolean isPalindrome(String str) {
    	int length = str.length();
    	int i = 0;
    	while (i < length / 2) {
    		if ( str.charAt(i) != str.charAt(length - 1 - i) ) {
    			return false;
    		}
    		i++;
    	}

    	return true;
    }

    public void partitionHelper(String rest, ArrayList<String> oneList, ArrayList<ArrayList<String>> ret) {
    	// base case
    	if (rest.length() == 0) {
    		ret.add( new ArrayList<String>(oneList) );
    	}

    	int length = rest.length();
    	for (int i = 1; i <= length; i++) {
    		String current = rest.substring(0, i);
    		if ( isPalindrome(current) ) {
    			oneList.add(current);
    			String restSubString = rest.substring(i, length);
    			partitionHelper(restSubString, oneList, ret);
    			oneList.remove(oneList.size() - 1);
    		}
    	}
    }
}