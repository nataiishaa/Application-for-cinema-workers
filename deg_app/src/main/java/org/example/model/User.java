package org.example.model;

/**
 * The User class represents a user in the cinema application.
 * It stores information about the user's name and surname.
 *
 * The class provides methods to get and set the user's name and surname,
 * and includes a toString method for easy representation of the user as a string.
 */
public class User {
    private String name;
    private String surname;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return surname + " " + name;
    }
}
