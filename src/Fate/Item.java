package Fate;

import java.util.Random;

public class Item {
    private String itemName;
    Item(){}

    //Creates a random or an assigned item
    Item(String itemName){
        if(itemName.equals("Random")){
            Random rand = new Random();
            int randomChoice = rand.nextInt(11);
            switch(randomChoice) {
                case 0: {
                    this.itemName = "npCharge";
                }
                break;
                case 1: {
                    this.itemName = "heal";
                }
                break;
                case 2: {
                    this.itemName = "attack";
                }
                break;
                case 3: {
                    this.itemName = "curse";
                }
                break;
                case 4: {
                    this.itemName = "attack";
                    }
                break;
                case 5: {
                    this.itemName = "shield";
                    }
                break;
                case 6: {
                    this.itemName = "addNp";
                    }
                break;
                case 7: {
                    this.itemName = "charm";
                    }
                break;
                case 8:{
                    this.itemName = "npSeal";
                    }
                break;
                case 9:{
                    this.itemName = "stun";
                    }
                break;
                case 10:{
                    this.itemName = "attack";
                }
                break;
                default: {
                    System.out.println("Oops");
                }
            }
        }
        else{
            this.itemName = itemName;
        }
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
