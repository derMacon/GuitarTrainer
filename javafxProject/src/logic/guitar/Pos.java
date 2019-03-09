package logic.guitar;

public class Pos {

    private final int string;
    private final int fret;

    public Pos(int string, int fret) {
        this.string = string;
        this.fret = fret;
    }

    public int getGuitarString() {
        return string;
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
        return this.string == other.string && this.fret == other.fret;
    }
}
