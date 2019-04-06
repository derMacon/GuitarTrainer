package logic.organization;

import java.util.Arrays;
import java.util.List;

/**
 * Overall categories available for the modes
 */
public enum Category {

    FREEPLAY("Freeplay", Mode.GUITAR_FREEPLAY, Mode.SHEET_FREEPLAY),
    HEARING_EXERCISE("Hearing", Mode.HEARING_SINGLE_NOTE, Mode.HEARING_MULTIPLE_NOTES),
    TRANSLATION_EXERCISE("Reading", Mode.SEE_ON_FRETBOARD, Mode.SEE_ON_SHEET);

    private List<Mode> modes;
    private String name;

    /**
     * Constructor
     * @param name name / title of the mode
     * @param mode array of modes the which are part of category
     */
    Category(String name, Mode... mode) {
        this.name = name;
        this.modes = Arrays.asList(mode);
    }

    /**
     * Getter for the modes
     * @return modes which are part of the category
     */
    public List<Mode> getModes() {
        return modes;
    }

    /**
     * Getter for the name / title of the category
     * @return the name / title of the category
     */
    public String getName() {
        return name;
    }
}
