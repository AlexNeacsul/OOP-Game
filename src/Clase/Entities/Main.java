package Clase.Entities;

public class Main {
    public static void main(String[] args) {
        Enemy enemy = new Enemy();
        Warrior warrior = new Warrior(100, null, 50, false, false, false, "Warrior", 0, 1, 10, 10, 10);
        System.out.println(enemy.getCurrentHealth());
        warrior.defaultAttack(enemy);
        System.out.println(warrior.getDamage());
        System.out.println(enemy.getCurrentHealth());
        System.out.println("Viata razboinc " + warrior.getCurrentHealth());
        System.out.println("mana inamic" + enemy.getCurrentMana());
        System.out.println(enemy.getSpells());
        enemy.useSpell(enemy.getSpells().get(0), warrior);
        System.out.println(warrior.getCurrentHealth());
        System.out.println("mana inamic" + enemy.getCurrentMana());

    }
}
