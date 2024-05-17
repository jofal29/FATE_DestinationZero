package Fate;

import java.util.Random;

public class Action {
    Function function = new Function();

    String itemName;
    int count;
    boolean isTrue = true;

    public void chooseSelfAction(String action, HeroicSpirit ownServant) {
        if(ownServant.checkImmune(action).equals("not immune")) {
            switch(action) {
                case "npCharge": {
                    npCharge(ownServant);
                }
                break;
                case "addNp":{
                    addNp(ownServant);
                }
                break;
                case "heal": {
                    heal(ownServant);
                }
                break;
                case "shield": {
                    shield(ownServant);
                }
                break;
                case "protectFromNP":{
                    protectFromNP(ownServant);
                }break;
                default: {
                    System.out.println("Oops");
                }
                break;
            }		}
        else {
            System.out.println(ownServant.getName()+ " is immune to " + action);
        }
    }
    public void chooseAMasterAction(String action, int enemyMasterID, int ownMasterID){
        switch (action) {
            case "stun": {
                stun(enemyMasterID, ownMasterID);
            }
            break;
        }
    }
    public void chooseEnemyAction(String action,int ownMasterID, HeroicSpirit enemyServant){
        if(enemyServant.checkImmune(action).equals("not immune")) {
            switch(action) {
                case "npSeal": {
                    npSeal(ownMasterID, enemyServant);
                }
                break;
                case "attack": {
                    attack(ownMasterID, enemyServant);
                }
                break;
                case "curse": {
                    curse(ownMasterID, enemyServant);
                }
                break;
                case "takeLife": {
                    enemyServant.takeLife();
                }
                break;
                case "charm": {
                    if(enemyServant.getTurnsFrozen()==0)
                    {
                        charm(1, enemyServant);
                    }
                    else{System.out.println(enemyServant.getName()+" cannot be charmed again. Charm already applied.");}
                }
                break;
                default: {
                    System.out.println("Oops");
                }
                break;
            }		}
        else {
            System.out.println(enemyServant.getName()+ " is immune to " + action);
        }
    }

