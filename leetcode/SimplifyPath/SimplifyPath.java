/*
Simplify Path
Given an absolute path for a file (Unix-style), simplify it.

idea:
. current directory
.. parent direcotry
"cd -" go to last direcotry or previous directory

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
        // Start typing your Java solution below
        // DO NOT write main() function
        Stack<String> simplePath = new Stack<String>();
        String segment = "";
		// path = "/home/", => "/home"
		// path = "/a/./b/../../c/", => "/c"
        for (int i = 0; i <= path.length(); i++) {
            // i == path.length() in order to push last segment into stack
            if (i == path.length() || path.charAt(i) == '/') {
                if (segment.equals("..")) {
                    if (simplePath.size() > 0) {
                        simplePath.pop();
                    } 
					else {
						// error, in the test set, this case just ignore
                        // return "/";
                    }
                } 
				else if (segment.equals(".")) {
                    // do nothing
                }
				else if (segment.length() > 0) {
                    simplePath.push(segment);
                }
                segment = "";
            } 
			else {
                segment += path.charAt(i);
            }
        }
		
        String ret = "/";
        for (int i = 0; i < simplePath.size(); i++) {
            if (i > 0) 
				ret += "/";
            ret += simplePath.get(i);
        }
        return ret;
    }


    // cleaner version
    public String simplifyPath(String path) {
        Stack<String> simplePath = new Stack<String>();
        String segment = "";
        for (int i = 0; i <= path.length(); i++) {
            if (i == path.length() || path.charAt(i) == '/') {
                if (segment.equals("..")) {
                    if (simplePath.size() > 0) {
                        simplePath.pop();
                    } 
                } 
                else if (segment.equals(".")) {
                }
                else if (segment.length() > 0) {
                    simplePath.push(segment);
                }
                segment = "";
            } 
            else {
                segment += path.charAt(i);
            }
        }
        
        String ret = "/";
        for (int i = 0; i < simplePath.size(); i++) {
            if (i > 0) 
                ret += "/";
            ret += simplePath.get(i);
        }
        return ret;
    }
}