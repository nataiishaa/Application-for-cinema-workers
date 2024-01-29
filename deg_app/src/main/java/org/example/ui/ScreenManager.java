package org.example.ui;

import org.example.DataBase;
import org.example.service.DayTime;
import org.example.util.LocalStorageManagerImpl;
import org.example.model.Movie;
import org.example.model.Session;
import org.example.model.User;

import java.util.List;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.DateTimeException;

import com.fasterxml.jackson.core.type.TypeReference;

public class ScreenManager {
    private final DataBase db;
    private final Scanner scanner;
    private static final String ANSI_RESET = "\u001B[0m";
    //private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private final LocalStorageManagerImpl<List<Movie>> movieManager;
    private final LocalStorageManagerImpl<List<Session>> sessionManager;


    public ScreenManager(DataBase db) {
        this.db = db;
        this.scanner = new Scanner(System.in);
        this.movieManager = new LocalStorageManagerImpl<>("movies.json", new TypeReference<List<Movie>>() {
        });
        this.sessionManager = new LocalStorageManagerImpl<>("sessions.json", new TypeReference<List<Session>>() {
        });
        loadMovies();
        loadSessions();
    }

    private void loadSessions() {
        try {
            db.sessions = sessionManager.readSessionsFromFile("session.json");
        } catch (IOException e) {
            System.out.println("Could not load sessions from file.");
        }
    }


    private void saveSessions() {
        try {
            sessionManager.writeSessionsToFile(db.sessions, "session.json");
        } catch (IOException e) {
            System.out.println("Could not save sessions to file.");
        }
    }

    // Метод для загрузки фильмов из файла
    private void loadMovies() {
        try {
            db.movies = movieManager.readMoviesFromFile("movies.json");
        } catch (IOException e) {
            System.out.println("Не удалось загрузить фильмы из файла.");
        }
    }

    // Метод для сохранения фильмов в файл
    private void saveMovies() {
        try {
            movieManager.writeMoviesToFile(db.movies, "movies.json");
        } catch (IOException e) {
            System.out.println("Не удалось сохранить фильмы в файл.");
        }
    }

