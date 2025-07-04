import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
 
class TrieNode {
    char data;
    LinkedList children; 
    TrieNode parent;
    boolean isEnd;
 
    public TrieNode(char c) {
        data = c;
        children = new LinkedList();
        isEnd = false;
    }  
    
    public TrieNode getChild(char c) {
        if (children != null) {
            for (TrieNode eachChild : children) {
                if (eachChild.data == c) {
                    return eachChild;
                }
            }
        }

        return null;
    }
    
    protected List getWords() {
       List list = new ArrayList();      
       if (isEnd) {
           list.add(toString());
       }
       
       if (children != null) {
           for (int i = 0; i < children.size(); i++) {
              if (children.get(i) != null) {
                 list.addAll(children.get(i).getWords());
              }
           }
       }

       return list;
    }
    
    public String toString() {
        if (parent == null) {
            return "";
        } else {
            return parent.toString() + new String(new char[] {data});
        }
    }
}
 
class TrieBetter {
    private TrieNode root;
 
    public TrieBetter() {
        root = new TrieNode(' '); 
    }
 
    public void insert(String word) {
        if (search(word) == true) {
            return;    
        }
        
        TrieNode current = root; 
        TrieNode pre ;
        for (char ch : word.toCharArray()) {
            pre = current;
            TrieNode child = current.getChild(ch);
            if (child != null) {
                current = child;
                child.parent = pre;
            } else {
                 current.children.add(new TrieNode(ch));
                 current = current.getChild(ch);
                 current.parent = pre;
            }
        }
        current.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode current = root;      
        for (char ch : word.toCharArray()) {
            if (current.getChild(ch) == null) {
                return false;
            } else {
                current = current.getChild(ch);
            }
        }

        return current.isEnd;
    }
    
    public List autocomplete(String prefix) {     
        TrieNode lastNode = root;
        for (int i = 0; i< prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));      

            if (lastNode == null) {
                return new ArrayList();
            }
        }
        
        return lastNode.getWords();
    }
}    
 
public class AutocompleteWithTrie {
     public static void main(String[] args) {            
            TrieBetter t = new TrieBetter();            
            t.insert("amazon"); 
            t.insert("amazon prime"); 
            t.insert("amazing");             
            t.insert("amazing spider man"); 
            t.insert("amazed");
            t.insert("alibaba");
            t.insert("ali express");
            t.insert("ebay");
            t.insert("walmart");          
            List a = t.autocomplete("amaz");
            for (int i = 0; i < a.size(); i++) {
                System.out.println(a.get(i));
            }
      }
}