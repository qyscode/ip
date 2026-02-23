package tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing date and time strings into {@link LocalDateTime} objects.
 * Expects input in the format "yyyy-MM-dd HHmm".
 */
public class TimeParser {

    /**
     * Parses a string into a {@link LocalDateTime}.
     *
     * @param input The date-time string in the format "yyyy-MM-dd HHmm", e.g., "2019-12-02 1800".
     * @return The corresponding {@link LocalDateTime} if parsing succeeds, or {@code null} if the format is invalid.
     */
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