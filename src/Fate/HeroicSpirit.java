package Fate;

import java.util.ArrayList;
import java.util.HashSet;

public class HeroicSpirit implements Cloneable{
    private String name;	        private String gender;	 private String axis;	private String alignment;
    private String species;	        private String hg_class; private String npName;	private NoblePhantasm np;
    private String noble_Phantasm;	private String skill;

    private String npStatus = "Enabled";
    private String skillSealed = "Enabled";
    private String usableSkill = "";

    HeroicSpirit next = null;

    private int npHeld = 0;			private int npRequired = 1;
    private int lives = 1; 			private int ongoingLives = 0;
    private int originalMaxHealth = 3;
    private int maxHealth = 3; 		private int ongoingMaxHealth = 0;
    private int hearts = 3; 		private int ongoingHearts = 0;
    private int npCharge = 0;		private int npMaxCharges = 3;		private int ongoingNpCharge = 0;
    private int curses = 0; 		private int ongoingCurses = 0;
    private int defense = 0; 		private int ongoingDefense = 0;
    private int turnsFrozen = 0; 	private int ongoingFrozen = 0;
    private int masterID = 0; 		private int ongoingMaster = 0;
    private int npSeals = 0; 		private int npProtection = 0;		private int ongoingNpSeals = 0;

    private boolean sacrifice = false;
    private boolean deathbyAttack = false;

    private ArrayList<String> immuneTo = new ArrayList<>();
    private HashSet<String> restrictions = new HashSet<>();
    private HashSet<String> extraTraits = new HashSet<>();

    //Constructor
    public HeroicSpirit() {
        super();
        name = "";
        gender = "";
        axis = "";
        alignment = "";
        species = "";
        hg_class = "";
        noble_Phantasm = "";
        skill = "";
    }

    public HeroicSpirit(String name, String gender, String hg_class, String axis, String alignment, String species) {
        super();
        this.name = name;
        this.gender = gender;
        this.hg_class = hg_class;
        this.axis = axis;
        this.alignment = alignment;
        this.species = species;
    }

