/*
idea:
N-Queens and Telephone letterCombination
for loop call recursion
*/
import java.util.*;

public class Binary {
    public static void main(String[] args) {
        new Binary();
    }
    public Binary() {       
        List<String> ret = binary(5);
        System.out.println(ret);
        System.out.println(ret.size());
    }
    public List<String> binary(int n) {
        List<String> aList = new ArrayList<>();
        String[] oneNum = new String[n];
        compute(aList, oneNum, 0);
        return aList;
    }

    private void compute(List<String> ret, String[] solution, int index) {
        int n = solution.length;
        if (index == n) {           
            ret.add(String.join("", solution));
            return;
        } else {
            // solution[index] = 0+1, do not have to remove, hard go back 
            // index + 1
            for (int i = 0; i <= 1; i++) {
                solution[index] = "" + i;
                compute(ret, solution, index + 1);          
            }
        }       
    }
}
