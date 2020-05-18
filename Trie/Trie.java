import java.util.HashMap;
import java.util.Map;

// Trie implementation using hashmap
// Trie operations-
// 1. insert(String word) - insert a word
// 2. search(String word) - search a word - if a word is present in the trie, return true.
// 3. delete(String word) - delete a word - 
// 4. startsWith(String prefix) - does a word with prefix x exists in the trie


class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isEnd;

    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        this.root =  new TrieNode();
    }

    //Insert a word into the trie
    public void insert(String word){
        HashMap<Character, TrieNode> children = root.children;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);

            TrieNode t;
            if(children.containsKey(c)){
                t = children.get(c);
            }else{
                t = new TrieNode(c);
                children.put(c, t);
            }

            children = t.children;

            //set end of word to true
            if(i == word.length() - 1){
                t.isEnd = true;
            }
        }
    }

    // search a word- returns true, if the word is present in the trie
    public boolean search(String word){
        TrieNode t = searchNode(word);
        if( t!= null && t.isEnd){
            return true;
        }else{
            return false;
        }
    }

    private TrieNode searchNode(String str){
        Map<Character, TrieNode> children = root.children; 
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(children.containsKey(c)){
                t = children.get(c);
                children = t.children;
            }else{
                return null;
            }
        }
        return t;
    }

    // startWith- returns true if there exists a word with given prefix
    public boolean startsWith(String prefix) {
        if(searchNode(prefix) == null) 
            return false;
        else
            return true;
    }

    public void delete(String word){
        deleteNode(word, root, 0);
    }

    // delete a word from the trie
    private TrieNode deleteNode(String word, TrieNode node, int index) {
       Map<Character, TrieNode> children = node.children; 

        if(index == word.length()){
            if(node.isEnd){
                node.isEnd = false;
            }
            if(!hasChildren(node)){
                node = null;
            }
            return node;
        }
        if(children.size() == 0){
            return null;
        }
        TrieNode t;
        if(children.containsKey(word.charAt(index))){
            t = deleteNode(word, children.get(word.charAt(index)), index+1);
            //remove key from children if it node does not exist anymore
            if(t == null){
                children.remove(word.charAt(index));
            }
            if(!hasChildren(node)){
                node = null;
            }
        }
        return node;
         
    }

    public boolean hasChildren( TrieNode t) {
        Map<Character, TrieNode> children = t.children;
        if(children.size() > 0){
            return true;
        }
        return false;
    }

    public int countChildren( TrieNode t) {
        Map<Character, TrieNode> children = t.children;
        return children.size();
    }
}
