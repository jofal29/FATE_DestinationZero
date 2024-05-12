package Fate;

import java.util.Scanner;

public class UserInput {
    private static final Scanner in = new Scanner(System.in);

    public static String getInput(){
        return in.nextLine();
    }

    public static int getIntInput(){
        return Integer.parseInt(getInput());
    }
}
