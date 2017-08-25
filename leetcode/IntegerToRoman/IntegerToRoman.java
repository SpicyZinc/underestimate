/*
I 	1
V 	5
X 	10
L 	50
C 	100
D 	500
M 	1000

multiple of 1
The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more. 
(They may appear more than three times if they appear non-sequentially, such as XXXIX.)

multiple of 5 
"D", "L", and "V" can never be repeated.

"I" can be subtracted from "V" and "X" only. 
"X" can be subtracted from "L" and "C" only. 
"C" can be subtracted from "D" and "M" only.

multiple of 5 
"V", "L", and "D" can never be subtracted.

for easier way:
3 times in succession so 40 400 etc are needed
because these will be repeated 4 times in succession which is not allowed

while 60 70 80 can be divided to 50 + 
but 90 cannot so 90 needs separately considered
*/
public class IntegerToRoman {
	public static void main(String[] args) {
		IntegerToRoman aTest = new IntegerToRoman();
		int n = 1999;
		
		System.out.println("1999 == " + aTest.intToRoman(n));
	}
    // self written version passed test
    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        // thousand
        result.append(digitSymbolConverter( num / 1000 % 10, '0', '0', 'M' ));
        // hundred
        result.append(digitSymbolConverter( num / 100 % 10, 'M', 'D', 'C' ));
        // ten
        result.append(digitSymbolConverter( num / 10 % 10, 'C', 'L', 'X' ));
        // one
        result.append(digitSymbolConverter( num % 10, 'X', 'V', 'I' ));
        
        return result.toString();
    }
    
    public String digitSymbolConverter(int num, char ten, char five, char one) {
        String ret = "";
        if (num == 9) {
            ret += one;
            ret += ten;
        }
        else if (num >= 5) {
            ret += five;
            while (num > 5) {
                ret += one;
                num--;
            }
        }
        else if (num == 4) {
            ret += one;
            ret += five;
        }
        else if (num <= 3 && num > 0) {
            while (num > 0) {
                ret += one;
                num--;
            }
        }
        
        return ret;
    }


	public StringBuilder intToRoman(int num) {
		StringBuilder roman = new StringBuilder();
        // Thousand
        roman.append(digitSymbol(num / 1000 % 10, '0', '0', 'M')); // 1000 corresponds to oneSymbol
		// Hundred
        roman.append(digitSymbol(num / 100 % 10, 'M', 'D', 'C')); // 100 corresponds to oneSymbol
		// Ten
        roman.append(digitSymbol(num / 10 % 10, 'C', 'L', 'X')); // 10 corresponds to oneSymbol
		// One
        roman.append(digitSymbol(num % 10, 'X', 'V', 'I')); // 1 corresponds to oneSymbol
        
        return roman;        
    }
    
    public String digitSymbol(int num, char tenSymbol, char fiveSymbol, char oneSymbol) {
        String result = "";
        if (num == 9) {
            result += oneSymbol;
            result += tenSymbol;
        }
        else if (num >= 5) {
            result += fiveSymbol;
            while (num > 5) {
                result += oneSymbol;
                num--;
            }
        }
        else if (num == 4) {
            result += oneSymbol;
            result += fiveSymbol;
        }
        else if (num > 0 && num < 4)
            while (num > 0) {
                result += oneSymbol;
                num--;
            }
                
        return result;
    }

    // easier way
    public String intToRoman(int num) {
        Map<Integer, String> arabic = new HashMap<Integer, String>();
        arabic.put(1000, "M");
        arabic.put(900, "CM");
        arabic.put(500, "D");
        arabic.put(400, "CD");
        arabic.put(100, "C");
        arabic.put(90, "XC");
        arabic.put(50, "L");
        arabic.put(40, "XL");
        arabic.put(10, "X");
        arabic.put(9, "IX");
        arabic.put(5, "V");
        arabic.put(4, "IV");
        arabic.put(1, "I");

        int[] nums = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (num > 0) {
            int tmp = nums[i];
            int times = num / tmp;
            num -= tmp * times;
            for (; times>0; times--) {
                sb.append(arabic.get(tmp));
            }

            i++;
        }
        
        return sb.toString();    
    }
}