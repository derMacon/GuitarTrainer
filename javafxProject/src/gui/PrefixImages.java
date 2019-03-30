package gui;

public enum PrefixImages {
    NEUTRAL("sheetNotes\\prefix_flat.png"),
    SHARP("sheetNotes\\prefix_neutral.png"),
    FLAT("sheetNotes\\prefix_sharp.png"),
    NEUTRAL_SHARP("sheetNotes\\prefix_sharp_neutral.png"),
    NEUTRAL_FLAT("sheetNotes\\prefix_flat_neutral.png"),
    SHARP_FLAT("sheetNotes\\prefix_sharp_flat.png");

    private String path;

    PrefixImages(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static PrefixImages getId(String path) {
        for(PrefixImages curr : values()) {
            if(curr.getPath().equals(path)) {
                return curr;
            }
        }
        return null;
    }

    public PrefixImages add(PrefixImages other) {
        return values()[2 + this.ordinal() + other.ordinal()];
    }
}
