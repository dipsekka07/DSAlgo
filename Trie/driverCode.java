public class driverCode {

    public static void main(String[] args){
        
        // and lower case) 
        String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their", "hero", "heroplane" }; 
        Trie t = new Trie();
        for(String s: keys) {
            t.insert(s);
        }

        boolean p = t.search("an");
        boolean pref = t.startsWith("ans");
        t.delete("answer");
        p = t.search("a");
        pref = t.startsWith("a");

    }
    
}