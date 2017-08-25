
class SquareMinusCombinationOfTwo{
   
	private static void printPermutations(int[] numbers) {
		int count = 0;
		for(int i=0; i<numbers.length; i++) {
			for (int j=i; j<numbers.length; j++) {
				System.out.println(++count + ": [" + numbers[i] + "-"+ numbers[j] +"]");
			}
		}
	}

	public static void main(String[] args){
		int[] numbers={1, 2, 3, 4};
		printPermutations(numbers);
	}
}
