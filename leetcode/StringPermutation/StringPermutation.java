/*
Given two strings, write a method to decide if one is a permutation of the other.
Example
abcd is a permutation of bcad, but abbe is not a permutation of abe

idea:
no need to c - '0', can use c directly
*/
public class StringPermutation {
    /**
     * @param A: a string
     * @param B: a string
     * @return: a boolean
     */
    public boolean Permutation(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        
        int[] letters = new int[256];
        for (int i = 0; i < A.length(); i++) {
            char charA = A.charAt(i);
            char charB = B.charAt(i);
            letters[charA]++;
            letters[charB]--;
        }
        
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
}