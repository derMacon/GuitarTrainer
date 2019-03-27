package logic.note;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static logic.note.Prefix.*;

public enum NoteCircle {
    C(new Tone[]{Tone.C}, new Prefix[]{NEUTRAL}),
    C_SHARP(new Tone[]{Tone.C, Tone.D}, new Prefix[]{SHARP, FLAT}),
    D(new Tone[]{Tone.D}, new Prefix[]{NEUTRAL}),
    D_SHARP(new Tone[]{Tone.D, Tone.E}, new Prefix[]{SHARP, FLAT}),
    E(new Tone[]{Tone.E}, new Prefix[]{NEUTRAL}),
    F(new Tone[]{Tone.F}, new Prefix[]{NEUTRAL}),
    F_SHARP(new Tone[]{Tone.F, Tone.G}, new Prefix[]{SHARP, FLAT}),
    G(new Tone[]{Tone.G}, new Prefix[]{NEUTRAL}),
    G_SHARP(new Tone[]{Tone.G, Tone.A}, new Prefix[]{SHARP, FLAT}),
    A(new Tone[]{Tone.A}, new Prefix[]{NEUTRAL}),
    A_SHARP(new Tone[]{Tone.A, Tone.B}, new Prefix[]{SHARP, FLAT}),
    B(new Tone[]{Tone.B}, new Prefix[]{NEUTRAL});

    private Map<Tone, Prefix> notes;

    NoteCircle(Tone[] id, Prefix[] prefixes) {
        assert id.length == prefixes.length;
        this.notes = new LinkedHashMap<>();
        for (int i = 0; i < id.length; i++) {
            this.notes.put(id[i], prefixes[i]);
        }
    }

    public Map<Tone, Prefix> getNotes() {
        return notes;
    }

    public Tone getPrimaryTone() {
        return getTones().get(0);
    }

    public List<Tone> getTones() {
        return new ArrayList<>(this.notes.keySet());
    }

    public static NoteCircle getId(Note note) {
        return getId(note.getTone(), note.getPrefix());
    }

    public static NoteCircle getId(Tone tone, Prefix prefix) {
        NoteCircle[] circ = values();
        for (int i = 0; i < circ.length; i++) {
            if(circ[i].getNotes().get(tone) == prefix) {
                return circ[i];
            }
        }
        System.out.println("// todo thow some kind of exception");
        return null;
    }

//    public NoteCircle nextMajorTone() {
//        NoteCircle output = nextSemiTone();
////        while (this.getTones().equals(output.getTones())) {
//        while(output.getTones().contains(this.getPrimaryTone())) {
//            output = output.nextSemiTone();
//        }
//        return output;

//    }


}