    public void regScreen() {

        System.out.println(ANSI_BLUE + "Welcome to the app for cinema workers! Please login or register.\n Good working day!\n" + ANSI_RESET);
        while (true) {
            try {
                System.out.println("Create an account if you don't already have one, or sign in to an existing one to gain access to all available features and functionality.");
                System.out.println("\n\n1.Authorization.\n2.Registration.\n3.Exit the application.\n>>");
                int input = scanner.nextInt();

                switch (input) {
                    case 1:
                        System.out.print("Login: ");
                        String log = scanner.next();
                        System.out.print("Password:");
                        String pas = scanner.next();
                        if (db.allowAccess(log, pas)) {
                            return;
                        }
                        System.out.println("Invalid login or password :( Check the information you entered.");
                        break;

                    case 2:
                        System.out.print("Login (no spaces): ");
                        String regLog = scanner.next();
                        System.out.print("Password (no spaces): ");
                        String regPas = scanner.next();
                        db.addAccess(regLog, regPas);
                        System.out.println("Registration is completed! Have a good day! ʕ ᵔᴥᵔ ʔ");
                        break;

                    case 3:
                        System.out.println("Exit! Thank you for using our application.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please choose a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter correct number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
    }

    // Меню управления.
    public void mainScreen() {
        while (true) {
            System.out.println("\n\nControl:\n1. Add/remove a movie or edit an existing one\n" +
                    "2. Schedule moderation; ticket sale/return/check\n3. Exit\n>>");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                switch (input) {
                    case 1:
                        moviesScreen();
                        break;
                    case 2:
                        sessionsScreen();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Please choose one of the available options.");
                }
            } else {
                System.out.println("Please enter a correct number.");
                scanner.next(); // Clear input to avoid infinite loop on incorrect input
            }
        }
    }


    public void moviesScreen() {
        while (true) {
            System.out.println("\n\nMovies\nMovie List:");
            if (db.movies.size() == 0) {
                System.out.println(" ---> The list is empty :(  There are no movies currently scheduled in the cinema.");
            } else {
                for (int i = 0; i < db.movies.size(); i++) {
                    System.out.println(" " + (i + 1) + "->" + db.movies.get(i));
                }
            }
            System.out.println("1) Add a movie");
            if (db.movies.size() > 0) {
                System.out.println("2) Remove a movie\n3) Edit title");
            }
            System.out.print("4) Back\n>>");
            int input;
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a numerical value.");
                scanner.nextLine(); // Clear buffer
                continue;
            }
            scanner.nextLine(); // Clear the buffer from the newline character
            switch (input) {
                case 1:
                    System.out.print("Title: ");
                    db.movies.add(new Movie(scanner.nextLine()));
                    saveMovies();  // Сохранение после добавления фильма

                    break;

                case 2:
                    try {
                        System.out.print("Number: ");
                        int numToRemove = scanner.nextInt() - 1;
                        scanner.nextLine(); // Clear buffer
                        db.movies.remove(numToRemove);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Please enter a numerical value.");
                        scanner.nextLine(); // Clear buffer
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: Invalid movie number.");
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Number: ");
                        int num = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (num >= 0 && num < db.movies.size()) {
                            System.out.print("New title: ");
                            db.movies.get(num).setTitle(scanner.nextLine());
                        } else {
                            System.out.println("Invalid movie number.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Please enter a numerical value.");
                        scanner.nextLine(); // Clear buffer
                    }
                    break;

                case 4:
                default:
                    return;
            }
        }
    }


    public void sessionsScreen() {
        while (true) {
            try {
                System.out.println("\n\nSessions\nSession List:");
                if (db.sessions.isEmpty()) {
                    System.out.println(" ----> The session list is empty :(");
                } else {
                    for (int i = 0; i < db.sessions.size(); i++) {
                        System.out.println(" " + (i + 1) + "->" + db.sessions.get(i));
                    }
                    System.out.println("1) Change time\n2) Sell ticket\n3) Return ticket\n" +
                            "4) Show available seats\n5) Mark attendees (FOR EMPLOYEE-CONTROLLER)\n6) Show occupied seats after entry marking (FOR EMPLOYEE-CONTROLLER) ");
                }
                if (db.movies.size() > 0) {
                    System.out.println("\n7) Add session ");
                }
                System.out.print("8) Back\n>> ");
                int input = scanner.nextInt();
                if (input < 1 || input > 8) {
                    System.out.println("Invalid input. Please choose an option from the list.");
                    continue;
                }

                switch (input) {
                    case 1:
                        System.out.print("Session number: ");
                        int k = 0;
                        try {
                            k = scanner.nextInt();
                            scanner.nextLine(); // clear buffer after entering the number
                            if (k < 1 || k > db.sessions.size()) {
                                System.out.println("Invalid session number.");
                                break;
                            }

                            int y, m, d, h, min;
                            System.out.print("Show date\nYear: ");
                            y = scanner.nextInt();
                            System.out.print("Month: ");
                            m = scanner.nextInt();
                            System.out.print("Day: ");
                            d = scanner.nextInt();
                            System.out.print("Hour: ");
                            h = scanner.nextInt();
                            System.out.print("Minutes: ");
                            min = scanner.nextInt();
                            scanner.nextLine(); // clear buffer after entering the last number

                            try {
                                LocalDate.of(y, m, d); // Check for the validity of the date
                            } catch (DateTimeException e) {
                                System.out.println("Invalid date. Please enter a correct date.");
                                break;
                            }
                            if (h < 0 || h > 23 || min < 0 || min > 59) {
                                System.out.println("Wrong time.");
                                break;
                            }

                            db.sessions.get(k - 1).changeTime(new DayTime(y, m, d, h, min));
                            saveSessions();
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Enter a numeric value.");
                            scanner.nextLine(); // очистка буфера
                        }
                        break;
                    case 2:
                        sellTicketScreen();
                        break;

                    case 3:
                        refundTicket();
                        break;

                    case 4:
                        System.out.print("Session number: ");
                        db.sessions.get(scanner.nextInt() - 1).printPlaces();
                        break;

                    case 5:
                        System.out.print("Session number:");
                        int num = scanner.nextInt();
                        System.out.print("Seat numbers (11-99) separated by spaces: ");
                        db.sessions.get(num - 1).markPlaces(scanner.next());
                        break;

                    case 6:
                        System.out.print("Session number: ");
                        int sessionNum = scanner.nextInt() - 1;
                        scanner.nextLine(); // Очистка буфера после ввода числа
                        if (sessionNum < 0 || sessionNum >= db.sessions.size()) {
                            System.out.println("Invalid session number.");
                            break;
                        }
                        db.sessions.get(sessionNum).printMarkedPlaces();
                        break;

                    case 7:
                        if (db.movies.size() > 0) {
                            addSessionScreen();
                        } else {
                            System.out.println("The list of films is empty.");
                        }
                        break;

                    case 8:
                    default:
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input Error. Please enter correct information.");
                scanner.next(); // Очистка ввода, чтобы избежать бесконечного цикла при некорректном вводе
            }
        }
    }

    public void addSessionScreen() {
        System.out.println("\n\nMovies \nList of films:");
        if (db.movies.size() == 0) {
            System.out.println(" ----->Movie list is empty :(");
            return; // Выход из метода, если список фильмов пуст
        } else {
            for (int i = 0; i < db.movies.size(); i++) {
                System.out.println(" " + (i + 1) + "->" + db.movies.get(i));
            }
        }

        System.out.print("Movie number: ");
        int movieIndex;
        try {
            movieIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Очистить буфер
            if (movieIndex < 0 || movieIndex >= db.movies.size()) {
                System.out.println("Invalid movie number.");
                return; // Выход из метода, если индекс неверный
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Enter a numeric value.");
            scanner.nextLine(); // Очистить буфер
            return;
        }

        try {
            System.out.print("Show date\nYear: ");
            int y = scanner.nextInt();
            System.out.print("Month: ");
            int m = scanner.nextInt();
            if (m < 1 || m > 12) {
                System.out.println("Invalid month.");
                return;
            }

            System.out.print("Day: ");
            int d = scanner.nextInt();
            System.out.print("Hour: ");
            int h = scanner.nextInt();
            if (h < 0 || h > 23) {
                System.out.println("Wrong hour.");
                return;
            }
            System.out.print("Minutes:");
            int min = scanner.nextInt();
            if (min < 0 || min > 59) {
                System.out.println("Wrong minutes.");
                return;
            }
            scanner.nextLine(); // Очистить буфер

            try {
                LocalDate.of(y, m, d); // Проверка на валидность даты
            } catch (DateTimeException e) {
                System.out.println("Non-existent date. Please enter a valid date.");
                return;
            }

            db.sessions.add(new Session(db.movies.get(movieIndex), new DayTime(y, m, d, h, min)));
            saveSessions();
        } catch (InputMismatchException e) {
            System.out.println("Error: Enter a numeric value.");
            scanner.nextLine(); // Очистить буфер
            return;
        }
    }

    public void refundTicket() {
        try {
            System.out.print("Session number: ");
            int sessionNum = scanner.nextInt();

            if (sessionNum < 1 || sessionNum > db.sessions.size()) {
                System.out.println("Please enter a valid session number.");
                return;
            }

            System.out.print("Visitor's surname:");
            String surname = scanner.next();
            System.out.print("Visitor name:");
            String name = scanner.next();

            Session session = db.sessions.get(sessionNum - 1);

            User user = db.users.stream()
                    .filter(u -> u.getName().equals(name) && u.getSurname().equals(surname))
                    .findFirst()
                    .orElse(null);

            if (user != null) {
                session.refund(user);
            } else {
                System.out.println(surname + " " + name + " didn't buy a ticket. Check the entered data for correctness.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input Error. Please enter correct information.");
            scanner.next(); // Очистка ввода, чтобы избежать бесконечного цикла при некорректном вводе
        }
    }


    public void sellTicketScreen() {
        try {
            System.out.print("Surname: ");
            String surname = scanner.next();
            System.out.print("Name: ");
            String name = scanner.next();
            System.out.print("Session number: ");
            int sessionNum = scanner.nextInt();

            if (sessionNum < 1 || sessionNum > db.sessions.size()) {
                System.out.println("Please enter a valid session number.");
                return;
            }

            System.out.print("Seat number (11-99): ");
            int place = scanner.nextInt();

            if (place < 11 || place > 99) {
                System.out.println("Please enter a valid seat number (11-99).");
                return;
            }

            User user = db.users.stream()
                    .filter(u -> u.getName().equals(name) && u.getSurname().equals(surname))
                    .findFirst()
                    .orElseGet(() -> {
                        User newUser = new User(name, surname);
                        db.users.add(newUser);
                        return newUser;
                    });

            db.sessions.get(sessionNum - 1).buyPlace(place, user);
            saveSessions();
        } catch (InputMismatchException e) {
            System.out.println("Input Error. Please enter correct information.");
            scanner.next(); // Очистка ввода, чтобы избежать бесконечного цикла при некорректном вводе
        }
    }
}

