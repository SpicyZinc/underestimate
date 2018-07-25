/*
Given an array of integers, find if the array contains any duplicates. 
Your function should return true if any value appears at least twice in the array, 
and it should return false if every element is distinct

idea:
method 1. sort the array first, then compare the two consecutive elements; if same, return true
method 2. hash set, add method return true if not added before
*/

import java.util.*;

public class ContainsDuplicate {
    public static void main(String[] args) {
        ContainsDuplicate eg = new ContainsDuplicate();
        Random randomGenerator = new Random(); 
        int[] a = new int[6];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomGenerator.nextInt(100);
            System.out.print(a[i] + " ");
        }
        System.out.print("\n");

        System.out.println("Does this array contain duplicate: " + eg.containsDuplicate(a));        
        System.out.println("Does this array contain duplicate: " + eg.containsDuplicate1(a));        
    }
    // method 1
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] == nums[i]) {
                return true;
            }
        }

        return false;
    }
    // method 2
    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> hs = new HashSet<Integer>();
        for ( int i = 0; i < nums.length; i++ ) {
            if ( !hs.add(nums[i] ) ) {
                return true;
            }
        }

        return false;
    }
}