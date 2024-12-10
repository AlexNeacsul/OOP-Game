package Clase;

import java.util.ArrayList;

public abstract class Character extends Entity{
    private String name;
    private int currentExperience;
    private int level;
    private int strength, dexterity, charisma;


    public Character(int maxHealth, ArrayList<Spell> spells, int maxMana, boolean fireResist, boolean earthResist, boolean iceResist) {
        super(maxHealth, spells, maxMana, fireResist, earthResist, iceResist);
    }
}
