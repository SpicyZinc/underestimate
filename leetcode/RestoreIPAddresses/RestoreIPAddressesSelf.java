/*
Given a string containing only digits, 
restore it by returning all possible valid IP address combinations.

For example: "25525511135",
return ["255.255.11.135", "255.255.111.35"]

idea:
dfs another application
from the 1st one, keep digging deep

*/

public class RestoreIPAddressesSelf {
	public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> ret = new ArrayList<String>();
        if (s == null || s.length() < 4 || s.length() > 12) {
        	return ret;
        }
        String temp = "";
        get(s, temp, ret, 0);

        return ret;
    }

    public void get(String s, String temp, ArrayList<String> holder, int count) {
    	if (count == 3 && isValidIP(s)) {
    		holder.add(temp + s);

    		return;
    	}

    	for (int i = 1; i < 4 && i < s.length(); i++) {
    		String sub = s.substring(0, i);
    		if (isValidIP(sub)) {
    			get(s.substring(i), temp + sub + ".", holder, count + 1);
    		}
    	}
    }

    private boolean isValidIP(String s) {
    	if (s.charAt(0) == '0') {
    		return s.equals("0");
    	}
    	int num = Integer.parseInt(s);

    	return num > 0 && num <= 255;
    }
}