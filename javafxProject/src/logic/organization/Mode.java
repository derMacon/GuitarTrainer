package logic.organization;

public enum Mode {
    FREEPLAY("todo freeplay"),
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
