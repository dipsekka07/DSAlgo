import sun.text.normalizer.Trie;

/*
------------------Trie implementation using array----------------
    Trie operations:
    1. insert(String word) - insert a word
    2. search(String word) - search a word - if a word is present in the trie, return true
    3. delete(String word) - delete a word
    4. startsWith(String prefix) - does a word with prefix x exists in the trie
*/

public class TrieArrayImp {
    class TrieNode {
        TrieNode[] arr;
        boolean isEnd;
        int countChildren;

        public TrieNode() {
            this.arr = new TrieNode[26];
            this.countChildren = 0;
        }
    }

    private TrieNode root;

    public TrieArrayImp() {
        this.root = new TrieNode();
    }

    //Insert a word into the trie
    public void insert(String word) {
        TrieNode p = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            int index = c-'a';
            if(p.arr[index] == null) {
                TrieNode temp = new TrieNode();
                p.arr[index] = temp;
                p.countChildren++;
                p = temp;
            } else {
                p = p.arr[index];
            }
        }
        //set end of word to true
        p.isEnd = true;
    }


    // search a word- returns true, if the word is present in the trie
    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if(p == null){
            return false;
        }
        return true;
    }

    private TrieNode searchNode(String word) {
        TrieNode p = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            int index = c - 'a';
            if(p.arr[index] != null){
                p = p.arr[index];
            }else{
                return null;
            }
        }
        if(p == root)
            return null;
        return p;
    }

    // startWith- returns true if there exists a word with given prefix
    public boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if( p == null ) {
            return false;
        }else{
            return true;
        }
    }

    public boolean hasChildren( TrieNode t) {
        return t.countChildren > 0 ? true : false;
    }

    public int countChildren( TrieNode t) {
        return t.countChildren;
    }

    public void delete(String word) {
        deleteNode(word, root, 0);
    }

    private TrieNode deleteNode(String word, TrieNode node, int index) {
        if(index == word.length()){
            if(node.isEnd ) {
                node.isEnd = false;
            }
            if(node.countChildren == 0){
                node = null;
            }
            return node;
        }

        TrieNode t;
        char c = word.charAt(index);
        int i = c - 'a';
        if(node.arr[i] != null){
            t = deleteNode(word, node.arr[i], index+1);
            //remove key from children if it node does not exist anymore
            if(t == null){
                node.arr[i] = null;
                node.countChildren--;
            }
            if(!hasChildren(node)){
                node = null;
            }
        }
        return node;

    }
}
