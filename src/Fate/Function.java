package Fate;

import java.util.ArrayList;
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
    public ConcurrentHashMap<Integer, Master> getMasters(){
        return HolyGrailWar.masters;
    }
    public ArrayList<Integer> getRemainingMasters(){
        return HolyGrailWar.remainingMasters;
    }
    public Queue<Item> getItemQueue(){
        return HolyGrailWar.itemQueue;
    }
    public HeroicSpirit getServant (Master master, int masterID){
        return HolyGrailWar.masters.get(masterID).getServantList().get(masterID);
    }
}
