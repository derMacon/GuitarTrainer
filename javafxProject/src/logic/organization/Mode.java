package logic.organization;

public enum Mode {
    HEARINGnOTE("todo default"), SEE_ON_SHEET("todo sheet mode"), SEE_ON_FRETBOARD("todo fretboard mode"), FREEPLAY(
            "todo freeplay");

    private final String descr;

    Mode(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }
}
