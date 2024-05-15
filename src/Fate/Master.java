package Fate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Master {
    Function function = new Function();
    Action action = new Action();

    private int masterID_Number = 0;
    private int turnsFrozen = 0;
    private int maxServants = 0;
    private int numOfServants = 0;
    private int extraTurns = 0;
    private int count;
    private String itemChoice = null;

    private boolean active = true;
    private boolean deceased = false;


    private ArrayList<Item> masterItems = new ArrayList<>();
    private LinkedList<HeroicSpirit> servantList = new LinkedList<>();

    public Master(int masterNum, int numServants) {
        masterID_Number = masterNum;
        maxServants = numServants;
        numOfServants = maxServants;
    }

    public void masterTurn(){
        for(HeroicSpirit heroicSpirit : servantList){
            heroicSpirit.subtractNpProtection();
        }

        boolean itemUsed = false;

        while (!itemUsed){
            if(turnCheck()) {
                drawItem();
                itemChoice = chooseItem();
//                if (itemChoice.equals("useNP")) {
//                    //Display list of heroic spirits
//                    System.out.println("Choose a heroic spirit to use their Noble Phantasm:");
//                    int i = 1;
//                    for (HeroicSpirit spirit : servantList) {
//                        System.out.println(i + ". " + spirit.getName() +
//                                " - NP: " + spirit.getNpRequired() +
//                                " (Loaded: " + spirit.getNpHeld() + "/NP Chargers:" + spirit.getNpCharge() + ")");
//                        System.out.println("  - Noble Phantasm: " + spirit.getNp());
//                        i++;
//                    }
//
//                    //Get user input for heroic spirit choice
//                    int choice = -1;
//                    while (choice < 0 || choice >= servantList.size()) {
//                        System.out.print("Enter a number between 1 and " + servantList.size() + ": ");
//                        try {
//                            choice = Integer.parseInt(UserInput.getInput()) - 1;
//                        } catch (NumberFormatException e) {
//                            System.out.println("Invalid input. Please enter a number.");
//                        }
//                    }
//                    HeroicSpirit chosenSpirit = servantList.get(choice);
//
//                    //Check if the chosen spirit does not have enough NP to use their Noble Phantasm
//                    if (chosenSpirit.getNpHeld() < chosenSpirit.getNpRequired()) {
//                        System.out.println(chosenSpirit.getName() + " doesn't have enough NP to use their Noble Phantasm. NP Required: " + chosenSpirit.getNpRequired());
//                    } else {
//                        //Check if the chosen spirit has their noble phantasm sealed
//                        if (chosenSpirit.getNpSeals() > 0 || chosenSpirit.getNP_Status().equals("Disabled")) {
//                            System.out.println("Noble Phantasm Sealed. " + chosenSpirit.getName() + " cannot activate NP.");
//                        } else {
//                            NoblePhantasm hogu = new NoblePhantasm();
//                            hogu.activate(chosenSpirit);
//                        }
//                    }
//                }
                if (itemChoice.equals("shield") || itemChoice.equals("npCharge")
                        || itemChoice.equals("addNp") || itemChoice.equals("heal")) {
                    action.chooseAction(itemChoice, chooseOwnServant());
                    itemUsed = true;
                }
                if (itemChoice.equals("attack") || itemChoice.equals("curse")
                        || itemChoice.equals("charm") || itemChoice.equals("npSeal")
                        || itemChoice.equals("stun")) {
                    chooseEnemyServant();
                    action.chooseAction(itemChoice, chooseEnemyServant());
                    itemUsed = true;
                }
            }
        }
    }
    public void drawItem(){
        //Get random Items Object and Add Into Master's ArrayList<Items>
        System.out.println("Drawing Card...");
        function.newLine();
        masterItems.add(HolyGrailWar.itemFromDrawPile());
        function.pause(1);
        displayItems();
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
    private HeroicSpirit chooseOwnServant(){
                    //Display list of heroic spirits
                    System.out.println("Choose a heroic spirit.");
                    int i = 1;
                    for (HeroicSpirit spirit : servantList) {
                        System.out.println(i + ". " + spirit.getName());
                        i++;
                    }

                    //Get user input for heroic spirit choice
                    int choice = -1;
                    while (choice < 0 || choice >= servantList.size()) {
                        System.out.print("Enter a number between 1 and " + servantList.size() + ": ");
                        try {
                            choice = Integer.parseInt(UserInput.getInput()) - 1;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                    }
        return servantList.get(choice);
    }
    private HeroicSpirit chooseEnemyServant(){
        //Print out masters
        System.out.println("Choose an enemy master to target.");
        for(Integer masterID : HolyGrailWar.masters.keySet()){
            System.out.print("Master "+masterID+" ");
        }

        //Get user input for enemy master choice
        int masterChoice = -1;
        while (!HolyGrailWar.masters.containsKey(masterChoice)) {
            try {
                masterChoice = Integer.parseInt(UserInput.getInput()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter the correct master number.");
            }
        }

        //Choose enemy servant from master
        System.out.println("Choose an enemy servant from Master "+masterChoice);
        int i = 1;
        for(HeroicSpirit heroicSpirit : HolyGrailWar.masters.get(masterChoice).getServantList()){
            System.out.println(i + ". " + heroicSpirit.getName());
            i++;
        }
        //Get user input for heroic spirit choice
            int servantChoice = -1;
            while (servantChoice < 0 || servantChoice >= HolyGrailWar.masters.get(masterChoice).getServantList().size()) {
                System.out.print("Enter a number between 1 and " + servantList.size() + ": ");
                try {
                    servantChoice = Integer.parseInt(UserInput.getInput()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
            return HolyGrailWar.masters.get(masterChoice).getServantList().get(servantChoice);
    }
    public String chooseItem() {
        //Master Chooses An Item
        boolean choice = false;
        String itemChoice = null;
        while (!choice) {
            System.out.println("Choose an action.");
            itemChoice = UserInput.getInput();

            if (Dictionary.itemDictionary.contains(itemChoice) && doesItemExists(itemChoice)) {
                Item removeThisItem = null;
                for (Item masterItem : masterItems) {
                    if (Objects.equals(masterItem.getItemName(), itemChoice)) {
                        removeThisItem = masterItem;
                        break;
                    }
                }
                masterItems.remove(removeThisItem);
                choice = true;
            } else {
                System.out.println("Please Enter a Correct Item Name.");
                System.out.println();
            }
        }
        choice = false;
        return itemChoice;
    }
    public void displayItems(){
        System.out.println("Item List:");
        for(Item item : masterItems) {
            System.out.println(" | " + item.getItemName() + " | ");
        }
    }
    public Boolean doesItemExists(String name) {
        count = 0;
        for (Item masterItem : masterItems) {
            if (masterItem.getItemName().equals(name)) {
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

    //Getters and Setters
    public int getMasterID_Number() {
        return masterID_Number;
    }
    public void setMasterID_Number(int masterID_Number) {
        this.masterID_Number = masterID_Number;
    }

    public int getTurnsFrozen() {
        return turnsFrozen;
    }
    public void setTurnsFrozen(int turnsFrozen) {
        this.turnsFrozen = turnsFrozen;
    }

    public int getMaxServants() {
        return maxServants;
    }
    public void setMaxServants(int maxServants) {
        this.maxServants = maxServants;
    }

    public int getNumOfServants() {
        return numOfServants;
    }
    public void setNumOfServants(int numOfServants) {
        this.numOfServants = numOfServants;
    }

    public int getExtraTurns() {
        return extraTurns;
    }
    public void setExtraTurns(int extraTurns) {
        this.extraTurns = extraTurns;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public LinkedList<HeroicSpirit> getServantList() {
        return servantList;
    }
    public ArrayList<Item> getMasterItems() {
        return masterItems;
    }
    public void setMasterItems(ArrayList<Item> masterItems) {
        this.masterItems = masterItems;
    }
    public void setServantList(LinkedList<HeroicSpirit> servantList) {
        this.servantList = servantList;
    }
}
