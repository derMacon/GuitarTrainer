package gui;

public class Doo {

    public static void main(String[] args) {
        for (int i = 3; i < 24; i++) {
            System.out.println("\ninputOffset = " + i + ";\n" +
                    "note = new SheetNote(inputOffset);\n" +
                    "Assert.assertEquals(inputOffset, note.getOffsetToLowestE());");
        }
    }


}