    //Setters and Getters
    public double noblePhantasm() {
        return 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAxis() {
        return axis;
    }
    public void setAxis(String axis) {
        this.axis = axis;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAlignment() {
        return alignment;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public String getHg_class() {
        return hg_class;
    }
    public void setHg_class(String hg_class) {
        this.hg_class = hg_class;
    }

    public HashSet<String> getExtraTraits() {
        return extraTraits;
    }
    public void setExtraTraits(HashSet<String> extraTraits) {
        this.extraTraits = extraTraits;
    }

    public HashSet<String> getRestrictions() {
        return restrictions;
    }
    public void setRestrictions(HashSet<String> restrictions) {
        this.restrictions = restrictions;
    }

    public ArrayList<String> getImmuneTo() {
        return immuneTo;
    }
    public void setImmuneTo(ArrayList<String> immuneTo) {
        this.immuneTo = immuneTo;
    }

    public boolean isDeathbyAttack() {
        return deathbyAttack;
    }
    public void setDeathbyAttack(boolean deathbyAttack) {
        this.deathbyAttack = deathbyAttack;
    }

    public boolean isSacrifice() {
        return sacrifice;
    }
    public void setSacrifice(boolean sacrifice) {
        this.sacrifice = sacrifice;
    }

    public int getOngoingNpSeals() {
        return ongoingNpSeals;
    }
    public void setOngoingNpSeals(int ongoingNpSeals) {
        this.ongoingNpSeals = ongoingNpSeals;
    }

    public int getNpProtection() {
        return npProtection;
    }
    public void setNpProtection(int npProtection) {
        this.npProtection = npProtection;
    }

    public int getNpSeals() {
        return npSeals;
    }
    public void setNpSeals(int npSeals) {
        this.npSeals = npSeals;
    }

    public int getOngoingMaster() {
        return ongoingMaster;
    }
    public void setOngoingMaster(int ongoingMaster) {
        this.ongoingMaster = ongoingMaster;
    }

    public int getMasterID() {
        return masterID;
    }
    public void setMasterID(int masterID) {
        this.masterID = masterID;
    }

    public int getOngoingFrozen() {
        return ongoingFrozen;
    }
    public void setOngoingFrozen(int ongoingFrozen) {
        this.ongoingFrozen = ongoingFrozen;
    }

    public int getTurnsFrozen() {
        return turnsFrozen;
    }
    public void setTurnsFrozen(int turnsFrozen) {
        this.turnsFrozen = turnsFrozen;
    }

    public int getOngoingDefense() {
        return ongoingDefense;
    }
    public void setOngoingDefense(int ongoingDefense) {
        this.ongoingDefense = ongoingDefense;
    }

    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getOngoingCurses() {
        return ongoingCurses;
    }
    public void setOngoingCurses(int ongoingCurses) {
        this.ongoingCurses = ongoingCurses;
    }

    public int getCurses() {
        return curses;
    }
    public void setCurses(int curses) {
        this.curses = curses;
    }

    public int getOngoingNpCharge() {
        return ongoingNpCharge;
    }
    public void setOngoingNpCharge(int ongoingNpCharge) {
        this.ongoingNpCharge = ongoingNpCharge;
    }

    public int getNpMaxCharges() {
        return npMaxCharges;
    }
    public void setNpMaxCharges(int npMaxCharges) {
        this.npMaxCharges = npMaxCharges;
    }

    public int getNpCharge() {
        return npCharge;
    }
    public void setNpCharge(int npCharge) {
        this.npCharge = npCharge;
    }

    public int getOngoingHearts() {
        return ongoingHearts;
    }
    public void setOngoingHearts(int ongoingHearts) {
        this.ongoingHearts = ongoingHearts;
    }

    public int getHearts() {
        return hearts;
    }
    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public int getOngoingMaxHealth() {
        return ongoingMaxHealth;
    }
    public void setOngoingMaxHealth(int ongoingMaxHealth) {
        this.ongoingMaxHealth = ongoingMaxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getOriginalMaxHealth() {
        return originalMaxHealth;
    }
    public void setOriginalMaxHealth(int originalMaxHealth) {
        this.originalMaxHealth = originalMaxHealth;
    }

    public int getOngoingLives() {
        return ongoingLives;
    }
    public void setOngoingLives(int ongoingLives) {
        this.ongoingLives = ongoingLives;
    }

    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getNpRequired() {
        return npRequired;
    }
    public void setNpRequired(int npRequired) {
        this.npRequired = npRequired;
    }

    public int getNpHeld() {
        return npHeld;
    }
    public void setNpHeld(int npHeld) {
        this.npHeld = npHeld;
    }

    public HeroicSpirit getNext() {
        return next;
    }
    public void setNext(HeroicSpirit next) {
        this.next = next;
    }

    public String getUsableSkill() {
        return usableSkill;
    }
    public void setUsableSkill(String usableSkill) {
        this.usableSkill = usableSkill;
    }

    public String getSkillSealed() {
        return skillSealed;
    }
    public void setSkillSealed(String skillSealed) {
        this.skillSealed = skillSealed;
    }

    public String getNP_Status() {
        return npStatus;
    }
    public void setNP_Status(String npSealed) {
        this.npStatus = npSealed;
    }

    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getNoble_Phantasm() {
        return noble_Phantasm;
    }
    public void setNoble_Phantasm(String noble_Phantasm) {
        this.noble_Phantasm = noble_Phantasm;
    }

    public NoblePhantasm getNp() {
        return np;
    }
    public void setNp(NoblePhantasm np) {
        this.np = np;
    }

    public String getNpName() {
        return npName;
    }
    public void setNpName(String npName) {
        this.npName = npName;
    }



    public static HeroicSpirit clone(HeroicSpirit heroic_Spirits) {
        return heroic_Spirits;
    }

    //Methods
    public String genderName() {
        if (this.gender == "F")
        {
            return "Female";
        }
        if (this.gender == "M")
        {
            return "Male";
        }
        else
        {
            return "netruel";
        }
    }

    public void checkOngoing() {
        if (ongoingCurses != 0) {
            curses = curses + ongoingCurses;
        }

        if (ongoingDefense == 1) {
            defense++;
        }
        if (ongoingDefense == -1) {
            defense--;
        }

        if (ongoingFrozen == 1) {
            turnsFrozen++;
        }

        if (ongoingHearts == 1) {
            hearts++;
        }
        if (ongoingHearts == -1) {
            hearts--;
        }

        if (ongoingLives == 1) {
            ongoingLives++;
        }

        if (ongoingMaxHealth == 1) {
            maxHealth++;
        }
        if (ongoingMaxHealth == -1) {
            maxHealth--;
        }

        if (ongoingNpCharge > 0) {
            ongoingNpCharge++;
        }

        if (ongoingNpSeals > 0) {
            npSeals = npSeals + ongoingNpSeals;
            ongoingNpSeals = 0;
        }}

    public void subtractDurationEffect() {
            if (npSeals > 0) {
                npSeals--;
            }
            if (npSeals < 0) {
                npSeals = 0;
            }

            if (turnsFrozen > 0) {
                turnsFrozen--;
            }
            if (turnsFrozen < 0) {
                turnsFrozen = 0;
            }
    }

    public void subtractNpProtection(){
        if(npProtection>0){
            npProtection--;
        }
        if(npProtection<0){
            npProtection=0;
        }
    }


    public void takeLife() {
        lives--;
        if(lives>0)
        {
            hearts = maxHealth;
            System.out.println(name + " loses a life.");
        }
        else
        {
            hearts = 0;
        }
    }

    public void checkHealth() {
        if (hearts<=0) {
            this.lives=this.lives-1;
            if(lives>0) {
                System.out.println(this.name + " -1 life");
            }else {}
        }
        else {
            if(defense>0)
            {
                System.out.println(this.name+ ": " + defense + " Shields Active.");
            }
            else {}

            System.out.println(this.name + ": " + hearts + " Health Remaining.");
        }

        if ((maxHealth<=0 && lives>0) || (lives>0 && hearts<=0))
        {
            maxHealth = originalMaxHealth;
            hearts = maxHealth;
        }
        else {}

        if(hearts>maxHealth) {
            hearts=maxHealth;
        }
    }

    public void loseHearts() {
        hearts = hearts - 1;
    }

    public void gainHearts() {
        if (hearts<this.maxHealth)
        {
            hearts = hearts + 1;
            System.out.println(this.name + ": +1 Health");
        }
        else
        {
            System.out.println(this.name + ": Max Health");

        }
    }

    public boolean turnCheck() {
        if(this.turnsFrozen>0) {
            turnsFrozen--;
            return false;
        }
        else {
            return true;
        }
    }

    public void stats() {
        System.out.println
                ("Name: 	 	"+ this.name + "\n" +
                        "Gender: 	"+ genderName() + "\n" +
                        "Class:  	"+ this.hg_class + "\n" +
                        "Axis:		"+ this.axis + "\n" +
                        "Alignment:	"+ this.alignment + "\n" +
                        "Type:		"+ this.species + "\n" +
                        "Noble Phantasm:	"+ this.noble_Phantasm + "\n" +
                        "Skill:		"+ this.skill + "\n" +
                        "Master: 	"+ this.masterID);

    }

    public void selfSacrifice() {
        sacrifice=true;
    }

    public String checkImmune(String obj) {
        int immunity=0;

        for (String itemName : immuneTo) {
            if (obj.equals(itemName)) {
                immunity++;
            }
        }
        if(immunity>0)
        {
            return "immune";
        }
        else {
            return "not immune";
        }
    }
}
