/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

idea:
http://yuanhsh.iteye.com/blog/2185976

1. direct thought
two lists, one is result, one is all 10-letter substrings' list
if appears in 10-letter substring but NOT in result list, add to result list
if not, add to 10-letter substrings' list
2. bit operation
*/

import java.util.*;

public class RepeatedDNASequences {
    public static void main(String[] args) {
    	RepeatedDNASequences eg = new RepeatedDNASequences();
    	String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

    	List<String> result = eg.findRepeatedDnaSequences(s);
    	for ( int i = 0; i < result.size(); i++ ) {
    		System.out.print(result.get(i) + "  ");
    	}
    	System.out.println();

    	List<String> res = eg.findRepeatedDnaSequencesBitOperation(s);
		for ( int i = 0; i < res.size(); i++ ) {
    		System.out.print(res.get(i) + "  ");
    	}
    	System.out.println();    	

    	// System.out.println(Integer.toBinaryString('A'));
    	// //  1000001
    	// // 01000001　
    	// int sum = 1;
    	// sum = sum << 2;
    	// System.out.println(sum);
    	// System.out.println(Integer.toBinaryString(sum));
    	// int x = sum & 0xFFFFF;
    	// System.out.println(Integer.toBinaryString(x));

    	// System.out.println(Integer.toBinaryString(1 & 0xFFFFF));
    	// System.out.println(Integer.toBinaryString(2 & 0xFFFFF));
    }

    public List<String> findRepeatedDnaSequences(String s) {
    	List<String> res = new ArrayList<String>();
    	if ( s.length() < 10 ) {
    		return res;
    	}
    	List<String> stringList = new ArrayList<String>();
    	for ( int i = 0; i < s.length() - 9; i++ ) {
    		String temp = s.substring(i, i + 10);
    		if ( stringList.contains(temp) && !res.contains(temp) ) {
    			res.add(temp);
    		}
    		else {
    			stringList.add(temp);
    		}
    	}

    	return res;
    }

    public List<String> findRepeatedDnaSequencesBitOperation(String s) {
        List<String> result = new ArrayList<String>();
        int size = s.length();
        if (size < 10) {
        	return result;
        }

        Map<Character, Integer> hm = new HashMap<Character, Integer>();
        hm.put('A', 0); // 00
        hm.put('C', 1); // 01
        hm.put('G', 2); // 10
        hm.put('T', 3); // 11

        int substringHash = 0;
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        for ( int i = 0; i < size; i++ ) {
        	// left shift 2 is because mapping each character to a 2-bit number
        	substringHash = (substringHash << 2) + hm.get( s.charAt(i) ) & 0xFFFFF;
        	if ( i < 9 ) {
        		continue;
        	}
        	Integer cnt = frequency.get(substringHash);
        	if ( cnt != null && cnt == 1 ) {
        		result.add(s.substring(i-9, i+1));
        	}
        	frequency.put(substringHash, cnt == null ? 1 : cnt + 1);
        }

        return result;
    }
}