package Fate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Master {
    Function function = new Function();
    Action action = new Action();
    Random randomGenerator = new Random();

    private int masterID_Number = 0;
    private int turnsFrozen = 0;
    private int maxServants = 0;
    private int numOfServants = 0;
    private int extraTurns = 0;
    private int count;
    private int choice;
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

        if(turnCheck()) {
            drawItem();
            count=0;
            while (!itemUsed){
                itemChoice = chooseItem();
                System.out.println("Master " + masterID_Number + " uses " + itemChoice);
                function.newLine();

                if (itemChoice.equals("useNP")) {
                    //Display list of heroic spirits
                    System.out.println("Choose a heroic spirit to use their Noble Phantasm:");
                    int i = 1;
                    for (HeroicSpirit spirit : servantList) {
                        System.out.println(i + ". " + spirit.getName() +
                                " - NP: " + spirit.getNpRequired() +
                                " (Loaded: " + spirit.getNpHeld() + "/NP Charges:" + spirit.getNpCharge() + ")");
                        System.out.println("  - Noble Phantasm: " + spirit.getNoble_Phantasm());
                        i++;
                    }

                    //Get user input for heroic spirit choice
                    int choice = -1;
                    while (choice < 0 || choice > servantList.size()) {
                        System.out.print("Enter a number between 1 and " + servantList.size() + ": ");
                        try {
                            choice = Integer.parseInt(UserInput.getInput());
                            choice--;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                        }
                    }
                    activateNP(choice);
                }
                if (itemChoice.equals("shield") || itemChoice.equals("npCharge")
                        || itemChoice.equals("addNp") || itemChoice.equals("heal")) {
                    action.chooseSelfAction(itemChoice, chooseOwnServant());
                }
                if (itemChoice.equals("attack") || itemChoice.equals("curse")
                        || itemChoice.equals("charm") || itemChoice.equals("npSeal")) {
                    action.chooseEnemyAction(itemChoice, masterID_Number, chooseEnemyServant());
                }
                if(itemChoice.equals("stun")){
                    action.chooseAMasterAction(itemChoice, chooseAMaster(), masterID_Number);
                }
                function.newLine();
                count++;
                if(!masterItems.isEmpty()) {
                    if (count < 3) {
                        System.out.println("Would you like to play another item? 'yes' or 'no'");
                        itemChoice = UserInput.getInput().toLowerCase();

                        while (!itemChoice.equals("yes") && !itemChoice.equals("no")) {
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                            itemChoice = UserInput.getInput().toLowerCase();
                        }
                        if (itemChoice.equals("yes")) {
                            function.newLine();
                            System.out.println("Able to play " + (3 - count) + " more items.");
                            function.newLine();
                            Stats.displayMasterAndServants();
                            displayItems();
                        }
                        if (itemChoice.equals("no")) {
                            itemUsed = true;
                        }
                    } else {
                        System.out.println("Max amount items used per turn reached.");
                        itemUsed = true;
                    }
                }
                else{
                    System.out.println("No more items held.");
                    itemUsed = true;
                }
            }
        }
        decrementAllServants();
        System.out.println("**********Master " + masterID_Number + "'s Turn End**********");
        function.pause(1);
    }
    public void botTurn(){
        for(HeroicSpirit heroicSpirit : servantList){
            heroicSpirit.subtractNpProtection();
        }

        boolean itemUsed = false;

        if(turnCheck()) {
            drawItem();
            count=0;
            while (!itemUsed){
                function.pause(2);
                itemChoice = chooseRandomItem();
                System.out.println("Master " + masterID_Number + " uses " + itemChoice);
                function.pause(2);
                if (itemChoice.equals("useNP")) {
                    //Random input for heroic spirit choice
                    int choice = -1;
                    while (choice < 0 || choice >= servantList.size()) {
                        choice = randomGenerator.nextInt(servantList.size());
                    }
                    activateNP(choice);
                }
                if (itemChoice.equals("shield") || itemChoice.equals("npCharge")
                        || itemChoice.equals("addNp") || itemChoice.equals("heal")) {
                    action.chooseSelfAction(itemChoice, randomOwnServant());
                }
                if (itemChoice.equals("attack") || itemChoice.equals("curse")
                        || itemChoice.equals("charm") || itemChoice.equals("npSeal")) {
                    action.chooseEnemyAction(itemChoice, masterID_Number,randomEnemyServant());
                }
                if(itemChoice.equals("stun")){
                    action.chooseAMasterAction(itemChoice, randomEnemyMaster(), masterID_Number);
                }
                count++;
                if(!masterItems.isEmpty()) {
                    if (count < 3) {
                        choice = randomGenerator.nextInt(2);
                        if (choice == 0 && !masterItems.isEmpty()) {
                            System.out.println("Playing another item...");
                        }
                        if (choice == 1) {
                            itemUsed = true;
                        }
                    } else {
                        itemUsed = true;
                    }
                }
                else{
                    System.out.println("No more items held.");
                    itemUsed = true;
                }
            }
        }
        decrementAllServants();
        System.out.println("**********Master " + masterID_Number + "'s Turn End**********");
        function.pause(2);
    }



    public void drawItem(){
        //Get random Items Object and Add Into Master's ArrayList<Items>
        System.out.println("Master " + masterID_Number + " is drawing a card...");
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

    public int chooseAMaster(){
        //Print out masters
        System.out.println("Choose an enemy master to target. (Type in numeric value)");
        System.out.print("Remaining Master(s): ");
        for(Integer masterID : HolyGrailWar.masters.keySet()){
            if(masterID != masterID_Number) {
                System.out.print(" | " + masterID + " | ");
            }
        }

        //Get user input for enemy master choice
        int masterChoice = -1;
        while (!HolyGrailWar.masters.containsKey(masterChoice) || masterChoice==masterID_Number) {
            try {
                masterChoice = Integer.parseInt(UserInput.getInput());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter the correct master number.");
            }
        }
        return masterChoice;
    }
    public int randomEnemyMaster(){
        int randomChoice = -1;
        while(!HolyGrailWar.masters.containsKey(randomChoice) || randomChoice == masterID_Number)
        {
            randomChoice = randomGenerator.nextInt(HolyGrailWar.remainingMasters.size()+1);
        }
        return randomChoice;
    }

    public HeroicSpirit chooseOwnServant(){
        //Display list of heroic spirits
        System.out.println("Choose a heroic spirit. (Type in numeric value)");
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
    public HeroicSpirit randomOwnServant(){
        int randomChoice = -1;
        while(randomChoice < 0 || randomChoice >= servantList.size())
        {
            randomChoice = randomGenerator.nextInt(servantList.size());
        }
        return servantList.get(randomChoice);
    }

    public HeroicSpirit chooseEnemyServant(){
        int masterChoice = chooseAMaster();

        //Choose enemy servant from master
        function.newLine();
        System.out.println("Choose an enemy servant from Master "+masterChoice+" (Type in numeric value)");
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
    public HeroicSpirit randomEnemyServant(){
        int randomChoice = -1;
        while (randomChoice < 0 || randomChoice >= HolyGrailWar.masters.get(randomEnemyMaster()).getServantList().size()){
            randomChoice = randomGenerator.nextInt(HolyGrailWar.masters.get(randomEnemyMaster()).getServantList().size());
        }
        return HolyGrailWar.masters.get(randomEnemyMaster()).getServantList().get(randomChoice);
    }

    public String chooseItem() {
        //Master Chooses An Item
        boolean choice = false;
        String itemChoice = null;
        while (!choice) {
            System.out.println("Choose an action. (Type in exact word)");
            itemChoice = UserInput.getInput();

            if (Dictionary.itemDictionary.contains(itemChoice) && doesItemExists(itemChoice)) {
                Item removeThisItem = null;
                for (Item masterItem : masterItems) {
                    if (masterItem.getItemName().equals(itemChoice)) {
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
    private String chooseRandomItem(){
        itemChoice = null;
        int randomChoice = randomGenerator.nextInt(masterItems.size());
        itemChoice = masterItems.get(randomChoice).getItemName();
        masterItems.remove(randomChoice);

        return itemChoice;
    }

    public Boolean doesItemExists(String name) {
        int itemCount = 0;
        for (Item masterItem : masterItems) {
            if (masterItem.getItemName().equals(name)) {
                itemCount++;
            }
        }
        if(itemCount >0){
            return true;
        }
        else{
            return false;
        }
    }

    public void displayItems(){
        System.out.println("Item List:");
        for(Item item : masterItems) {
            System.out.println(" | " + item.getItemName() + " | ");
        }
    }
    public void decrementAllServants(){
        for(HeroicSpirit heroicSpirit : servantList){
            heroicSpirit.checkOngoing();
            heroicSpirit.subtractDurationEffect();
        }
    }
    public boolean hasExtraTurn() {
        if(extraTurns>0) {
            System.out.println("		  	    *Master #: "+masterID_Number +"'s Extra Turn:*");
            return true;
        }
        else{
            return false;
        }
    }

    public void addExtraTurn(){
        extraTurns++;
    }
    public void removeExtraTurns(){
        extraTurns=0;
    }
    public void increaseTurnsFrozen(){
        turnsFrozen++;
    }
    public void decreasedTurnsFrozen(){
        turnsFrozen--;
    }
    public void increaseServants(){
        numOfServants++;
    }
    public void decreaseServants(){
        numOfServants--;
    }

    public void activateNP(int choice){
        HeroicSpirit chosenSpirit = servantList.get(choice);

        //Check if the chosen spirit does not have enough NP to use their Noble Phantasm
        if (chosenSpirit.getNpHeld() < chosenSpirit.getNpRequired()) {
            System.out.println(chosenSpirit.getName() + " doesn't have enough NP to use their Noble Phantasm. NP Required: " + chosenSpirit.getNpRequired());
        } else {
            //Check if the chosen spirit has their noble phantasm sealed
            if (chosenSpirit.getNpSeals() > 0 || chosenSpirit.getNP_Status().equals("Disabled")) {
                System.out.println("Noble Phantasm Sealed. " + chosenSpirit.getName() + " cannot activate NP.");
            } else {
                NoblePhantasm hogu = new NoblePhantasm();
                hogu.activate(chosenSpirit);
            }
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
