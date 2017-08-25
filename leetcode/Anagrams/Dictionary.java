

import java.util.*;
import java.io.*;

public class Dictionary {
	private List<String> words = new ArrayList<String>();
	private final String DICTIONARY_FILE = "dictionary.txt";
	private static int size;
	//--------------------------------------------------
	private static int count;
	private static char[] charArray;
	//--------------------------------------------------
	public void load() throws Exception{
		try {
			FileInputStream fstream = new FileInputStream(DICTIONARY_FILE);
			DataInputStream in = new DataInputStream(fstream);
			InputStreamReader ins = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(ins);
			
			String next;
			while ((next = br.readLine()) != null)   {
			      words.add(next);
			}
			in.close();
		} 
		catch (Exception e) {
			throw new Exception("[ERROR] Encountered a problem reading the dictionary. " + e.getMessage());
		}
	}
	
	public int size(){
		return words.size();
	}
	
	public String[] getSortedWords(){
		return (String[]) words.toArray(new String[words.size()]);
	}
	
	//-------------------------------------------------------------------

	  public static void doAnagram(int newSize) {
	    int limit;
	    if (newSize == 1) // if too small, return;
	      return;
	    // for each position,
	    for (int i = 0; i < newSize; i++) {
	      doAnagram(newSize - 1); // anagram remaining
	      if (newSize == 2) // if innermost,
	        display();
	      rotate(newSize); // rotate word
	    }
	  }

	  // rotate left all chars from position to end
	  public static void rotate(int newSize) {
	    int i;
	    int position = size - newSize;
	    // save first letter
	    char temp = charArray[position];
	    //shift others left
	    for (i = position + 1; i < size; i++)
	      charArray[i - 1] = charArray[i];
	    //put first on right
	    charArray[i - 1] = temp;
	  }

	  public static void display() {
	    System.out.print(++count + " ");
	    for (int i = 0; i < size; i++)
			System.out.print(charArray[i]);
	    System.out.println();
	  }
	//-------------------------------------------------------------
	
	public static void main(String[] args) throws IOException {
				String input = "java";
				size = input.length();
				count = 0;
				charArray = new char[size];
				for (int j = 0; j < size; j++)
				  charArray[j] = input.charAt(j);
				doAnagram(size);
	}	
}


