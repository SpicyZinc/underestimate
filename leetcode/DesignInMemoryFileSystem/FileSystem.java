/*
Design an in-memory file system to simulate the following functions:

ls: Given a path in string format.
If it is a file path, return a list that only contains this file's name.
If it is a directory path, return the list of file and directory names in this directory.
Your output (file and directory names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
If the middle directories in the path don't exist either, you should create them as well. This function has void return type.

addContentToFile: Given a file path and file content in string format.
If the file doesn't exist, you need to create that file containing given content.
If the file already exists, you need to append given content to original content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.

Example:
Input: 
["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
Output:
[null,[],null,null,["a"],"hello"]

Explanation:
https://leetcode.com/static/images/problemset/filesystem.png

Note:
You can assume all file or directory paths are absolute paths which begin with / and do not end with /except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.

idea:
method 1
https://www.cnblogs.com/grandyang/p/6944331.html
use two hashmaps,
note, absolute path
第一个map directory => directories and files
directories: directory => directories and files 
e.g. {/a=[b], /a/b=[c], /a/b/c=[d], /=[a]}
files: directory => file content
e.g. {/a/b/c/d=hello}

ls:
如果该路径存在于files中
说明最后一个字符串是文件
那么我们按照文件名取出来返回即可
如果不存在
说明最后一个字符串是文件夹
那么到dirs中取出该文件夹内所有的东西返回

mkdir: root directory, need to add /
addContentToFile: if no such file, mkdir first
readContentFromFile: read from files hashmap

method 2
similar to Trie, TrieNode
https://discuss.leetcode.com/topic/90000/java-solution-file-class
*/

import java.util.*;

public class FileSystem {
	// 12/02/2018
    public Map<String, Set<String>> directories;
    public Map<String, String> files;

    public FileSystem() {
        directories = new HashMap<>();
        files = new HashMap<>();
    }
    
    public List<String> ls(String path) {
        if (files.containsKey(path)) {
            int lastIndex = path.lastIndexOf("/");
            String file = path.substring(lastIndex + 1);
            
            List<String> result = new ArrayList<>();
            result.add(file);
            return result;
        }
        
        if (directories.containsKey(path)) {
            Set<String> hs = directories.get(path);
            List<String> result = new ArrayList<>(hs);
            Collections.sort(result);
            return result;
        }
        
        return new ArrayList<String>();
    }
    
    public void mkdir(String path) {
        if (path.length() == 0 || path == null) {
            return;
        }
        
        String[] dirs = path.split("/");
        String absolutePath = "";

        for (String dir : dirs) {
            // skip empty directory
            if (dir.length() == 0 || dir == null) {
				continue;
			}
            if (absolutePath.length() == 0) {
                absolutePath += '/';
            }
            
            Set<String> hs = directories.get(absolutePath);
            if (hs == null) {
                hs = new HashSet<>();
            }
            
            hs.add(dir);
            directories.put(absolutePath, hs);
            
            // absolutePath = (absolutePath.length() > 1 ? "/" : "") + dir;
            absolutePath += absolutePath.length() > 1 ? "/" + dir : dir;
        }
    }

