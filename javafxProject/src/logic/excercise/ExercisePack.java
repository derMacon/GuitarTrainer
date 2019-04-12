package logic.excercise;

import logic.organization.Mode;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Enum representing the different exercise packs
 */
public enum ExercisePack {
    SINGLE_NOTES("/exercises/singleNote_excercise.txt", Mode.HEARING_SINGLE_NOTE, Mode.SEE_ON_FRETBOARD,
            Mode.SEE_ON_SHEET),
    BEGINNER_MULTI_NOTES("/exercises/beginner_openChord_exercise.txt", Mode.HEARING_MULTIPLE_NOTES);

    private String pathToFile;
    private List<Mode> mode;

    /**
     * Constructor
     *
     * @param modes      mode of the program (determines the difficulty of the chord that needs to be recognized by
     *                   the user
     * @param pathToFile path to the text file containing the possible exercises for the mode
     */
    ExercisePack(String pathToFile, Mode ... modes) {
        this.mode = Arrays.asList(modes);
        this.pathToFile = pathToFile;
    }

    /**
     * Returns the corresponding File for the mode
     *
     * @param mode mode of the program
     * @return the path to the corresponding File of the mode
     */
    public static String translate(Mode mode) {
        for (ExercisePack currPack : values()) {
            if (currPack.mode.contains(mode)) {
                return currPack.pathToFile;
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
