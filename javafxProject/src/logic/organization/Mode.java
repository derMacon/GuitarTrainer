package logic.organization;

public enum Mode {
    GUITAR_FREEPLAY("Freeplay mode for the guitar:" +
            "\nAll selected notes will be displayed on the sheet" +
            "\npage. In this mode there are no excercises so the" +
            "\nreplay and the check in buttons are grayed out."),
    SHEET_FREEPLAY("Freeplay mode for the sheet music:" +
            "\nOnly one note at a time can be selected on the " +
            "\nsheet page. All possible fret positions of the " +
            "\nnote will be displayed on the guitar."),
    HEARING_NOTE("todo default"),
    SEE_ON_SHEET("todo sheet mode"),
    SEE_ON_FRETBOARD("todo fretboard mode");

    private final String descr;

    Mode(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }
}
