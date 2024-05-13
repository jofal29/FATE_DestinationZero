package Fate;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

public class Action {
    Function function = new Function();

    HeroicSpirit attacker = new HeroicSpirit();
    HeroicSpirit target = new HeroicSpirit();
    String itemName;
    int count;
    boolean isTrue = true;

    public void useItem(Master master, int masterID){
        //Show Your Hand
        master.displayItems();
        function.pause(1);
        function.newLine();

        //Get random Items Object and Add Into Master's ArrayList<Items>
        System.out.println("Drawing Card...");
        function.newLine();
        master.masterItems.add(HolyGrailWar.drawItem());
        function.pause(1);

        //Master Chooses An Item
        while(isTrue) {
            //Shows New Item Name Into Master's ArrayList
            master.displayItems();
            System.out.println("Choose an action.");
            String itemChoice = UserInput.getInput();

            if (Dictionary.itemDictionary.contains(itemChoice) && master.itemExists(itemChoice)) {
                Item removeThisItem = null;
                for (int i = 0; i < master.masterItems.size(); i++) {
                    if (Objects.equals(master.masterItems.get(i).itemName, itemChoice)) {
                        removeThisItem = master.masterItems.get(i);
                        break;
                    }
                }
                master.masterItems.remove(removeThisItem);
                isTrue = false;
            } else {
                System.out.println("Please Enter a Correct Item Name.");
                System.out.println();
            }
        }
        isTrue = true;

        //Choose a Servant
        while(isTrue){

            //Players Turn
            if(masterID==1){
                int targetedMaster;
                System.out.println("Choose a master to attack.");
                System.out.println("Remaining Masters and Servants: ");

                //Display Masters and Servants
                for (int i = 0; i < function.getMasters().size(); i++) {
                    System.out.print("Master: " + function.getRemainingMasters().get(i) + " -> | Servant: " + function.getServant(i));
                    if (function.getServant(i).next != null) {
                        HeroicSpirit node = function.getServant(i);
                        while (node.next != null) {
                            System.out.print("  | " + node.name);
                            node = node.next;
                        }
                        System.out.print("  | " + node.name);
                    }
                    System.out.println();
                }

                //Take user input between which master choice
                System.out.println("Enter a number to choose a master, or 0 to quit: ");
                targetedMaster = UserInput.getIntInput();
                while (targetedMaster < 0 || targetedMaster > function.getMasters().size() || targetedMaster == 0) {
                    if (targetedMaster == 0) {
                        System.out.println("Exiting...");
                        System.exit(0);
                    }
                    System.out.println("Invalid input. Please enter a number between 1 and " + function.getRemainingMasters().size() + ", or 0 to quit:");
                    targetedMaster = UserInput.getIntInput();
                }

                //Check if that master has multiple servants
                if (function.getServant(targetedMaster).next != null) {
                    System.out.println("Choose a specific servant. ");

                    //Display Servants
                    HeroicSpirit node = function.getServant(targetedMaster);
                    int count = 1;
                    while (node.next != null) {
                        System.out.print(count + ". " + node.name + " ");
                        count++;
                        node = node.next;
                    }
                    System.out.print(count + ". " + node.name);

                    //Take user input for which servant to choose
                    System.out.println("Enter a number to choose a servant, or 0 to quit: ");
                    boolean wrongInput = true;
                    int servantChoice = -1;
                    while(wrongInput){
                        try {
                            servantChoice = UserInput.getIntInput();
                            if(servantChoice == 0) {
                                System.out.println("Exiting...");
                                System.exit(0);
                            }
                            if (servantChoice < 0 || servantChoice > 7) {
                                System.out.println("Invalid input. Please enter a number between 1 and 7, or 0 to exit.");
                                function.newLine();
                            }
                            else{
                                wrongInput = false;
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println("That's not even a number -_- ... Please enter a number between 0 and 7.");
                            function.newLine();
                        }
                    }
                }
                else{
                    System.out.println("Targeting "+ function.getServant(targetedMaster));
                }
            }

            //Bot's Turn
            else{
                Linked3
            }
        }
    }




























    public Item drawItem(){
        while(function.getItemQueue().size()<10) {
            function.getItemQueue().enqueue(new Item("Random"));
        }
        return function.getItemQueue().dequeue();
    }

    public void chooseAction(String action, HeroicSpirit targetedServant) {
        if(Objects.equals(targetedServant.checkImmune(action), "not immune")) {
            target=targetedServant;
            attacker =HolyGrailWar.summonedServants.get(1);
            switch(action) {
                case "npCharge": {
                    npCharge();
                }
                break;
                case "addNp":{
                    addNp();
                }
                break;
                case "np": {
                    np();
                }
                break;
                case "heal": {
                    heal();
                }
                break;
                case "npSeal": {
                    npSeal();
                }
                break;
                case "attack": {
                    attack();
                }
                break;
                case "curse": {
                    curse();
                }
                break;
                case "shield": {
                    shield();
                }
                break;
                case "stun": {
                    stun();
                }
                break;
                case "takeLife": {
                    targetedServant.takeLife();
                }
                break;
                case "charm": {
                    if(targetedServant.turnsFrozen==0)
                    {
                        charm(1);
                    }
                    else{System.out.println(targetedServant.getName()+" cannot be charmed again.");}
                }
                break;
                case "protectFromNP":{
                    protectFromNP();
                }break;
                default: {
                    System.out.println("Oops");
                }
                break;
            }		}
        else {
            System.out.println(targetedServant.getName()+ " is immune to " + action);
        }
    }



    public void randomPlay(HeroicSpirit f, HeroicSpirit t) {
        this.attacker = f; this.target = t;
        //Turn
        if(this.attacker.turnCheck())
        {
            randomItem();
        }
        else {
            System.out.println(this.attacker.getName()+" is unable to make a move.");
        }
    }















    private void addNp() {
        double randomOfTwo = new Random().nextBoolean() ? 1 : 2;
        for (int i=0; i<randomOfTwo; i++) {
            System.out.println(this.attacker.getName() + " +1NP");
            this.attacker.npHeld++;
        }
    }
    private void np() {
        if(this.attacker.npSeals>0)
        {
            System.out.println("Noble Phantasm Sealed. "+this.attacker.getName()+ " cannot activate NP.");
        }
        else {
            NoblePhantasm hogu = new NoblePhantasm(this.attacker,this.target);
            hogu.activate(this.attacker.getName());
        }
    }
    public void curse() {
        this.target.curses++;
        this.target.maxHealth--;
        System.out.println(this.target.getName()+" Cursed (-1 Max Health) by Master " + this.attacker.masterID);
        if(this.target.maxHealth <= this.target.hearts) {
            this.target.hearts--;
        }
        else {System.out.println("Max Health: "+this.target.maxHealth);}
        this.target.checkHealth();
    }
    public void npCharge() {
        double randomOfTwo = new Random().nextBoolean() ? 1 : 2;
        for (int i=0; i<randomOfTwo; i++) {
            System.out.println(this.attacker.getName() + " +1np charge");
            this.attacker.npCharge++;
        }
        if(this.attacker.npCharge>=this.attacker.npMaxCharges) {
            this.attacker.npHeld++;
            this.attacker.npCharge = (this.attacker.npCharge-this.attacker.npMaxCharges);
            np();
        }
        else	{
            int npNeed = this.attacker.npMaxCharges - this.attacker.npCharge;
            System.out.println(this.attacker.getName() + " needs " + npNeed + " np charges.");
        }
    }
    public void attack() {

        System.out.println(this.attacker.getName() + " attacks " + this.target.getName());
        if(this.target.defense > 0)
        {
            this.target.defense--;
            System.out.println(this.target.getName()+" -1 Shield");
        }
        else {
            this.target.loseHearts();
        }
        this.target.checkHealth();

        if(this.target.hearts==0) {
            this.target.deathbyAttack = true;
        }
    }
    public void skillUse() {
        Skill useSkill = new Skill();
        useSkill.movePlay(this.attacker.getName(), attacker);
    }
    public void heal() {
        if(this.attacker.curses > 0)
        {
            this.attacker.curses--;
            this.attacker.maxHealth++;
            this.attacker.checkHealth();
        }
        else {
            if(this.attacker.hearts==this.attacker.maxHealth)
            {
                npCharge();
            }
            else {
                this.attacker.gainHearts();
                this.attacker.checkHealth();
            }
        }
    }
    public void charm(int numberOfCharms) {
        for(int i=0;i<numberOfCharms;i++) {
            this.target.turnsFrozen++;}
        System.out.println(this.target.getName()+" has been charmed.");
    }
    public void shield() {
        if(this.attacker.defense==this.attacker.maxHealth)
        {
            System.out.println(this.attacker.getName()+": Max Shields Held");
        }
        else
        {
            System.out.println(this.attacker.getName()+" +1 Shield");
            this.attacker.defense++;
        }
        this.attacker.checkHealth();
    }
    public void stun() {
        HolyGrailWar.masters.get(target.masterID).turnsFrozen++;
        System.out.println("Master "+HolyGrailWar.masters.get(target.masterID).masterID_Number +" has been stunned by Master "+this.attacker.masterID);
//        for(int v=0; v<HolyGrailWar.masters().size();v++) {
//            if(HolyGrailWar.masters().get(v).masterNumber==this.target.master)
//            {
//                HolyGrailWar.masters().get(v).turnsFrozen++;
//                System.out.println("Master "+HolyGrailWar.masters().get(v).masterNumber+" has been stunned by Master "+this.focus.master);
//            }
//            else
//            {}
//        }
    }
    public void npSeal() {
        this.target.npSeals++;
        System.out.println(this.target.getName()+"'s NP has been sealed by Master " + this.attacker.masterID);
    }
    public void protectFromNP() {
        this.target.npProtection++;
        System.out.println(this.target.getName()+" gains protection for NP for one turn.");
    }






    public void chooseOngoing(String string, HeroicSpirit ser, int loops) {
        if(ser.checkImmune(string)=="not immune") {
            switch(string) {
                case "ongoingCurses": {
                    ser.ongoingCurses = ser.ongoingCurses+loops;
                    System.out.println("Ongoing Curses +"+loops);
                }
                break;
                case "disableNP": {
                    ser.npSealed="Disabled";
                    System.out.println(ser.getName()+"'s NP has been disabled.");
                }
                break;
                case "attack": {
                    attack();
                }
                break;
                case "curse": {
                    curse();
                }
                break;
                case "shield": {
                    shield();
                }
                break;
                case "takeLife": {
                    ser.takeLife();
                }
                break;
                case "charm": {
                    if(ser.turnsFrozen==0)
                    {
                        charm(1);
                    }
                    else{System.out.println(ser.getName()+" cannot be charmed again.");}
                }
                break;
                default: {
                    System.out.println("Oops");
                }
                break;
            }}
        else{
            System.out.println(ser.getName()+ " is immune to " + string);
        }
    }
}
