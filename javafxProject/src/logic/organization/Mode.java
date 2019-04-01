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
            + "\nOnly one note at a time can be selected on the "
            + "\nsheet page. All possible fret positions of the "
            + "\nnote will be displayed on the guitar."),
    HEARING_SINGLE_NOTE("todo single note"),
    HEARING_MULTIPLE_NOTES("todo multiple notes"),
    SEE_ON_SHEET("todo sheet mode"),
    SEE_ON_FRETBOARD("todo fretboard mode");

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
