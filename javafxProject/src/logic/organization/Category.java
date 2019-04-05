package logic.organization;

import java.util.Arrays;
import java.util.List;

/**
 * Overall categories available for the modes
 */
public enum Category {

    FREEPLAY ("Freeplay", Mode.GUITAR_FREEPLAY, Mode.SHEET_FREEPLAY),
    HEARING_EXERCISE ("Hearing", Mode.HEARING_SINGLE_NOTE, Mode.HEARING_MULTIPLE_NOTES),
    TRANSLATION_EXERCISE ("Reading", Mode.SEE_ON_FRETBOARD, Mode.SEE_ON_SHEET);

    private List<Mode> modes;
    private String name;

    Category(String name, Mode ... mode) {
        this.name = name;
        this.modes = Arrays.asList(mode);
    }

    public List<Mode> getModes() {
        return modes;
    }

    public String getName() {
        return name;
    }
}
