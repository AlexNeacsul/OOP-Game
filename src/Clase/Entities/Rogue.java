package Clase.Entities;

import Clase.Spells.Spell;

import java.util.ArrayList;

public class Rogue extends Character {
    private String immunity;

    public Rogue(int maxHealth, ArrayList<Spell> spells, int maxMana, boolean fireResist, boolean earthResist,
                 boolean iceResist, String name, int currentExperience, int level,
                 int strength, int dexterity, int charisma) {
        super(maxHealth, spells, maxMana, fireResist, earthResist, iceResist,
                name, currentExperience, level, 3, 5, 1);
        this.immunity = "Earth";
    }

    public String getImmunity() {
        return immunity;
    }

    public void setImmunity(String immunity) {
        this.immunity = immunity;
    }

    @Override
    public void defaultAttack(Entity target) {
        target.receiveDamage(super.getAttackDamage() + super.getStrength());
    }

    @Override
    public void receiveDamage(int damage) {
        super.setCurrentHealth(super.getCurrentHealth() - damage);
    }

    @Override
    public void healthRegen(int regenAmount) {
        if (super.getCurrentHealth() + regenAmount > super.getMaxHealth()) {
            super.setCurrentHealth(super.getMaxHealth());
        } else {
            super.setCurrentHealth(super.getCurrentHealth() + regenAmount);
        }
    }

    @Override
    public void manaRegen(int regenAmount) {
        if (super.getCurrentMana() + regenAmount > super.getMaxMana()) {
            super.setCurrentMana(super.getMaxMana());
        } else {
            super.setCurrentMana(super.getCurrentMana() + regenAmount);
        }
    }

    @Override
    public void useSpell(Spell spell, Entity target) {
        if (super.getCurrentMana() >= spell.getManaCost()) {
            super.setCurrentMana(super.getCurrentMana() - spell.getManaCost());
            target.receiveDamage(spell.getDamage());
        } else {
            System.out.println("Not enough mana to cast spell");
            defaultAttack(target);
        }
    }

    @Override
    public int getDamage() {
        return this.getAttackDamage() + this.getStrength();
    }
}
