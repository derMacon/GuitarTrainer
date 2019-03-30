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

    ExcercisePack(Mode mode, String pathToFile) {
        this.mode = mode;
        this.pathToFile = pathToFile;
    }

    public static File translate(Mode mode) {
        for(ExcercisePack currPack : values()) {
            if(currPack.mode == mode) {
                return new File(currPack.pathToFile);
            }
        }
        return null;
    }

    public static boolean contains(Mode mode) {
        return translate(mode) != null;
    }
}
