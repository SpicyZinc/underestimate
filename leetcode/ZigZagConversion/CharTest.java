/**
char[][] default value is '\0';
a space

*/

public class CharTest{
	public static void main(String[] args) {
		char[][] a = new char[2][3];
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[0].length; j++) {
				if(a[i][j] == '\0') {
					System.out.print(a[i][j] + "||");
					int aa = a[i][j];
					// int aa = '0';
					System.out.print(aa);
				}
			}
			System.out.print("\n");
		}
		System.out.println(Character.getNumericValue('a'));
	}
}