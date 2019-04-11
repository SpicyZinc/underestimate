/*
Comparator
@Override
compare
*/

List<Integer> list = new ArrayList<Integer>();
Collections.sort(list, new Comparator<Integer>() {
	@Override
	public int compare(int a, int b) {
		return a - b;
	}
});

int[] a = new int[10];
Arrays.sort(a, new Comparator<Integer>() {
	@Override
	public int compare(int a, int b) {
		return b - a;
	}
});

or

class SizeComparator implements Comparator<HDTV> {
	
}

/*
Comparable
.compareTo
*/

class HDTV implements Comparable<HDTV> {
	int size;
	String brand;

	public HDTV(int size, String brand) {
		this.size = size;
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public int compareTo(HDTV other) {
		if (this.getSize() > other.getSize()) {
			return 1;
		} else if (this.getSize() < other.getSize()) {
			return -1;
		} else {
			return 0;
		}
	}
}

public class Main {
	public static void main(String[] args) {
		HDTV tv1 = new HDTV(55, "Samsung");
		HDTV tv2 = new HDTV(60, "Sony");

		if (tv1.compareTo(tv2) > 0) {
			System.out.println(tv1.getBrand() + " greater " + tv2.getBrand());
		} else {
			System.out.println(tv1.getBrand() + " NOT greater " + tv2.getBrand());	
		}
	}
}

