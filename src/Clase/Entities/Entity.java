package Clase.Entities;

import Clase.Spells.Spell;
import Interfete.Battle;

import java.util.ArrayList;

public abstract class Entity implements Battle {
    private ArrayList<Spell> spells;
    private int maxHealth, currentHealth;
    private int maxMana, currentMana;
    private boolean fireResist, earthResist, iceResist;

    public Entity(int maxHealth, ArrayList<Spell> spells, int maxMana, boolean fireResist,
                    boolean earthResist, boolean iceResist) {
        this.maxHealth = maxHealth;
        this.spells = spells;
        this.maxMana = maxMana;
        this.fireResist = fireResist;
        this.earthResist = earthResist;
        this.iceResist = iceResist;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public boolean isFireResist() {
        return fireResist;
    }

    public void setFireResist(boolean fireResist) {
        this.fireResist = fireResist;
    }

    public boolean isEarthResist() {
        return earthResist;
    }

    public void setEarthResist(boolean earthResist) {
        this.earthResist = earthResist;
    }

    public boolean isIceResist() {
        return iceResist;
    }

    public void setIceResist(boolean iceResist) {
        this.iceResist = iceResist;
    }

    public abstract void healthRegen(int regenAmount);
    public abstract void manaRegen(int regenAmount);
    public abstract void useSpell(Spell spell, Entity target);
    public abstract void defaultAttack(Entity target);
}
