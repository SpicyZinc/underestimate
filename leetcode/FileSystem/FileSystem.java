/*
Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name.
If it is a directory path, return the list of file and directory names in this directory.
Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content.
If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

Example:
Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
Output:
[null,[],null,null,["a"],"hello"]


Note:

You can assume all file or directory paths are absolute paths which begin with / and do not end with /except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.

idea:
https://www.cnblogs.com/grandyang/p/7007974.html
two hashmaps
directories: directory => directories and files, note: absolute path
{/a=[b], /a/b=[c], /a/b/c=[d], /=[a]}
files: directory => file content
{/a/b/c/d=hello}

ls: 如果该路径存在于files中，说明最后一个字符串是文件，那么我们将文件名取出来返回即可，如果不存在，说明最后一个字符串是文件夹，那么我们到dirs中取出该文件夹内所有的东西返回
mkdir: root directory, need to add /
addContentToFile: if no such file, mkdir first
readContentFromFile: read from files hashmap

method 2
https://discuss.leetcode.com/topic/90000/java-solution-file-class
*/

import java.util.*;
// method 1, use two hashmaps
public class FileSystem {
    Map<String, HashSet<String>> directories;
    Map<String, String> files;

    public static void main(String[] args) {
  		// ["FileSystem", "ls",   "mkdir",     "addContentToFile",   "ls",    "readContentFromFile"]
		// [[],           ["/"],  ["/a/b/c"],  ["/a/b/c/d","hello"], ["/"],   ["/a/b/c/d"]]
		// [null,         [],     null,        null,                 ["a"],   "hello"]

		FileSystem fs = new FileSystem();
		List<String> lsResult = fs.ls("/");
		fs.mkdir("/a/b/c");
		fs.addContentToFile("/a/b/c/d", "hello");
		lsResult = fs.ls("/");
		System.out.println(lsResult);
		String result = fs.readContentFromFile("/a/b/c/d");
		System.out.println(result);
    }

    public FileSystem() {
    	directories = new HashMap<>();
    	files = new HashMap<>();
    	directories.put("/", new HashSet<String>());
    }

    public List<String> ls(String path) {
    	if (files.containsKey(path)) {
    		int lastIndex = path.lastIndexOf('/');
    		List<String> res = new ArrayList<String>();
    		res.add( path.substring(lastIndex + 1) );
    		return res;
    	}
    	HashSet<String> hs = directories.get(path);
    	return new ArrayList<String>(hs);
	}

    public void mkdir(String path) {
    	String[] dirs = path.split("/");
    	String fullPath = "";
    	for (String dir : dirs) {
    		if (dir.length() == 0 || dir == null) {
    			continue;
    		}
    		if (fullPath.length() == 0) {
    			fullPath += "/";
    		}
    		HashSet<String> hs = directories.get(fullPath);
    		if (hs == null) {
    			hs = new HashSet<String>();
    		}
    		hs.add(dir);
    		directories.put(fullPath, hs);
    		// update fullPath
    		fullPath += fullPath.length() > 1 ? "/" + dir : dir;
    	}
	}

    public void addContentToFile(String filePath, String content) {
    	int lastIndex = filePath.lastIndexOf('/');
    	String directory = filePath.substring(0, lastIndex);
    	String file = filePath.substring(lastIndex + 1);

    	if (directory.length() == 0) {
    		directory = "/";
    	}
    	if (!directories.containsKey(directory)) {
    		mkdir(directory);
    	}

    	// directories
    	HashSet<String> hs = directories.get(directory);
		if (hs == null) {
			hs = new HashSet<String>();
		}
		hs.add(file);
		directories.put(directory, hs);

    	// files
    	String fileContent = files.get(filePath);
    	if (fileContent == null || fileContent.length() == 0) {
    		fileContent = content;
    	} else {
			fileContent += content;
    	}
    	files.put(filePath, fileContent);
	}
    
    public String readContentFromFile(String filePath) {
    	if (!files.containsKey(filePath)) {
    		return "";
    	}
    	return files.get(filePath);
	}
}

// method 2, use File subclass
// File is a pseudo concept, it refers to a real file or a directory
class File {
    boolean isFile = false;
    Map<String, File> children = new HashMap<>();
    String content = "";
}

public class FileSystem {
    public static void main(String[] args) {
  		// ["FileSystem", "ls",   "mkdir",     "addContentToFile",   "ls",    "readContentFromFile"]
		// [[],           ["/"],  ["/a/b/c"],  ["/a/b/c/d","hello"], ["/"],   ["/a/b/c/d"]]
		// [null,         [],     null,        null,                 ["a"],   "hello"]
		FileSystem fs = new FileSystem();
		List<String> lsResult = fs.ls("/");
		fs.mkdir("/a/b/c");
		fs.addContentToFile("/a/b/c/d", "hello");
		lsResult = fs.ls("/");
		System.out.println(lsResult);
		String result = fs.readContentFromFile("/a/b/c/d");
		System.out.println(result);
    }

	File root;
	public FileSystem() {
		root = new File();
	}
	public List<String> ls(String path) {
		List<String> result = new ArrayList<String>();
		File node = root;
		String[] dirs = path.split("/");
		String last = "";
		for (String dir : dirs) {
			if (dir.length() == 0) {
				continue;
			}
			if (!node.children.containsKey(dir)) {
				return result;
			}
			node = node.children.get(dir);
			last = dir;
		}
		if (node.isFile) {
			result.add(last);
		}
		else {
			for (String key : node.children.keySet()) {
				result.add(key);
			}
		}
		Collections.sort(result);
		return result;
	}
	public void mkdir(String path) {
		findNode(path);
	}
	public void addContentToFile(String filePath, String content) {
		File node = findNode(filePath);
		node.isFile = true;
		node.content += content;
	}
	public String readContentFromFile(String filePath) {
		File node = findNode(filePath);
		return node.content;
	}
	// helper to find file node
	private File findNode(String path) {
		String[] dirs = path.split("/");
		File node = root;
		for (String dir : dirs) {
			if (dir.length() == 0) {
				continue;
			}
			if (!node.children.containsKey(dir)) {
				File newFileNode = new File();
				node.children.put(dir, newFileNode);
			}
			node = node.children.get(dir);
		}

		return node;
	}
}
