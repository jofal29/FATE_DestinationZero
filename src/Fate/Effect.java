package Fate;

public class Effect {
    Action remove = new Action();
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
        if(HolyGrailWar.deceasedServants.get(deathName).getNP_Status().equals("Enabled")){
            if(HolyGrailWar.deceasedServants.get(deathName).getNpCharge()==2) {
                if(HolyGrailWar.deceasedServants.get(deathName).isSacrifice())
                {}else {
                    System.out.println("But with his last breath & 2np charges, he activates his");
                    HolyGrailWar.deceasedServants.get(deathName).setNpCharge(0);
                    HolyGrailWar.deceasedServants.get(deathName).addNp();
                    NoblePhantasm np = new NoblePhantasm();
                    np.override(deathName,HolyGrailWar.deceasedServants.get(deathName));
                }

            }

        }

    }
    private void williamShakespeareDeathEffect(String deathName) {
        for(Master master : HolyGrailWar.masters.values())
        {
            for(HeroicSpirit heroicSpirit : master.getServantList()){
                if(heroicSpirit.getRestrictions().contains(deathName)){
                    heroicSpirit.setNP_Status("Enabled");
                    System.out.println("NP Seal from " + deathName + " has been removed from " + heroicSpirit.getName());
                    heroicSpirit.getRestrictions().remove(deathName);
                    heroicSpirit.checkHealth(heroicSpirit);
                }
            }
        }
    }

}
