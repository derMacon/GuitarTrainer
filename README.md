# GuitarTrainer: 

### Gui (clickable)
![alt text](https://github.com/derMacon/GuitarTrainer/blob/master/otherDocs/screenshots/screenshot0903.png)

### Fretboard 
![alt text](https://github.com/derMacon/GuitarTrainer/blob/master/otherDocs/diagrams/fretboard-diagram.jpg)

### Chromatic Circle
<img src="https://github.com/derMacon/GuitarTrainer/blob/master/otherDocs/diagrams/chromatic-circle.png" width="600">

Modi: 
- Gehörte Töne / Akkorde auf dem Griffbrett einzeichnen. 
- Gehörte Töne / Akkorde auf Notenblatt markieren. 
- Gehörte Töne / Akkorde auf Tableau markieren. 

## Usecases
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
