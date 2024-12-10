package Clase;

public class Earth extends Spell {
    public Earth(String name, int damage, int manaCost) {
        super(name, "Earth", damage, manaCost);
    }

    @Override
    public String toString() {
        return "Earth{" +
                "name='" + super.getName() + '\'' +
                ", type='" + super.getType() + '\'' +
                ", damage=" + super.getDamage() +
                ", manaCost=" + super.getManaCost() +
                '}';
    }
}
