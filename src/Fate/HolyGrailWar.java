package Fate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class HolyGrailWar {
    int numOfMasters;
    int numOfServants;
    public static final Queue<Item> itemQueue = new Queue<>();

    //public static ConcurrentHashMap<Integer, LinkedList<HeroicSpirit>> summonedServants = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Integer, Master> masters = new ConcurrentHashMap<>();
    public static final Hashtable<String, HeroicSpirit> deceased = new Hashtable<>();
    public static ArrayList<Integer> remainingMasters = new ArrayList<>();

    public static final HashSet<String> servantsWithDeathEffects = new HashSet<>();

    Play play = new Play();
    Function function = new Function();
    Action action = new Action();

    //Calls the methods to set up the game
    public HolyGrailWar() {
        displayRules();
        setUp();
        masterSetUp(numOfMasters,numOfServants);
        theSummoning();
        initializeEffects();
        itemDeck();
        initialHand();
    }

    private void displayRules() {
        System.out.println("Rules:");
        System.out.println("- Players are known as Masters. Servants are magically created characters (Heroic Spirits) that are based off Legends and History.");
        System.out.println("- Each master starts with a servant. Last master with a servant in the game wins.");
        System.out.println("- At the start of your turn, you MUST draw a card (an item) before playing any cards.");
        System.out.println("- Master MUST play at least one card. Master can play up to THREE cards");
        System.out.println("- Masters start with one servant but may increase their team of servants.");
        System.out.println("- A Master is instantly ELIMINATED once they have no servant.");
        System.out.println("- Noble Phantasm (NP) is the heroic spirit's special power or ability. It is based off their legends");
        System.out.println("- 3 Np Charges = 1 NP. *Varies between heroic spirits.");
        System.out.println("- Max Shields Matches Max Hearts");
        System.out.println("- Explanation of some items:");
        System.out.println("    Curse: Removes 1 Max Health from a servant.");
        System.out.println("    Shield: Can only be applied to own servants.");
        System.out.println("    Heal: Can only health to max health.");
        System.out.println("- Stun vs Charm:");
        System.out.println("    Stun affects the master to be skipped.");
        System.out.println("    Charm affects a servant to be unusable.");
        System.out.println("- If a servant uses a noble phantasm without having a np loaded, they will sacrifice their lives");
        System.out.println();
    }

    //Methods To Setting Up The Game
    private void setUp() {
        //User input number of masters
        boolean wrongInput = true;
        while (wrongInput) {
            System.out.println("Enter number of masters participating. Limit: 1-7 (Enter 0 to exit):");
            String input = UserInput.getInput();

            //0 = exit the program
            if (input.equals("0")) {
                System.out.println("Aw alright :( Exiting Fate/Stay Night: Destination Zero...");
                function.pause(2);
                System.exit(0);
            }

            //Edge Case for User Input + Number of Masters Assigned
            //Learned Try Catch from https://www.w3schools.com/java/java_try_catch.asp
            try {
                int numOfMasters = Integer.parseInt(input);
                if (numOfMasters < 0 || numOfMasters > 7) {
                    System.out.println("Invalid input. Please enter a number between 1 and 7, or 0 to exit.");
                    function.newLine();
                } else {
                    this.numOfMasters = numOfMasters;
                    this.numOfServants = 1;
                    wrongInput = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("That's not even a number -_- ... Please enter a number between 0 and 7.");
                function.newLine();
            }
        }

        //Additional Print Statements
        {
            function.newLine();
            System.out.println(numOfMasters + " Masters Participating. Start Summoning Process...");
            function.pause(2);
            function.newLine();
        }
    }

    //Create Master Objects + Add to Hash Table
    private void masterSetUp(int totalMasters, int servantsPer) {
        int numOfMaster = 1;
        for(int i=0; masters.size()<totalMasters; i++) {
            Master m = new Master(numOfMaster,servantsPer);
            m.setMasterID_Number(numOfMaster);
            masters.put(numOfMaster,m);
            remainingMasters.add(numOfMaster);
            numOfMaster++;
        }
    }

    //Effects That Happen If These Servants Are Eliminated
    private void initializeEffects() {
        servantsWithDeathEffects.add("Arash");
        servantsWithDeathEffects.add("William Shakespeare");
    }

    //Creates Heroic Spirit Objects and Assigns Them Accordingly
    private void theSummoning() {

        //Creates Possible Characters (Noble Phantasm, Skills, Attributes, + More) The Heroic Spirits Can Be
        ThroneOfHeroes throneOfHeroes = new ThroneOfHeroes();
        throneOfHeroes.initializeThrone();

        // Assigns Heroic Spirits To The Masters + Printout Masters & Servants
        int masterNumber = 1;
        for (int i = 0; i < numOfMasters; i++) {
            System.out.println("Master " + masterNumber + ", Servant(s): ");
            for (int j = 0; j < numOfServants; j++) {
                HeroicSpirit servant = throneOfHeroes.summonAny();
                servant.setMasterID(masterNumber);
                function.getMaster(masterNumber).getServantList().add(servant);
                System.out.print(servant.getName() + " | ");
            }
            masterNumber++;
        }
    }

    //Creates a Queued List of Items objects
    private void itemDeck() {
        for (int i = 0; i < 10; i++) {
            itemQueue.enqueue(new Item("Random"));
        }
        //Print Statements
        {
            function.newLine();
            System.out.println("Item Deck Created. Get Ready to Play...");
            function.pause(4);
            function.newLine();
        }
    }

    //Creates a Master's Hand w/ Items
    private void initialHand() {
        int hand = 0;
        for(Integer masterNumber : masters.keySet()) {
            while (hand < 3) {
                function.getMaster(masterNumber).getMasterItems().add(itemFromDrawPile());
                hand++;
            }
        }
    }

    //Updates the status of the servants
    public void update() {

        //Print Surviving Servants
        // iterate through the master objects, O(1) because only one should be left.
        for (Master master : masters.values()) {
            System.out.println("-----------------------------------------");
            System.out.println("Master ID#" + master.getMasterID_Number() + " has " + master.getServantList().size() + " remaining servants.");

            // iterate through the linked list of heroic spirits
            for (HeroicSpirit servant : master.getServantList()) {
                System.out.println(servant.getName() + " has " + servant.getHearts() + " left.");
            }
        }

        //Print Deceased Servants
        for (String key : deceased.keySet()) {
            System.out.println("---------------Defeated------------------");
            System.out.println(deceased.get(key).getName() + " has 0 left.");
        }
    }

    //Provides stats of the winner
    public void winner() {
        for (Master master : masters.values()) {
            System.out.println("-----------------------------------------");
            System.out.println("Master ID#" + master.getMasterID_Number() + " | and ");

            // iterate through the linked list of heroic spirits
            for (HeroicSpirit servant : master.getServantList()) {
                System.out.println(servant.getName() + " | ");
            }
            System.out.print(" are the Winner!");
        }
    }

    public String askToPlayAgain(){
        System.out.println();
        System.out.println("Would you like to play again?");
        System.out.print("Enter 0 to quit or 1 to play again: ");

        //Edge Case
        String input = UserInput.getInput();
        while(!input.equals("0") && !input.equals("1")) {
            System.out.println("Invalid input, please enter 0 to Quit or 1 to Play Again:");
            input = UserInput.getInput();
        }
        if(input.equals("0")) {
            return "Quit";
        } else {
            return "Play Again";
        }
    }

    public static Item itemFromDrawPile(){
        while(itemQueue.size()<10) {
            itemQueue.enqueue(new Item("Random"));
        }
        return itemQueue.dequeue();
    }
}
