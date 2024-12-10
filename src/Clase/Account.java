package Clase;

import java.util.TreeSet;

public class Account {
    private Information information;


    class Information {
        private Credentials credentials;
        private String name;
        private String country;
        private TreeSet<String> likedGames;

        public Information(Credentials credentials, String name, String country) {
            this.credentials = credentials;
            this.name = name;
            this.country = country;
            likedGames = new TreeSet<String>();
        }

        public void addGames(String game) {
            likedGames.add(game);
        }
    }
}
