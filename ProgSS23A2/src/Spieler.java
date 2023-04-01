package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;

public class Spieler {
    private final Farbe farbe;
    private final Figur[] figuren;

    public Spieler(Farbe farbe, int anzahlFiguren) {
        this.farbe = farbe;
        this.figuren = new Figur[anzahlFiguren];
        char index = 'A';
        for (int i = 0; i < anzahlFiguren; i++) {
            figuren[i] = new Figur(farbe + "-" + index);
            index++;
        }
    }

    public Farbe getFarbe() {
        return farbe;
    }

    /**
     * überprüft, ob alle Figuren des Spielers auf dem Paradies stehen
     *
     * @return ob der Spieler Gewinner ist
     */
    public boolean istGewinner() {
        for (Figur figur : figuren) {
            if (!figur.getParadies()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param name der Name einer bestimmten Figur
     * @return die gesuchte Figur als Objekt
     */
    public Figur getFigur(String name) {
        for (Figur figur : figuren) {
            if (figur.getName().equals(name)) {
                return figur;
            }
        }
        return null;
    }

    public Figur[] getFiguren() {
        return figuren;
    }
}
