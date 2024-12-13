package Clase.Entities;

import Clase.Spells.Spell;

import java.util.ArrayList;

public abstract class Character extends Entity{
    private String name;
    private int currentExperience;
    private int level;
    private int strength, dexterity, charisma;
    private int attackDamage;


    public Character(int maxHealth, ArrayList<Spell> spells, int maxMana,
                     boolean fireResist, boolean earthResist, boolean iceResist, String name, int currentExperience,
                     int level, int strength, int dexterity, int charisma) {
        super(maxHealth, spells, maxMana, fireResist, earthResist, iceResist);
        this.name = name;
        this.currentExperience = currentExperience;
        this.level = level;
        this.strength = strength;
        this.dexterity = dexterity;
        this.charisma = charisma;
        attackDamage = 5 + level;
        this.setCurrentHealth(maxHealth);
        this.setCurrentMana(maxMana);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
}
