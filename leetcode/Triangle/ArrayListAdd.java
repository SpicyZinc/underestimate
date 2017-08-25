/**
idea:

ArrayList.add() is not set() method, surpress others to the behind

*/
import java.util.*;

public class ArrayListAdd {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i=0; i<3; i++) {
			list.add(0, i+1);
		}
		for (Integer i : list) {
			System.out.println(i);
		}
		for (int i=0; i<3; i++) {
			System.out.println("position " + i + " == " + list.get(i));
		}
	}
}