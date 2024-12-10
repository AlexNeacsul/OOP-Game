package Clase;

public class Fire extends Spell {
    public Fire(String name, int damage, int manaCost) {
        super(name, "Fire", damage, manaCost);
    }

    @Override
    public String toString() {
        return "Fire{" +
                "name='" + super.getName() + '\'' +
                ", type='" + super.getType() + '\'' +
                ", damage=" + super.getDamage() +
                ", manaCost=" + super.getManaCost() +
                '}';
    }
}
