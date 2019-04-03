package logic.organization;

import java.util.Arrays;
import java.util.List;

public enum Category {
    FREEPLAY(Mode.SHEET_FREEPLAY, Mode.GUITAR_FREEPLAY),
    HEARING_EXERCISE(Mode.HEARING_SINGLE_NOTE, Mode.HEARING_MULTIPLE_NOTES),
    TRANSLATION_EXERCISE(Mode.SEE_ON_FRETBOARD, Mode.SEE_ON_SHEET);

    private List<Mode> implementations;

    Category(Mode ... modes) {
        this.implementations = Arrays.asList(modes);
    }

    public List<Mode> getImplementations() {
        return implementations;
    }
}
