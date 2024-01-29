package org.example;


import org.example.ui.ScreenManager;

public class Main {
    public static void main(String[] args) {
        DataBase db = new DataBase();
        ScreenManager sm = new ScreenManager(db);
        //MovieReader mr = new MovieReader();
        sm.regScreen();
        sm.mainScreen();
        //mr.printMovies();
    }
}
