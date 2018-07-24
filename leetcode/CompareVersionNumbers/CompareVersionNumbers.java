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
Math.max() to loop not the min()
Integer.valueOf()

2. method 2
recursion
first occurrence of '.', then the rest will be continually applied with compareVersion()

1st part is treated as integer
2nd part is treated as string, and recursively call compareVersion()
*/
public class CompareVersionNumbers  {
    // method 1
    public int compareVersion(String version1, String version2) {
        String[] m1 = version1.split("\\.");
        String[] m2 = version2.split("\\.");
        
        int len = Math.max(m1.length, m2.length);
        for ( int i = 0; i < len; i++ ) {
            int val1 = i < m1.length ? Integer.valueOf(m1[i]) : 0;
            int val2 = i < m2.length ? Integer.valueOf(m2[i]) : 0;
            if ( val1 > val2 ) {
                return 1;
            } else if (val1 < val2) {
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

        String[] parts1 = integerAndFraction(version1);
        String[] parts2 = integerAndFraction(version2);

        int firstVersion1 = Integer.valueOf(parts1[0]);
        int firstVersion2 = Integer.valueOf(parts2[0]);

        String restVersion1 = parts1[1];
        String restVersion2 = parts2[1];
        
        if (firstVersion1 > firstVersion2) {
            return 1;
        } else if (firstVersion1 < firstVersion2) {
            return -1;
        } else {
            return compareVersion(restVersion1, restVersion2);
        }
    }

    // helper to extract integer and fraction part of the number
    private String[] integerAndFraction(String version) {
        String[] parts = new String[2];
        if (version.contains(".")) {
            int pos = version.indexOf(".");
            parts[0] = version.substring(0, pos);
            parts[1] = version.substring(pos + 1);
        } else {
            parts[0] = version;
            parts[1] = "0";
        }
        
        return parts;
    }
}