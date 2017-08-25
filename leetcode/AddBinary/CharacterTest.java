/**
'0' == 48

*/

public class CharacterTest{
	public static void main(String[] args){
		String s = "11110011010110";
		int[] s1 = new int[s.length()];
		for(int i=0; i<s.length(); i++){
			// System.out.println(s1[i] = (s.charAt(i) - 48));	
			System.out.println(s1[i] = (s.charAt(i) - '0'));
			System.out.println("s1[" + i + "] == " + s1[i]);	
            System.out.println("?" + Character.getNumericValue(s.charAt(i)));
			System.out.println("===");
		}
		
		int x = "1".charAt(0); // String to Character to int
		int y = 'A'; // Character to int
		byte[] z = "B|".getBytes(); // String to bytes
		System.out.println("1 == " + x + " A == " + y + " B == " + z[0] + " | == " + z[1]);
	}

}