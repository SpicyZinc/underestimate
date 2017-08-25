/*
Compare two version numbers version1 and version2.
If version1 > version2 return 1, 
if version1 < version2 return -1, 
otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", 
it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:
0.1 < 1.1 < 1.2 < 13.37

idea:
1. method 1
use . separator to separate to string array
Integer.valueOf()

2. method 2
use . as a separator
1st part is treated as integer
2nd part is treated as string, and recursively call compareVersion()
*/
public class CompareVersionNumbers  {
    // method 1
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        for ( int i = 0; i < Math.max(v1.length, v2.length); i++ ) {
            int a = i < v1.length ? Integer.valueOf(v1[i]) : 0;
            int b = i < v2.length ? Integer.valueOf(v2[i]) : 0;
            if ( a > b ) {
                return 1;
            }
            else if (a < b) {
                return -1;
            }
        }
        
        return 0;
    }
    // method 2
    public int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }

        int fversion1, fversion2;
        String sversion1, sversion2;
        // operation on version1
        if (version1.contains(".")) {
            int pos = version1.indexOf(".");
            fversion1 = Integer.valueOf(version1.substring(0, pos));
            sversion1 = version1.substring(pos + 1, version1.length());
        }
        else {
            fversion1 = Integer.valueOf(version1);
            sversion1 = "0";
        }
        // operation on version2
        if (version2.contains(".")) {
            int pos = version2.indexOf(".");
            fversion2 = Integer.valueOf(version2.substring(0, pos));
            sversion2 = version2.substring(pos + 1, version2.length());
        }
        else {
            fversion2 = Integer.valueOf(version2);
            sversion2 = "0";
        }
        
        if (fversion1 > fversion2) {
            return 1;
        }
        else if (fversion1 < fversion2) {
            return -1;
        }
        else {
            return compareVersion(sversion1, sversion2);
        }
    }
}