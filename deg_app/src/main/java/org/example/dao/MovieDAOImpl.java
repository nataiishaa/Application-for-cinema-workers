package org.example.dao;
import org.example.model.Movie;
import java.util.ArrayList;
import java.util.List;

abstract class MovieDAOImpl implements MovieDAO {
    private List<Movie> movies;

    public MovieDAOImpl(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return List.copyOf(movies);
    }
}