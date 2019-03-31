package logic.excercise;

import logic.organization.Mode;

import java.io.File;

/**
 * Enum representing the difficulty of an excercise
 */
public enum ExcercisePack {
    SINGLE_NOTES(Mode.HEARING_SINGLE_NOTE, "res\\ëxercises\\singleNote_excercise.txt");

    private Mode mode;
    private String pathToFile;

    /**
     * Constructor
     *
     * @param mode       mode of the program (determines the difficulty of the chord that needs to be recognized by the user
     * @param pathToFile path to the text file containing the possible exercises for the mode
     */
    ExcercisePack(Mode mode, String pathToFile) {
        this.mode = mode;
        this.pathToFile = pathToFile;
    }

    /**
     * Returns the corresponding File for the mode
     *
     * @param mode mode of the program
     * @return the corresponding File for the mode
     */
    public static File translate(Mode mode) {
        for (ExcercisePack currPack : values()) {
            if (currPack.mode == mode) {
                return new File(currPack.pathToFile);
            }
        }
        return null;
    }

    public static boolean contains(Mode mode) {
        return translate(mode) != null;
    }
}
