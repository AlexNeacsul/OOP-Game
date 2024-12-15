package Clase.Accounts;

import java.util.ArrayList;
import java.util.SortedSet;
import Clase.Entities.Character;
import Clase.Entities.Mage;
import Clase.Entities.Rogue;
import Clase.Entities.Warrior;
import Clase.Spells.Spell;
import Enumerations.Colors;


public class Account {
    private Information information;
    private ArrayList<Character> characters;
    private int gamesCompleted;

    public static class Information {
        private Credentials credentials;
        private String name;
        private String country;
        private SortedSet<String> likedGames;

        public Information(Credentials credentials,SortedSet<String> likedGames ,String name, String country) {
            this.credentials = credentials;
            this.name = name;
            this.country = country;
            this.likedGames = likedGames;
        }

        public Credentials getCredentials() {
            return credentials;
        }

        public void setCredentials(Credentials credentials) {
            this.credentials = credentials;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public SortedSet<String> getLikedGames() {
            return likedGames;
        }

        public void setLikedGames(SortedSet<String> likedGames) {
            this.likedGames = likedGames;
        }
    }

    public Account( ArrayList<Character> characters, int gamesCompleted, Information information) {
        this.information = information;
        this.characters = characters;
        this.gamesCompleted = gamesCompleted;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public int getGamesCompleted() {
        return gamesCompleted;
    }

    public void setGamesCompleted(int gamesCompleted) {
        this.gamesCompleted = gamesCompleted;
    }

    public static String getColorForClass(Character character) {
        if (character instanceof Warrior) {
            return Colors.RED_BRIGHT.getColor();
        } else if (character instanceof Rogue) {
            return Colors.GREEN_BRIGHT.getColor();
        } else if (character instanceof Mage) {
            return Colors.CYAN_BRIGHT.getColor();
        }
        return Colors.RESET.getColor();
    }

    private static String formatCharacter(Character character) {
        return String.format(
                "%s - Class: " + getColorForClass(character) + "%s" + Colors.RESET.getColor()
                        + "| Level: %d | Current Experience: %d",
                character.getName(),
                character.getClass().getSimpleName(),
                character.getLevel(),
                character.getCurrentExperience()
        );
    }

    public void printCharacters() {
        System.out.println("Your Characters:");
        int characterNumber = 1;
        for (Character character : characters) {
            System.out.println(characterNumber + ". " + formatCharacter(character));
            characterNumber++;
        }
        System.out.println("\n");
    }
}
