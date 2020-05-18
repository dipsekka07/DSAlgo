public class driverCode {

    public static void main(String[] args){
        
        // and lower case) 
        String keys[] = { "the", "a", "there", "answer", "any", "by", "bye", "their", "hero", "heroplane" }; 
        TrieArrayImp t = new TrieArrayImp();
        for(String s: keys) {
            t.insert(s);
        }

        boolean p = t.search("answer");
        boolean pref = t.startsWith("ans");
        t.delete("answer");
        p = t.search("answer");
        pref = t.startsWith("a");

    }
    
}