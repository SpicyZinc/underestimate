public class MultiplyViaRecursion_01{

	public static void main(String[] args){
	
		System.out.println("8 * 9 == " + multiply(8, 9));
		System.out.println("6 * 0 == " + multiply(6, 0));
		System.out.println("0 * 6 == " + multiply(0, 6));
		System.out.println("7 * -6 == " + multiply(7, -6));
	}
	
	public static int multiply(int x, int y){
	    int result = 0;
		
		if(y > 0)
			return result = (x + multiply(x, (y-1)));
		if(y == 0)
			return result = 0;
			
		return result = -multiply(x, -y);	
		
	}
}