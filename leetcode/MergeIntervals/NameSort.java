import java.util.*;

public class NameSort {
    public static void main(String[] args) {
        Name[] nameArray = {
            new Name("John", "Smith"),
            new Name("Karl", "Ng"),
            new Name("Jeff", "Smith"),
            new Name("Tom", "Rich")
        };

        List<Name> names = Arrays.asList(nameArray);
        Collections.sort(names);
        System.out.println(names);
        // String implements Comparable interface and provides a lexicographic implementation for CompareTo method
		
		String s1 = "What";
		String s2 = "what";
		
		if (s1.compareTo(s2) > 0) {
			System.out.println("What > what");
        }
		else {
			System.out.println("What < what");
        }
    }
}


class Name implements Comparable<Name> {
    private final String firstName;
	private final String lastName;

    public Name(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new NullPointerException();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String firstName() { return firstName; }
    public String lastName()  { return lastName;  }

    public boolean equals(Object o) {
        if (!(o instanceof Name))
            return false;
        Name n = (Name) o;
        return n.firstName.equals(firstName) && n.lastName.equals(lastName);
    }

    public int hashCode() {
        return 31 * firstName.hashCode() + lastName.hashCode();
    }

    public String toString() {
		return firstName + " " + lastName;
    }

    public int compareTo(Name n) {
		// because lastName is of Class String, which already implemented interface 
		// can use compareTo() method
        int lastCmp = lastName.compareTo(n.lastName);
        return (lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName));
    }
}