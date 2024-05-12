package Fate;

import java.util.Random;

public class Item {
    String itemName;
    int itemNumber;

    Item(){}

    //Creates a random item
    Item(String random){
        Random rand = new Random();
        int randomChoice = rand.nextInt(11);
        switch(randomChoice) {
            case 0: {
                this.itemName = "npCharge";
                this.itemNumber = 0;
            }
            break;
            case 1: {
                this.itemName = "heal";
                this.itemNumber = 1;
            }
            break;
            case 2: {
                this.itemName = "attack";
                this.itemNumber = 2;}
            break;
            case 3: {
                this.itemName = "curse";
                this.itemNumber = 3;}
            break;
            case 4: {
                this.itemName = "attack";
                this.itemNumber = 4;}
            break;
            case 5: {
                this.itemName = "shield";
                this.itemNumber = 5;}
            break;
            case 6: {
                this.itemName = "addNp";
                this.itemNumber = 6;}
            break;
            case 7: {
                this.itemName = "charm";
                this.itemNumber = 7;}
            break;
            case 8:{
                this.itemName = "npSeal";
                this.itemNumber = 8;}
            break;
            case 9:{
                this.itemName = "stun";
                this.itemNumber = 9;}
            break;
            case 10:{
                this.itemName = "attack";
                this.itemNumber = 10;
            }
            break;
            default: {
                System.out.println("Oops");
            }
        }
    }
}
