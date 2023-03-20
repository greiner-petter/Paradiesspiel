package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;

public class Spieler {
    private Farbe farbe;
    private final Figur[] figuren;

    public Spieler(Farbe farbe, int anzahlFiguren) {
        setFarbe(farbe);
        this.figuren = new Figur[anzahlFiguren];
        char index = 'A';
        for (int i = 0; i < anzahlFiguren; i++) {
            figuren[i] = new Figur(farbe + "-" + index);
            index++;
        }
    }

    public void setFarbe(Farbe farbe) {
        this.farbe = farbe;
    }

    public Farbe getFarbe() {
        return farbe;
    }

    public boolean istGewinner() {
        for (Figur figur : figuren) {
            if (!figur.getParadies()) {
                return false;
            }
        }
        return true;
    }


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
