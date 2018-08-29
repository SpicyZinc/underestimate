/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".

idea:
. current directory
.. parent directory
"cd -" go to last directory or previous directory

"segment" is a string holder used to record "last" real "files" before "/", set to null each time
get rid of // or /, only push "files" names into the stack "simplePath"
as long as not "/", pack them together into a segment string

stack.push() all strings between "/", "/" could be of many "/////"
if segment.equals("."), don't push into stack, ignore it
if segment.equals(".."), pop "file" at the stack's top
 
after all elements are in the stack, pop all elements, 
concatenate string from right to left by adding "/"
*/

import java.util.*;

public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> files = new Stack<>();
        String segment = "";
        int n = path.length();
		// path = "/home/", => "/home"
		// path = "/a/./b/../../c/", => "/c"
        for (int i = 0; i <= n; i++) {
        	// i == path.length() in order to push last segment into stack
            if (i == n || path.charAt(i) == '/') {
                if (segment.equals("..")) {
                    if (files.size() > 0) {
                        files.pop();
                    }
                } else if (segment.length() > 0 && !segment.equals(".")) { // can only be file
                    files.push(segment);
                }
                segment = "";
            } else {
                segment += path.charAt(i);
            }
        }

        if (files.isEmpty()) {
            return "/";
        }
        
        String result = "";
        // can index access stack
        for (int i = 0; i < files.size(); i++) {
            result += "/" + files.get(i);
        }
        
        return result;
    }
}