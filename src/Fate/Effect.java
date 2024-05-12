package Fate;

public class Effect {
    Action remove = new Action("Random");
    Play insurance = new Play();

    Effect(){}

    public void deathEffects(String nameOfDeadServant) {
        switch(nameOfDeadServant) {
            case "William Shakespeare":{
                williamShakespeareDeathEffect(nameOfDeadServant);
            }
            break;
            case "Arash":{
                arashSkillDeathEffect(nameOfDeadServant);
            }
            break;
            default:{
                System.out.println("No death effects");
            }
        }
    }
    private void arashSkillDeathEffect(String deathName) {
        if(HolyGrailWar.deceased.get(deathName).npSealed=="Enabled"){
            if(HolyGrailWar.deceased.get(deathName).npCharge==2) {
                if(HolyGrailWar.deceased.get(deathName).sacrifice)
                {}else {
                    System.out.println("But with his last breath & 2np charges, he activates his");
                    HolyGrailWar.deceased.get(deathName).npCharge=0;
                    HolyGrailWar.deceased.get(deathName).npHeld++;
                    NoblePhantasm np = new NoblePhantasm();
                    np.override(deathName,HolyGrailWar.deceased.get(deathName));
                    //insurance.pause();
                }

            }

        }

    }

    private void williamShakespeareDeathEffect(String deathName) {
        for(Integer key : HolyGrailWar.summonedServants.keySet()) {
            if (HolyGrailWar.summonedServants.get(key).restrictions.contains(deathName)) {
                HolyGrailWar.summonedServants.get(key).npSealed = "Enabled";
                System.out.println("NP Seal from " + deathName + " has been removed from " + HolyGrailWar.summonedServants.get(key));
                HolyGrailWar.summonedServants.get(key).restrictions.remove(deathName);
                insurance.checkLives(HolyGrailWar.summonedServants.get(key));
            }
        }
//        for(int i=0; i<HolyGrailWar.totalServants().size();i++) {
//            for(int size=0; size<HolyGrailWar.totalServants().get(i).restrictions.size();size++) {
//                if(HolyGrailWar.totalServants().get(i).restrictions.get(size)==deathName) {
//                    HolyGrailWar.totalServants().get(i).npSealed="Enabled";
//                    System.out.println("NP Seal from "+deathName+" has been removed from "+HolyGrailWar.totalServants().get(i).getName());
//                    HolyGrailWar.totalServants().get(i).restrictions.remove(deathName);
//                    insurance.checkLives(HolyGrailWar.totalServants().get(i));
//                }
//            }
//        }
    }

}
