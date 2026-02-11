package tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeParser {

    // Take a string input and return LocalDateTime
    public static LocalDateTime parseTime(String input) {
        // Define a formatter that matches input: yyyy-MM-dd HHmm
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            return LocalDateTime.parse(input, inputFormatter);  // return the parsed LocalDateTime
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Use yyyy-MM-dd HHmm, e.g., 2019-12-02 1800");
            return null;  // return null if parsing fails
        }
    }
}