    private void addNp(HeroicSpirit ownServant) {
        double randomOfTwo = new Random().nextBoolean() ? 1 : 2;
        for (int i=0; i<randomOfTwo; i++) {
            System.out.println(ownServant.getName() + " +1NP");
            ownServant.addNp();
            HolyGrailWar.masters.get(ownServant.getMasterID()).getMasterItems().add(new Item("useNP"));
        }
    }
    public void curse(int ownMasterID,HeroicSpirit targetedServant) {
        targetedServant.addCurse();
        targetedServant.decreaseMaxHealth();
        System.out.println(targetedServant.getName()+" Cursed (-1 Max Health) by Master " + ownMasterID);
        if(targetedServant.getMaxHealth() < targetedServant.getHearts()) {
            targetedServant.setHearts(targetedServant.getMaxHealth());
            System.out.println("Hearts exceeds Max Health... stabilizing...");
            System.out.println("Max Health: "+targetedServant.getMaxHealth()+ " | Hearts: "+ targetedServant.getHearts());
        }
        else {System.out.println("Max Health: "+targetedServant.getMaxHealth());}
        targetedServant.checkHealth(targetedServant);
    }
    public void npCharge(HeroicSpirit ownServant) {
        double randomOfTwo = new Random().nextBoolean() ? 1 : 2;
        for (int i=0; i<randomOfTwo; i++) {
            System.out.println(ownServant.getName() + " +1np charge");
            ownServant.addNpCharge();
        }
        if(ownServant.getNpCharge()>=ownServant.getNpMaxCharges()) {
            ownServant.addNp();
            ownServant.setNpCharge(ownServant.getNpCharge()-ownServant.getNpMaxCharges());
            System.out.println("Max charges reached... NP Held +1");
            HolyGrailWar.masters.get(ownServant.getMasterID()).getMasterItems().add(new Item("useNP"));
        }
        else	{
            int npNeed = ownServant.getNpMaxCharges() - ownServant.getNpCharge();
            System.out.println(ownServant.getName() + " needs " + npNeed + " more to transform into a NP load.");
        }
    }
    public void attack(int master, HeroicSpirit targetedServant) {

        System.out.println("Master " + master + " sets out an attack on " + targetedServant.getName());
        if(targetedServant.getDefense() > 0)
        {
            targetedServant.subtractDefense();
            System.out.println(targetedServant.getName()+" -1 Shield");
        }
        else {
            targetedServant.loseHearts();
        }
        targetedServant.checkHealth(targetedServant);

        if(targetedServant.getHearts()==0) {
            targetedServant.setDeathbyAttack(true);
        }
    }
    public void heal(HeroicSpirit ownServant) {
        if(ownServant.getCurses() > 0)
        {
            ownServant.subtractCurse();
            ownServant.increaseMaxHealth();
            ownServant.checkHealth(ownServant);
        }
        else {
            if(ownServant.getHearts()==ownServant.getMaxHealth())
            {
                System.out.println("Heal wasted...");
            }
            else {
                ownServant.gainHearts();
                ownServant.checkHealth(ownServant);
            }
        }
    }
    public void charm(int numberOfCharms, HeroicSpirit enemyServant) {
        for(int i=0;i<numberOfCharms;i++) {
            enemyServant.increaseTurnsFrozen();}
        System.out.println(enemyServant.getName()+" has been charmed.");
    }
    public void shield(HeroicSpirit ownServant) {
        if(ownServant.getDefense()==ownServant.getMaxHealth())
        {
            System.out.println(ownServant.getName()+": Max Shields Already Reached");
        }
        else
        {
            System.out.println(ownServant.getName()+" +1 Shield");
            ownServant.addDefense();
        }
        ownServant.checkHealth(ownServant);
    }
    public void stun(int enemyMasterID, int ownMasterID) {
        HolyGrailWar.masters.get(enemyMasterID).increaseTurnsFrozen();
        System.out.println("Master "+HolyGrailWar.masters.get(enemyMasterID).getMasterID_Number() +" has been stunned by Master "+ ownMasterID);
    }
    public void npSeal(int ownMasterID, HeroicSpirit targetedServant) {
        targetedServant.addNpSeals();
        System.out.println(targetedServant.getName()+"'s NP has been sealed by Master " + ownMasterID);
    }
    public void protectFromNP(HeroicSpirit ownServant) {
        ownServant.increasedNpProtection();
        System.out.println(ownServant.getName()+" gains protection for NP for one turn.");
    }
    public void chooseOngoing(String string, int ownMasterID, HeroicSpirit targetedServant, int loops) {
        if(targetedServant.checkImmune(string)=="not immune") {
            switch(string) {
                case "ongoingCurses": {
                    targetedServant.setOngoingCurses(targetedServant.getOngoingCurses()+loops);
                    System.out.println("Ongoing Curses +"+loops);
                }
                break;
                case "disableNP": {
                    targetedServant.setNP_Status("Disabled");
                    System.out.println(targetedServant.getName()+"'s NP has been disabled.");
                }
                break;
                case "attack": {
                    attack(ownMasterID, targetedServant);
                }
                break;
                case "curse": {
                    curse(ownMasterID, targetedServant);
                }
                break;
                case "shield": {
                    shield(targetedServant);
                }
                break;
                case "takeLife": {
                    targetedServant.takeLife();
                }
                break;
                case "charm": {
                    if(targetedServant.getTurnsFrozen()==0)
                    {
                        charm(1, targetedServant);
                    }
                    else{System.out.println(targetedServant.getName()+" cannot be charmed again.");}
                }
                break;
                default: {
                    System.out.println("Oops");
                }
                break;
            }}
        else{
            System.out.println(targetedServant.getName()+ " is immune to " + string);
        }
    }
}
