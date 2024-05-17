package Fate;

public class Stats {
    public static void displayMasterAndServants(){
        System.out.println("========================================================================================");
        System.out.println("Lives: | Health: | Defense: | NP Charges: | NPs | Master | Servant");
        System.out.println("========================================================================================");
        for(Master master : HolyGrailWar.masters.values()) {
            {
                for(HeroicSpirit heroicSpirit : master.getServantList())
                    if (heroicSpirit.getLives() >= 10) {
                        System.out.println("  " + heroicSpirit.getLives() + "        " + heroicSpirit.getHearts() + "        " + heroicSpirit.getDefense() + "            " + heroicSpirit.getNpCharge() + "          " + heroicSpirit.getNpHeld() + "      " + heroicSpirit.getMasterID() + "      " + heroicSpirit.getName());
                    } else {
                        System.out.println("  0" + heroicSpirit.getLives() + "        " + heroicSpirit.getHearts() + "        " + heroicSpirit.getDefense() + "            " + heroicSpirit.getNpCharge() + "          " + heroicSpirit.getNpHeld() + "      " + heroicSpirit.getMasterID() + "      " + heroicSpirit.getName());
                    }
            }
        }
    }
}
