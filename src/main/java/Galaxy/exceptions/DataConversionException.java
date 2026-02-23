package galaxy.exceptions;

public class DataConversionException extends Exception {
    /**
     * Constructor for the exception.
     * @param message The specific error detail (e.g., "Invalid date format on line 3")
     */
    public DataConversionException(String message) {
        super(message);
    }
}