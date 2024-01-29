package org.example.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DayTime class represents a specific date and time in the cinema application.
 * It provides methods for creating instances based on specified year, month, day, hour, and minute values,
 * as well as utility methods for comparing instances based on equality, order, and formatting.
 **/
public class DayTime {
    private LocalDateTime dateTime;

    public DayTime(int year, int month, int day, int hour, int minute) {
        this.dateTime = LocalDateTime.of(year, month, day, hour, minute);
    }

    public static DayTime now() {
        return new DayTime(LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonthValue(),
                LocalDateTime.now().getDayOfMonth(),
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute());
    }

    public boolean isEqual(DayTime other) {
        return this.dateTime.isEqual(other.dateTime);
    }

    public boolean isAfter(DayTime other) {
        return this.dateTime.isAfter(other.dateTime);
    }

    public boolean isBefore(DayTime other) {
        return this.dateTime.isBefore(other.dateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
        return dateTime.format(formatter);
    }
}
