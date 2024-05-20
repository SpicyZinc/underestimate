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

note, split("/")
or 1-by-1 character
*/

import java.util.*;

public class SimplifyPath {
    // Better use this
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>(); // create a stack to keep track of directories
        String[] directories = path.split("/"); // split the path by slash '/'
        for (String dir : directories) { // iterate over the directories
            if (dir.equals(".") || dir.isEmpty()) { // ignore the current directory '.' and empty directories
                continue;
            } else if (dir.equals("..")) { // go one level up for double period '..'
                if (!stack.isEmpty()) { // if stack is not empty, pop the top element
                    stack.pop();
                }
            } else { // for any other directory, push it to the stack
                stack.push(dir);
            }
        }
        return "/" + String.join("/", stack);
    }

    // Mon May 13 20:21:22 2024
    public String simplifyPath(String path) {
        Stack<String> directories = new Stack<>();
        int n = path.length();
        String chars = "";

        for (int i = 0; i <= n; i++) {
            char c = i == n ? '0' : path.charAt(i);

            if (c == '/' || i == n) {
                // note, 如果stack空时 而 chars == ".." do nothing
                // GOT STUCK here
                // if (chars.equals("..") && !directories.isEmpty()) {
                if (chars.equals("..")) {
                    if (!directories.isEmpty()) {
                        directories.pop();
                    }
                } else if (!chars.isEmpty() && !chars.equals(".")) {
                    directories.push(chars);
                }
                chars = "";
            } else {
                chars += c;
            }
        }

        return "/" + String.join("/", directories);
    }

    // Sat Apr  6 04:16:13 2024
    public String simplifyPath(String path) {
        Stack<String> directories = new Stack<>();
        String chars = "";
        int n = path.length();
        for (int i = 0; i <= n; i++) {
            char c = i == n ? '0' : path.charAt(i);
            if (c == '/' || i == n) {
                if (chars.equals("..")) {
                    if (!directories.isEmpty()) {
                        directories.pop();
                    }
                } else if (chars.length() > 0 && !chars.equals(".")) {
                    directories.push(chars);
                }
                chars = "";
            } else {
                chars += c;
            }
        }

        if (directories.size() == 0) {
            return "/";
        }
        String result = "";
        // stack can loop this way
        for (int i = 0; i < directories.size(); i++) {
            result += "/" + directories.get(i);
        }
        return result;
    }

    // 09/27/2018
    public String simplifyPath(String path) {
        int n = path.length();
        
        Stack<String> directories = new Stack<>();
        String chars = "";

        for (int i = 0; i <= n; i++) {
            char c = i == n ? '0' : path.charAt(i);

            if (i == n || c == '/') {
                if (chars.equals("..")) {
                    if (!directories.isEmpty()) {
                        directories.pop();
                    }
                } else if (chars.length() > 0 && !chars.equals(".")) { // can only be directory name
                    directories.push(chars);
                }
                chars = "";
            } else {
                chars += c;
            }
        }
        
        // if nothing, return current directory
        if (directories.isEmpty()) {
            return "/";
        }
        
        String result = "";
        for (int i = 0; i < directories.size(); i++) {
            result += "/" + directories.get(i);
        }
        
        return result;
    }

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