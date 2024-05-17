package Fate;

public class Skill {
    String firstCheck = "";
    String secondCheck = "";
    Skill(){}

    public void checkSkills(String servantCheck, HeroicSpirit heroicSpirit) {
        onSummon(servantCheck, heroicSpirit);
        onGoing(servantCheck, heroicSpirit);
    }

    public String returnCheck() {
        if(firstCheck == "None" && secondCheck == "None")
        {
            return "";
        }
        else {
            return "";
        }
    }

    public void onSummon(String servant, HeroicSpirit familiar) {
        switch(servant) {
            case "Heracles":{
                heraclesSkill(familiar);
            }break;
            default:{}
        }
    }
    public void movePlay(String servant, HeroicSpirit familiar) {
        switch(servant) {
            case "":{
            }break;
            default:{secondCheck="None";}
        }
    }
    public void onGoing(String servant, HeroicSpirit familiar) {
        switch(servant) {
            case "Achilles":{
                achillesSkill(familiar);
            }break;
            case "Arash":{
                arashSkill(familiar);
            }break;
            case "Atalante":{
                atalanteSkill(familiar);
            }break;
            case "Cu Chulainn":{
                cuChulainnSkill(familiar);
            }break;
            case "Nitocris":{
                nitocrisSkill(familiar);
            }break;
            default:{firstCheck="None";}
        }}

    private void arashSkill(HeroicSpirit heroicSpirit) {
        heroicSpirit.setSkill("If eliminated and has 2 np charges, activate NP before discarding(Ongoing)");
        if(heroicSpirit.getSkillSealed().equals("Enabled")){
            //Do nothing
        }
        if(heroicSpirit.getSkill().equals("Disabled")){
            System.out.println(heroicSpirit.getSkill()+"[DISABLED]");
        }
        if(heroicSpirit.getSkillSealed().equals("Protected")){
            System.out.println("Skill cannot be disabled.");
        }
    }
    private void atalanteSkill(HeroicSpirit heroicSpirit) {
        heroicSpirit.setSkill("Cannot Target or Elimate Children(Ongoing)");
        if(heroicSpirit.getSkillSealed().equals("Enabled")){}
        if(heroicSpirit.getSkill().equals("Disabled")){
            System.out.println(heroicSpirit.getSkill()+"[DISABLED]");
        }
        if(heroicSpirit.getSkillSealed().equals("Protected")){
            System.out.println("Skill cannot be disabled.");
        }
    }

    private void nitocrisSkill(HeroicSpirit heroicSpirit) {
        heroicSpirit.setSkill("2 NP Charges=1NP(Ongoing)");
        if(heroicSpirit.getSkillSealed().equals("Enabled")){
            heroicSpirit.setNpMaxCharges(2);
        }
        if(heroicSpirit.getSkill().equals("Disabled")){
            heroicSpirit.setNpMaxCharges(3);
            System.out.println(heroicSpirit.getSkill()+"[DISABLED]");
        }
        if(heroicSpirit.getSkillSealed().equals("Protected")){
            System.out.println("Skill cannot be disabled.");
        }
    }

    private void cuChulainnSkill(HeroicSpirit heroicSpirit) {
        heroicSpirit.setSkill("+1 Guts (Ongoing)");
        if(heroicSpirit.getSkillSealed().equals("Enabled")){
            heroicSpirit.setLives(heroicSpirit.getLives() + 1);
        }
        if(heroicSpirit.getSkill().equals("Disabled")){
            heroicSpirit.setLives(heroicSpirit.getLives() - 1);
            System.out.println(heroicSpirit.getSkill()+"[DISABLED]");
            heroicSpirit.checkHealth(heroicSpirit);
        }
        if(heroicSpirit.getSkillSealed().equals("Protected")){
            System.out.println("Skill cannot be disabled.");
        }
    }

    private void achillesSkill(HeroicSpirit heroicSpirit) {
        heroicSpirit.setSkill("+1 Max Health (Ongoing)");
        if(heroicSpirit.getSkillSealed().equals("Enabled")){
            heroicSpirit.setMaxHealth(heroicSpirit.getMaxHealth() + 1);
            heroicSpirit.setHearts(heroicSpirit.getMaxHealth());
        }
        if(heroicSpirit.getSkillSealed().equals("Disabled")){
            heroicSpirit.setMaxHealth(heroicSpirit.getMaxHealth() - 1);
            System.out.println(heroicSpirit.getSkill()+"[DISABLED]");
            heroicSpirit.checkHealth(heroicSpirit);
        }
        if(heroicSpirit.getSkillSealed().equals("Protected")){
            System.out.println("Skill cannot be disabled.");
        }
    }

    private void heraclesSkill(HeroicSpirit heroicSpirit) {
        heroicSpirit.setSkill("12 Lives (Cannot Deactivate)");
        heroicSpirit.setLives(12);
        heroicSpirit.setMaxHealth(1);
        heroicSpirit.setOriginalMaxHealth(1);
        heroicSpirit.setHearts(1);
    }
}
