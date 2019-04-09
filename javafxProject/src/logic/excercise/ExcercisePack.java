package logic.excercise;

import logic.organization.Mode;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Enum representing the difficulty of an excercise
 */
public enum ExcercisePack {
    SINGLE_NOTES("/res/ëxercises/singleNote_excercise.txt", Mode.HEARING_SINGLE_NOTE, Mode.SEE_ON_FRETBOARD,
            Mode.SEE_ON_SHEET),
    BEGINNER_MULTI_NOTES("/res/ëxercises/beginner_openChord_exercise.txt", Mode.HEARING_MULTIPLE_NOTES);

    private List<Mode> mode;
    private String pathToFile;

    /**
     * Constructor
     *
     * @param modes      mode of the program (determines the difficulty of the chord that needs to be recognized by
     *                   the user
     * @param pathToFile path to the text file containing the possible exercises for the mode
     */
    ExcercisePack(String pathToFile, Mode ... modes) {
        this.mode = Arrays.asList(modes);
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
            if (currPack.mode.contains(mode)) {
                return new File(currPack.pathToFile);
            }
        }
        return null;
    }

    /**
     * Checks if there is an enum member containing the given mode
     *
     * @param mode mode to check the enum values for
     * @return true if there is an enum member containing the given mode
     */
    public static boolean contains(Mode mode) {
        return translate(mode) != null;
    }
}
