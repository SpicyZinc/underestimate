/*
pay-pal on-site
unique s
given output, the minimum number of machines to give output string
each machine sequentially print s 
*/

import java.util.*;

class MinMachine {
	public static void main(String[] args) {
		MinMachine eg = new MinMachine();

		String s = "ABC";
		String output = "ABABCABCC";

		int min = eg.getMin(s, output);

		System.out.println(min);
	}

	public int getMin(String s, String output) {
		List<String> machines = new ArrayList<>();
		machines.add(output.charAt(0) + "");

		for (int i = 1; i < output.length(); i++) {
			char c = output.charAt(i);

			boolean useOldMachine = false;

			for (int j = 0; j < machines.size(); j++) {
				String str = machines.get(j);
				if (str.equals(s)) {
					machines.remove(j);
					continue;
				}

				String newStr = str + c;

				if (newStr.equals(s) || s.indexOf(newStr) != -1) {
					machines.remove(j);
					machines.add(newStr);

					useOldMachine = true;

					break;
				}
			}

			if (!useOldMachine) {
				machines.add("");
			}
		}

		return machines.size();
	}	
}