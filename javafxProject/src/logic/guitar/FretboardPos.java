package logic.guitar;

public class FretboardPos {

    private final int guitarString;
    private final int fret;

    public FretboardPos(int string, int fret) {
        this.guitarString = string;
        this.fret = fret;
    }

    public int getGuitarString() {
        return guitarString;
    }

    public int getFret() {
        return fret;
    }

    @Override
    public boolean equals(Object o) {
        if(null == o || !(o instanceof FretboardPos)) {
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
