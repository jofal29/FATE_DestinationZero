package Fate;

import java.util.HashSet;
import java.util.Hashtable;

public class Dictionary {
    public static HashSet<String> itemDictionary = new HashSet<>();

    Dictionary(){
        initializeDictionary();
    }

    private void initializeDictionary() {
        HashSet<String> itemDictionary = new HashSet<>();
        itemDictionary.add("attack");
        itemDictionary.add("heal");
        itemDictionary.add("np charge");
        itemDictionary.add("curse");
        itemDictionary.add("shield");
        itemDictionary.add("add np");
        itemDictionary.add("charm");
        itemDictionary.add("np seal");
        itemDictionary.add("stun");
        itemDictionary.add("np");
    }
}
