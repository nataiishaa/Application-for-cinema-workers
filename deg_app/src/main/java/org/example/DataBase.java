package org.example;
//import org.example.dao.MovieDAO;
//import org.example.dao.SessionDAO;
//import org.example.dao.UserDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.example.model.Movie;
import org.example.model.Session;
import org.example.model.User;

/**
 * The DataBase class serves as a simple in-memory database for managing cinema-related entities such as movies, sessions, and users.
 * It includes methods for authentication, hashing passwords, and maintaining lists of movies, sessions, and users.
 * <p>
 * The class also contains commented-out sections that suggest the possibility of integrating DAO (Data Access Object) patterns,
 * such as MovieDAO, UserDAO, and SessionDAO, for more structured data access and separation of concerns.
 */
public class DataBase {
//    private MovieDAO movieDAO;
//    private UserDAO userDAO;
//    private SessionDAO sessionDAO;
    //private final FileHandler fileHandler;

    public DataBase() {
    }

    public List<Session> sessions = new ArrayList<>();
    public List<Movie> movies = new ArrayList<>();
    public List<User> users = new ArrayList<>();
    private List<String> logins = new ArrayList<>();
    private List<String> passwords = new ArrayList<>();

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public String hash(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = s.getBytes();
            byte[] digest = md.digest(bytes);

            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void addAccess(String login, String password) {
        logins.add(login);
        passwords.add(hash(password));
    }

    public boolean allowAccess(String login, String p) {
        String h = hash(p);
        for (int i = 0; i < logins.size(); i++) {
            if (logins.get(i).equals(login) && passwords.get(i).equals(h)) {
                return true;
            }
        }
        return false;
    }
//    public void addMovie(Movie movie) {
//        movieDAO.addMovie(movie);
//    }
//
//    public List<Movie> getAllMovies() {
//        return movieDAO.getAllMovies();
//    }
//
//
//    public void addSession(Session session) {
//        sessionDAO.addSession(session);
//    }
//
//    public List<Session> getAllSessions() {
//        return sessionDAO.getAllSessions();
//    }

}
