# GuitarTrainer: 

### Gui (WIP)
![alt text](https://github.com/derMacon/GuitarTrainer/blob/master/otherDocs/screenshots/schreenshot1303.png)

### Fretboard 
![alt text](https://github.com/derMacon/GuitarTrainer/blob/master/otherDocs/diagrams/fretboard-diagram.jpg)

### Chromatic Circle
<img src="https://github.com/derMacon/GuitarTrainer/blob/master/otherDocs/diagrams/chromatic-circle.png" width="600">

### UML-Diagram
![alt text](https://github.com/derMacon/GuitarTrainer/blob/master/otherDocs/diagrams/GuitarTrainerUML.png)

## Usecases
### Modi: 
- Gehörte Töne / Akkorde auf dem Griffbrett einzeichnen. 
- Gehörte Töne / Akkorde auf Notenblatt markieren. 
- Gehörte Töne / Akkorde auf Tableau markieren. 

### Benutzer:
- soll einzelne Töne auf Griffbrett anklicken
- soll gehörte Töne auf einer Tableau Darstellung einzeichnen
- soll gehörte Töne in ihrer klassischen Notendarstellung notieren
- besitzt die Möglichkeit Töne zu wiederholen
- soll Akkorde / mehrere Töne gleichzeitig spielen können
### Gitarre: 
- neu angeklickter Ton kann einzeln und im Zusammenspiel mit bereits geklickten Tönen wiedergegeben werden
- es kann am Nacken "gescrollt" werden (Töne auf höheren Stegs erreichen, am besten mit Animation)
### Logik: 
- Mehrere Schwierigkeitsgerade (Open chords vs. Bar chords, etc.)
- Fehler werden ähnlich wie bei Anki schwerer gewichtet

## Links
- [MIDI-Converter](https://www.zamzar.com/)
- [Note reading excercises](https://www.bonedo.de/artikel/einzelansicht/noten-lesen-lernen-fuer-gitarristen-1-das-notensystem-grundlagen-und-leersaitenspiel.html)
- [Note reading lesson](https://www.youtube.com/watch?v=8Mj6305Rr2w&t=418s)
- [Uml-Editor](http://www.umlet.com/umletino/umletino.html)
- [Octave summary](http://www.musikkunde.info/notenlehre/oktavraeume)

---

### Todo: near-tearm
- cut music to single notes (first discuss naming format)
- test `updateString()`

### Todo: itermediate-term
- `inc / decOctave`: Implementation, Tests 
- Gui textures
- Gui Menu

### Todo: long-term
- Gui textures
- Gui Menu
- combining guitar with AudioConverter
- Implement Excercise mode
- scale to bigger texture on gui (min. 12 frets)
