package Fate;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class Master {
    int masterID_Number = 0;
    int turnsFrozen = 0;
    int maxServants = 0;
    int numOfServants = 0;
    int extraTurns = 0;
    int count;

    boolean active = true;

    public ArrayList<Item> masterItems = new ArrayList<>();

    public LinkedList<HeroicSpirit> servantList = new LinkedList<>();

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

    public LinkedList<HeroicSpirit> getServantList() {
        return servantList;
    }
    public ArrayList<Item> getMasterItems() {
        return masterItems;
    }

    public Boolean itemExists(String name) {
        count = 0;
        for (Item masterItem : masterItems) {
            if (masterItem.itemName.equals(name)) {
                count++;
            }
        }
        if(count >0){
            return true;
        }
        else{
            return false;
        }
    }
}
