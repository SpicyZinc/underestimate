/*
An abbreviation of a word follows the form <first letter><number><last letter>.
Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n

Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]
isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true

idea:
make sure understand the problem
find if the given word has the unique abbreviation in the dictionary
by unique, the dictionary does not have it before, including the real value the key refers to
use hashmap, compare (key => value) the value vs word
*/
import java.util.*;
public class UniqueWordAbbreviation {
	public static void main(String[] args) {
		UniqueWordAbbreviation eg = new UniqueWordAbbreviation();
		String[] dictionary = {"deer", "door", "cake", "card"};
		eg.ValidWordAbbr(dictionary);
		boolean isUnique = eg.isUnique("cane");
		System.out.println(isUnique);
	}
    private Map<String, String> hm = new HashMap<String, String>();

    public void ValidWordAbbr(String[] dictionary) {
        for (int i = 0; i < dictionary.length; i++){
            String key = abbreviate(dictionary[i]);
            if (!hm.containsKey(key)) {
                hm.put(key, dictionary[i]);
            } else {
                hm.put(key, "");
            }
        }
    }

    public boolean isUnique(String word) {
        String x = abbreviate(word);
        if (hm.containsKey(x)) {
        	return hm.get(x).equals(word);
        }
        return true;
    }

    private String abbreviate(String str) {
        return str.charAt(0) + Integer.toString(str.length() - 2) + str.charAt(str.length()-1);
    }
}

// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");