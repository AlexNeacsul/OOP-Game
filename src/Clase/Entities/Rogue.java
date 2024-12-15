package Clase.Entities;

import Clase.Spells.Earth;
import Clase.Spells.Fire;
import Clase.Spells.Ice;
import Clase.Spells.Spell;
import Enumerations.Colors;

import java.util.ArrayList;
import java.util.Random;

public class Rogue extends Character {
    private String immunity;

    public Rogue(String name, int currentExperience, int level) {
        super(85, generateCharacterSpells(), 85, false , true, false,
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
            target.receiveDamage(spell.getDamage() + super.getCharisma());
        } else {
            System.out.println("Not enough mana to cast spell");
            defaultAttack(target);
        }
    }

    @Override
    public int getDamage() {
        return this.getAttackDamage() + this.getStrength();
    }

    @Override
    public void Concede() {
        System.out.println("The rogue is dead.");
        System.out.println(Colors.RED_BRIGHT.getColor() + "Game Over" + Colors.RESET.getColor());
        this.setCurrentHealth(0);
    }

    public static ArrayList<Spell> generateCharacterSpells() {
        ArrayList<Spell> spells = new ArrayList<>();
        spells.add(new Fire("Ember", 10, 10));
        spells.add(new Ice("Icicle Spear", 10, 10));
        spells.add(new Earth("Rock Throw", 10, 10));
        spells.add(new Fire("Flamethrower", 20, 20));
        spells.add(new Ice("Icebeam", 20, 20));
        spells.add(new Earth("Rockslide", 20, 20));
        spells.add(new Fire("Incinerate", 30, 30));
        spells.add(new Ice("Blizzard", 30, 30));
        spells.add(new Earth("Earthquake", 30, 30));
        spells.add(new Fire("Hellfire", 50, 50));
        spells.add(new Ice("Absolute Zero", 50, 50));
        spells.add(new Earth("Meteor Shower", 50, 50));

        ArrayList<Spell> selectedSpells = new ArrayList<>();
        int spellCount = randomNumber(3, 6);
        for (int i = 0; i < spellCount; i++) {
            selectedSpells.add(spells.get(new Random().nextInt(spells.size())));
        }
        return selectedSpells;
    }

    private static int randomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public void levelUp(Character character) {
        character.setLevel(character.getLevel() + 1);
        character.setCurrentExperience(character.getCurrentExperience() % 100);
        int strengthUp = randomNumber(1, 3);
        int dexterityUp = randomNumber(2, 4);
        int charismaUp = randomNumber(1, 2);
        character.setStrength(character.getStrength() + strengthUp);
        character.setDexterity(character.getDexterity() + dexterityUp);
        character.setCharisma(character.getCharisma() + charismaUp);
        System.out.println("You have leveled up! Your new stats are:\n");
        System.out.println("The new player stats are:" + Colors.RED_BRIGHT.getColor() + "\nHealth: "
                + character.getCurrentHealth() + Colors.RESET.getColor()
                + Colors.BLUE_BRIGHT.getColor() + "\nMana: "
                + character.getCurrentMana() + Colors.RESET.getColor()
                + "\nDamage: " + character.getDamage()
                + "\nStrength: " + character.getStrength()
                + "\nDexterity: " + character.getDexterity()
                + "\nCharisma: " + character.getCharisma() + "\n");
    }
}