    public void addContentToFile(String filePath, String content) {
        int lastIndex = filePath.lastIndexOf("/");
        String directory = filePath.substring(0, lastIndex);
        String file = filePath.substring(lastIndex + 1);
        
        // if directory is empty
        if (directory.length() == 0 || directory == null) {
            directory = "/";
        }
        // if directories not contain directory, make it first
        if (!directories.containsKey(directory)) {
            mkdir(directory);
        }
        
        // directories
        Set<String> hs = directories.get(directory);
        if (hs == null) {
            hs = new HashSet<>();
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


	public static void main(String[] args) {
// "FileSystem"            []                    		null
// "mkdir"                 ["/zijzllb"]          		null,
// "ls"                    ["/"]                 		["zijzllb"],
// "ls"                    ["/zijzllb"]          		[],
// "mkdir"                 ["/r"]                		null,
// "ls"                    ["/"]                 		["r","zijzllb"],
// "ls"                    ["/r"]                 		[],
// "addContentToFile"      ["/zijzllb/hfktg","d"]      null,
// "readContentFromFile"   ["/zijzllb/hfktg"]          "d",
// "ls"                    ["/"]                       ["r","zijzllb"],
// "readContentFromFile"   ["/zijzllb/hfktg"]]         "d"
		FileSystem fs = new FileSystem();
		List<String> lsResult = fs.ls("/");
		System.out.println(lsResult);

		fs.mkdir("/zijzllb");
		lsResult = fs.ls("/");
		System.out.println(lsResult);

		lsResult = fs.ls("/zijzllb");
		System.out.println(lsResult);

		fs.mkdir("/r");

		lsResult = fs.ls("/");
		System.out.println(lsResult);

		lsResult = fs.ls("/r");
		System.out.println(lsResult);

		fs.addContentToFile("/zijzllb/hfktg", "d");
		lsResult = fs.ls("/");
		System.out.println(lsResult);

		String result = fs.readContentFromFile("/zijzllb/hfktg");
		System.out.println(result);

		lsResult = fs.ls("/");
		System.out.println(lsResult);

		result = fs.readContentFromFile("/zijzllb/hfktg");
		System.out.println(result);
	}

	Map<String, Set<String>> directories;
	Map<String, String> files;

	public FileSystem() {
		directories = new HashMap<>();
		files = new HashMap<>();
		directories.put("/", new HashSet<String>());
	}

	public List<String> ls(String path) {
		if (files.containsKey(path)) {
			int lastIndex = path.lastIndexOf('/');
			List<String> result = new ArrayList<String>();
			result.add(path.substring(lastIndex + 1));

			return result;
		}

		if (directories.containsKey(path)) {
			List<String> result = new ArrayList<String>(directories.get(path));
			// damn sort, cost me the whole night
			Collections.sort(result);
			
			return result;
		}

		return new ArrayList<String>();
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
			Set<String> hs = directories.get(fullPath);
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
		Set<String> hs = directories.get(directory);
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
    String content = "";
    Map<String, File> children = new HashMap<>();
    boolean isFile = false;
}

public class FileSystem {
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
		} else {
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


class FileSystem {
    class File {
        String content = "";
        Map<String, File> files = new HashMap<>();
        boolean isFile = false;
    }

    File root;
    public FileSystem() {
        root = new File();
    }

    public List<String> ls(String path) {
        File node = root;
        List<String> files = new ArrayList<>();

        if (!path.equals("/")) {
            String[] dirs = path.split("/");
            int size = dirs.length;
            for (int i = 1; i < size; i++) {
                node = node.files.get(dirs[i]);
            }

            if (node.isFile) {
                files.add(dirs[size - 1]);

                return files;
            }
        }

        files = new ArrayList<>(node.files.keySet());
        Collections.sort(files);

        return files;
    }

    public void mkdir(String path) {
        File node = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++) {
            if (!node.files.containsKey(d[i])) {
                node.files.put(d[i], new File());
            }

            node = node.files.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {
        File node = root;
        String[] dirs = filePath.split("/");
        for (int i = 1; i < dirs.length - 1; i++) {
            node = node.files.get(dirs[i]);
        }
        if (!node.files.containsKey(dirs[dirs.length - 1])) {
            node.files.put(dirs[dirs.length - 1], new File());
        }

        node = node.files.get(dirs[dirs.length - 1]);
        node.isFile = true;
        node.content = node.content + content;
    }

    public String readContentFromFile(String filePath) {
        File node = root;
        String[] dirs = filePath.split("/");
        for (int i = 1; i < dirs.length - 1; i++) {
            node = node.files.get(dirs[i]);
        }

        return node.files.get(dirs[dirs.length - 1]).content;
    }
}


/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */