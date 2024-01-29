package org.example.dao;

import org.example.model.Movie;
import java.util.ArrayList;
import java.util.List;
public interface MovieDAO {
    void addMovie(Movie movie);
    List<Movie> getAllMovies();
}
