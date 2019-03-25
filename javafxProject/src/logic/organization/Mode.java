package logic.organization;

public enum Mode {
    FREEPLAY("Freeplay mode:" +
            "\nAll selected notes will be displayed on the guitar " +
            "\nfretboard as  well as on  the sheet page.  In this " +
            "\nmode there are no excercises so the replay and the " +
            "\ncheck in buttons are grayed out."),
    HEARINGnOTE("todo default"),
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
