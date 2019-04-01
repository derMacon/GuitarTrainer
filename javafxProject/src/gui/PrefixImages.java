package gui;

/**
 * Enum representing the images of the prefix from the notes on the sheet pages. Mostly a help to make it easier to
 * update the gui.
 */
public enum PrefixImages {
    NEUTRAL("sheetNotes\\prefix_flat.png"),
    SHARP("sheetNotes\\prefix_neutral.png"),
    FLAT("sheetNotes\\prefix_sharp.png"),
    NEUTRAL_SHARP("sheetNotes\\prefix_sharp_neutral.png"),
    NEUTRAL_FLAT("sheetNotes\\prefix_flat_neutral.png"),
    SHARP_FLAT("sheetNotes\\prefix_sharp_flat.png");

    private String path;

    /**
     * Construcotr
     * @param path path to the image of the prefix
     */
    PrefixImages(String path) {
        this.path = path;
    }

    /**
     * Getter for the id from a given path
     * @param path path to an image
     * @return the id from a given path
     */
    public static PrefixImages getId(String path) {
        for (PrefixImages curr : values()) {
            if (curr.getPath().equals(path)) {
                return curr;
            }
        }
        return null;
    }

    /**
     * Getter for the path of an prefix image
     * @return the path of an prefix image
     */
    public String getPath() {
        return path;
    }

    /**
     * Adds another prefix to a given prefix
     * @param other other prefix image instance
     * @return enum member with the added prefix
     */
    public PrefixImages add(PrefixImages other) {
        return values()[2 + this.ordinal() + other.ordinal()];
    }
}
