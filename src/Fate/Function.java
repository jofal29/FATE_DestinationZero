package Fate;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class Function {
    public void pause(int seconds){
        int i = 0;
        while(i < seconds){
            try{Thread.sleep(1000);
                i++;}
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void newLine(){
        System.out.println(" ");
    }

    //Helper Functions
    public Master getMaster(int masterID){
        return HolyGrailWar.masters.get(masterID);
    }
    public HeroicSpirit getServant (int masterID){
        return HolyGrailWar.summonedServants.get(masterID);
    }
    public ConcurrentHashMap<Integer, HeroicSpirit> getServants(){
        return HolyGrailWar.summonedServants;
    }
    public ConcurrentHashMap<Integer, Master> getMasters(){
        return HolyGrailWar.masters;
    }
}
