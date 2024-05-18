package Fate;

import java.util.Random;

public class NoblePhantasm {
    //Attributes
    private String name;
    private int ongoing = 0;
    int npUserMasterID;
    HeroicSpirit servantNameAndIDInfo;

    Effect servantDeath = new Effect();

    public NoblePhantasm() {
    }

    Keyword searchFunction = new Keyword();
    Play play = new Play();
    Action action = new Action();

    private void ongoingHit(String string, int numOfHits, int masterID, String enemyName) {
        for(HeroicSpirit heroicSpirit : HolyGrailWar.masters.get(masterID).getServantList()){
            if(heroicSpirit.getName().equals(enemyName)){
                System.out.println("Target Found: " + enemyName);
                action.chooseOngoing(string, masterID, heroicSpirit, numOfHits);
                play.updateMasters();
                heroicSpirit.checkHealth(heroicSpirit);
                break;
            }
        }
    }
    private void addRestriction(String servantInControl, int masterID, String userName) {
        for(HeroicSpirit heroicSpirit : HolyGrailWar.masters.get(masterID).getServantList()){
            if(heroicSpirit.getName().equals(userName)){
                System.out.println("Target Found: " + userName);
                heroicSpirit.getRestrictions().add(servantInControl);
                play.updateMasters();
                heroicSpirit.checkHealth(heroicSpirit);
                break;
            }
        }

    }
    public void hitEnemyTarget(String np, int enemyMaster, String servantTarget) {
        for(HeroicSpirit heroicSpirit : HolyGrailWar.masters.get(enemyMaster).getServantList()){
            if(heroicSpirit.getName().equals(servantTarget)){
                System.out.println("Target Found: " + servantTarget);
                action.chooseEnemyAction(np, enemyMaster, heroicSpirit);
                play.playAnalysis();
                break;
            }
        }
    }
    public void hitOwnServants(String np, int masterID, String userName) {
        for(HeroicSpirit heroicSpirit : HolyGrailWar.masters.get(masterID).getServantList()){
            if(heroicSpirit.getName().equals(userName)){
                System.out.println("Target Found: " + userName);
                action.chooseSelfAction(np, heroicSpirit);
                play.playAnalysis();
                break;
            }
        }
    }
    public void assignNPs(String n, HeroicSpirit h) {
        switch(n) {
            case "Achilles":{
                h.setNoble_Phantasm("1NP:Eliminate One || 2NP(Required):Protect self and teammates for one turn from NPs");
                h.setNpRequired(1);
            }	break;
            case "Arash":{
                h.setNoble_Phantasm("1NP:Choose a master. Elimate all servants on their deck then discard Arash");
                h.setNpRequired(1);
            }	break;
            case "Atalante":{
                h.setNoble_Phantasm("1NP:Eliminate One Servant From Each Master then discard Atalante");
                h.setNpRequired(1);
            }	break;
            case "Artoria":{
                h.setNoble_Phantasm("2NP:Eliminate All Servants From All Enemy Masters");
                h.setNpRequired(2);
            }	break;
            case "CuChulainn":{
                h.setNoble_Phantasm("(1 NP Required) Eliminate One");
                h.setNpRequired(1);
            }	break;
            case "Emiya":{
                h.setNoble_Phantasm("(1 NP Required) Eliminate 3 Human Servants. If none, eliminate one.");
            }	break;
            case "Euryale":{
                h.setNoble_Phantasm("Eliminate one servant.");
            }	break;
            case "Heracles":{
                h.setNoble_Phantasm("Eliminate one servant");
                h.setNpRequired(1);
            }	break;
            case "Jason":{
                h.setNoble_Phantasm("Summon argonaut servants.");
            }	break;
            case "Kama":{
                h.setNoble_Phantasm("Charm all male servants. Chance of eliminating one.");
            }	break;
            case "Nitocris":{
                h.setNoble_Phantasm("1NP(Required):Repeat Master's Turn||2NP(Required):Eliminate all king servants except Nitocris and Ozymandias.");
            }	break;
            case "William Shakespeare":{
                h.setNoble_Phantasm("Disable an enemy servant's noble phantasm.");
            }	break;
            default:
                System.out.println("None");
        }
    }
    public void activate(HeroicSpirit npUser) {
        //Sets master ID of the current servant using a noble phantasm
        npUserMasterID = npUser.getMasterID();

        //Checks if the servant will sacrifice themselves.
        if(npUser.getNpHeld()==0 && npUser.getNpRequired()==0)
        {
            npUser.selfSacrifice();
        }

        //Switch Case, activates the respected method according to the servant's name
        System.out.println(npUser.getName()+" uses ");
        System.out.println("*****************************************************************************");
        System.out.println("*                                                                           *");
        System.out.println("*                                                                           *");

        switch(npUser.getName()) {
            case "Achilles":{
                achillesNP(npUser);
            }	break;
            case "Arash":{
                arashNP(npUser);
            }	break;
            case "Atalante":{
                atalanteNP(npUser);
            }	break;
            case "Artoria":{
                artoriaNP(npUser);
            }	break;
            case "Emiya":{
                emiyaNP(npUser);
            }	break;
            case "Euryale":{
                euryaleNP(npUser);
            }	break;
            case "Heracles":{
                heraclesNP(npUser);
            }	break;
            case "Jason":{
                jasonNP(npUser);
            }	break;
            case "Kama":{
                kamaNP(npUser);
            }	break;
            case "Nitocris":{
                nitocrisNP(npUser);
            }	break;
            case "William Shakespeare":{
                williamShakespeareNP(npUser);
            }	break;
            default:{
                System.out.println("				   None");}
        }
        checkSelfSacrifice(npUser);
        System.out.println("*                                                                           *");
        System.out.println("*                                                                           *");
        System.out.println("*****************************************************************************");
    }
    public void eliminateList(){
        for(int i = 0; i< searchFunction.temp.size(); i++){
            if(servantNameAndIDInfo != null) {
                servantNameAndIDInfo = searchFunction.temp.get(i);
                hitEnemyTarget("takeLife", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
            }
        }
        searchFunction.clearList();
    }
    public void override(String npName, HeroicSpirit heroicSpirit) {
        switch(npName) {
            case "Arash":{
                arashNP(heroicSpirit);
            }	break;
            default:
                System.out.println("None");
        }
    }
    private void checkSelfSacrifice(HeroicSpirit heroicSpirit) {
        if(heroicSpirit.isSacrifice()) {
            heroicSpirit.takeLife();
            System.out.println(heroicSpirit.getName()+" has used their spirit origin to use their NP. Fading away...");
            heroicSpirit.checkHealth(heroicSpirit);
            HolyGrailWar.deceasedMasters.add(heroicSpirit.getMasterID());
            play.playAnalysis();
        }
    }

    //Library of Servants' Noble Phantasms=======================================================================================================================================
    private void achillesNP(HeroicSpirit servant) {
        int randomNP = new Random().nextBoolean() ? 1 : 2;
        if(servant.getNpHeld()==0) {
            randomNP=1;
        }
        switch(randomNP) {
            case 1:{
                System.out.println("			 "+"Noble Phantasm, Troias Tragōidia!");
                searchFunction.targetServants("Any","Any");
                searchFunction.removeSelfTeammatesOrSpecificName("Achilles", npUserMasterID);
                servantNameAndIDInfo = searchFunction.randomServantChoice();
                if(servantNameAndIDInfo != null) {
                    hitEnemyTarget("takeLife", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
                }
                searchFunction.clearList();
                servant.selfSacrifice();
            }break;
            case 2:{
                if(servant.getNpHeld()<2)
                {
                    System.out.println("Insufficient Mana. Need "+(2-servant.getNpHeld())+" more NP card.");
                }else
                {
                    System.out.println("			 "+"Noble Phantasm, Akhilleus Kosmos");
                    searchFunction.targetSelfAndTeammates(npUserMasterID);
                    for(int i = 0; i< searchFunction.temp.size(); i++){
                        servantNameAndIDInfo = searchFunction.temp.get(i);
                        hitOwnServants("protectFromNP",servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
                    }
                    servant.subtractNpHeld();
                    servant.subtractNpHeld();
                    searchFunction.clearList();
                }
            }break;
            default:
        }
    }
    private void arashNP(HeroicSpirit servant) {
        System.out.println("			"+"Noble Phantasm... STELLAAAAA!!");
        searchFunction.targetServants("Master","AnyMaster");
        searchFunction.removeSelfTeammatesOrSpecificName("Arash", npUserMasterID);
        servantNameAndIDInfo = searchFunction.randomServantChoice();
        eliminateList();
        servant.selfSacrifice();
    }
    private void artoriaNP(HeroicSpirit servant) {

        //NP Required = 2
        System.out.println("			  "+"Noble Phantasm... EXCALIBUR!!");
        searchFunction.targetServants("Master","AnyMaster");
        searchFunction.removeSelfTeammatesOrSpecificName("Artoria", npUserMasterID);
        eliminateList();
        servant.subtractNpHeld();
        servant.subtractNpHeld();
    }
    private void atalanteNP(HeroicSpirit servant) {
        System.out.println("  "+"Noble Phantasm...  Phoebus Catastrophe:Complaint Message on the Arrow!!");
        searchFunction.targetServants("Master","EachMaster");
        searchFunction.removeSelfTeammatesOrSpecificName("Atalante", npUserMasterID);
        if(servant.getNP_Status().equals("Enabled")){
            searchFunction.removeType("Child");
        }
        eliminateList();
        servant.subtractNpHeld();
    }
    private void heraclesNP(HeroicSpirit servant) {
        System.out.println("	Noble Phantasm... Nine Lives: Shooting the Hundred Heads");
        searchFunction.targetServants("Any","Any");
        searchFunction.removeSelfTeammatesOrSpecificName("Heracles", npUserMasterID);
        eliminateList();
        servant.subtractNpHeld();
    }

    private void emiyaNP(HeroicSpirit servant) {
        System.out.println("	 "+"I am the bone of my sword... Unlimited BladeWorks");
        searchFunction.targetServants("Species","Human");
        searchFunction.removeSelfTeammatesOrSpecificName("Emiya", npUserMasterID);
        servantNameAndIDInfo = searchFunction.randomServantChoice();
        if(searchFunction.temp.size()>=3) {
            for (int i = 0; i < 3; i++) {
                servantNameAndIDInfo = searchFunction.temp.get(i);
                if (servantNameAndIDInfo != null) {
                    hitEnemyTarget("takeLife", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
                }
            }
            servant.subtractNpHeld();
            searchFunction.clearList();
        }
        else{
            searchFunction.targetServants("Any", "Any");
            searchFunction.removeSelfTeammatesOrSpecificName("Emiya", npUserMasterID);
            servantNameAndIDInfo = searchFunction.randomServantChoice();
            if(servantNameAndIDInfo != null) {
                hitEnemyTarget("takeLife", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
            }
            servant.subtractNpHeld();
            searchFunction.clearList();
        }
    }
    private void euryaleNP(HeroicSpirit servant) {
        System.out.println("			 "+"==Noble Phantasm==");
        searchFunction.targetServants("Any","Any");
        searchFunction.removeSelfTeammatesOrSpecificName("Euryale", npUserMasterID);
        servantNameAndIDInfo = searchFunction.randomServantChoice();
            if(servantNameAndIDInfo != null) {
                hitEnemyTarget("takeLife", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
            }
        servant.subtractNpHeld();
        searchFunction.clearList();
    }
    private void jasonNP(HeroicSpirit servant) {
        System.out.println("			 "+"==Astrapste Argo==");
        System.out.println("		    	"+"==COME MY ARGONAUTS==");
        searchFunction.targetServants("Extra","Argonaut");
        searchFunction.removeSelfTeammatesOrSpecificName("Jason", npUserMasterID);
        servantNameAndIDInfo = searchFunction.randomServantChoice();
        for(int i = 0; i< searchFunction.temp.size(); i++){
            servantNameAndIDInfo = searchFunction.temp.get(i);
            if(servantNameAndIDInfo != null) {
                System.out.println(servantNameAndIDInfo.getName()+" has changed masters ("+ servantNameAndIDInfo.getMasterID()+"->"+npUserMasterID);
                HolyGrailWar.masters.get(servantNameAndIDInfo.getMasterID()).decreaseServants();
                System.out.println("Master "+HolyGrailWar.masters.get(servantNameAndIDInfo.getMasterID()) + " has "+HolyGrailWar.masters.get(servantNameAndIDInfo.getMasterID()).getNumOfServants() + " left.");
                play.updateMasters();
            }
        }
        servant.subtractNpHeld();
        searchFunction.clearList();
//      Example of improvement on data structure
//        for(int i=0;i<HolyGrailWar.totalServants().size();i++)
//        {
//            for(int a=0;a<HolyGrailWar.totalServants().get(i).extraTraits.size();a++)
//            {
//                if(HolyGrailWar.totalServants().get(i).extraTraits.get(a)=="Argonaut") {
//                    if(HolyGrailWar.totalServants().get(i).master==this.user.master) {
//                    }
//
//                    else{
//                        System.out.println(HolyGrailWar.totalServants().get(i).getName()+
//                                " has changed masters ("+HolyGrailWar.totalServants().get(i).master+"->"+this.user.master+")");
//
//                        for(int m=0;m<HolyGrailWar.masters().size();m++) {
//                            if(HolyGrailWar.masters().get(m).masterNumber==HolyGrailWar.totalServants().get(i).master) {
//                                HolyGrailWar.masters().get(m).numOfServants--;
//                                System.out.println("Master "+HolyGrailWar.masters().get(m).masterNumber+
//                                        " has "+HolyGrailWar.masters().get(m).numOfServants + " left.");
//                            }
//                        }
//
//                        HolyGrailWar.totalServants().get(i).master=this.user.master;
//
//                        check.updateMasters();
//                    }
//                }
//            }
//        }
//        this.user.npHeld--;
    }
    private void kamaNP(HeroicSpirit servant) {
        System.out.println("				"+"--Noble Phantasm.--");
        searchFunction.targetServants("Gender","M");
        servantNameAndIDInfo = searchFunction.randomServantChoice();
        for(int i = 0; i< searchFunction.temp.size(); i++){
            servantNameAndIDInfo = searchFunction.temp.get(i);
            if(servantNameAndIDInfo != null) {
                hitEnemyTarget("charm", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
            }
        }
        int randomOfTwo = new Random().nextBoolean() ? 1 : 2;
        if(randomOfTwo==1){
            searchFunction.clearList();
            searchFunction.targetServants("Any", "Any");
            searchFunction.removeSelfTeammatesOrSpecificName("Kama", npUserMasterID);
            hitEnemyTarget("takeLife", servantNameAndIDInfo.getMasterID(), searchFunction.randomServantChoice().getName());
            searchFunction.clearList();
            servant.subtractNpHeld();
        }
        else{
            searchFunction.clearList();
            servant.subtractNpHeld();
        }

    }
    private void nitocrisNP(HeroicSpirit servant) {
        int randomNP = new Random().nextBoolean() ? 1 : 2;
        switch(randomNP) {
            case 1:{
                System.out.println("	     "+"Noble Phantasm... Anpu Neb Ta Djeser...");
                System.out.println("         "+"    Giving master an extra turn...");
                HolyGrailWar.masters.get(servant.getMasterID()).addExtraTurn();
                play.playAnalysis();
                servant.subtractNpHeld();
            }break;
            case 2:{
                if(servant.getNpHeld()<2)
                {
                    System.out.println("Insufficient Mana. Need "+(2-servant.getNpHeld())+" more NP Card.");
                }
                else {
                    System.out.println("	     "+"Noble Phantasm... ANPU NEB TA DJESER!!");
                    for(int i=0; i<2; i++)
                    {
                        searchFunction.targetServants("Extra","King");
                        searchFunction.removeSelfTeammatesOrSpecificName("Ozymandias", servant.getMasterID());
                        servantNameAndIDInfo = searchFunction.randomServantChoice();
                        if(servantNameAndIDInfo != null) {
                            hitEnemyTarget("takeLife", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
                        }
                        servant.subtractNpHeld();
                    }
                    searchFunction.targetServants("Any", "Any");
                    searchFunction.removeSelfTeammatesOrSpecificName("Nitocris", -1);
                    servantNameAndIDInfo = searchFunction.randomServantChoice();
                    if(servantNameAndIDInfo != null) {
                        hitEnemyTarget("takeLife", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
                    }
                    servant.subtractNpHeld();
                    searchFunction.clearList();
                    searchFunction.clearList();
                    servant.selfSacrifice();}
            }break;
            default:{}}
    }
    private void williamShakespeareNP(HeroicSpirit servant) {
        System.out.println("				 "+"==Noble Phantasm==");
        searchFunction.targetServants("Any","Any");
        searchFunction.removeSelfTeammatesOrSpecificName("William Shakespeare", npUserMasterID);
        servantNameAndIDInfo = searchFunction.randomServantChoice();
            if(servantNameAndIDInfo != null) {
                addRestriction("William Shakespeare", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
                hitEnemyTarget("takeLife", servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
                ongoingHit("disableNP",1,servantNameAndIDInfo.getMasterID(), servantNameAndIDInfo.getName());
            }
        servant.subtractNpHeld();
        searchFunction.clearList();
    }



}
