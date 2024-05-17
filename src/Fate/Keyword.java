
package Fate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Keyword {
    public ArrayList<HeroicSpirit> temp;
    int mast;
    boolean status;

    Random rand = new Random();

    public Keyword() {}

    //Including Self
//    public void includingSelf(String attribute, String keyword ) {}

    //Excluding Self
    public void targetServants(String attribute, String keyword) {
        mast = 0;
        this.temp = new ArrayList<HeroicSpirit>();
        temp.clear();

        //A more concise way to iterate over the servants
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
                            mast = servant.getMasterID();
                            for(Master masterID : HolyGrailWar.masters.values()){
                                if(mast != masterID.getMasterID_Number()){
                                    temp.add(masterID.randomOwnServant());
                                }
                            }
                        }
                        if (keyword.equals("AnyMaster")) {
                            mast = servant.getMasterID();
                            while(mast == servant.getMasterID() || !HolyGrailWar.masters.containsKey(mast)){
                                mast = rand.nextInt(HolyGrailWar.masters.size());
                            }
                            System.out.println("Random Master: "+mast);
                            temp.addAll(HolyGrailWar.masters.get(mast).getServantList());
                        }
                        else {
                            temp.addAll(HolyGrailWar.masters.get(Integer.parseInt(keyword)).getServantList());
                        }
                        break;
                    case "Any":
                        temp.addAll(getAllServants());
                        break;
                    case "Extra":
                        for(Master masterID : HolyGrailWar.masters.values()){
                            for(HeroicSpirit heroicSpirit : masterID.getServantList()){
                                if(heroicSpirit.getExtraTraits().contains(keyword)){
                                    temp.add(heroicSpirit);
                                }
                            }
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

    public HeroicSpirit servantChoice() {
        if(temp.size()<=0) {
            System.out.println("No more enemy servants under that attribute");
            return null;
        }
        else {
            int randomAllignedServant = rand.nextInt(temp.size());
            HeroicSpirit tempServant = temp.get(randomAllignedServant);
            temp.remove(randomAllignedServant);
            System.out.println("Servant Choice: " + tempServant.getName());
            return tempServant;
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

    public void removeType(String attribute) {
        for(HeroicSpirit heroicSpirit : temp) {
            if(heroicSpirit.getExtraTraits().contains(attribute)){
                temp.remove(heroicSpirit);
            }
        }
    }

    //Removes a specific servant's name and servants from same side.
    //Enter a random string if it's just teammmates or -1 if not a specific master
    //Learned about iterator when researching how to resolve/avoid a concurrent modification.
    //Source:https://rollbar.com/blog/java-concurrentmodificationexception/#:~:text=To%20avoid%20the%20ConcurrentModificationException%20in,degrade%20performance%20for%20larger%20ones.
    public void removeSelfTeammatesOrSpecificName(String servantName, int masterID) {
        Iterator<HeroicSpirit> iterator = temp.iterator();
        while (iterator.hasNext()) {
            HeroicSpirit heroicSpirit = iterator.next();
            if (heroicSpirit.getMasterID() == masterID || heroicSpirit.getName().equals(servantName)) {
                iterator.remove();
            }
        }
    }


    public void targetSelfAndTeammates(int masterID){
        temp.clear();
        temp.addAll(HolyGrailWar.masters.get(masterID).getServantList());
    }
}

