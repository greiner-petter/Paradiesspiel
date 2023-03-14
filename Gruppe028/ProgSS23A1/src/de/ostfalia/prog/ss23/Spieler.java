package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;

public class Spieler {
    private String name;
    private Figur[] figur = new Figur[2];

    public Spieler(String name, Farbe farbe) {
        setName(name);
        this.figur[0] = new Figur(farbe, farbe + "-A");
        this.figur[1] = new Figur(farbe, farbe + "-B");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Figur[] getFigur() {
        return figur;
    }
}
