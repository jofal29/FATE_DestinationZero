package Fate;

import java.util.ArrayList;
import java.util.Hashtable;

public class Master {
    int masterID_Number = 0;
    int turnsFrozen = 0;
    int maxServants = 0;
    int numOfServants = 0;
    int extraTurns = 0;

    boolean active = true;

    public ArrayList<Item> masterItems = new ArrayList<>();

    //Plan to allow Masters to have multiple servants in the future
    public static final Hashtable<String, HeroicSpirit> ownedServants = new Hashtable<>();

    public Master(int masterNum, int numServants) {
        masterID_Number = masterNum;
        maxServants = numServants;
        numOfServants = maxServants;
    }

    public boolean turnCheck() {
        if(turnsFrozen>0) {
            turnsFrozen--;
            return false;
        }
        else {
            return true;
        }
    }

    public void displayItems(){
        System.out.println("Item List:");
        for(Item item : masterItems) {
            System.out.println(" | " + item.itemName + " | ");
        }
    }

    public ArrayList<Item> getMasterItems() {
        return masterItems;
    }

    public Hashtable<String, HeroicSpirit> getOwnedServants() {
        return ownedServants;
    }

}
