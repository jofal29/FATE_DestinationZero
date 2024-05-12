package Fate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class ThroneOfHeroes {
    private ArrayList<HeroicSpirit> servants;
    private Random randomGenerator;

    public ThroneOfHeroes() {
        this.servants = new ArrayList<>();
    }

    public void initializeThrone() {

        HeroicSpirit achilles = new HeroicSpirit("Achilles", "M", "Rider","Lawful", "Balanced", "Earth");
        HeroicSpirit arash = new HeroicSpirit("Arash", "M", "Archer", "Chaotic", "Balanced", "Earth");
        HeroicSpirit artoria = new HeroicSpirit("Artoria", "F", "Saber", "Lawful", "Good", "Earth");
        HeroicSpirit emiya = new HeroicSpirit("Emiya", "M", "Archer", "Neutral", "Balanced", "Human");
        HeroicSpirit heracles = new HeroicSpirit("Heracles", "M", "Berserker","Chaotic", "Madness", "Heaven");
        HeroicSpirit jason = new HeroicSpirit("Jason","M","Saber","Lawful", "Good","Earth");
        HeroicSpirit kama = new HeroicSpirit("Kama", "F", "Assassin","Chaotic", "Evil", "Beast");
        HeroicSpirit williamShakespeare = new HeroicSpirit("William Shakespeare", "M", "Caster", "Neutral", "Balanced", "Human");
        HeroicSpirit euryale = new HeroicSpirit("Euryale", "F", "Archer", "Chaotic", "Good", "Heaven");
        //HeroicSpirits angra = new heroic_Spirits("Angra Mainyu", "M", "Avenger", "Chaotic", "Evil", "Human");
        HeroicSpirit nitocris = new HeroicSpirit("Nitocris", "F", "Caster", "Lawful", "Good", "Earth");
        //heroic_Spirits cu_Chulainn = new heroic_Spirits("Cu Chulainn", "M", "Lancer", "Lawful", "Balanced", "Heaven");
        //heroic_Spirits mataHari = new heroic_Spirits("Mata Hari", "F", "Assassin", "Chaotic", "Balanced", "Human");
        //heroic_Spirits frankenstein = new heroic_Spirits("Frankenstein", "F", "Berserker", "Chaotic", "Balanced", "Earth");
        //heroic_Spirits jeanneDArc = new heroic_Spirits("Jeanne D Arc", "F", "Ruler","Lawful", "Good", "Star");
        //heroic_Spirits medusa = new heroic_Spirits("Medusa", "F", "Rider", "Chaotic", "Good", "Earth");
        //heroic_Spirits ereshkigal = new heroic_Spirits("Ereshkigal", "F", "Lancer", "Chaotic", "Evil", "Earth");
        //heroic_Spirits cursedArm = new heroic_Spirits("Hassan of the Cursed Arm", "M", "Assassin", "Lawful", "Evil", "Human");
        HeroicSpirit atalante = new HeroicSpirit("Atalante", "F", "Archer", "Neutral", "Evil", "Earth");

        this.servants.add(achilles);
        this.servants.add(arash);
        this.servants.add(artoria);
        this.servants.add(emiya);
        this.servants.add(euryale);
        this.servants.add(heracles);
        this.servants.add(jason);
        this.servants.add(kama);
        this.servants.add(williamShakespeare);
        //this.servants.add(mataHari);
        //this.servants.add(medusa);
        //this.servants.add(jeanneDArc);
        this.servants.add(nitocris);
        //this.servants.add(cu_Chulainn);
        //this.servants.add(angra);
        //this.servants.add(ereshkigal);
        //this.servants.add(cursedArm);
        //this.servants.add(frankenstein);
        this.servants.add(atalante);

        assignTraits();
        assignSkills();
        assignNoblePhantasms();

    }



    private void assignNoblePhantasms() {
        NoblePhantasm initializeNPs = new NoblePhantasm();
        for(int np=0; np<this.servants.size();np++)
        {
            initializeNPs.assignNPs(this.servants.get(np).getName(),this.servants.get(np));
        }
    }

    private void assignSkills() {
        Skill initialize = new Skill();
        for(int s=0; s<this.servants.size();s++)
        {
            initialize.checkSkills(this.servants.get(s).getName(),this.servants.get(s));
        }
    }

    //List of unsummoned servants
    public void unsummoned() {
        System.out.println("**************************UnSummoned**************************");
        for (int i=0; i<servants.size(); i++) {
            HeroicSpirit tempServant = servants.get(i);
            System.out.println("\n");
            tempServant.stats();
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    //Summoning methods
    public HeroicSpirit summon(String className) {
        randomGenerator = new Random();
        HeroicSpirit tempServant = new HeroicSpirit();

        while(tempServant.getHg_class() != className) {
            int index = randomGenerator.nextInt(servants.size());
            tempServant = servants.get(index);
        }
        return tempServant;
    }

    public HeroicSpirit summonAny() {
        randomGenerator = new Random();
        HeroicSpirit tempServant = new HeroicSpirit();
        int index = randomGenerator.nextInt(servants.size());
        tempServant = servants.get(index);
        servants.remove(index);
        return tempServant;
    }

    /********************************************************************************************************************************************************************************/
    //Attributes
    private HashSet<String> greeks;
    private HashSet<String> argonauts;
    private HashSet<String> kings;
    private HashSet<String> divine;
    private HashSet<String> kotrt;
    private HashSet<String> children;

    public HashSet<String> getGreeks() {
        return greeks;
    }
    public void setGreeks(HashSet<String> greeks) {
        this.greeks = greeks;
    }
    public HashSet<String> getArgonauts() {
        return argonauts;
    }
    public void setArgonauts(HashSet<String> argonauts) {
        this.argonauts = argonauts;
    }
    public HashSet<String> getKings() {
        return kings;
    }
    public void setKings(HashSet<String> kings) {
        this.kings = kings;
    }
    public HashSet<String> getDivine() {
        return divine;
    }
    public void setDivine(HashSet<String> divine) {
        this.divine = divine;
    }
    public HashSet<String> getKotrt() {
        return kotrt;
    }
    public void setKotrt(HashSet<String> kotrt) {
        this.kotrt = kotrt;
    }
    public HashSet<String> getChildren() {
        return children;
    }
    public void setChildren(HashSet<String> children) {
        this.children = children;
    }

    //Assigning Extra Traits
    private void assignTraits() {
        for(int t=0;t<this.servants.size();t++) {
            addToArgonauts(this.servants.get(t));
            addToGreeks(this.servants.get(t));
            addToKings(this.servants.get(t));
            addToDivine(this.servants.get(t));
            addToRoundTable(this.servants.get(t));
            addToChildren(this.servants.get(t));
        }
    }
    private void addToChildren(HeroicSpirit child) {
        //King Trait
        this.children = new HashSet<String>();
        this.children.add("Euryale");

        for(String name : children)
        {
                child.extraTraits.add("Child");
        }
    }
    private void addToDivine(HeroicSpirit divinity) {
        //King Trait
        this.divine = new HashSet<String>();
        this.divine.add("Ereshkigal");
        this.divine.add("Euryale");
        this.divine.add("Jason");
        this.divine.add("Nitocris");
        this.divine.add("Medusa");

        for(String name : divine)
        {
                divinity.extraTraits.add("Divine");
        }
    }
    private void addToRoundTable(HeroicSpirit knight) {
        //King Trait
        this.kotrt = new HashSet<String>();
        this.kotrt.add("Artoria");
        for(String name : kotrt)
        {
                knight.extraTraits.add("Round Table");
        }
    }
    private void addToKings(HeroicSpirit king) {
        //King Trait
        this.kings = new HashSet<String>();
        this.kings.add("Ereshkigal");
        this.kings.add("Artoria");
        this.kings.add("Nitocris");


        for(String name : kings)
        {
                king.extraTraits.add("King");
        }
    }
    private void addToGreeks(HeroicSpirit greek) {
        //Greek Trait
        this.greeks = new HashSet<String>();
        this.greeks.add("Jason");
        this.greeks.add("Atalante");
        this.greeks.add("Heracles");
        this.greeks.add("Medea");
        this.greeks.add("Achilles");
        this.greeks.add("Medusa");

        for(String name : greeks)
        {
                greek.extraTraits.add("Greek");
        }
    }
    private void addToArgonauts(HeroicSpirit argo) {
        //Argonauts List
        this.argonauts = new HashSet<String>();
        this.argonauts.add("Jason");
        this.argonauts.add("Atalante");
        this.argonauts.add("Heracles");
        this.argonauts.add("Medea");

        for(String name : argonauts)
        {
                argo.extraTraits.add("Argonaut");
        }
    }


}
