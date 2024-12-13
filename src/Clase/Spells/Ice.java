package Clase.Spells;

public class Ice extends Spell {
    public Ice(String name, int damage, int manaCost) {
        super(name, "Ice", damage, manaCost);
    }

    @Override
    public String toString() {
        return
                "name='" + super.getName() + '\'' +
                ", type='" + super.getType() + '\'' +
                ", damage=" + super.getDamage() +
                ", manaCost=" + super.getManaCost();
    }
}
