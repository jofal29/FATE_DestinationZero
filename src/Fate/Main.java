package Fate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(" ");

        String gameStatus = "In Progress";
        Dictionary dictionary = new Dictionary();

        while(gameStatus == "In Progress") {
            HolyGrailWar hgw = new HolyGrailWar();
            Play Game_One = new Play();
            Game_One.playGame();
            hgw.update();
            hgw.winner();

            //Play Again?
            if(hgw.askToPlayAgain() == "Play Again"){
                gameStatus = "In Progress";
                System.out.println("Starting New Game...");
                System.out.println();
            }
        }

        System.out.println(" ");
        System.out.println("Shutting down code... Thank you :)");
    }}
