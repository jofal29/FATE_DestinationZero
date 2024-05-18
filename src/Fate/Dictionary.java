package Fate;

import java.util.HashSet;
import java.util.Hashtable;

public class Dictionary {
    public static final HashSet<String> itemDictionary = new HashSet<>();

    Dictionary(){
        initializeDictionary();
    }

    private void initializeDictionary() {
        itemDictionary.add("attack");
        itemDictionary.add("heal");
        itemDictionary.add("npCharge");
        itemDictionary.add("curse");
        itemDictionary.add("shield");
        itemDictionary.add("addNp");
        itemDictionary.add("charm");
        itemDictionary.add("npSeal");
        itemDictionary.add("stun");
        itemDictionary.add("np");
        itemDictionary.add("takeLike");
        itemDictionary.add("protectFromNP");
        itemDictionary.add("useNP");
        itemDictionary.add("commandSeal");
        itemDictionary.add("doubleAttack");
    }
}
