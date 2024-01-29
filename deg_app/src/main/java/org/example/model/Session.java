package org.example.model;
import org.example.service.DayTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * The Session class represents a cinema session in the application.
 * It encapsulates information about the movie, session time, and seat availability.
 *
 * The class provides methods for marking, buying, and refunding seats, as well as
 * changing the session time. It also includes methods to print information about
 * marked and available seats, and a toString method for easy representation of
 * the session as a string.
 */
public class Session {
    private Movie movie;
    private DayTime time;
    private List<Integer> places = new ArrayList<>(100);
    private List<Boolean> markedPlaces = new ArrayList<>(100);

    public Session(Movie movie, DayTime time) {
        this.movie = movie;
        this.time = time;

        // Инициализация списков мест и отмеченных мест
        for (int i = 0; i < 100; i++) {
            places.add(0);
            markedPlaces.add(false);
        }
    }

    public void markPlaces(String places) {
        int[] intArr = Arrays.stream(places.split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i : intArr) {
            markedPlaces.set(i, true);
        }
    }

    public void printMarkedPlaces() {
        System.out.println("  1  2  3  4  5  6  7  8  9");
        for (int i = 1; i <= 9; i++) {
            System.out.print(i);
            for (int j = 1; j <= 9; j++) {
                if (markedPlaces.get(i * 10 + j)) {
                    System.out.print(" + ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    public void buyPlace(int num, User user) {
        if (places.get(num) > 0) {
            System.out.println("Seat " + num + " busy");
            return;
        }
        places.set(num, user.hashCode());
        System.out.println("Seat " + (num) + " purchased " + user);
    }

    public void printPlaces() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (places.get(i * 10 + j) == 0) {
                    System.out.print(i * 10 + j);
                } else {
                    System.out.print("--");
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public void refund(User user) {
        if (time.isBefore(DayTime.now())) {
            System.out.println("The session has already started! No money back");
            return;
        }
        int i = indexOf(user.hashCode());
        if (i < 0) {
            System.out.println(user + " doesn't own the seat");
        } else {
            places.set(i, 0);
            System.out.println(user + " got my money back for the seat " + i);
        }
    }

    public void changeTime(DayTime newTime) {
        time = newTime;
    }

    @Override
    public String toString() {
        return movie + " " + time;
    }

    private int indexOf(int hashCode) {
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i) == hashCode) {
                return i;
            }
        }
        return -1;
    }

    public Movie getMovie() {
        return movie;
    }

    public DayTime getTime() {
        return time;
    }
}
