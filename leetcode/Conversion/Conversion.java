/*
common methods for conversion

Convert String to Integer
Convert Integer to String
*/

class Conversion {
	public static void main(String[] args) {
		System.out.println("Convert String to Integer");
		int i = Integer.parseInt("000123");
        System.out.println("String 000123 turns into Integer : " + i);
		int ii = Integer.valueOf("0000000081");
        System.out.println("Integer.valueOf(0000000081): " + ii);
		float f = Float.parseFloat("0.123");
		System.out.println("f: " + f);
		double d = Double.parseDouble("0.0123");
		System.out.println("d: " + d);
		System.out.println("Convert Integer to String");
		String price = "" + 123;
		System.out.println("Integer 123 turns into String : " + price);
		System.out.println("Integer 123 turns into String : " + String.valueOf(123));
	}
}