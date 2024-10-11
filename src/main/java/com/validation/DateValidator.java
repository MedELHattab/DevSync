package com.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

    // Use the DateTimeFormatter to match the user input format "dd/MM/yyyy"
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Method to validate and parse the string into LocalDate
    public static LocalDate validateAndParse(String dateStr) throws IllegalArgumentException {
        if (dateStr == null || dateStr.isEmpty()) {
            throw new IllegalArgumentException("Due date is required.");
        }

        try {
            // Parse the string into LocalDate using the custom format
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd/MM/yyyy.");
        }
    }

    // Method to check if the due date is within 3 days from now
    public static boolean isWithinThreeDays(LocalDate dueDate) {
        LocalDate currentDate = LocalDate.now();
        return !dueDate.isAfter(currentDate.plusDays(3));
    }
}

