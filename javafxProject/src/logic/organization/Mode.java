package logic.organization;

/**
 * Enum representing the different game / lesson modes
 */
public enum Mode {
    GUITAR_FREEPLAY("Freeplay mode for the guitar:"
            + "\nAll selected notes will be "
            + "\ndisplayed on the sheet page. In "
            + "\nthis mode there are no excercises "
            + "\nso the replay button is grayed out."),
    SHEET_FREEPLAY("Freeplay mode for the sheet music:"
            + "\nOnly one note at a time can be"
            + "\nselected on the sheet page. All possible fret positions of the "
            + "\nnote will be displayed on the guitar."),
    HEARING_SINGLE_NOTE("A single note will be played. This note should be selected on the fretboard as well as on " +
            "the sheet page."),
    HEARING_MULTIPLE_NOTES("Multple notes will be played. These notes all should be selected on the freboard as well " +
            "as on the sheet page."),
    SEE_ON_SHEET("A note will be displayed on the sheet page. This note should be selected on the fretboard."),
    SEE_ON_FRETBOARD("A note will be displayed on the fretboard. This note should be selected on the sheet page.");

    /**
     * Description of the mode used to display on the gui
     */
    private final String descr;

    /**
     * Constructor setting the description of the mode used to display on the gui
     *
     * @param descr the description of the mode used to display on the gui
     */
    Mode(String descr) {
        this.descr = descr;
    }

    /**
     * Getter for the description
     *
     * @return description of the mode
     */
    public String getDescr() {
        return descr;
    }
}
