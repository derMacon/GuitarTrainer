package logic.dataPreservation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.ZonedDateTime;

/**
 * Logger for the game. Uses the singleton pattern, so that there can only be one single instance of this logger.
 * The constructor is private and the instance can only be retuned be the corresponding static getter method.
 */
public class Logger {

    //<editor-fold defaultstate="collapsed" desc="Format strings used as templates in the different classes">
    public static final String DEPOSIT_LOGGER_FORMAT = "%s put %s %s to %s";
    public static final String SELECTION_LOGGER_FORMAT = "%s chose %s at index %d for %s round";
    public static final String CC_DRAG_LOGGER_FORMAT = "%s dragged center to %s";
    public static final String DISMISSAL_LOGGER_FORMAT = "%s did not use %s";
    public static final String ERROR_DELIMITER = "...................................";
    public static final String GAME_SEPARATOR = "-----------------------------------\n"
            + ZonedDateTime.now().toString();
    //</editor-fold>

    /**
     * Default file path for the file to which the data will be saved.
     */
    private static final File DEFAULT_FILE = new File("./dataOutput/logFile.txt");

    /**
     * Single instance of the logger. Initialized with null, can be returned with the corresponding getter.
     */
    private static Logger singleInstance = null;

    /**
     * Determines if the logfile can be opened / used for logging
     */
    private boolean loggingPossible = true;


    /**
     * Path to dir where log data should be stored
     */
    private File dir;

    /**
     * Constructor only setting the playerCnt, taking a default path as the file path for the data
     */
    private Logger() {
        DEFAULT_FILE.getParentFile().mkdir();
        this.dir = DEFAULT_FILE;
    }

    /**
     * Getter for the logger instance
     *
     * @return logger instance
     */
    public static Logger getInstance() {
        if (null == singleInstance) {
            singleInstance = new Logger();
        }
        return singleInstance;
    }

    /**
     * Setter for the file path (Type: File)
     *
     * @param path File representing the file path
     */
    public void setPath(File path) {
        this.dir = path;
    }

    /**
     * Setter for the file path (Type: String)
     *
     * @param path String representing the file path
     */
    public void setPath(String path) {
        setPath(new File(path));
    }

    /**
     * prints the given message and then saves it to a given File
     *
     * @param inputLog text that will be displayed and saved
     */
    public void printAndSafe(String inputLog) {
        System.out.println(inputLog);
        appendFileWithNewMove(inputLog);
    }

    /**
     * appends the log file with a given message
     * https://www.baeldung.com/java-exceptions
     *
     * @param logInput input to attach to the logfile
     */
    private void appendFileWithNewMove(String logInput) {
        if (this.loggingPossible) {
            try (Writer outputStream = new BufferedWriter(new FileWriter(this.dir, true))) {
                outputStream.write(logInput + "\n");
            } catch (IOException e) {
                System.err.println(ERROR_DELIMITER + "\nThe current game will not have a logfile available:\n"
                        + e.getMessage() + "\n" + ERROR_DELIMITER + "\n");
                this.loggingPossible = false;
            }
        }
    }

}
