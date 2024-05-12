package Fate;

import java.util.ArrayList;
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
    public void includingSelf(String attribute, String keyword ) {
        mast=0;
        this.temp = new ArrayList<HeroicSpirit>();
        temp.clear();
        switch(attribute) {
            case "Class":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).getHg_class() == keyword) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);
                    }
                }
            }break;
            case"Gender":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).getGender() == keyword) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);
                    }
                }
            }break;
            case"Alignment":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).getAlignment() == keyword) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);
                    }
                }
            }break;
            case"Species":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).getSpecies() == keyword) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);
                    }
                }
            }break;
            case"Master":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).masterID == Integer.parseInt(keyword)) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);
                    }
                }
            }break;
            case"Any":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    assignedServant=(HolyGrailWar.summonedServants.get(key));
                    temp.add(assignedServant);
                }
            }break;
            default:{}
        }

        //End of Switch Case
        if(temp.size()<=0) {
            System.out.println("No more enemy servants under that attribute");
            this.status = false;
        }

        else {
            this.status = true;
        }
    }

    //Excluding Self
    public void excludingSelf(String attribute, String keyword, String servantName) {
        mast=0;
        this.temp = new ArrayList<HeroicSpirit>();
        temp.clear();
        switch(attribute) {
            case "Class":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).getHg_class() == keyword) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);
                    }
                }
            }break;
            case"Gender":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).getGender() == keyword) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);
                    }
                }
            }break;
            case"Axis":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).getAxis() == keyword) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);				}
                }
            }break;
            case"Alignment":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).getAlignment() == keyword) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);				}
                }
            }break;
            case"Species":{
                for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                    if(HolyGrailWar.summonedServants.get(key).getSpecies() == keyword) {
                        assignedServant=(HolyGrailWar.summonedServants.get(key));
                        temp.add(assignedServant);				}
                }
            }break;
            case"Master":{
                if(keyword.equals("EachMaster")) {
//                    for(int i=0; i < HolyGrailWar.totalServants().size();i++) {
//                        if(HolyGrailWar.totalServants().get(i).getName() == servantName) {
//                            mast=HolyGrailWar.totalServants().get(i).master;
//                        }}
                    for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                        if(HolyGrailWar.summonedServants.get(key).name.equals(servantName)) {
                            mast = HolyGrailWar.summonedServants.get(key).masterID;
                        }
                    }

                    for(int j=0;j<HolyGrailWar.remainingMasters.size();j++) {
                        ArrayList<HeroicSpirit> mT = new ArrayList<HeroicSpirit>();
                        for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                                if(HolyGrailWar.summonedServants.get(key).masterID == mast) {
                                }
                                else {
                                    mT.add(HolyGrailWar.summonedServants.get(key));
                                }
                        }
                        if(mT.size()<=0) {}
                        else {
                            Random ran = new Random(); int randomServant;
                            randomServant = ran.nextInt(mT.size());
                            temp.add(mT.get(randomServant));
                            mT.clear();}
                    }

                }
                else {
                    if(keyword=="AnyMaster")
                    {
                        for(Integer key : HolyGrailWar.summonedServants.keySet()) {
                            if(HolyGrailWar.summonedServants.get(key).getName() == servantName) {
                                mast=HolyGrailWar.summonedServants.get(key).masterID;
                            }}

                        Random ran = new Random();
                        int randomMaster=mast;
                        while(randomMaster==mast || randomMaster==0) {
                            randomMaster = ran.nextInt(HolyGrailWar.remainingMasters.size());
                        }

                        System.out.println("Random Master: "+randomMaster);
                        //for(int i=0; i < HolyGrailWar.totalServants().size();i++) {
                        //    if(randomMaster == HolyGrailWar.totalServants().get(i).master) {
                                assignedServant=(HolyGrailWar.summonedServants.get(randomMaster));
                                temp.add(assignedServant);
                        //    }
                        //}
                    }
                    else {
//                        for(int i=0; i < HolyGrailWar.totalServants().size();i++) {
//                            if(HolyGrailWar.totalServants().get(i).master == Integer.parseInt(keyword)) {
                                assignedServant=(HolyGrailWar.summonedServants.get(Integer.parseInt(keyword)));
                                temp.add(assignedServant);				}}
            //}}
            }break;
            case"Any":{
                for(Integer key : HolyGrailWar.summonedServants.keySet())
                {
                    assignedServant=(HolyGrailWar.summonedServants.get(key));
                    temp.add(assignedServant);
                }
            break;
            }

            case"Extra": {
                for (Integer key : HolyGrailWar.summonedServants.keySet()) {
                    {
                        //for(int h=0;h<HolyGrailWar.totalServants().get(i).extraTraits.size();h++) {
                        if (HolyGrailWar.summonedServants.get(key).extraTraits.contains(keyword)) {
                            assignedServant = (HolyGrailWar.summonedServants.get(key));
                            temp.add(assignedServant);
                        }
                        // }
                    }
                }
                break;
            }
            default:{}
        }

        removeSelf(servantName);
        removeTeammates(mast);
    }


    //Methods
    public void servantChoice() {
        if(temp.size()<=0) {
            System.out.println("No more enemy servants under that attribute");
            this.status = false;
        }

        else {
            this.status = true;
        }
        if(this.status == true) {
            Random rando = new Random();
            int randomAllignedServant = rando.nextInt(temp.size());
            assignedServant = temp.get(randomAllignedServant);
            temp.remove(randomAllignedServant);
            System.out.println("Servant Choice: " + assignedServant.getName());

        }else {}
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
