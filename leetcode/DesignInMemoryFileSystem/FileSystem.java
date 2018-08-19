/*
Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that only contains this file's name.
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
You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.

idea:
Trie, TrieNode
similar
*/

class FileSystem {
    class File {
        String content = "";
        Map<String, File> files = new HashMap<>();
        boolean isfile = false;
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

            if (node.isfile) {
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
        node.isfile = true;
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