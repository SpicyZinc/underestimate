class IsDivisibleBy7
{
	public static void main(String[] args)
	{
		System.out.println("Is 616 divisible by 7: " + isDivisibleBy7(616));
		System.out.println("Is 616 divisible by 7: " + isDivisibleBySeven(616));
	}
	
	public static boolean isDivisibleBy7(int x)
	{
		int a = x;
		while (a>0)
		{
			a -= 7;
		}
		if (a == 0)
			return true;
		else 
			return false;
	}
	
	public static boolean isDivisibleBySeven(int x)
	{
		int a = x;
		if (a < 0) return isDivisibleBySeven(-a);
		if (a == 0 || a == 7) return true;
		if (a < 10) return false;
		/// suppose a = 10m + n
		/// m - 2n divisible by 7 means a divisible by 7
		if(isDivisibleBySeven(a/10-(a-a/10*10)*2))
			return true;
		else 
			return false;
	}
}