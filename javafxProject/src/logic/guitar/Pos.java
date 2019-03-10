package logic.guitar;

public class Pos {

    private final int guitarString;
    private final int fret;

    public Pos(int string, int fret) {
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
        if(null == o || !(o instanceof Pos)) {
            return false;
        }
        Pos other = (Pos) o;
        return this.guitarString == other.guitarString && this.fret == other.fret;
    }

    @Override
    public String toString() {
        return "(s: " + this.guitarString + ", f: " + this.fret + ")";
    }
}
