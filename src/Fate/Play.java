package Fate;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Play {
    Action action = new Action();
    Random rand = new Random();
    Function function = new Function();

    int turnNumber = 0;
    int currentMasterTurn = 0;

    boolean isTrue = true;

    HeroicSpirit attacker;
    HeroicSpirit attacked;
    HeroicSpirit nodeCursor = new HeroicSpirit();

    public void playGame(){
        while (HolyGrailWar.masters.size()>1) {
            gameTurn();
            pause(2);
        }
    }

    public void gameTurn() {
        turnNumber++;
        System.out.println("\n"+"\n" + "~~~~~~~~~~~~~~~~~~~~~~ Fate/Fractured Reality: Round " + turnNumber +" ~~~~~~~~~~~~~~~~~~~~~~");

        //Iterate through the array of master IDs
        for(int i=0; i<HolyGrailWar.remainingMasters.size();i++) {
            this.currentMasterTurn = HolyGrailWar.remainingMasters.get(i);

            //Checks if the master has been eliminated, if so, it skips onto the next master ID
            if(!HolyGrailWar.deceased.contains(currentMasterTurn)){

                //Pre-Checklist
                checkOngoing(getServant(currentMasterTurn));
                printStatement_whichMaster(currentMasterTurn);

                //Decrements Any NP Effects That Are On The Master By One
                npCheck(getMaster(currentMasterTurn));

                //Check if master is stunned
                if(getMaster(currentMasterTurn).turnsFrozen==0)
                {
                    //Checks if the master is the player, if so, it's the player's turn.
                    if(currentMasterTurn == 1){
                        action.useItem(getMaster(currentMasterTurn), currentMasterTurn);
                        action.chooseAction(playersTurn(getMaster(1)), chooseServants(1));
                        extraTurn(1);
                        playAnalysis();
                        endOfMasterTurn(getServant(1));
                    }
                    else {
                        action.chooseAction(botTurn(getMaster(currentMasterTurn)), randomServant());
                        chooseServants(currentMasterTurn);
                        action.randomPlay(HolyGrailWar.summonedServants.get(currentMasterTurn), chooseServants(currentMasterTurn));

                        System.out.println(" ");

                        if(HolyGrailWar.summonedServants.get(currentMasterTurn) != null) {
                            extraTurn(currentMasterTurn);
                            playAnalysis();
                            endOfMasterTurn(HolyGrailWar.summonedServants.get(currentMasterTurn));
                        }
                    }
                }
                else {
                    System.out.println("Unable to make any moves: Master is Stunned");
                    HolyGrailWar.masters.get(currentMasterTurn).turnsFrozen--;
                    endOfMasterTurn(HolyGrailWar.summonedServants.get(currentMasterTurn));
                    System.out.println(" ");
                }
                pause(2);
            }
            else{

            }

        }

        if(HolyGrailWar.summonedServants.get(currentMasterTurn) != null) {
            checkOngoing(HolyGrailWar.summonedServants.get(currentMasterTurn));
        }

        currentStats();
        pause(1);
    }

    private ArrayList<Integer> getRemainingMasters(){
        return HolyGrailWar.remainingMasters;
    }
    //Method that allows the user to draw an item.
    //To always have the queue be full, we add an item onto the queue before the master draws an item.

    private void extraTurn(int numMaster) {
        if(HolyGrailWar.masters.get(numMaster) != null){
                if(HolyGrailWar.masters.get(numMaster).extraTurns>0) {
                    checkOngoing(HolyGrailWar.summonedServants.get(numMaster));
                    System.out.println("		  	    *Master #: "+this.currentMasterTurn +"'s Turn:*");
                    for(int e=0; e<HolyGrailWar.masters.get(numMaster).extraTurns; e++) {
                        chooseServants(this.currentMasterTurn);
                        HolyGrailWar.masters.get(numMaster).extraTurns--;
                        action.randomPlay(attacker, attacked);
                        playAnalysis();
                        System.out.println(" ");
                    }
                }
        }
    }

    public void playAnalysis() {
        defeatUpdate();
        checkDeathEffects();
        updateMasters();
        updateStats();
    }

    private void defeatUpdate() {
        for (Integer key : HolyGrailWar.summonedServants.keySet())
        {
            if(HolyGrailWar.summonedServants.get(key).lives == 0)
            {
                System.out.println( HolyGrailWar.summonedServants.get(key).name + " has been defeated.");
                HolyGrailWar.masters.get(key).numOfServants--;
                HolyGrailWar.deceased.put(HolyGrailWar.summonedServants.get(key).name, HolyGrailWar.summonedServants.get(key));
                HolyGrailWar.summonedServants.remove(key);
            }
            else {}
        }
    }

    public void checkDeathEffects() {
        for(String name : HolyGrailWar.servantsWithDeathEffects){
            if(HolyGrailWar.deceased.containsKey(name)){
                Effect getEffect = new Effect();
                getEffect.deathEffects(name);
            }
        }
    }

    private void checkOngoing(HeroicSpirit hs) {
        if (hs.ongoingCurses != 0) {
        hs.curses = hs.curses + hs.ongoingCurses;
        }

        if (hs.ongoingDefense == 1) {
            hs.defense++;
        }
        if (hs.ongoingDefense == -1) {
            hs.defense--;
        }

        if (hs.ongoingFrozen == 1) {
            hs.turnsFrozen++;
        }

        if (hs.ongoingHearts == 1) {
            hs.hearts++;
        }
        if (hs.ongoingHearts == -1) {
            hs.hearts--;
        }

        if (hs.ongoingLives == 1) {
            hs.ongoingLives++;
        }

        if (hs.ongoingMaxHealth == 1) {
            hs.maxHealth++;
        }
        if (hs.ongoingMaxHealth == -1) {
            hs.maxHealth--;
        }

        if (hs.ongoingNpCharge > 0) {
            hs.ongoingNpCharge--;
        }
        if (hs.ongoingNpSeals > 0) {
            hs.npSeals = hs.npSeals + hs.ongoingNpSeals;
            hs.ongoingNpSeals = 0;
        }}

    private void endOfMasterTurn(HeroicSpirit hs) {
        if (hs != null) {

            if (hs.npSeals > 0) {
                hs.npSeals--;
            }
            if (hs.npSeals < 0) {
                hs.npSeals = 0;
            }

            if (hs.turnsFrozen > 0) {
                hs.turnsFrozen--;
            }
            if (hs.turnsFrozen < 0) {
                hs.turnsFrozen = 0;
            }
        }
    }

    private void npCheck(Master m){
        while (nodeCursor != null){
            nodeCursor = getServant(m.masterID_Number);
            if(getServant(nodeCursor.masterID).npProtection>0)
            {
                getServant(nodeCursor.masterID).npProtection--;
            }
            if(getServant(nodeCursor.masterID).npProtection<0)
            {
                getServant(nodeCursor.masterID).npProtection=0;
            }
            nodeCursor = nodeCursor.next;
        }
    }

    private String botTurn(Master m) {
        m.myActions.add(HolyGrailWar.drawItem());
        int randomChoice = rand.nextInt(m.myActions.size());

        Action removeThisAction = m.myActions.get(randomChoice);
        String itemChoice = removeThisAction.itemName;

        m.myActions.remove(removeThisAction);

        return itemChoice;
    }

    private HeroicSpirit chooseServants(int masterFocus) {

        if(masterFocus==1){
            System.out.println("Choose a master to attack. Don't choose an eliminated master please :( ");
            System.out.println("Remaining Masters: ");
            for(int i=0; i<HolyGrailWar.remainingMasters.size(); i++){
                System.out.print(HolyGrailWar.remainingMasters.get(i) +" -> Servant: " + HolyGrailWar.summonedServants.get(HolyGrailWar.remainingMasters.get(i)).name + " | ");
            }

            int mast = Integer.parseInt(UserInput.getInput());

            return HolyGrailWar.summonedServants.get(mast);

        }
        else {
            int masters = 0;
            for(int i=0; i<getRemainingMasters().size(); i++) {
                if(getMaster(getRemainingMasters().get(i)).active){
                    masters++;
                }
            }
            int attackedMaster = masterFocus;


            attacker = getServant(masterFocus);

            //Choose an enemy that is not themselves

//            while ((attacker.masterID == attacked.masterID)) {
//                attacked
//            }
            return attacked;
        }
    }


    private HeroicSpirit randomServant() {
        int masters = 0;
        for(int i=0; i<getRemainingMasters().size(); i++) {
            if(getMaster(getRemainingMasters().get(i)).active){
                masters++;
            }
        }
        int masterChoice = rand.nextInt(masters);
        return getServant(masterChoice);
    }
    private void printStatement_whichMaster(int masterT) {
        System.out.println("		  	       *Master #"+masterT+"'s Turn:*");
    }

    public void updateMasters() {
//        //update num of servants
//        for(int b=0; b<HolyGrailWar.masters().size();b++) {
//            HolyGrailWar.masters().get(b).numOfServants=0;
//            for(int sv=0;sv<HolyGrailWar.totalServants().size();sv++)
//            {
//                if(HolyGrailWar.masters().get(b).masterNumber==HolyGrailWar.totalServants().get(sv).master) {
//                    HolyGrailWar.masters().get(b).numOfServants++;
//                }
//            }
//        }

        for(Integer masterNum : HolyGrailWar.masters.keySet()){
            if(HolyGrailWar.masters.get(masterNum).numOfServants==0){
                HolyGrailWar.masters.remove(masterNum);

                int index = HolyGrailWar.remainingMasters.indexOf(masterNum);
                if (index != -1) {
                    HolyGrailWar.remainingMasters.remove(index);
                    System.out.println("==== Master#: " + masterNum + " is out of the war. ====");
                }
            }
        }

        //update on Master's status of being in or out
//        for (int i=0; i<HolyGrailWar.totalServants().size(); i++)
//        {
//            if(HolyGrailWar.totalServants().get(i).lives==0)
//            {
//                for (int x=0; x<HolyGrailWar.masters().size();x++)
//                {
//                    if(HolyGrailWar.masters().get(x).numOfServants == 1 &&
//                            HolyGrailWar.totalServants().get(i).master == HolyGrailWar.masters().get(x).masterNumber) {
//                        HolyGrailWar.masters().get(x).numOfServants--;
//                        System.out.println("==== Master#: "+HolyGrailWar.masters().get(x).masterNumber+" is out of the war. ====");
//                        HolyGrailWar.masters().remove(x);}
//                    else {
//                        if(HolyGrailWar.totalServants().get(i).master == HolyGrailWar.masters().get(x).masterNumber) {
//                            HolyGrailWar.masters().get(x).numOfServants--;}
//                        else {}
//                    }
//                }
//            }
//            else {}
//        }

//        //update on if they out in case(double check)
//        for (int y=0; y<HolyGrailWar.masters().size();y++) {
//            if(HolyGrailWar.masters().get(y).numOfServants==0)
//            {
//                System.out.println("==== Master#: "+HolyGrailWar.masters().get(y).masterNumber+" is out of the war. ====");
//                HolyGrailWar.masters().remove(y);
//            }
//        }

    }

    private void currentStats() {
        System.out.println("========================================================================================");
        System.out.println("Lives: | Health: | Defense: | NP Charges: | NPs | Master | Servant");
        System.out.println("========================================================================================");
        for(Integer key : HolyGrailWar.summonedServants.keySet()) {
            {
                if (HolyGrailWar.summonedServants.get(key).lives >= 10) {
                    System.out.println("  " + HolyGrailWar.summonedServants.get(key).lives + "        " + HolyGrailWar.summonedServants.get(key).hearts + "        " + HolyGrailWar.summonedServants.get(key).defense + "            " + HolyGrailWar.summonedServants.get(key).npCharge + "          " + HolyGrailWar.summonedServants.get(key).npHeld + "      " + HolyGrailWar.summonedServants.get(key).masterID + "      " + HolyGrailWar.summonedServants.get(key).getName());
                } else {
                    System.out.println("  0" + HolyGrailWar.summonedServants.get(key).lives + "        " + HolyGrailWar.summonedServants.get(key).hearts + "        " + HolyGrailWar.summonedServants.get(key).defense + "            " + HolyGrailWar.summonedServants.get(key).npCharge + "          " + HolyGrailWar.summonedServants.get(key).npHeld + "      " + HolyGrailWar.summonedServants.get(key).masterID + "      " + HolyGrailWar.summonedServants.get(key).getName());
                }
            }
        }
    }

    private void updateStats() {
        for (Integer key : HolyGrailWar.summonedServants.keySet())
        {
            if(HolyGrailWar.summonedServants.get(key).lives == 0)
            {
                HolyGrailWar.deceased.put(HolyGrailWar.summonedServants.get(key).name, HolyGrailWar.summonedServants.get(key));
                HolyGrailWar.summonedServants.remove(key);
            }
            else {}
        }
    }

    public void checkLives(HeroicSpirit hS) {
        if(hS.lives==0)
        {
            System.out.println(hS.getName()+" has been defeated.");
            HolyGrailWar.deceased.put(hS.name, hS);
            HolyGrailWar.summonedServants.remove(hS.masterID);
        }
        else {}
    }


}
