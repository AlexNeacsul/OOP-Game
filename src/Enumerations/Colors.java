package Enumerations;

import Clase.Entities.Character;
import Clase.Entities.Mage;
import Clase.Entities.Rogue;
import Clase.Entities.Warrior;

public enum Colors {
    RESET("\u001B[0m"),
    RED_BRIGHT("\033[0;91m"),
    CYAN_BRIGHT("\033[0;96m"),
    PURPLE_BRIGHT("\033[0;95m"),
    BLUE_BRIGHT("\033[0;94m"),
    GREEN_BRIGHT("\033[0;92m");

    private final String color;

    Colors(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getColorForClass(Character character) {
        if (character instanceof Warrior) {
            return Colors.RED_BRIGHT.getColor();
        } else if (character instanceof Rogue) {
            return Colors.GREEN_BRIGHT.getColor();
        } else if (character instanceof Mage) {
            return Colors.CYAN_BRIGHT.getColor();
        }
        return Colors.RESET.getColor();
    }
}
