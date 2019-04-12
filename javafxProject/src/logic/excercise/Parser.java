package logic.excercise;

import logic.note.ExerciseChord;
import logic.note.NoteFactory;
import logic.organization.Mode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that makes it possible to parse a string to a list of notes 2
 */
public class Parser {

    private static final String COMMENT_PREFIX = "//";

    /**
     * Generates a list of Excercise chords from a given mode and a the desired length of the output list
     *
     * @param mode     mode of the game
     * @param poolSize length of the output list
     * @return a list of Excercise chords from a given mode and a the desired length of the output list
     */
    public static List<ExerciseChord> parseExercise(Mode mode, int poolSize) {
        // todo use poolsize var
        List<ExerciseChord> output = new ArrayList<>();
        String filePath = ExercisePack.translate(mode);
        System.out.println(filePath);

        try {
            InputStream in = Parser.class.getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            ExerciseChord currChord = new ExerciseChord();
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(COMMENT_PREFIX)) {
                    // text file must start with something other than a blank line
                    if (line.length() == 0) {
                        output.add(currChord);
                        currChord = new ExerciseChord();
                    } else {
                        currChord.add(NoteFactory.createNote(line));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (output.size() > 1) {
            Collections.shuffle(output);
        }
        return output;
    }
}
