package logic.instrument;

/**
 * Exception to throw if a fretboard position is not located on the actual guitar fretboard
 */
public class NotOnFretException extends Exception {

    /**
     * Constructor setting up the error message
     * @param message error message to be displayed
     */
    public NotOnFretException(String message) {
        super(message);
    }
}
