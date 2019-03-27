package logic.instrument;

/**
 * Position on the instrument fretboard
 */
public class FretboardPos {

    /**
     * Guitar string of the position
     */
    private final int guitarString;

    /**
     * Fret position of the position
     */
    private final int fret;

    /**
     * Constructor setting both components of the position
     *
     * @param string base string of the position
     * @param fret   fret position of the position
     */
    public FretboardPos(int string, int fret) {
        this.guitarString = string;
        this.fret = fret;
    }

    /**
     * Getter for the base string
     *
     * @return the base string
     */
    public int getGuitarString() {
        return guitarString;
    }

    /**
     * Getter for the fret position
     *
     * @return the fret position
     */
    public int getFret() {
        return fret;
    }

    /**
     * Generates a new fretboardpos instance with an incremented fret id
     * @return a new fretboardpos instance with an incremented fret id
     */
    public FretboardPos incFret() {
        return new FretboardPos(this.guitarString, this.fret + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || !(o instanceof FretboardPos)) {
            return false;
        }
        FretboardPos other = (FretboardPos) o;
        return this.guitarString == other.guitarString && this.fret == other.fret;
    }

    @Override
    public String toString() {
        return "(s: " + this.guitarString + ", f: " + this.fret + ")";
    }
}
