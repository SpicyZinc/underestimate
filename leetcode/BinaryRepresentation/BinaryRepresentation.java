/*
Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in as a string.
If the fractional part of the number can not be represented accurately in binary with at most 32 characters, return ERROR.

Example
For n = "3.72", return "ERROR".
For n = "3.5", return "11.1".

idea:
convert integer to its binary format,  /2 , %2 add to the front of the string format
convert float to its binary format
关键是如何 convert float to binary
不断 * 2 >= 1, append 1 to result, 用(d - 1)继续

e.g. Convert 2.625 to binary.
We will consider the integer and fractional part separately.
For the fractional part:
0.625   × 2 =   1.25    1   Generate 1 and continue with the rest.
0.25    × 2 =   0.5     0   Generate 0 and continue.
0.5     × 2 =   1.0     1   Generate 1 and nothing remains.

http://sandbox.mc.edu/~bennet/cs110/flt/dtof.html
*/

public class BinaryRepresentation {
    /**
     * @param n: Given a decimal number that is passed in as a string
     * @return: A string
     */
	public String binaryRepresentation(String n) {
		if (n.indexOf(".") == -1) {
			return convertFromInteger(n);
		}

		String[] parts = n.split("\\.");
		String integerPart = convertFromInteger(parts[0]);
		String decimalPart = convertFromFloat(parts[1]);

		if (decimalPart.equals("ERROR")) {
			return decimalPart;
		}
		if (decimalPart.equals("0") || decimalPart.equals("")) {
			return integerPart;
		}
		return integerPart + "." + decimalPart;
	}

	private String convertFromInteger(String str) {
		if (str.equals("") || str.equals("0")) {
			return "0";
		}

		int n = Integer.parseInt(str);
		String binary = "";
		while (n > 0) {
			binary = Integer.toString(n % 2) + binary;
			n /= 2;
		}

		return binary;
	}

	private String convertFromFloat(String str) {
		Set<Double> hs = new HashSet<Double>();
		double d = Double.parseDouble("0." + str);
		String binary = "";

		while (d > 0) {
			// 若以前包含 说明 陷入了循环, return "ERROR" early
			if (binary.length() > 32 || hs.contains(d)) {
				return "ERROR";
			}

			hs.add(d);
			d = d * 2;
			if (d >= 1) {
				binary = binary + "1";
				d = d - 1;
			} else {
				binary = binary + "0";
			}
		}

		return binary;
	}
}