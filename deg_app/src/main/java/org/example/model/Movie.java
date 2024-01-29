package org.example.model;

/**
 * The Movie class represents a film entity in the cinema application.
 * It encapsulates information about the title of the movie.
 *
 * The class provides methods to get and set the movie title, as well as
 * a toString method for easy representation of the movie instance as a string.
 */
public class Movie {
    private String title;

    public Movie() { }

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
