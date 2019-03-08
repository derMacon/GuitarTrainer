package logic.guitar;

public class Pos {

    private final int string;
    private final int fret;

    public Pos(int string, int fret) {
        this.string = string;
        this.fret = fret;
    }

    public int getString() {
        return string;
    }

    public int getFret() {
        return fret;
    }

}
