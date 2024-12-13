package Clase.Spells;

public abstract class Spell {
    private String name;
    private String type;
    private int damage;
    private int manaCost;

    public Spell(String name, String type, int damage, int manaCost) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public abstract String toString();
}
