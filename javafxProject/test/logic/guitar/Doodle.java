package logic.guitar;

public class Doodle {

    public static void main(String[] args) {
        Guitar guitar = new Guitar(new FakeGui());
        guitar.pressNote(new Pos(5, 1));
        guitar.playDownStrum();
    }

}
