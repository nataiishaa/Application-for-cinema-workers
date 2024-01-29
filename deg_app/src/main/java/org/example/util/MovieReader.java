package org.example.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Movie;

public class MovieReader {
    public void printMovies() {
        String jsonFilePath = "src/main/resources/movies.json";

        ObjectMapper objectMapper = new ObjectMapper();
        List<Movie> moviesList = new ArrayList<>();


        try {
            // Reading the JSON file and converting it into an array of Movie objects
            Movie[] moviesArray = objectMapper.readValue(new File(jsonFilePath), Movie[].class);

            // If you prefer to work with a list
            moviesList = Arrays.asList(moviesArray);

            // Printing the movies to check
            for (Movie movie : moviesList) {
                System.out.println(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
