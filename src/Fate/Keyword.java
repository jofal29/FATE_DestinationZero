package Fate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Keyword {
    public ArrayList<HeroicSpirit> temp;
    HeroicSpirit assignedServant = new HeroicSpirit();
    HeroicSpirit npUser = new HeroicSpirit();
    Play action = new Play();
    int mast;
    boolean status;

    public Keyword() {}

    //Including Self
//    public void includingSelf(String attribute, String keyword ) {}

    //Excluding Self
    public void excludingSelf(String attribute, String keyword, String servantName) {
        mast = 0;
        this.temp = new ArrayList<HeroicSpirit>();
        temp.clear();

        // Use a more concise way to iterate over the servants
        for (Master master : HolyGrailWar.masters.values()) {
            for (HeroicSpirit servant : master.getServantList()) {
                switch (attribute) {
                    case "Class":
                        if (servant.getHg_class().equals(keyword)) {
                            temp.add(servant);
                        }
                        break;
                    case "Gender":
                        if (servant.getGender().equals(keyword)) {
                            temp.add(servant);
                        }
                        break;
                    case "Axis":
                        if (servant.getAxis().equals(keyword)) {
                            temp.add(servant);
                        }
                        break;
                    case "Alignment":
                        if (servant.getAlignment().equals(keyword)) {
                            temp.add(servant);
                        }
                        break;
                    case "Species":
                        if (servant.getSpecies().equals(keyword)) {
                            temp.add(servant);
                        }
                        break;
                    case "Master":
                        if (keyword.equals("EachMaster")) {
                            // ...
                        } else if (keyword.equals("AnyMaster")) {
                            // ...
                        } else {
                            // ...
                        }
                        break;
                    case "Any":
                        temp.addAll(getAllServants());
                        break;
                    case "Extra":
                        if (servant.getExtraTraits().contains(keyword)) {
                            temp.add(servant);
                        }
                        break;
                    default:
                        // do nothing
                }
            }
        }
    }


    //Methods
    private List<HeroicSpirit> getAllServants() {
        List<HeroicSpirit> allServants = new ArrayList<>();
        for (Master master : HolyGrailWar.masters.values()) {
            allServants.addAll(master.getServantList());
        }
        return allServants;
    }

    public void servantChoice() {
        if(temp.size()<=0) {
            System.out.println("No more enemy servants under that attribute");
        }
        else {
            Random rando = new Random();
            int randomAllignedServant = rando.nextInt(temp.size());
            assignedServant = temp.get(randomAllignedServant);
            temp.remove(randomAllignedServant);
            System.out.println("Servant Choice: " + assignedServant.getName());
        }
    }

    public void removeServant(String rs) {
        removeSelf(rs);
        if(temp.size()<=0) {
            this.status = false;
        }

        else {
            this.status = true;
        }
    }

    public void clearList() {
        int size = temp.size() - 1;
        for (int i = size; i>=0; i--)		{
            temp.remove(i);
        }
    }

    public boolean status() {
        return this.status;
    }

    public void selfAndTeammates(int mast) {
        this.temp = new ArrayList<HeroicSpirit>();
        temp.clear();
       // for(int i=0; i < HolyGrailWar.totalServants().size();i++)
       // {
        //    if(HolyGrailWar.totalServants().get(i).master==mast) {
                assignedServant=(HolyGrailWar.summonedServants.get(mast));
                temp.add(assignedServant);
         //   }
       // }
    }

    public void removeType(String attribute) {
        for(int i=0;i<temp.size();i++) {
            for(int u=0; u<temp.get(i).extraTraits.size();u++) {
                if(temp.get(i).extraTraits.contains(attribute)) {
                    temp.remove(i);
                }
            }
        }
    }

    public void removeTeammates(int mas) {
        for (int i=0;i<temp.size();i++) {
            if(temp.get(i).masterID ==mas)
            {
                System.out.println("NP USER TEAMMMATE REMOVED: "+temp.get(i).getName());
                temp.remove(i);
            }
            else {}
        }
    }

    public void removeSelf(String off)
    {
        for(int i=0; i < temp.size(); i++) {
            if(temp.get(i).getName() == off) {
                mast = temp.get(i).masterID;
                System.out.println("NP USER REMOVED: "+temp.get(i).getName());
                temp.remove(temp.get(i));
            }
            else {}
        }
    }
}
