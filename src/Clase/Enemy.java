package Clase;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entity{
    private int attackDamage;

    public Enemy() {
        super(randomNumber(100, 75), generateSpells(), randomNumber(100, 75),
                    generateResist(), generateResist(), generateResist());
        this.attackDamage = randomNumber(10, 15);
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    private static int randomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private static ArrayList<Spell> generateSpells(){
        ArrayList<Spell> spells = new ArrayList<>();
        spells.add(new Fire("Fireball", 10, 10));
        spells.add(new Ice("Iceball", 10, 10));
        spells.add(new Earth("Earthball", 10, 10));
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
        }
    }

    @Override
    public void defaultAttack(Entity target) {
        target.receiveDamage(this.attackDamage);
    }

    @Override
    public void receiveDamage(int damage) {
        super.setCurrentHealth(super.getCurrentHealth() - damage);
    }

    @Override
    public int getDamage() {
        return this.attackDamage;
    }
}
