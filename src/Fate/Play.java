package Fate;

import java.util.ArrayList;
import java.util.Random;

public class Play {
    Function function = new Function();

    int turnNumber = 0;
//    int currentMasterTurn = 0;
    int trashBin;

    boolean isTrue = true;

    public void playGame(){
        while (HolyGrailWar.masters.size()>1) {
            gameTurn();
            function.pause(2);
        }
    }

    public void gameTurn() {
        int currentMasterTurn = 0;
        turnNumber++;
        System.out.println("\n"+"\n" + "~~~~~~~~~~~~~~~~~~~~~~ Fate/Fractured Reality: Round " + turnNumber +" ~~~~~~~~~~~~~~~~~~~~~~");

        //Iterate through the array of master IDs
        for(int i=0; i<HolyGrailWar.remainingMasters.size();i++) {
            currentMasterTurn = HolyGrailWar.remainingMasters.get(i);
            //System.out.println(" Pass 1: " + currentMasterTurn);

            //Checks if the master has been eliminated, if so, it skips onto the next master ID
            if(!HolyGrailWar.deceasedMasters.contains(currentMasterTurn)){
                //System.out.println(" Pass 2: " + currentMasterTurn);

                //Print out which master's turn it is
                printStatement_whichMaster(currentMasterTurn);
                //System.out.println(" Pass 3: " + currentMasterTurn);

                //Check if master is stunned
                if(function.getMaster(currentMasterTurn).getTurnsFrozen()==0)
                {
                    //System.out.println(" Pass 4: " + currentMasterTurn);
                    isTrue = true;
                    while(isTrue) {
                        //Checks if the master is the player, if so, it's the player's turn.
                        if (currentMasterTurn == 1) {
                            function.getMaster(currentMasterTurn).masterTurn();
                            playAnalysis();
                            function.newLine();
                            if(!function.getMaster(currentMasterTurn).hasExtraTurn()){
                                isTrue = false;
                            }

                        }
                        //Bot Turn
                        else {
                            function.getMaster(currentMasterTurn).botTurn();
                            playAnalysis();
                            function.newLine();
                            if(!function.getMaster(currentMasterTurn).hasExtraTurn()){
                                isTrue = false;
                            }

                        }
                    }
                }
                else {
                    System.out.println("Unable to make any moves: Master is Stunned");
                    function.getMaster(currentMasterTurn).decreasedTurnsFrozen();
                    function.getMaster(currentMasterTurn).decrementAllServants();
                    function.newLine();
                }
                function.pause(2);
            }
        }

        Stats.displayMasterAndServants();
        function.pause(1);
    }

    public void playAnalysis() {
        defeatUpdate();
        checkDeathEffects();
        updateMasters();
    }
    public void checkDeathEffects() {
        for(String name : HolyGrailWar.servantsWithDeathEffects){
            if(HolyGrailWar.deceasedServants.containsKey(name)){
                Effect getEffect = new Effect();
                getEffect.deathEffects(name);
            }
        }
    }
    public void updateMasters() {
        for(Master master : HolyGrailWar.masters.values()){
            if(master.getNumOfServants()==0){
                HolyGrailWar.deceasedMasters.add(master.getMasterID_Number());
                if(master.getMasterID_Number()==1){
                    function.pause(3);
                    System.out.println("============= You have been eliminated... =============");
                    System.out.println("==== Master#: " + master.getMasterID_Number() + " is out of the war. ====");
                }
                else {
                    System.out.println("==== Master#: " + master.getMasterID_Number() + " is out of the war. ====");
                }
                HolyGrailWar.masters.remove(master.getMasterID_Number());
            }
        }

        //Example of use of HashMap improving code.
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
    private void defeatUpdate() {
        trashBin = 0;
        for (HeroicSpirit heroicSpirit : HolyGrailWar.interimSpirits)
        {
            if(HolyGrailWar.masters.containsKey(heroicSpirit.getMasterID())) {
                System.out.println(heroicSpirit.getName() + " has been defeated.");
                function.getMaster(heroicSpirit.getMasterID()).getServantList().remove(heroicSpirit);
                function.getMaster(heroicSpirit.getMasterID()).decreaseServants();
                HolyGrailWar.deceasedServants.put(heroicSpirit.getName(), heroicSpirit);
                trashBin++;
            }
        }
        if(trashBin > 0){
            for(HeroicSpirit heroicSpirit : HolyGrailWar.deceasedServants.values()){
                HolyGrailWar.interimSpirits.remove(heroicSpirit);
            }
        }
    }
    private void printStatement_whichMaster(int masterT) {
        System.out.println("		  	       *Master #"+masterT+"'s Turn:*");
    }
//    private HeroicSpirit chooseServants(int masterFocus) {
//
//        if(masterFocus==1){
//            System.out.println("Choose a master to attack. Don't choose an eliminated master please :( ");
//            System.out.println("Remaining Masters: ");
//            for(int i=0; i<HolyGrailWar.remainingMasters.size(); i++){
//                System.out.print(HolyGrailWar.remainingMasters.get(i) +" -> Servant: " + HolyGrailWar.summonedServants.get(HolyGrailWar.remainingMasters.get(i)).name + " | ");
//            }
//
//            int mast = Integer.parseInt(UserInput.getInput());
//
//            return HolyGrailWar.summonedServants.get(mast);
//
//        }
//        else {
//            int masters = 0;
//            for(int i=0; i<getRemainingMasters().size(); i++) {
//                if(getMaster(getRemainingMasters().get(i)).active){
//                    masters++;
//                }
//            }
//            int attackedMaster = masterFocus;
//
//
//            attacker = getServant(masterFocus);
//
//            //Choose an enemy that is not themselves
//
////            while ((attacker.masterID == attacked.masterID)) {
////                attacked
////            }
//            return attacked;
//        }
//    }
//
//    private HeroicSpirit randomServant() {
//        int masters = 0;
//        for(int i=0; i<getRemainingMasters().size(); i++) {
//            if(getMaster(getRemainingMasters().get(i)).active){
//                masters++;
//            }
//        }
//        int masterChoice = rand.nextInt(masters);
//        return getServant(masterChoice);
//    }




//    public void checkLives(HeroicSpirit hS) {
//        if(hS.lives==0)
//        {
//            System.out.println(hS.getName()+" has been defeated.");
//            HolyGrailWar.deceased.put(hS.name, hS);
//            HolyGrailWar.summonedServants.remove(hS.masterID);
//        }
//        else {}
//    }


}
