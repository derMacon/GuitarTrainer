package logic.dataPreservation;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class that implements the procedures needed to work with the filechooser
 */
public class Loader {

    /**
     * Not viewable prefix of a bom file
     */
    public static final String UTF8_BOM = "\uFEFF";
    /**
     * Default directory for the filechooser
     */
    private static final String DEFAULT_DIRECTORY = "./dataOutput";
    /**
     * Single instance of the logger. Initialized with null, can be returned with the corresponding getter.
     */
    private static Loader singleInstance = null;
    /**
     * Filechoose that will be used to to save or read the file from
     */
    private final FileChooser fChooser;

    /**
     * File to save to or read from
     */
    private File file = null;

    /**
     * Stage that will be used to display the filechoose
     */
    private Stage stage;


    /**
     * Constructor for the Loader class. Sets the initial dirctory for the loader as well as the stage for the
     * and filechooser itself
     */
    private Loader() {
        fChooser = new FileChooser();
        fChooser.setTitle("Open Resource File");
        fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File dir = new File(DEFAULT_DIRECTORY);
        dir.mkdir();
        fChooser.setInitialDirectory(dir);
    }

    /**
     * Getter for the logger instance
     *
     * @return the logger instance
     */
    public static Loader getInstance() {
        if (null == singleInstance) {
            singleInstance = new Loader();
        }
        return singleInstance;
    }

    /**
     * Opens an output stream, writes the text into the file and closes the stream afterwards
     *
     * @param output file to which the data will be written
     * @param text   data that will be written
     */
    private static void actualSavingProcess(File output, String text) {
        try (PrintWriter outputStream = new PrintWriter(output)) {
            outputStream.print(text);
        } catch (FileNotFoundException e) {
            Logger.getInstance().printAndSafe("Could not save file\n" + e.getMessage());
        }
    }

    // --- saving ---

    /**
     * Opens the file that was saved earlier on in the game
     *
     * @return String that was saved earlier on in the game
     */
    public static String openSavedFile() {
        String temp = null;
        try {
            File file = Loader.getInstance().file;
            if (null != file) {
                temp = openGivenFile(file);
            }
        } catch (FileNotFoundException e) {
            Logger.getInstance().printAndSafe(e.getMessage());
        }
        return temp;
    }

    /**
     * opens a the string from the given filepath in its' string representation
     *
     * @param filePath filepath from which the string should be read
     * @return string that was saved in the file at the specified location
     * @throws FileNotFoundException Exception that will be thrown if the file was not found
     */
    public static String openGivenFile(String filePath) throws FileNotFoundException {
        return openGivenFile(new File(filePath));
    }

    /**
     * opens a given file
     *
     * @param file file from which the content should be read
     * @return content of the file
     * @throws FileNotFoundException Exception that will be thrown if the file was not found
     * @pre file.isFile()
     */
    public static String openGivenFile(File file) throws FileNotFoundException {
        assert file.isFile();
        StringBuilder sb = new StringBuilder();
        // has to be UTF8 to read german "Umlaute"
        try (Scanner in = new Scanner(file, "UTF8")) {
            while (in.hasNextLine()) {
                sb.append(in.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
        return removeUTF8BOM(sb.toString());
    }

//    /**
//     * Saving process used in the tests to avoid calling a javafx gui
//     *
//     * @param filename name of the directory to which the given Game should be saved. The file is located in the
//     *                 directory defined in the according field of this class.
//     * @param game     game instance that should be saved
//     */
//    public static void saveWithoutGUI(String filename, Game game) {
//        actualSavingProcess(new File(filename), game.toFile());
//    }


    // --- loading ---

    /**
     * https://stackoverflow.com/questions/21891578/removing-bom-characters-using-java
     * Problems occured whith the regex / pattern matching on my system. After delting the BOM Prefix it works fine.
     *
     * @param s input string from which the BOM prefix will be deleted
     * @return input string but without the BOM prefix
     */
    private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }

    /**
     * Setter for the file of the loader instance
     *
     * @param file file to be set
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Opens a Filechooser and saves the given input to the desired location with a given name.
     *
     * @param input text that will be saved
     */
    public void saveFileAs(String input) {
        if (null == stage) {
            this.stage = new Stage();
        }
        if (null != this.file) {
            this.fChooser.setInitialDirectory(this.file.getParentFile());
        }
        this.file = fChooser.showSaveDialog(stage);
        if (null == this.file) {
            Logger.getInstance().printAndSafe(Logger.ERROR_DELIMITER
                    + "\nUser aborted the saving process\n" + Logger.ERROR_DELIMITER + "\n");
        } else {
            actualSavingProcess(this.file, input);
            Logger.getInstance().printAndSafe("User saved the game as \"" + this.file.getName() + "\" to "
                    + this.file.getPath() + "\n");
        }
    }

    /**
     * Saves a given input to an already chosen directory. If no directory was selected
     * beforehand the method saveFileAs(...) will be called.
     *
     * @param input text that will be saved
     */
    public void saveFile(String input) {
        try {
            if (null == this.file) {
                saveFileAs(input);
            } else {
                actualSavingProcess(this.file, input);
            }
        } catch (RuntimeException e) {
            Logger.getInstance().printAndSafe(e.getMessage() + ", das Spiel konnte nicht gespeichert werden.");
        }
    }

    /**
     * Method to convert a file to a String
     * Opens a filechooser and the user can then select the file he want to load. Besides
     * returning the String value, the file attribut in the Loader class will be updated
     * <p>
     * Source: https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
     *
     * @return String value saved in the selected file
     * @throws FileNotFoundException that will be thrown if the selected file was not found
     */
    public String openFileChooser() throws FileNotFoundException {
        this.file = fChooser.showOpenDialog(stage);
        if (null == this.file) {
            Logger.getInstance().printAndSafe("\nUser aborted reading process\n");
            return "";
        } else {
            Logger.getInstance().printAndSafe("User opended the game \"" + this.file.getName() + "\" from "
                    + this.file.getAbsolutePath() + "\n");
            return openGivenFile(this.file);
        }
    }

}