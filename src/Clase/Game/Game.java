package Clase.Game;

import Clase.Accounts.Account;
import Clase.Entities.Character;
import Clase.Entities.Warrior;
import Clase.Exceptii.ImpossibleMove;
import Clase.Exceptii.InvalidCommandException;
import Clase.Inputs.JsonInput;
import Clase.Map.Grid;
import Clase.Spells.Spell;
import Enumerations.CellCommands;
import Enumerations.Colors;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Game {
    ArrayList<Account> accounts;
    Grid map;
    CellCommands cellCommands;
    Colors colors;

    public String displayOptions() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        boolean validCommand = false;

        if (map.getCurrentcell().isVisited()) {
            cellCommands = CellCommands.FIRST_MOVE;
            while (!validCommand) {
                System.out.println("Options available:\n" + cellCommands.getOptions());
                command = scanner.nextLine();
                if (cellCommands.isValidCommand(command)) {
                    validCommand = true;
                } else {
                    System.out.println("Invalid command. Please try again.");
                }
            }
            cellCommands = CellCommands.MOVE;
            while (true) {
                System.out.println("Options available:\n" + cellCommands.getOptions());
                command = scanner.nextLine();
                if (cellCommands.isValidCommand(command)) {
                    break;
                } else {
                    System.out.println("Invalid command. Please try again.");
                }
            }
        } else {
            switch (map.getCurrentcell().getOriginalType()) {
                case ENEMY:
                    if (map.getCurrentcell().isVisited()) {
                        cellCommands = CellCommands.ENEMY_DEFEATED;
                        while (!validCommand) {
                            System.out.println("Options available:\n" + cellCommands.getOptions());
                            command = scanner.nextLine();
                            if (cellCommands.isValidCommand(command)) {
                                validCommand = true;
                            } else {
                                System.out.println("Invalid command. Please try again.");
                            }
                        }
                        cellCommands = CellCommands.MOVE;
                        while (true) {
                            System.out.println("Options available:\n" + cellCommands.getOptions());
                            command = scanner.nextLine();
                            if (cellCommands.isValidCommand(command)) {
                                break;
                            } else {
                                System.out.println("Invalid command. Please try again.");
                            }
                        }
                    } else {
                        if (map.getCurrentcell().getEnemy().getCurrentHealth() > 0) {
                            cellCommands = CellCommands.ENEMY;
                            System.out.println("The enemy stats are:" + Colors.RED_BRIGHT.getColor() + "\nHealth: "
                                    + map.getCurrentcell().getEnemy().getCurrentHealth() + Colors.RESET.getColor()
                                    + Colors.BLUE_BRIGHT.getColor() + "\nMana: "
                                    + map.getCurrentcell().getEnemy().getCurrentMana() + Colors.RESET.getColor()
                                    + "\nDamage: " + map.getCurrentcell().getEnemy().getDamage() + "\n");
                            while (!validCommand) {
                                System.out.println("Options available:\n" + cellCommands.getOptions());
                                command = scanner.nextLine();
                                if (cellCommands.isValidCommand(command)) {
                                    validCommand = true;
                                } else {
                                    System.out.println("Invalid command. Please try again.");
                                }
                            }
                            if (Objects.equals(command, "Attack")) {
                                cellCommands = CellCommands.ATTACK;
                                System.out.println("The player stats are:" + Colors.RED_BRIGHT.getColor() + "\nHealth: "
                                        + map.getCharacter().getCurrentHealth() + Colors.RESET.getColor()
                                        + Colors.BLUE_BRIGHT.getColor() + "\nMana: "
                                        + map.getCharacter().getCurrentMana() + Colors.RESET.getColor()
                                        + "\nDamage: " + map.getCharacter().getDamage() + "\n");
                                System.out.println("The enemy stats are:" + Colors.RED_BRIGHT.getColor() + "\nHealth: "
                                        + map.getCurrentcell().getEnemy().getCurrentHealth() + Colors.RESET.getColor()
                                        + Colors.BLUE_BRIGHT.getColor() + "\nMana: "
                                        + map.getCurrentcell().getEnemy().getCurrentMana() + Colors.RESET.getColor()
                                        + "\nDamage: " + map.getCurrentcell().getEnemy().getDamage() + "\n");
                                while (true) {
                                    System.out.println("Options available:\n" + cellCommands.getOptions());
                                    command = scanner.nextLine();
                                    if (cellCommands.isValidCommand(command)) {
                                        break;
                                    } else {
                                        System.out.println("Invalid command. Please try again.");
                                    }
                                }
                            }
                        } else {
                            cellCommands = CellCommands.ENEMY_DEFEATED;
                            while (!validCommand) {
                                System.out.println("Options available:\n" + cellCommands.getOptions());
                                command = scanner.nextLine();
                                if (cellCommands.isValidCommand(command)) {
                                    validCommand = true;
                                } else {
                                    System.out.println("Invalid command. Please try again.");
                                }
                            }
                            cellCommands = CellCommands.MOVE;
                            while (true) {
                                System.out.println("Options available:\n" + cellCommands.getOptions());
                                command = scanner.nextLine();
                                if (cellCommands.isValidCommand(command)) {
                                    break;
                                } else {
                                    System.out.println("Invalid command. Please try again.");
                                }
                            }
                        }
                    }
                    break;
                case SANCTUARY:
                    if (map.getCurrentcell().isVisited()) {
                        cellCommands = CellCommands.SANCTUARY_VISITED;
                        while (!validCommand) {
                            System.out.println("Options available:\n" + cellCommands.getOptions());
                            command = scanner.nextLine();
                            if (cellCommands.isValidCommand(command)) {
                                validCommand = true;
                            } else {
                                System.out.println("Invalid command. Please try again.");
                            }
                        }
                        cellCommands = CellCommands.MOVE;
                        while (true) {
                            System.out.println("Options available:\n" + cellCommands.getOptions());
                            command = scanner.nextLine();
                            if (cellCommands.isValidCommand(command)) {
                                break;
                            } else {
                                System.out.println("Invalid command. Please try again.");
                            }
                        }
                    } else {
                        cellCommands = CellCommands.SANCTUARY;
                        while (!validCommand) {
                            System.out.println("Options available:\n" + cellCommands.getOptions());
                            command = scanner.nextLine();
                            if (cellCommands.isValidCommand(command)) {
                                validCommand = true;
                            } else {
                                System.out.println("Invalid command. Please try again.");
                            }
                        }
                    }
                    break;
                case VOID:
                    cellCommands = CellCommands.VOID;
                    while (!validCommand) {
                        System.out.println("Options available:\n" + cellCommands.getOptions());
                        command = scanner.nextLine();
                        if (cellCommands.isValidCommand(command)) {
                            validCommand = true;
                        } else {
                            System.out.println("Invalid command. Please try again.");
                        }
                    }
                    cellCommands = CellCommands.MOVE;
                    while (true) {
                        System.out.println("Options available:\n" + cellCommands.getOptions());
                        command = scanner.nextLine();
                        if (cellCommands.isValidCommand(command)) {
                            break;
                        } else {
                            System.out.println("Invalid command. Please try again.");
                        }
                    }
                    break;
                case PORTAL:
                    cellCommands = CellCommands.PORTAL;
                    while (!validCommand) {
                        System.out.println("Options available:\n" + cellCommands.getOptions());
                        System.out.println("\n");
                        command = scanner.nextLine();
                        if (cellCommands.isValidCommand(command)) {
                            validCommand = true;
                        } else {
                            System.out.println("Invalid command. Please try again.\n");
                        }
                    }
                    break;
            }
        }
        return command;
    }

    public void commandHandler(String command, Character character, Account account)
            throws ImpossibleMove, InvalidCommandException {
        Random random = new Random();
        switch (command) {
            case "North":
                map.goNorth();
                break;
            case "South":
                map.goSouth();
                break;
            case "East":
                map.goEast();
                break;
            case "West":
                map.goWest();
                break;
            case "Strike":
                int evade = random.nextInt(2 - 1 + 1) + 1;
                if (evade == 1) {
                    System.out.println("The mighty " + character.getName() + " attacks the enemy and dealt " +
                            Colors.RED_BRIGHT.getColor() + character.getDamage() + Colors.RESET.getColor()
                            + " damage.\n");
                    character.defaultAttack(map.getCurrentcell().getEnemy());
                } else {
                    System.out.println("The enemy evaded the attack.\n");
                }
                if (map.getCurrentcell().getEnemy().getCurrentHealth() > 0) {
                    map.getCurrentcell().getEnemy().Decide(character);
                } else {
                    System.out.println(Colors.GREEN_BRIGHT.getColor()
                            + "Congratulations! The enemy has been defeated." + Colors.RESET.getColor());
                    int experience = random.nextInt(15 - 10 + 1) + 10;
                    character.setCurrentExperience(character.getCurrentExperience() + experience);
                    character.healthRegen(character.getCurrentHealth());
                    character.manaRegen(character.getCurrentMana());
                    System.out.println("You gained " + experience + " experience points.\n");
                    if(character.getCurrentExperience() >= 100) {
                        character.levelUp(character);
                    }
                    map.displayMap();
                }
                break;
            case "Bless":
                int value = random.nextInt(10 - 1 + 1) + 1;
                character.setMaxHealth(character.getMaxHealth() + value);
                character.setCurrentHealth(character.getCurrentHealth() + value);
                character.setMaxMana(character.getMaxMana() + value);
                character.setCurrentMana(character.getCurrentMana() + value);
                System.out.println("The player has been blessed and gained " + value + " health and mana.\n");
                System.out.println("The new player stats are:" + Colors.RED_BRIGHT.getColor() + "\nHealth: "
                        + map.getCharacter().getCurrentHealth() + Colors.RESET.getColor()
                        + Colors.BLUE_BRIGHT.getColor() + "\nMana: "
                        + map.getCharacter().getCurrentMana() + Colors.RESET.getColor()
                        + "\nDamage: " + map.getCharacter().getDamage() + "\n");
                map.getCurrentcell().setVisited(true);
                map.displayMap();
                break;
            case "Concede":
                character.Concede();
                break;
            case "Ascend":
                account.setGamesCompleted(account.getGamesCompleted() + 1);
                character.setCurrentExperience(character.getCurrentExperience() + 5 * account.getGamesCompleted());
                System.out.println("You gained " + account.getGamesCompleted() * 5 + " experience points.\n");
                if(character.getCurrentExperience() >= 100) {
                    character.levelUp(character);
                }
                map = Grid.generateMap(10, 10, character);
                map.displayMap();
                break;
            case "Spell":
                character.printSpells();
                while (true) {
                    System.out.println("Please select a spell to cast(by name).");
                    Scanner scanner = new Scanner(System.in);
                    String spellName = scanner.nextLine().trim();
                    Spell selectedSpell = null;
                    for (Spell spell : character.getSpells()) {
                        if (spell.getName().equals(spellName)) {
                            selectedSpell = spell;
                            break;
                        }
                    }
                    if (selectedSpell == null) {
                        System.out.println("Invalid spell name. Please try again.");
                    } else {
                        System.out.println("The player casts " + selectedSpell.getName() + " and dealt "
                                + Colors.RED_BRIGHT.getColor() + (selectedSpell.getDamage() + character.getCharisma()) + Colors.RESET.getColor()
                                + " damage.\n");
                        character.useSpell(selectedSpell, map.getCurrentcell().getEnemy());

                        character.getSpells().remove(selectedSpell);

                        if (map.getCurrentcell().getEnemy().getCurrentHealth() > 0) {
                            map.getCurrentcell().getEnemy().Decide(character);
                        } else {
                            System.out.println(Colors.GREEN_BRIGHT.getColor()
                                    + "Congratulations! The enemy has been defeated." + Colors.RESET.getColor());
                            int experience = random.nextInt(15 - 10 + 1) + 10;
                            character.healthRegen(character.getCurrentHealth());
                            character.manaRegen(character.getCurrentMana());
                            System.out.println("You gained " + experience + " experience points.\n");
                            if(character.getCurrentExperience() >= 100) {
                                character.levelUp(character);
                            }
                            map.displayMap();
                        }
                        break;
                    }
                }
                break;
        }
    }
    public void run(int width, int length) throws Exception {
        accounts = JsonInput.deserializeAccounts();
        boolean loggedIn = false;
        Account currentAccount = null;
        Character character = null;
        System.out.println("Welcome to the game!");
        while (true) {
            while (!loggedIn) {
                System.out.println("Please enter your account credentials:");
                System.out.println("\nUserName: ");
                Scanner scanner = new Scanner(System.in);
                String userName = scanner.nextLine();
                System.out.println("\nPassword: ");
                String password = scanner.nextLine();
                for (Account account : accounts) {
                    if (account.getInformation().getCredentials().getEmail().equals(userName) &&
                            account.getInformation().getCredentials().getPassword().equals(password)) {
                        currentAccount = account;
                        loggedIn = true;
                        break;
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                }
            }
            System.out.println(Colors.GREEN_BRIGHT.getColor() + "You logged in." + Colors.RESET.getColor());
            currentAccount.printCharacters();
            while (true) {
                System.out.println("Please select a character(by name).");
                Scanner scanner = new Scanner(System.in);
                String characterName = scanner.nextLine().trim();
                for (Character character1 : currentAccount.getCharacters()) {
                    if (character1.getName().equals(characterName)) {
                        character = character1;
                        break;
                    }
                }
                if (character == null) {
                    System.out.println("No character with this name. Please try again.");
                } else {
                    System.out.println("You selected " + character.getName() +
                            " the " + Colors.RESET.getColorForClass(character) + character.getClass().getSimpleName()
                            + Colors.RESET.getColor() + ".\nGood luck!\n");
                    break;
                }
            }
            map = Grid.generateMap(width, length, character);
            map.setCharacter(character);
            map.displayMap();
            while (true) {
                try {
                    String command = displayOptions();
                    commandHandler(command, character, currentAccount);
                } catch (ImpossibleMove e) {
                    System.out.println(e.getMessage());
                    System.out.println("Try another direction.\n");
                } catch (InvalidCommandException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Available options are shown above. Try again.\n");
                } catch (Exception e) {
                    System.out.println("An error occurred. Please try again.");
                }
                if (character.getCurrentHealth() <= 0) {
                    loggedIn = false;
                    break;
                }
            }
        }
    }
}
