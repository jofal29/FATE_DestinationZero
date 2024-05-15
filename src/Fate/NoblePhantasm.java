package Fate;

import java.util.Objects;
import java.util.Random;

public class NoblePhantasm {
    //Attributes
    String name;
    int ongoing = 0;
    Effect servantDeath = new Effect();

    public NoblePhantasm() {
    }

    Keyword searchFor = new Keyword();
    Play check = new Play();
    Action cause = new Action("Random");

    private void ongoingHit(String string, int numOfHits) {

        if (searchFor.status == true) {
            for (Integer key : HolyGrailWar.summonedServants.keySet()) {
                {
                    if (Objects.equals(HolyGrailWar.summonedServants.get(key).getName(), searchFor.assignedServant.getName())) {
                        System.out.println("Target Found: " + searchFor.assignedServant.getName());
                        cause.chooseOngoing(string, HolyGrailWar.summonedServants.get(key), numOfHits);
                        check.updateMasters();
                        check.checkLives(HolyGrailWar.summonedServants.get(key));
                    }
                }
            }
        }
    }

    private void addRestriction(String servantInControl) {
        if(searchFor.status==true)
        {
            for(Integer j : HolyGrailWar.getSummonedServants().keySet())
            {
                if(HolyGrailWar.getSummonedServants().get(j).getName().toString()==searchFor.assignedServant.getName().toString())
                {
                    System.out.println("Target Found: "+searchFor.assignedServant.getName().toString());
                    HolyGrailWar.getSummonedServants().get(j).restrictions.add(servantInControl);
                    check.updateMasters();
                    check.checkLives(HolyGrailWar.getSummonedServants().get(j));				}
                else {}}}
        else {
        }

    }
    public void hitTarget(String np) {
        if(searchFor.status==true)
        {
            for(Integer j : HolyGrailWar.getSummonedServants().keySet())
            {
                if(HolyGrailWar.getSummonedServants().get(j).getName() == searchFor.assignedServant.getName())
                {
                    System.out.println("Target Found: "+ searchFor.assignedServant.getName());
                    cause.chooseAction(np, HolyGrailWar.getSummonedServants().get(j));
                    check.playAnalysis();
//						check.updateMasters();
//						check.checkDeathEffects();
//						check.checkLives(holyGrailWar.totalServants().get(j));
                }
                else {}}}
        else {
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
            case "Cu Chulainn":{
                h.setNoble_Phantasm("(NP Required) Eliminate One");
                h.setNpRequired(1);
            }	break;
            case "Emiya":{
                h.setNoble_Phantasm("");
            }	break;
            case "Euryale":{
                h.setNoble_Phantasm("");
            }	break;
            case "Heracles":{
                h.setNoble_Phantasm("");
                h.setNpRequired(1);
            }	break;
            case "Jason":{
                h.setNoble_Phantasm("");
            }	break;
            case "Kama":{
                h.setNoble_Phantasm("");
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

    public void activate(HeroicSpirit servant) {

        //Checks if the servant will sacrifice themselves.
        if(servant.getNpHeld()==0 && servant.getNpRequired()==0)
        {
            servant.selfSacrifice();
        }

        //Switch Case, activates the respected method according to the servant's name
        System.out.println(servant.getName()+" uses ");
        System.out.println("*****************************************************************************");
        System.out.println("*                                                                           *");
        System.out.println("*                                                                           *");

        switch(servant.getName()) {
            case "Achilles":{
                achillesNP(servant);
            }	break;
            case "Arash":{
                arashNP(servant);
            }	break;
            case "Atalante":{
                atalanteNP(servant);
            }	break;
            case "Artoria":{
                artoriaNP(servant);
            }	break;
            case "Emiya":{
                emiyaNP(servant);
            }	break;
            case "Euryale":{
                euryaleNP(servant);
            }	break;
            case "Heracles":{
                heraclesNP(servant);
            }	break;
            case "Jason":{
                jasonNP(servant);
            }	break;
            case "Kama":{
                kamaNP(servant);
            }	break;
            case "Nitocris":{
                nitocrisNP(servant);
            }	break;
            case "William Shakespeare":{
                williamShakespeareNP(servant);
            }	break;
            default:{
                System.out.println("				   None");}
        }
        checkSelfSacrifice(servant);
        System.out.println("*                                                                           *");
        System.out.println("*                                                                           *");
        System.out.println("*****************************************************************************");
    }

    public void override(String npName, HeroicSpirit hes) {
        switch(npName) {
            case "Arash":{
                arashNP(hes);
            }	break;
            default:
                System.out.println("None");
        }
    }

    private void checkSelfSacrifice(HeroicSpirit heroicSpirit) {
        if(heroicSpirit.isSacrifice()) {
            heroicSpirit.takeLife();
            System.out.println(heroicSpirit.getName()+" has used their spirit origin to use their NP. Fading away...");
            check.playAnalysis();
        }
    }

    //Library of Servants' Noble Phantasms=======================================================================================================================================
    private void achillesNP(HeroicSpirit servant) {
        int randomNP = new Random().nextBoolean() ? 1 : 2;
        if(this.user.npHeld==0) {
            randomNP=1;
        }
        switch(randomNP) {
            case 1:{
                System.out.println("			 "+"Noble Phantasm, Troias Tragōidia!");
                searchFor.excludingSelf("Any","Any","Achilles");
                searchFor.servantChoice();
                hitTarget("takeLife");
                check.playAnalysis();
                searchFor.clearList();
                this.user.npHeld--;
            }break;
            case 2:{
                if(this.user.npHeld<2)
                {
                    System.out.println("Insufficient Mana. Need "+(2-this.user.npHeld)+" more NP card.");
                }else {
                    System.out.println("			 "+"Noble Phantasm, Akhilleus Kosmos");
                    {
                        searchFor.selfAndTeammates(this.user.masterID);
                        for(int i=0; i<searchFor.temp.size(); i++)
                            searchFor.servantChoice();
                        hitTarget("protectFromNP");
                        check.playAnalysis();
                    }
                    this.user.npHeld--;
                    this.user.npHeld--;
                    searchFor.clearList();
                }
            }break;
            default:
        }
    }
    private void arashNP(HeroicSpirit servant) {
        System.out.println("			"+"Noble Phantasm... STELLAAAAA!!");
        searchFor.excludingSelf("Master","AnyMaster","Arash");
        int numTimes = searchFor.temp.size();
        for(int i=0; i<numTimes;i++)
        {
            searchFor.servantChoice();
            hitTarget("takeLife");
            check.playAnalysis();
        }
        searchFor.clearList();

        this.user.selfSacrifice();
    }
    private void artoriaNP(HeroicSpirit servant) {

        //NP Required = 2
            System.out.println("			  "+"Noble Phantasm... EXCALIBUR!!");
            searchFor.excludingSelf("Any", "Any", "Artoria");
            for(int i=0; i<searchFor.temp.size(); i++) {
                searchFor.servantChoice();
            }
            hitTarget("takeLife");
            check.playAnalysis();

            //Removes NPs Loaded
            servant.setNpHeld(servant.getNpHeld()-servant.getNpRequired());
            searchFor.clearList();
    }
    private void atalanteNP(HeroicSpirit servant) {
        System.out.println("  "+"Noble Phantasm...  Phoebus Catastrophe:Complaint Message on the Arrow!!");
        searchFor.excludingSelf("Master","EachMaster","Atalante");
        if(this.user.npSealed=="Enabled")
        {
            searchFor.removeType("Child");
        }

        if(searchFor.temp.size()>0) {
            int numTimes = searchFor.temp.size();
            for(int i=0; i<numTimes;i++)
            {
                searchFor.servantChoice();
                hitTarget("takeLife");
                check.playAnalysis();
            }
            searchFor.clearList();
            this.user.npHeld--;
            this.user.selfSacrifice();}
        else {}
    }


    private void heraclesNP(HeroicSpirit servant) {
        System.out.println("	Noble Phantasm... Nine Lives: Shooting the Hundred Heads");
        searchFor.excludingSelf("Any","Any","Heracles");
        searchFor.servantChoice();
        hitTarget("takeLife");
        check.playAnalysis();
        searchFor.clearList();
        this.user.npHeld--;
    }
    private void emiyaNP(HeroicSpirit servant) {
        System.out.println("	 "+"I am the bone of my sword... Unlimited BladeWorks");
        int initialNum = HolyGrailWar.getSummonedServants().size();
        for(int i=0; i<3; i++) {
            searchFor.excludingSelf("Species","Human","Emiya");
            searchFor.servantChoice();
            hitTarget("takeLife");
            check.playAnalysis();
            searchFor.clearList();
        }
        if(HolyGrailWar.getSummonedServants().size()==initialNum){
            searchFor.excludingSelf("Any","Any","Heracles");
            searchFor.servantChoice();
            hitTarget("takeLife");
            check.playAnalysis();
            searchFor.clearList();
        }
        this.user.npHeld--;
    }
    private void euryaleNP(HeroicSpirit servant) {
        System.out.println("			 "+"==Noble Phantasm==");
        searchFor.excludingSelf("Any","Any","Euryale");
        searchFor.servantChoice();
        hitTarget("takeLife");
        check.playAnalysis();
        searchFor.clearList();
        this.user.npHeld--;
    }
    private void jasonNP(HeroicSpirit servant) {
        System.out.println("			 "+"==Astrapste Argo==");
        System.out.println("		    	"+"==COME MY ARGONAUTS==");

        for(Integer key : HolyGrailWar.summonedServants.keySet()){
            if(HolyGrailWar.summonedServants.get(key).equals("Argonaut") && HolyGrailWar.summonedServants.get(key).masterID !=this.user.masterID){
                System.out.println(HolyGrailWar.summonedServants.get(key)+
                        " has changed masters ("+HolyGrailWar.summonedServants.get(key).masterID +"->"+this.user.masterID +")");
                HolyGrailWar.masters.get(key).numOfServants--;
                System.out.println("Master "+HolyGrailWar.masters.get(key).masterID_Number + " has "+HolyGrailWar.masters.get(key).numOfServants + " left.");
                HolyGrailWar.summonedServants.get(key).masterID =this.user.masterID;
                check.updateMasters();
            }
        }

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
        this.user.npHeld--;
    }
    private void kamaNP(HeroicSpirit servant) {
        System.out.println("				"+"--Noble Phantasm.--");
        searchFor.includingSelf("Gender", "M");
        int numTimes = searchFor.temp.size();
        for(int i=0; i<numTimes;i++) {
            searchFor.servantChoice();
            hitTarget("charm");
            check.playAnalysis();
        }
        searchFor.clearList();
        this.user.npHeld--;
    }
    private void nitocrisNP(HeroicSpirit servant) {
        int randomNP = new Random().nextBoolean() ? 1 : 2;
        switch(randomNP) {
            case 1:{
                System.out.println("	     "+"Noble Phantasm... Anpu Neb Ta Djeser...");
                HolyGrailWar.masters.get(this.user.masterID).extraTurns++;

//                for(int m=0;m<HolyGrailWar.masters().size();m++) {
//                    if(HolyGrailWar.masters().get(m).masterNumber==this.user.master) {
//                        HolyGrailWar.masters().get(m).extraTurns++;
//                    }
//                }

                check.playAnalysis();
                this.user.npHeld--;
            }break;
            case 2:{
                if(this.user.npHeld<2)
                {
                    System.out.println("Insufficient Mana. Need "+(2-this.user.npHeld)+" more NP Card.");
                }
                else {
                    System.out.println("	     "+"Noble Phantasm... ANPU NEB TA DJESER!!");
                    for(int i=0; i<2; i++)
                    {
                        searchFor.excludingSelf("Extra","King","Nitocris");
                        searchFor.removeServant("Ozymandias");
                        searchFor.servantChoice();
                        hitTarget("takeLife");
                        check.playAnalysis();
                        this.user.npHeld--;
                    }searchFor.clearList();
                    this.user.selfSacrifice();}
            }break;
            default:{}}
    }
    private void williamShakespeareNP(HeroicSpirit servant) {
        System.out.println("				 "+"==Noble Phantasm==");

        searchFor.excludingSelf("Any","Any","William Shakespeare");
        searchFor.servantChoice();

        addRestriction("William Shakespeare");
        ongoingHit("disableNP",1);

        check.playAnalysis();
        this.user.npHeld--;

        searchFor.clearList();
    }



}
