package Clase.Entities;

import Clase.Spells.Earth;
import Clase.Spells.Fire;
import Clase.Spells.Ice;
import Clase.Spells.Spell;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import Enumerations.Colors;

public class Enemy extends Entity{
    private final int attackDamage;

    public Enemy() {
        super(randomNumber(75, 100), generateSpells(), randomNumber(75, 100),
                    generateResist(), generateResist(), generateResist());
        this.attackDamage = randomNumber(10, 15);
        this.setCurrentHealth(super.getMaxHealth());
        this.setCurrentMana(super.getMaxMana());
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    private static int randomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private static ArrayList<Spell> generateSpells(){
        ArrayList<Spell> fireSpells = new ArrayList<>();
        ArrayList<Spell> iceSpells = new ArrayList<>();
        ArrayList<Spell> earthSpells = new ArrayList<>();
        ArrayList<Spell> selectedSpells = new ArrayList<>();

        fireSpells.add(new Fire("Ember", 10, 10));
        fireSpells.add(new Fire("Flamethrower", 20, 20));
        fireSpells.add(new Fire("Incinerate", 30, 30));
        fireSpells.add(new Fire("Hellfire", 50, 50));

        iceSpells.add(new Ice("Icicle Spear", 10, 10));
        iceSpells.add(new Ice("Icebeam", 20, 20));
        iceSpells.add(new Ice("Blizzard", 30, 30));
        iceSpells.add(new Ice("Absolute Zero", 50, 50));

        earthSpells.add(new Earth("Rock Throw", 10, 10));
        earthSpells.add(new Earth("Rockslide", 20, 20));
        earthSpells.add(new Earth("Earthquake", 30, 30));
        earthSpells.add(new Earth("Meteor Shower", 50, 50));

        Random random = new Random();
        selectedSpells.add(fireSpells.get(random.nextInt(fireSpells.size())));
        selectedSpells.add(iceSpells.get(random.nextInt(iceSpells.size())));
        selectedSpells.add(earthSpells.get(random.nextInt(earthSpells.size())));

        ArrayList<Spell> allSpells = new ArrayList<>();
        allSpells.addAll(fireSpells);
        allSpells.addAll(iceSpells);
        allSpells.addAll(earthSpells);

        allSpells.removeAll(selectedSpells);

        int additionalSpellsCount = randomNumber(3, 6) - selectedSpells.size();
        for (int i = 0; i < additionalSpellsCount; i++) {
            Spell randomSpell = allSpells.get(random.nextInt(allSpells.size()));
            selectedSpells.add(randomSpell);
            allSpells.remove(randomSpell);
        }

        return selectedSpells;
    }

    private static boolean generateResist(){
        return new Random().nextBoolean();
    }

    @Override
    public void healthRegen(int regen) {
        if (super.getCurrentHealth() + regen > super.getMaxHealth()) {
            super.setCurrentHealth(super.getMaxHealth());
        } else {
            super.setCurrentHealth(super.getCurrentHealth() + regen);
        }
    }

    @Override
    public void manaRegen(int regen) {
        if (super.getCurrentMana() + regen > super.getMaxMana()) {
            super.setCurrentMana(super.getMaxMana());
        } else {
            super.setCurrentMana(super.getCurrentMana() + regen);
        }
    }

    @Override
    public void useSpell(Spell spell, Entity target) {
        if (super.getCurrentMana() >= spell.getManaCost()) {
            super.setCurrentMana(super.getCurrentMana() - spell.getManaCost());
            target.receiveDamage(spell.getDamage());
            this.getSpells().remove(spell);
        } else {
            System.out.println("Not enough mana to cast spell");
            defaultAttack(target);
        }
    }

    @Override
    public void defaultAttack(Entity target) {
        int value = randomNumber(1, 2);
        if (value == 1) {
            target.receiveDamage(this.attackDamage);
            System.out.println("The enemy retaliated with a basic attack and dealt "
                    + Colors.RED_BRIGHT.getColor() + this.getAttackDamage() + Colors.RESET.getColor() + " damage.\n");
        } else {
            target.receiveDamage(this.attackDamage * 2);
            System.out.println("The enemy landed a critical hit and dealt "
                    + Colors.RED_BRIGHT.getColor() + this.getAttackDamage() * 2 + Colors.RESET.getColor() + " damage.\n");
        }
    }

    @Override
    public void receiveDamage(int damage) {
        super.setCurrentHealth(super.getCurrentHealth() - damage);
    }

    @Override
    public int getDamage() {
        return this.attackDamage;
    }

    public void Decide(Entity target){
        int decision = randomNumber(1, 2);
        if (decision == 1) {
            defaultAttack(target);
        } else {
            int spellIndex = randomNumber(0, this.getSpells().size() - 1);
            System.out.println("The enemy used " + this.getSpells().get(spellIndex).getName() + " and dealt "
                    + Colors.RED_BRIGHT.getColor() + this.getSpells().get(spellIndex).getDamage()
                    + Colors.RESET.getColor() + " damage.\n");
            useSpell(this.getSpells().get(spellIndex), target);
        }
    }
}
